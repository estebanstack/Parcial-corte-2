package calculadora

var memoria=0.0

fun main() {
     val calculadora= Calculadora()
     val calculadoraC=CalculadoraCientifica()
     val lector= LectorExpresiones()
     while (true){
          println("Que desea hacer: ")
          println("1. Ingresar un numero")
          println("2. Ingresar una expresion compleja")
          println("3. Salir de la calculadora")
          println("4. Mostrar el ultimo valor guardado en memoria")
          val opcion= readln().toInt();

          when (opcion){
               1->{
                    println("Ingrese el numero: ")
                    val numero= readln().toDouble()
                    println("El seno de $numero es: ${calculadoraC.seno(numero)}")
                    println("El coseno de $numero es: ${calculadoraC.cos(numero)}")
                    println("La tangente de $numero es: ${calculadoraC.tan(numero)}")
                    println("La raiz cuadrada de $numero es: ${calculadoraC.raizCuadrada(numero)}")
                    println("El logaritmo en base diez de $numero es: ${calculadoraC.log10(numero)}")
                    println("EL logaritmo natural de $numero es: ${calculadoraC.logE(numero)}")
                    println("e elevado a $numero es: ${calculadoraC.exponencial(numero)}")
                    println("$numero grados en raidanes es: ${calculadoraC.gradosARadianes(numero)}")
                    println("$numero radianes en grados es: ${calculadoraC.radianesAGrados(numero)}")
               }
               2->{
                    println("Ingrese la expresion compleja")
                    val expresionCompleja= readln()
                    val resultado=lector.solveExpression(expresionCompleja, calculadoraC)
                    println("El resultado de la expresion es: $resultado")

                    println("Desea guardar el valor en memoria?")
                    println("1- Si,  2-No")
                    val opcion2= readln().toInt()
                    when(opcion2){
                         1-> memoria=resultado
                         2-> println("Vale ;)")
                    }
               }
               3-> {
                    println("Adios :)")
                    break;
               }
               4 -> {
                    println("El valor en memoria actual es: $memoria")

                    println("Que desea hacer con este valor? ")
                    println("1. Sumarle otro")
                    println("2. Restarle otro")
                    println("3. Pasar al menu")
                    val opcion3= readln().toInt()

                    when(opcion3){
                         1 -> {
                              println("Ingrese el valor: ")
                              val valor= readln().toDouble()
                              memoria+=valor
                              println("Nuevo valor en memoria: $memoria")
                         }
                         2 -> {
                              println("Ingrese el valor: ")
                              val valor= readln().toDouble()
                              memoria-=valor
                              println("Nuevo valor en memoria: $memoria")
                         }
                         3 ->{
                              continue
                         }
                    }
               }
          }
     }



}



