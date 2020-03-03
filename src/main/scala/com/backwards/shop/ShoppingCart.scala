package com.backwards.shop

import cats.Foldable
import cats.implicits._

class ShoppingCart(products: List[Product]) {
  def total: Price = Foldable[List].fold(products.map(_.price))
}

object ShoppingCart {
  def apply(products: Product*) = new ShoppingCart(products.toList)
}