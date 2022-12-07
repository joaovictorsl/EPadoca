package com.avaliacao.epadoca.purchase

/**
 * Coupon applicable on purchases.
 *
 * @param code Coupons code as string.
 * @param discount Function which receives the full value and returns the discount
 */
data class Coupon (
    val code: String,
    val discount: (Double) -> Double,
)
