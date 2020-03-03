package com.backwards.shop

import cats.Monoid
import cats.implicits._

final case class Price private(value: BigDecimal) extends AnyVal

object Price {
  val precision: Int = 2

  implicit val priceMonoid: Monoid[Price] =
    Monoid.instance(Price(0), (p1, p2) => Price(p1.value |+| p2.value))

  def apply(value: Double): Price =
    apply(BigDecimal(value))

  def apply(value: BigDecimal): Price =
    new Price(value.setScale(precision, BigDecimal.RoundingMode.HALF_UP))

  implicit class PriceOps(price: Price) {
    def *(n: Double) = Price(price.value * n)
  }
}