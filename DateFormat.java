package data;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormat {
	public static String dateFormat(Date day) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		String date = format.format(day);
		return date;
	}
}
