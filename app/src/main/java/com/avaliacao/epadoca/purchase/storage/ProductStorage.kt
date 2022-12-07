package com.avaliacao.epadoca.purchase.storage

class ProductStorage {
    private val storage: Map<Category, List<Product>> = mapOf(
        Pair(Category.BREAD, listOf(Product("Pão francês", 0.6, Category.BREAD), Product("Pão leite", 0.5, Category.BREAD), Product("Pão milho", 0.4, Category.BREAD))),
        Pair(Category.SNACK, listOf(Product("Coxinha", 2.0, Category.SNACK), Product("Enroladinho de salsicha", 4.0, Category.SNACK), Product("Pastel", 5.0, Category.SNACK))),
        Pair(Category.SWEET, listOf(Product("Brigadeiro", 1.5, Category.SWEET), Product("Beijinho", 2.0, Category.SWEET), Product("KitKat", 5.0, Category.SWEET)))
    )

    fun getListOfCategory(): List<String> {
        return listOf<String>(Category.BREAD.toString(), Category.SNACK.toString(), Category.SWEET.toString())
    }

    fun getListOfProducts(category: Category): List<Product> {
        return storage[category]!!
    }

}