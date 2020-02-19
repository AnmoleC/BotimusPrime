package util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateParser {
	private static final String parseErrMessage = "Invalid time format. Acceptable fomats are:\n<Hours:minutes>\n<Weekday Hours:minutes>";
	
	public static Date parse(String input) throws ParseException{	
		
		if(input.matches("[\\d]?[\\d]:[\\d]?[\\d]")){
//			System.out.println("Basic");
			DateFormat dateformat = new SimpleDateFormat("HH:mm");
			
			Date inputDate = dateformat.parse(input);
			Date zeroPoint = dateformat.parse("00:00:00");
			Date now = new Date();
			return new Date(inputDate.getTime() - zeroPoint.getTime() + now.getTime());
				
		}else if(input.matches("[a-zA-Z]+ [\\d]?[\\d]:[\\d]?[\\d]")){
//			System.out.println("Fancy");
			DateFormat dateformat = new SimpleDateFormat("EEE HH:mm");
			Date inputDate = dateformat.parse(input);
			Date zeroPoint = dateformat.parse("Thursday 00:00");
			Date now = new Date();

			Calendar c = Calendar.getInstance();
			c.setTime(now);
			c.set(Calendar.HOUR_OF_DAY, 0);
			c.set(Calendar.MINUTE, 0);
			c.set(Calendar.SECOND, 0);
			now = c.getTime();

			Date reminderTime = new Date(inputDate.getTime() - zeroPoint.getTime() + now.getTime());
			return reminderTime;
		}else if (input.matches("[\\d]{4}-[\\d]{2}-[\\d]{2}T[\\d]{2}:[\\d]{2}:[\\d]{2}\\+09:00")){
//			System.out.println("Google");
			DateTime dateTime = DateTime.parseRfc3339(input);
			return new Date(dateTime.getValue());
		}

		throw new ParseException(parseErrMessage, 0);
	}
	
	public static Calendar DateToCalendar(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c;
	}
}
