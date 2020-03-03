package com.backwards.shop

import cats.Monoid
import cats.implicits._

final case class Discount(price: Price)

object Discount {
  implicit val discountMonoid: Monoid[Discount] =
    Monoid.instance(Discount(Price(0)), (d1, d2) => Discount(d1.price |+| d2.price))
}