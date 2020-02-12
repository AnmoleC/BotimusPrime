package util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateParser {
	
	public static Date parse(String input) throws ParseException{
//		System.out.println("input:"+input+":");
		
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
			now.setHours(0);
			now.setMinutes(0);
			now.setSeconds(0);
			Date reminderTime = new Date(inputDate.getTime() - zeroPoint.getTime() + now.getTime());
			return reminderTime;
		}else if (input.matches("[\\d]{4}-[\\d]{2}-[\\d]{2}T[\\d]{2}:[\\d]{2}:[\\d]{2}\\+09:00")){
//			System.out.println("Google");
			DateTime dateTime = DateTime.parseRfc3339(input);
			return new Date(dateTime.getValue());
		}

		return null;
	}
}
