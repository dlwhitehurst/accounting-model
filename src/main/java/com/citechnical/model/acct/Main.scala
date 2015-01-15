package com.citechnical.model.acct

import com.citechnical.model.financial._
import com.citechnical.collection.acct._

object Main extends App {

  var chart:ChartOfAccounts = ChartOfAccounts.create(true)
  
  println("A Chart of Accounts")
  println("************************************")
  println
  chart.printChart
  println("************************************")
  println
  println("The default chart holds " + chart.accounts.length + " accounts")
}