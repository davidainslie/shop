package com.backwards.shop

import squants.market.{GBP, Money}

case class ShoppingCart(items: Seq[Item] = Nil)

object ShoppingCart {
  def apply(item: Item) = new ShoppingCart(Seq(item))

  def price(shoppingCart: ShoppingCart, discounts: (ShoppingCart => Discount)*): Money = {
    val price = (GBP(0.00) /: shoppingCart.items) { (acc, item) =>
      acc + item.price
    }

    (price /: discounts) { (price, discount) =>
      price - discount(shoppingCart).price
    }
  }

  implicit class IntMoney(quantity: Int) {
    def *(money: Money): Money = (money * quantity) rounded 2
  }
}