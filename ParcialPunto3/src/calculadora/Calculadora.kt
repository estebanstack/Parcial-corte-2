package calculadora

open class Calculadora {

    fun suma(a:Double, b:Double) : Any=a+b
    fun resta(a:Double, b: Double) : Any=a-b
    fun multiplicacion(a: Double, b: Double) : Any=a*b
    fun division(a:Double, b: Double): Any{
        if(b==0.0){
            println("No se puede realizar la operacion division por cero")
        }
        return a/b

    }

}
