package com.backwards.shop

import cats.Foldable
import cats.implicits._
import com.backwards.shop.ShoppingCart.Offer

class ShoppingCart(val products: List[Product]) {
  def total(offers: Offer*): Price = {
    val total: Price = Foldable[List].fold(products.map(_.price))

    val discount: Discount = Foldable[List].fold(offers.map(_(this)).toList)

    Price(total.value - discount.price.value)
  }
}

object ShoppingCart {
  type Offer = ShoppingCart => Discount

  def apply(products: Product*) = new ShoppingCart(products.toList)

  def apply(products: List[Product]) = new ShoppingCart(products)
}