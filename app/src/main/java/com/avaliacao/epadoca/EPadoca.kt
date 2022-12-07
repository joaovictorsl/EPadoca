package com.avaliacao.epadoca

import com.avaliacao.epadoca.io.InputOutput
import com.avaliacao.epadoca.purchase.Cart
import com.avaliacao.epadoca.purchase.storage.Category
import com.avaliacao.epadoca.purchase.storage.ProductStorage

class EPadoca {
    private val io = InputOutput()
    private val cart = Cart()
    private val storage = ProductStorage()

    fun run() {
            while (true) {
                val input = io.input({ io.printMenu(storage.getListOfCategory()) }) {input: String -> input.toIntOrNull() in 0..3}.toInt()
                val chosenCategory = when(input) {
                    1 -> Category.BREAD
                    2 -> Category.SNACK
                    3 -> Category.SWEET
                    else -> null
                }

                categoryMenu(chosenCategory)
                if (input != 0)
                    continue

                if (cart.isEmpty()) {
                    if (io.shouldCancelPurchase()){
                        break
                    }
                } else {
                    val coupon = askForCoupon()
                    io.outFlush(cart.generateBill(coupon))
                    break
                }

            }
    }

    private fun askForCoupon(): String {
        var coupon = ""
        var invalid = false

        while(true) {

            if (invalid) println("Coupon inválido")
            val wantCoupon = io.input({ println("Deseja aplicar um coupon? 0/1\n${if(invalid) "Coupon inválido" else ""}") },
                { i: String -> i.toIntOrNull() in 0..1 }).toInt() == 1


            if (!wantCoupon) break

            coupon = io.input({println("Digite um coupon:")})
            invalid = !cart.availableCoupon(coupon)

            if (!invalid) break
        }

        return coupon
    }

    private fun categoryMenu(chosenCategory: Category?) {
        if (chosenCategory != null) {
            val chosenItem = io.input({ printCategoryItems(chosenCategory) }) {input: String -> input.toIntOrNull() in 0..3}.toInt()

            if (chosenItem != 0) {
                val item = storage.getListOfProducts(chosenCategory)[chosenItem - 1]
                cart.add(item)
            }
        }
    }

    val printCategoryItems = fun (chosenCategory: Category) {
        val categoryItems = storage.getListOfProducts(chosenCategory)

        var toPrint = ""
        for (i in categoryItems.indices) {
            val p = categoryItems[i]
            toPrint += "${i + 1} - ${p.name.padEnd(22, '.')}R$ ${p.price}\n"
        }
        toPrint += "0 - Voltar"

        println(toPrint)
    }
}

fun main() {
    val epadoca = EPadoca()
    epadoca.run()
}