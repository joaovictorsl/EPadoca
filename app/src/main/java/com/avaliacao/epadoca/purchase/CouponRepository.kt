package com.avaliacao.epadoca.purchase

class CouponRepository {
    private val validCoupon: MutableMap<String, Coupon> = mutableMapOf<String, Coupon>()

    init {
        validCoupon["5PADOCA"] = Coupon("5PADOCA", { fullValue: Double -> fullValue * .95 })
        validCoupon["10PADOCA"] = Coupon("10PADOCA", { fullValue: Double -> fullValue * .9 })
        validCoupon["5OFF"] = Coupon("5OFF", { fullValue: Double -> fullValue - 5 })
    }

    fun createNewCoupon(code: String, discount: (Double) -> Double) {
        validCoupon[code] = Coupon(code, discount)
    }

    fun applyCoupon(code: String, fullValue: Double): Double {
        return validCoupon[code]?.discount?.invoke(fullValue) ?: fullValue
    }

    fun hasCoupon(code: String): Boolean {
        return validCoupon.containsKey(code)
    }

}