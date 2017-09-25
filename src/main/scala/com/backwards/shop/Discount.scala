package com.backwards.shop

import squants.Money

case class Discount(price: Money)

object Discount {
  val `buy 1 apple get 1 free`: ShoppingCart => Discount = { shoppingCart =>
    Discount(shoppingCart.items.count(_ == Apple) / 2 * Apple.price)
  }

  val `buy 3 oranges for price of 2`: ShoppingCart => Discount = { shoppingCart =>
    Discount(shoppingCart.items.count(_ == Orange) / 3 * Orange.price)
  }
}