package com.backwards.shop

import monocle.Iso
import org.scalacheck.Arbitrary
import org.scalacheck.Gen.Choose
import org.scalacheck.ScalacheckShapeless._
import org.scalatest.matchers.must.Matchers
import org.scalatest.wordspec.AnyWordSpec
import org.scalatestplus.scalacheck.ScalaCheckDrivenPropertyChecks

class PriceSpec extends AnyWordSpec with Matchers with ScalaCheckDrivenPropertyChecks {
  implicit val doubleArbitary: Arbitrary[Double] =
    Arbitrary(Choose.chooseDouble.choose(0d, 1000d))

  "Price" should {
    "be sanity checked by being isomorphic with Double" in {
      val iso = Iso[Double, Price](Price.apply)(_.value.toDouble)

      forAll(minSuccessful(100)) { double: Double =>
        val price = Price(double)

        iso.get(iso.reverseGet(price)) mustBe price
      }
    }
  }
}