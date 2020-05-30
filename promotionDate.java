package data;

import java.util.*;

public class promotionDate {
	public static Date promotionDate() {
		Calendar cal = Calendar.getInstance();
		Date day = new Date();
		cal.setTime(day);
		cal.add(Calendar.MONTH, 1);
		
		return cal.getTime();
	}
	
}
