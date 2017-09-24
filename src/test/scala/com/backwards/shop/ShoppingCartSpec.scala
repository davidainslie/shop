package com.backwards.shop

import squants.market.GBP
import org.specs2.mutable.Specification
import com.backwards.shop.ShoppingCart._

class ShoppingCartSpec extends Specification {
  "Shopping cart" should {
    "give price of empty shopping cart" in {
      price(ShoppingCart()) mustEqual GBP(0.00)
    }

    "give price of an apple" in {
      price(ShoppingCart(Apple)) mustEqual Apple.price
    }

    "give price of 2 apples" in {
      price(ShoppingCart(Apple * 2)) mustEqual 2 * Apple.price
    }

    "give price of an orange" in {
      price(ShoppingCart(Orange)) mustEqual Orange.price
    }

    "give price of 2 oranges" in {
      price(ShoppingCart(Orange * 2)) mustEqual 2 * Orange.price
    }

    "give price of 3 apples and 1 orange" in {
      price(ShoppingCart(Seq(Apple, Apple, Orange, Apple))) mustEqual 3 * Apple.price + Orange.price
    }
  }
}