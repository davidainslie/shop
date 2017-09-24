package com.backwards.shop

import squants.Money
import squants.market.GBP

sealed trait Item {
  def price: Money
}

object Item {
  implicit class ItemOps[A <: Item](item: A) {
    def *(quantity: Int): Seq[A] = Seq.fill(quantity)(item)
  }
}

case object Apple extends Item {
  override val price = GBP(0.60)
}

case object Orange extends Item {
  override val price = GBP(0.25)
}