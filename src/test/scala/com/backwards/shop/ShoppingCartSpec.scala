package com.backwards.shop

import squants.market.GBP
import org.specs2.matcher.DataTables
import org.specs2.mutable.Specification
import com.backwards.shop.Discount._
import com.backwards.shop.ShoppingCart._

class ShoppingCartSpec extends Specification with DataTables {
  "Shopping cart" should {
    "price shopping cart of apples" in {
      "apples"  | "price"         |>
        0       ! GBP(0.00)       |
        1       ! Apple.price     |
        2       ! 2 * Apple.price | { (quantityOfApples, priceOfShoppingCart) =>
        price(ShoppingCart(Apple * quantityOfApples)) mustEqual priceOfShoppingCart
      }
    }

    "price shopping cart of oranges" in {
      "oranges" | "price"         |>
        0       ! GBP(0.00)       |
        1       ! Orange.price     |
        2       ! 2 * Orange.price | { (quantityOfOranges, priceOfShoppingCart) =>
        price(ShoppingCart(Orange * quantityOfOranges)) mustEqual priceOfShoppingCart
      }
    }

    "price 3 apples and 1 orange" in {
      price(ShoppingCart(Seq(Apple, Apple, Orange, Apple))) mustEqual 3 * Apple.price + Orange.price
    }
  }

  "Shopping cart with apples offer" should {
    "price shopping cart of apples" in {
      "apples"  | "price"         |>
        0       ! GBP(0.00)       |
        1       ! Apple.price     |
        2       ! Apple.price     |
        3       ! 2 * Apple.price |
        4       ! 2 * Apple.price |
        5       ! 3 * Apple.price | { (quantityOfApples, priceOfShoppingCart) =>
        price(ShoppingCart(Apple * quantityOfApples), `buy 1 apple get 1 free`) mustEqual priceOfShoppingCart
      }
    }
  }

  "Shopping cart with oranges offer" should {
    "price shopping cart of oranges" in {
      "oranges" | "price"           |>
        0       ! GBP(0.00)         |
        1       ! Orange.price      |
        2       ! 2 * Orange.price  |
        3       ! 2 * Orange.price  |
        4       ! 3 * Orange.price  |
        5       ! 4 * Orange.price  |
        6       ! 4 * Orange.price  | { (quantityOfOranges, priceOfShoppingCart) =>
        price(ShoppingCart(Orange * quantityOfOranges), `buy 3 oranges for price of 2`) mustEqual priceOfShoppingCart
      }
    }
  }

  "Shopping cart of multiple offers" should {
    "price shopping cart of oranges" in {
      "apples"  | "oranges" | "discounts"                                                   | "price"                             |>
        0       ! 0         ! Seq(`buy 1 apple get 1 free`, `buy 3 oranges for price of 2`) ! GBP(0.00)                           |
        5       ! 7         ! Seq(`buy 1 apple get 1 free`, `buy 3 oranges for price of 2`) ! 3 * Apple.price + 5 * Orange.price  | {
        (quantityOfApples, quantityOfOranges, discounts, priceOfShoppingCart) =>
          price(ShoppingCart(Apple * quantityOfApples ++ Orange * quantityOfOranges), discounts: _*) mustEqual priceOfShoppingCart
        }
    }
  }
}