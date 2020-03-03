package com.backwards.shop

sealed abstract class Product {
  def price: Price
}

object Product {
  implicit class ProductOps[P <: Product](product: P) {
    def *(quantity: Int): List[P] = List.fill(quantity)(product)
  }
}

final case class Apple() extends Product {
  val price: Price = Price(0.60)
}

final case class Orange() extends Product {
  val price: Price = Price(0.25)
}