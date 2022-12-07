package com.avaliacao.epadoca.purchase

import com.avaliacao.epadoca.purchase.storage.Product

class Cart {
    private val toBuy: MutableMap<Product, Int> = mutableMapOf()
    private val couponRepository: CouponRepository = CouponRepository()

    fun add(p: Product) {
        if (toBuy.containsKey(p))
            toBuy[p] = toBuy[p]!! + 1
        else
            toBuy[p] = 1
    }

    fun isEmpty(): Boolean {
        return toBuy.isEmpty()
    }

    fun availableCoupon(code: String): Boolean {
        return couponRepository.hasCoupon(code)
    }

    fun generateBill(coupon: String): String {
        val header = "====================Comanda E-padoca=======================\n"
        val divisor = "===========================================================\n"
        val colName = "item.......Produto..........Qtd.......Valor...........Total\n"
        val footer = "=====================VOLTE SEMPRE ^-^======================\n"
        var bill = header + divisor + colName + divisor

        val cartInfo = compileCartInfo()
        for (product in cartInfo.first)
            bill += product + "\n"

        bill += divisor
        val totalWithApplicableDiscount = couponRepository.applyCoupon(coupon, cartInfo.second)

        val total = "> R\$$totalWithApplicableDiscount"
        bill += "Total ${total.padStart(53, '=')}\n"
        bill += footer

        return bill
    }

    private fun compileCartInfo(): Pair<MutableList<String>, Double> {
        val cartAsStringList = mutableListOf<String>()
        var i = 1
        var totalToPay = 0.0

        for (product in toBuy.keys) {
            val position = i.toString().padEnd(11, '.')
            val name = product.name.padEnd(17, '.')
            val amount = (toBuy[product]!!).toString().padEnd(10, '.')
            val singlePrice = "R\$" + product.price.toString().padEnd(12, '.')
            val totalPrice = "R\$ " + (product.price * toBuy[product]!!).toString()

            cartAsStringList.add("$position$name$amount$singlePrice$totalPrice")
            totalToPay += product.price * toBuy[product]!!
            i++
        }

        return Pair(cartAsStringList, totalToPay)
    }
}