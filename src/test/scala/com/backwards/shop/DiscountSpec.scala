package com.backwards.shop

import squants.market.GBP
import org.specs2.mutable.Specification
import com.backwards.shop.Discount._

class DiscountSpec extends Specification {
  "Buy 1 apple get 1 free" should {
    "give no discount for empty shopping cart" in {
      `buy 1 apple get 1 free`(ShoppingCart()) mustEqual Discount(GBP(0.00))
    }

    "give no discount for shopping cart containing no apples" in {
      `buy 1 apple get 1 free`(ShoppingCart(Orange * 3)) mustEqual Discount(GBP(0.00))
    }

    "give no discount for shopping cart with only 1 apple" in {
      `buy 1 apple get 1 free`(ShoppingCart(Orange * 3 :+ Apple)) mustEqual Discount(GBP(0.00))
    }

    "discount 1 of 2 apples" in {
      `buy 1 apple get 1 free`(ShoppingCart(Orange * 3 ++ Apple * 2)) mustEqual Discount(Apple.price)
    }

    "discount 1 of 3 apples" in {
      `buy 1 apple get 1 free`(ShoppingCart(Orange * 3 ++ Apple * 3)) mustEqual Discount(Apple.price)
    }

    "discount 2 of 4 apples" in {
      `buy 1 apple get 1 free`(ShoppingCart(Apple * 2 ++ Orange * 3 ++ Apple * 2)) mustEqual Discount(2 * Apple.price)
    }

    "discount 2 of 5 apples" in {
      `buy 1 apple get 1 free`(ShoppingCart(Apple * 2 ++ Orange * 3 ++ Apple * 3)) mustEqual Discount(2 * Apple.price)
    }
  }

  "Buy 3 oranges for price of 2" should {
    "give no discount for empty shopping cart" in {
      `buy 3 oranges for price of 2`(ShoppingCart()) mustEqual Discount(GBP(0.00))
    }

    "give no discount for shopping cart containing no oranges" in {
      `buy 3 oranges for price of 2`(ShoppingCart(Apple * 3)) mustEqual Discount(GBP(0.00))
    }

    "give no discount for shopping cart with only 1 orange" in {
      `buy 3 oranges for price of 2`(ShoppingCart(Apple * 3 :+ Orange)) mustEqual Discount(GBP(0.00))
    }

    "discount 1 of 3 oranges" in {
      `buy 3 oranges for price of 2`(ShoppingCart(Apple * 3 ++ Orange * 3)) mustEqual Discount(Orange.price)
    }

    "discount 1 of 5 oranges" in {
      `buy 3 oranges for price of 2`(ShoppingCart(Apple * 3 ++ Orange * 5)) mustEqual Discount(Orange.price)
    }

    "discount 2 of 6 oranges" in {
      `buy 3 oranges for price of 2`(ShoppingCart(Orange * 3 ++ Apple * 3 ++ Orange * 3)) mustEqual Discount(2 * Orange.price)
    }

    "discount 2 of 7 oranges" in {
      `buy 3 oranges for price of 2`(ShoppingCart(Orange * 3 ++ Apple * 3 ++ Orange * 4)) mustEqual Discount(2 * Orange.price)
    }
  }
}