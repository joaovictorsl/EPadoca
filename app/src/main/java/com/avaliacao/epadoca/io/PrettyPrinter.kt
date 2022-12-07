package com.avaliacao.epadoca.io

class PrettyPrinter {
    val divisor = "=================================================="

    fun print(body: () -> Unit) {
        flush()
        println(divisor)
        body()
    }

    fun flush() {
        for (i in 0..100) println()
    }
}