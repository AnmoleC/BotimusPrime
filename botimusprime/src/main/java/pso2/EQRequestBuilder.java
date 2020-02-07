package pso2;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

class EQRequestBuilder {
	private static String fetchURL = "https://www.googleapis.com/calendar/v3/calendars/{$calendar}/events?key={$key}&timeMin={$date}T00:00:00-05:00&singleEvents=true&orderBy=startTime";
	private static String calendarID = "pso2emgquest@gmail.com";
	private static String API_key;
	
	public static void setKey(String key){
		API_key = key;
	}
	
	public static String getURL(){
		Date today = new Date();		
		return getURL(today);
	}
	
	public static String getURL(Date date){
		if (API_key == null || API_key.equals("")){
			throw new IllegalArgumentException("Set a valid Google API key before calling getURL");
		}
		
		String result = fetchURL;
		result = result.replaceFirst("\\{\\$calendar\\}", calendarID);
		result = result.replaceFirst("\\{\\$key\\}", API_key);
		DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");		
		result = result.replaceFirst("\\{\\$date\\}", dateformat.format(date));
		return result;
	}
}
