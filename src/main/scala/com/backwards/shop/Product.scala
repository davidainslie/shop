package com.backwards.shop

sealed abstract class Product {
  def price: Price
}

final case class Apple() extends Product {
  val price: Price = Price(0.60)
}

final case class Orange() extends Product {
  val price: Price = Price(0.25)
}