package calculadora

interface OperacionesCientificas {
    fun seno(x: Double): Double
    fun cos(x: Double): Double
    fun tan(x: Double): Double
    fun potencia(x: Double, exp: Double): Double
    fun raizCuadrada(x: Double):Double
    fun log10(x: Double): Double
    fun logE(x: Double): Double
    fun exponencial(x: Double): Double
    fun gradosARadianes(grados: Double): Double
    fun radianesAGrados(radianes: Double): Double
}