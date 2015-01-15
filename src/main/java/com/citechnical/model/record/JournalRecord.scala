package com.citechnical.model.record

import java.util.Date
import com.citechnical.model.financial._

class JournalRecord (rd:Date, acct:String, cmt:String, ref:String, debit:Money, credit:Money) {
  
  var recDate:Date = rd
  var account:String = acct
  var comment:String = cmt
  var pr:String = ref // posting reference
  var dr:Money = debit // debit
  var cr:Money = credit // credit
  
}