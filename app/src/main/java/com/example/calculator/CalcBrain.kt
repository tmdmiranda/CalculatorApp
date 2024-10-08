package com.example.calculator

import kotlin.math.sqrt

class CalcBrain {

    enum class Operation( op:String) {
        SUM("+"),
        SUB("-"),
        MUL("*"),
        DIV("/"),
        SQRT("√"),
        SIGNAL("±"),
        PERCENTAGE("%")
    }

    var operation : Operation? = null
    var accumulator : Double = 0.0

    fun doOperation(current : Double) : Double {
        var result = when (operation){
            Operation.SUM -> accumulator + current
            Operation.DIV -> accumulator / current
            Operation.SUB -> accumulator - current
            Operation.MUL -> accumulator * current
            Operation.SQRT -> sqrt(current)
            Operation.SIGNAL -> -current
            Operation.PERCENTAGE -> -current/100.0
            else -> 0.0
        }
        return result
    }

}