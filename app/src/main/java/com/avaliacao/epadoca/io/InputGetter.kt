package com.avaliacao.epadoca.io

class InputGetter(private val pp: PrettyPrinter) {
    constructor() : this(PrettyPrinter())

    val getInput = fun (prompt: () -> Unit, isInputValid: (String) -> Boolean): String {
        pp.print(prompt)
        var input = readln()

        while (!isInputValid(input)) {
            pp.print(prompt)
            println("Entrada inválida. Tente novamente.")
            input = readln()
        }

        return input
    }

}