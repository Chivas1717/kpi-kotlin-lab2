package com.example.kpi_kotlin_lab2

object EmissionsCalculator {

    private const val A_WIN = 0.8        // Вихідний вміст золи (%)
    private const val AR = 25.20         // Зольність палива (%)
    private const val GAMMA_WIN = 1.5    // Втрати на віднесення золи (%)
    private const val EFFICIENCY = 0.985 // Ефективність золоулавлювальної установки

    private const val OIL_EMISSION_FACTOR = 0.57   // г/ГДж
    private const val GAS_EMISSION_FACTOR = 0.0    // г/ГДж

    fun calculateEmissions(coal: Double, oil: Double, gas: Double): String {
        val coalEmission = calculateCoalEmission(coal)
        val oilEmission = calculateOilEmission(oil)
        val gasEmission = calculateGasEmission(gas)

        return """
            Показник емісії при спалюванні вугілля: ${calculateCoalFactor()} г/ГДж
            Валовий викид вугілля: $coalEmission т

            Показник емісії при спалюванні мазуту: $OIL_EMISSION_FACTOR г/ГДж
            Валовий викид мазуту: $oilEmission т

            Показник емісії при спалюванні природного газу: $GAS_EMISSION_FACTOR г/ГДж
            Валовий викид газу: $gasEmission т
        """.trimIndent()
    }

    private fun calculateCoalFactor(): Double {
        val Qr = 20.47 // Теплотворна здатність вугілля (ГДж/т)
        return (1e6 / Qr) * A_WIN * (AR / (100 - GAMMA_WIN)) * (1 - EFFICIENCY)
    }

    private fun calculateCoalEmission(coal: Double): Double {
        val coalFactor = calculateCoalFactor()
        return 1e-6 * coalFactor * 20.47 * coal
    }

    private fun calculateOilEmission(oil: Double): Double {
        return 1e-6 * OIL_EMISSION_FACTOR * 39.48 * oil
    }

    private fun calculateGasEmission(gas: Double): Double {
        return 0.0 // Газ не виробляє твердих частинок
    }
}
