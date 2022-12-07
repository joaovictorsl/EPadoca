package com.avaliacao.epadoca.purchase.storage

enum class Category(val stringRep: String) {
    BREAD("Pão"),
    SNACK("Salgado"),
    SWEET("Doce");

    override fun toString(): String {
        return stringRep
    }
}