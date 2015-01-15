package com.cht.model.financial

import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.Spec
import com.cht.constant._

@RunWith(classOf[JUnitRunner])
class FinancialTests extends Spec with ShouldMatchers {
  describe("Money:") {
    it("should create and display $2345.56 from 234556 input") {
        val money = new Money(1,"234556", FinancialConstants.NUMERIC_ONLY, 2, "USD")
        money.displayAmount should be ("$2345.56")

    }
  }
  describe("Money:") {
    it("should create and display $350.75 from 350.75 input") {
        val money = new Money (1, "350.75", FinancialConstants.NUMERIC_DEC, 2, "USD")
        money.displayAmount should be ("$350.75")

    }
  }

  describe("Money:") {
    it("should validate ZZZ as an unacceptable ISO-4217 currency code, throw IllegalArgumentException") {
    	val thrown = intercept[java.lang.IllegalArgumentException] {
    		val money = new Money (1, "350.75", FinancialConstants.NUMERIC_DEC, 2, "ZZZ")
    	}
    	assert(thrown.getMessage === "Currency code is not one of the ISO-4217 accepted values.")	
    }
  }

  describe("Money:") {
    it("should create two monies, add, and display $701.50 as result") {
    	val money1 = new Money (1, "350.75", FinancialConstants.NUMERIC_DEC, 2, "USD")
    	val money2 = new Money (1, "350.75", FinancialConstants.NUMERIC_DEC, 2, "USD")
    		
    	money1 += money2
        money1.displayAmount should be ("$701.50")
    	money1.currencyCode should be ("USD")
    	money1.allNumericAmount should be ("70150")
    	money1.decimalAmount should be ("701.50")
    	money1.wholeNumberPortion should be (701)
    	money1.decimalPortion should be (50)
    	money1.digitPrecision should be (2)
    }
  }

  describe("Money:") {
    it("should create two monies, add, and display -$113.47 as result") {
    	val money1 = new Money (1, "50.75", FinancialConstants.NUMERIC_DEC, 2, "USD")
    	val money2 = new Money (-1, "164.22", FinancialConstants.NUMERIC_DEC, 2, "USD")
    		
    	money1 += money2
        money1.displayAmount should be ("-$113.47")
    	money1.currencyCode should be ("USD")
    	money1.allNumericAmount should be ("11347")
    	money1.decimalAmount should be ("113.47")
    	money1.wholeNumberPortion should be (113)
    	money1.decimalPortion should be (47)
    	money1.digitPrecision should be (2)
    	money1.sign should be (-1)
    }
  }

  describe("Money:") {
    it("add -25.29, 352.10, and 425.99, and display $752.82 as result") {
    	val money1 = new Money (-1, "25.27", FinancialConstants.NUMERIC_DEC, 2, "USD")
    	val money2 = new Money (1, "352.10", FinancialConstants.NUMERIC_DEC, 2, "USD")
    	val money3 = new Money (1, "425.99", FinancialConstants.NUMERIC_DEC, 2, "USD")
    		
    	money1 += money2
    	money1 += money3
    	
        money1.displayAmount should be ("$752.82")
    	money1.currencyCode should be ("USD")
    	money1.allNumericAmount should be ("75282")
    	money1.decimalAmount should be ("752.82")
    	money1.wholeNumberPortion should be (752)
    	money1.decimalPortion should be (82)
    	money1.digitPrecision should be (2)
    	money1.sign should be (1)
    }
  }

  describe("Money:") {
    it("add -2.50 and -3.79, and display -$6.29 as result") {
    	val money1 = new Money (-1, "2.50", FinancialConstants.NUMERIC_DEC, 2, "USD")
    	val money2 = new Money (-1, "3.79", FinancialConstants.NUMERIC_DEC, 2, "USD")
    		
    	money1 += money2
    	
        money1.displayAmount should be ("-$6.29")
    	money1.currencyCode should be ("USD")
    	money1.allNumericAmount should be ("629")
    	money1.decimalAmount should be ("6.29")
    	money1.wholeNumberPortion should be (6)
    	money1.decimalPortion should be (29)
    	money1.digitPrecision should be (2)
    	money1.sign should be (-1)
    }
  }

  describe("Money:") {
    it("add 1.25 and -350.23, and display -$348.98 as result") {
    	val money1 = new Money (1, "1.25", FinancialConstants.NUMERIC_DEC, 2, "USD")
    	val money2 = new Money (-1, "350.23", FinancialConstants.NUMERIC_DEC, 2, "USD")
    		
    	money1 += money2
    	
        money1.displayAmount should be ("-$348.98")
    	money1.currencyCode should be ("USD")
    	money1.allNumericAmount should be ("34898")
    	money1.decimalAmount should be ("348.98")
    	money1.wholeNumberPortion should be (348)
    	money1.decimalPortion should be (98)
    	money1.digitPrecision should be (2)
    	money1.sign should be (-1)
    }
  }
  
  describe("Currency:") {
    it("should validate EUR as an acceptable ISO-4217 currency code") {
    	Currency.validateCurrencyCode("EUR") should be (true)
    }
  }

  describe("Currency:") {
    it("should validate ZWL as an acceptable ISO-4217 currency code") {
    	Currency.validateCurrencyCode("ZWL") should be (true)
    }
  }

  describe("Currency:") {
    it("should validate LBP as an acceptable ISO-4217 currency code") {
    	Currency.validateCurrencyCode("LBP") should be (true)
    }
  }

  describe("Currency:") {
    it("should validate ABC as an unacceptable ISO-4217 currency code") {
    	val thrown = intercept[org.scalatest.TestFailedException] {
    		Currency.validateCurrencyCode("ABC") should be (true)
    	}
    	assert(thrown.getMessage === "false was not true")	
    }
  }

}