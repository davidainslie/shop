package com.backwards.shop

import squants.market.{GBP, Money}

case class ShoppingCart(items: Seq[Item] = Nil)

object ShoppingCart {
  def apply(item: Item) = new ShoppingCart(Seq(item))

  def price(shoppingCart: ShoppingCart): Money =
    shoppingCart.items.foldLeft(GBP(0.00)) { (acc, item) =>
      acc + item.price
    }

  implicit class IntMoney(quantity: Int) {
    def *(money: Money): Money = (money * quantity) rounded 2
  }
}