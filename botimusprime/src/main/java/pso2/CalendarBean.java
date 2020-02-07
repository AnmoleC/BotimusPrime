package pso2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class CalendarBean {
	private JSONObject calendarJO;

	public CalendarBean(String jsonData) throws ParseException {
		super();
		calendarJO = (JSONObject) new JSONParser().parse(jsonData);
	}

	public String getTitle(){
		return (String) calendarJO.get("summary");
	}
	
	public List<EQBean> getEQs(){
		List<EQBean> results = new ArrayList<>();
		
		JSONArray EQEvents = (JSONArray) calendarJO.get("items");
		Iterator<?> itr = EQEvents.iterator();
		while(itr.hasNext()){
			JSONObject currentJO = (JSONObject) itr.next();
			EQBean currentEQ;
			try {
				currentEQ = new EQBean(currentJO.toJSONString());
				results.add(currentEQ);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		return results;
	}
}
