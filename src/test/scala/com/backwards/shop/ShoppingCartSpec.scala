package com.backwards.shop

import org.scalatest.matchers.must.Matchers
import org.scalatest.wordspec.AnyWordSpec

class ShoppingCartSpec extends AnyWordSpec with Matchers {
  "Shopping cart" should {
    "give total price of 1 apple" in {
      ShoppingCart(Apple()).total mustBe Price(0.60)
    }

    "give total price of 1 orange" in {
      ShoppingCart(Orange()).total mustBe Price(0.25)
    }

    "give total price of various products" in {
      ShoppingCart(Apple(), Apple(), Orange(), Apple()).total mustBe Price(2.05)
    }
  }
}