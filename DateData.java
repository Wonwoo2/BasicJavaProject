package data;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateData {
	
	// 날짜 형식 지정
	public static String dateFormat(Date day) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		String date = format.format(day);
		return date;
	}
	
	// 프로모션 종료날짜
	public static Date promotionDate() {
		Calendar cal = Calendar.getInstance();
		Date day = new Date();
		cal.setTime(day);
		cal.add(Calendar.MONTH, 1);
		
		return cal.getTime();
	}
	
	// 날짜 차이 계산
	public static long diffOfDay(Date first, Date second) {
		long diffDay = 0;
		try {
			diffDay = (first.getTime() - second.getTime()) / (24 * 60 * 60 * 1000);
			return diffDay;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return diffDay;
	}
}
