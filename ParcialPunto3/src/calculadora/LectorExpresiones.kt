package calculadora
import java.util.*;

class LectorExpresiones {
    fun tokenize(expression: String): List<String> {
        val tokens = mutableListOf<String>()
        var currentToken = StringBuilder()

        val functions = setOf("sin", "cos", "tan", "ln", "log10")

        var i = 0
        while (i < expression.length) {
            val char = expression[i]

            when {
                char.isDigit() || char == '.' -> currentToken.append(char) // Números y decimales
                char in "+-*/^()" -> {
                    if (currentToken.isNotEmpty()) {
                        tokens.add(currentToken.toString())
                        currentToken.clear()
                    }
                    tokens.add(char.toString())
                }
                char.isLetter() -> {
                    currentToken.append(char)
                    // Revisa si el token es una función matemática completa
                    for (function in functions) {
                        if (expression.startsWith(function, i)) {
                            if (currentToken.isNotEmpty()) {
                                tokens.add(currentToken.toString())
                                currentToken.clear()
                            }
                            tokens.add(function)
                            i += function.length - 1
                            break
                        }
                    }
                }
                char.isWhitespace() -> {
                    if (currentToken.isNotEmpty()) {
                        tokens.add(currentToken.toString())
                        currentToken.clear()
                    }
                }
            }
            i++
        }

        if (currentToken.isNotEmpty()) {
            tokens.add(currentToken.toString())
        }

        return tokens
    }

    fun shuntingYard(tokens: List<String>): List<String> {
        val output = mutableListOf<String>()
        val operators = ArrayDeque<String>()
        val precedence = mapOf(
            "+" to 1, "-" to 1,
            "*" to 2, "/" to 2,
            "^" to 3
        )
        val rightAssociative = setOf("^")
        val functions = setOf("sin", "cos", "tan", "ln", "log10")

        for (token in tokens) {
            when {
                token.toDoubleOrNull() != null -> output.add(token) // Si es un numero
                token in precedence.keys -> {
                    while (operators.isNotEmpty() &&
                        (precedence[operators.peek()] ?: 0) >= (precedence[token] ?: 0) &&
                        token !in rightAssociative
                    ) {
                        output.add(operators.pop())
                    }
                    operators.push(token)
                }
                token == "(" -> operators.push(token)
                token == ")" -> {
                    while (operators.peek() != "(") {
                        output.add(operators.pop())
                    }
                    operators.pop() // Elimina el '(' de la pila
                    // Si después del parentesis hay una funcion, la agregamos a la salida
                    if (operators.isNotEmpty() && operators.peek() in functions) {
                        output.add(operators.pop())
                    }
                }
                token in functions -> operators.push(token) // Si es una funcion
            }
        }

        while (operators.isNotEmpty()) {
            output.add(operators.pop())
        }

        return output
    }
    fun evaluateRPN(tokens: List<String>, funcionesComp: CalculadoraCientifica): Double {
        val stack = ArrayDeque<Double>()

        for (token in tokens) {
            when {
                token.toDoubleOrNull() != null -> stack.push(token.toDouble())
                token in "+-*/^" -> {
                    val CC=CalculadoraCientifica()
                    val b = stack.pop()
                    val a = stack.pop()
                    val result = when (token) {
                        "+" -> CC.suma(a,b)
                        "-" -> CC.resta(a,b)
                        "*" -> CC.multiplicacion(a,b)
                        "/" -> CC.division(a,b)
                        "^" -> CC.potencia(a,b) 
                        else -> throw IllegalArgumentException("Operador desconocido: $token")
                    }
                    stack.push(result as Double)
                }
                token == "sin" -> stack.push(funcionesComp.seno(stack.pop()))
                token == "cos" -> stack.push(funcionesComp.cos(stack.pop()))
                token == "tan" -> stack.push(funcionesComp.tan(stack.pop()))
                token == "ln" -> stack.push(funcionesComp.logE(stack.pop()))
                token == "log10" -> stack.push(funcionesComp.log10(stack.pop()))
            }
        }

        return stack.pop()
    }
    fun solveExpression(expression: String, funcionesComp: CalculadoraCientifica): Double {
        val tokens = tokenize(expression)
        val rpn = shuntingYard(tokens)
        return evaluateRPN(rpn, funcionesComp)
    }
}
