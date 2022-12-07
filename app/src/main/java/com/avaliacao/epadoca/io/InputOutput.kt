package com.avaliacao.epadoca.io

import com.avaliacao.epadoca.purchase.storage.Product

class InputOutput {
    private val pp = PrettyPrinter()
    private val getter = InputGetter(pp)

    fun input(prompt: () -> Unit, inputValidator: (String) -> Boolean = {true}): String {
        return getter.getInput(prompt, inputValidator)
    }

    fun printMenu(categoryList: List<String>) {
        var menuToPrint = ""
        for (i in categoryList.indices) {
            val category = categoryList[i]
            menuToPrint += "${i + 1}${category.padStart(22, '.')}\n"
        }
        menuToPrint += "0......Finalizar compra"

        pp.print { println(menuToPrint)}
    }

    fun shouldCancelPurchase(): Boolean {
        val choice = getter.getInput({println("Seu carrinho está vazio, deseja cancelar a compra? (S/N)")}) {
            val validChoices = listOf<String>("S", "N")
            it.uppercase() in validChoices
        }

        return choice.uppercase() == "S"
    }

    fun outFlush(body: String) {
        pp.flush()
        println(body)
    }
}