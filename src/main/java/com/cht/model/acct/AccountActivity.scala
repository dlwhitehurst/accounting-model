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

package com.cht.model.acct

import com.cht.model.financial._
import com.cht.constant._

trait AccountActivity {
  
  /**
   * transaction history
   */
  val transactions:List[AccountTransaction] = List(new AccountTransaction(new Money (1,"0.00", FinancialConstants.NUMERIC_DEC, 2, "USD"),1))
  
  def debitAccount(value:Money):Money
  
  def creditAccount(value:Money):Money
  
  /**
   * add AccountTransaction to the Account collection, transactions
   */
  def addTransaction(trans:AccountTransaction)
  
  /**
   * remove AccountTransaction by id
   */
  def removeTransactionById(transId:Int)
  
}