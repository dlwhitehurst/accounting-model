/**
 * Copyright 2011 Cape Henry Technologies Inc.
 * 
 * Licensed under the Apache License, Version 2.0 
 * (the "License"); You may not use this file except 
 * in compliance with the License. You may obtain a 
 * copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, 
 * software distributed under the License is distributed on an 
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, 
 * either express or implied. See the License for the specific 
 * language governing permissions and limitations under the 
 * License.
 * 
 */

package com.citechnical.model.financial
import com.citechnical.constant.FinancialConstants

/**
 * Scala class representation of Money. Currency of exchange is validated and set.
 * Transactions between Monies of different currencies are NOT allowed.
 */
class Money (sgn:Int, repr:String, format:Int, dp:Int, currCode: String) {
  
  var currencyCode:String = currCode // default
  var allNumericAmount:String = "" // e.g. "234525" = $2,345.25
  var decimalAmount:String = "" // e.g. "2345.25"
  var wholeNumberPortion:Int = 0 // e.g. 2345 (taxes maybe? round up?)
  var decimalPortion:Int = 0 // e.g. 25 however if 2 would be represented by String as "02"
  var displayAmount:String = "" // e.g. $2,345.25 or ($345.75) negative
  var digitPrecision:Int = 2 // default, but could be 5 e.g. for interest rate calculations
  var sign:Int = sgn // default, set to -1 if negative
  
  if (!Currency.validateCurrencyCode(currCode))
    throw new IllegalArgumentException("Currency code is not one of the ISO-4217 accepted values.")
    		
  if (dp != 2)
    digitPrecision = dp // override or change digitPrecision if needed

  var lessDp = 0 
    	
  if (format == FinancialConstants.NUMERIC_ONLY) {
    allNumericAmount = repr
    lessDp = repr.length - dp
    decimalPortion = repr.substring(lessDp, lessDp + dp).toInt
  }
    
  if (format == FinancialConstants.NUMERIC_DEC) {
    decimalAmount = repr
    allNumericAmount = repr.replaceAll("\\.","")
    lessDp = repr.length - dp - 1
    decimalPortion = repr.substring(lessDp + 1, lessDp + dp + 1).toInt
  }
    
  wholeNumberPortion = repr.substring(0,lessDp).toInt
  if (this.sign == -1)  
	  displayAmount = "-$" + wholeNumberPortion + "." + decimalPortion // fix comma on thousands later
  else
	  displayAmount = "$" + wholeNumberPortion + "." + decimalPortion // fix comma on thousands later
    
  def += (addition:Money): this.type = { 
	    if (this.currencyCode == addition.currencyCode) {
	      var num = this.allNumericAmount.toInt
	      var add = addition.allNumericAmount.toInt
	      
	      num = num * this.sign // set sign
	      add = add * addition.sign
	      
	      num += add
	      
	      if (num < 0)
	        this.sign = -1
	        else this.sign = 1
	    	  
	      this.wholeNumberPortion = (num.abs)/ 100
	      this.decimalPortion = (num.abs)%100
	      this.allNumericAmount = this.wholeNumberPortion.toString + this.decimalPortion.toString
	      this.decimalAmount = this.wholeNumberPortion.toString + "." + this.decimalPortion.toString
	      if (this.sign == -1)
	      this.displayAmount = "-$" + wholeNumberPortion + "." + decimalPortion
	      else
	      this.displayAmount = "$" + wholeNumberPortion + "." + decimalPortion
	        
	    }
	    else throw new IllegalArgumentException("Currency codes per ISO-4217 must be the same.")
	  	this
  }
  
  def -= (subtraction:Money): this.type = {
	  if (this.currencyCode == subtraction.currencyCode) {
	      var num = this.allNumericAmount.toInt
	      var sub = subtraction.allNumericAmount.toInt
	      
	      num = num * this.sign // set sign
	      sub = sub * subtraction.sign
	      
	      num -= sub
	      
	      if (num < 0)
	        this.sign = -1
	        else this.sign = 1

	      this.wholeNumberPortion = (num.abs)/ 100
	      this.decimalPortion = (num.abs)%100
	      this.allNumericAmount = this.wholeNumberPortion.toString + this.decimalPortion.toString
	      this.decimalAmount = this.wholeNumberPortion.toString + "." + this.decimalPortion.toString
	      if (this.sign == -1)
	      this.displayAmount = "-$" + wholeNumberPortion + "." + decimalPortion
	      else
	      this.displayAmount = "$" + wholeNumberPortion + "." + decimalPortion
	        
	    }
	    else throw new IllegalArgumentException("Currency codes per ISO-4217 must be the same.")
	  	this
  }
}