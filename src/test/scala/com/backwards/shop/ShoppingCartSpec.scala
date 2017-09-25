package com.backwards.shop

import squants.market.GBP
import org.specs2.mutable.Specification
import com.backwards.shop.Discount._
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

  "Shopping cart with apples offer" should {
    """give price of 2 apples for "buy 1 apple get 1 free" offer""" in {
      price(ShoppingCart(Apple * 2), `buy 1 apple get 1 free`) mustEqual Apple.price
    }

    """give price of 3 apples for "buy 1 apple get 1 free" offer""" in {
      price(ShoppingCart(Apple * 3), `buy 1 apple get 1 free`) mustEqual 2 * Apple.price
    }

    """give price of 4 apples for "buy 1 apple get 1 free" offer""" in {
      price(ShoppingCart(Apple * 4), `buy 1 apple get 1 free`) mustEqual 2 * Apple.price
    }

    """give price of 5 apples for "buy 1 apple get 1 free" offer""" in {
      price(ShoppingCart(Apple * 5), `buy 1 apple get 1 free`) mustEqual 3 * Apple.price
    }
  }

  "Shopping cart with oranges offer" should {
    """give price of 2 oranges for "buy 3 oranges for price of 2" offer""" in {
      price(ShoppingCart(Orange * 2), `buy 3 oranges for price of 2`) mustEqual 2 * Orange.price
    }

    """give price of 3 oranges for "buy 3 oranges for price of 2" offer""" in {
      price(ShoppingCart(Orange * 3), `buy 3 oranges for price of 2`) mustEqual 2 * Orange.price
    }

    """give price of 4 oranges for "buy 3 oranges for price of 2" offer""" in {
      price(ShoppingCart(Orange * 4), `buy 3 oranges for price of 2`) mustEqual 3 * Orange.price
    }

    """give price of 5 oranges for "buy 3 oranges for price of 2" offer""" in {
      price(ShoppingCart(Orange * 5), `buy 3 oranges for price of 2`) mustEqual 4 * Orange.price
    }

    """give price of 6 oranges for "buy 3 oranges for price of 2" offer""" in {
      price(ShoppingCart(Orange * 6), `buy 3 oranges for price of 2`) mustEqual 4 * Orange.price
    }
  }

  "Shopping cart of multiple offers" should {
    """give price of 5 apples and 7 oranges for "buy 1 apple get 1 free" and "buy 3 oranges for price of 2" offers""" in {
      val shoppingCart = ShoppingCart(Seq(Apple, Apple, Orange, Orange, Apple, Orange, Apple, Apple) ++ Orange * 4)

      price(shoppingCart, `buy 1 apple get 1 free`, `buy 3 oranges for price of 2`) mustEqual 3 * Apple.price + 5 * Orange.price
    }
  }
}