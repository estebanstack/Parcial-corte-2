package calculadora
import kotlin.math.*

class CalculadoraCientifica: Calculadora(), OperacionesCientificas {
    override fun seno(x:Double):Double=sin(x)
    override fun cos(x:Double):Double= kotlin.math.cos(x)
    override fun tan(x:Double):Double= kotlin.math.tan(x)

    override fun potencia(x:Double, exp:Double)=x.pow(exp)
    override fun raizCuadrada(x: Double) :Double{
        return if (x < 0) {
            println("No hay raiz negativa")
            Double.NaN // -> Identifica que la raiz no es valida
        } else {
            sqrt(x)
        }
    }

    override fun log10(x:Double):Double= kotlin.math.log10(x)
    override fun logE(x:Double)= ln(x)

    override fun exponencial(x:Double):Double= exp(x)

    override fun gradosARadianes(grados: Double): Double {
        return grados * (Math.PI / 180.0)
    }
    override fun radianesAGrados(radianes: Double): Double {
        return radianes * (180.0 / Math.PI)
    }
}