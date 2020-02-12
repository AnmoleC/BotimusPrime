package pso2;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class CalendarBean {
	private JSONObject calendarJO;
	private List<EQBean> EQs = new ArrayList<>();

	public CalendarBean(String jsonData) throws ParseException {
		super();
		updateCalendar(jsonData);
	}
	
	public void updateCalendar(String jsonData) throws ParseException{
		calendarJO = (JSONObject) new JSONParser().parse(jsonData);
		parseEQs();
	}
	
	private void parseEQs(){
		EQs.clear();
		JSONArray EQEvents = (JSONArray) calendarJO.get("items");
		Iterator<?> itr = EQEvents.iterator();
		while(itr.hasNext()){
			JSONObject currentJO = (JSONObject) itr.next();
			EQBean currentEQ;
			try {
				currentEQ = new EQBean(currentJO.toJSONString());
				EQs.add(currentEQ);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
	}
	
	public String getTitle(){
		return (String) calendarJO.get("summary");
	}
	
	public List<EQBean> getAllEQs(){
		return new ArrayList<>(EQs);
	}
	
	public List<EQBean> getUpcomingEQs(){
		List<EQBean> result = new ArrayList<>(EQs);
		Iterator<EQBean> itr = result.iterator();
		Date now = new Date();
		while(itr.hasNext()){
			EQBean eq = itr.next();
			if(eq.getEndTime().getTime() < now.getTime()){
				itr.remove();
			}
		}		
		return result;
	}
	
}
