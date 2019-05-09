package com.example.blog

data class Calculator(var numOne: Float, var numTwo: Float) {

    operator fun plus(num: Calculator): Float {
        val first = num.numOne
        val second = num.numTwo

        return first + second
    }

    operator fun minus(num: Calculator): Float {
        val first = num.numOne
        val second = num.numTwo

        return first - second
    }

    operator fun times(num: Calculator): Float {
        val first = num.numOne
        val second = num.numTwo

        return first * second
    }

    operator fun div(num: Calculator): Float {
        val first = num.numOne
        val second = num.numTwo

        return first / second
    }

    operator fun rem(num:Calculator):Float{
        val first = num.numOne
        val second = num.numTwo
        return first % second
    }
}
