package com.citechnical.model.record
import java.text.SimpleDateFormat
import java.util.Date
import com.citechnical.model.financial._
import com.citechnical.constant._

class Journal {

	val records:List[JournalRecord] = List(new JournalRecord(new SimpleDateFormat("yyy-MM-dd HH:mm").parse("2008-05-06 13:29"),
	    "wash", "Initialization record", "100", new Money (1,"0.00", FinancialConstants.NUMERIC_DEC, 2, "USD"), 
	    new Money (1,"0.00", FinancialConstants.NUMERIC_DEC, 2, "USD")))

}