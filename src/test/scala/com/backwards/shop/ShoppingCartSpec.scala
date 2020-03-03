package com.backwards.shop

import org.scalatest.matchers.must.Matchers
import org.scalatest.wordspec.AnyWordSpec
import scala.util.chaining._

class ShoppingCartSpec extends AnyWordSpec with Matchers {
  "Shopping cart" should {
    "give total price of 1 apple" in {
      ShoppingCart(Apple()).total() mustBe Price(0.60)
    }

    "give total price of 1 orange" in {
      ShoppingCart(Orange()).total() mustBe Price(0.25)
    }

    "give total price of various products" in {
      ShoppingCart(Apple(), Apple(), Orange(), Apple()).total() mustBe Price(2.05)
    }
  }

  "Shopping cart with offers for apples" should {
    val `buy 1 apple get 1 free`: ShoppingCart => Discount =
      _.products.count(_ == Apple()) pipe (n => Discount(Apple().price * (n / 2)))

    "give total price of 1 apple where apple offer does not apply" in {
      val shoppingCart = ShoppingCart(Apple())

      shoppingCart.total(`buy 1 apple get 1 free`) mustBe Price(0.60)
    }

    "give total price of 2 apples where apple offer applies" in {
      val shoppingCart = ShoppingCart(Apple(), Apple())

      shoppingCart.total(`buy 1 apple get 1 free`) mustBe Price(0.60)
    }

    "give total price of 3 apples where apple offer applies" in {
      val shoppingCart = ShoppingCart(Apple(), Apple(), Apple())

      shoppingCart.total(`buy 1 apple get 1 free`) mustBe Price(1.20)
    }
  }

  "Shopping cart with offers for oranges" should {
    val `buy 3 oranges for the price of 2`: ShoppingCart => Discount =
      _.products.count(_ == Orange()) pipe (n => Discount(Orange().price * (n / 3)))

    "give total price of 1 orange where orange offer does not apply" in {
      val shoppingCart = ShoppingCart(Orange())

      shoppingCart.total(`buy 3 oranges for the price of 2`) mustBe Price(0.25)
    }

    "give total price of 3 orange where orange offer applies" in {
      val shoppingCart = ShoppingCart(Orange(), Orange(), Orange())

      shoppingCart.total(`buy 3 oranges for the price of 2`) mustBe Price(0.50)
    }

    "give total price of 4 orange where orange offer applies" in {
      val shoppingCart = ShoppingCart(Orange(), Orange(), Orange(), Orange())

      shoppingCart.total(`buy 3 oranges for the price of 2`) mustBe Price(0.75)
    }
  }

  "Shopping cart with offers for apples and oranges" should {
    val `buy 1 apple get 1 free`: ShoppingCart => Discount =
      _.products.count(_ == Apple()) pipe (n => Discount(Apple().price * (n / 2)))

    val `buy 3 oranges for the price of 2`: ShoppingCart => Discount =
      _.products.count(_ == Orange()) pipe (n => Discount(Orange().price * (n / 3)))

    "give total price of 1 apple and 1 orange where apple and orange offers do not apply" in {
      val shoppingCart = ShoppingCart(Apple(), Orange())

      shoppingCart.total(`buy 1 apple get 1 free`, `buy 3 oranges for the price of 2`) mustBe Price(0.85)
    }

    "give total price of 5 apples and 7 oranges where apple and orange offers apply" in {
      val shoppingCart = ShoppingCart(Apple() * 5 ++ Orange() * 7)

      shoppingCart.total(`buy 1 apple get 1 free`, `buy 3 oranges for the price of 2`) mustBe Price(3.05)
    }
  }
}