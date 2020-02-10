package pso2;

import org.json.simple.parser.ParseException;

import util.DateParser;

import java.util.Date;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class EQBean {
	private JSONObject EQJO;
	
	public EQBean(String jsonData) throws ParseException {
		super();
		EQJO = (JSONObject) new JSONParser().parse(jsonData);
	}
	
	public String getName(){
		return (String) EQJO.get("summary");
	}
	
	public Date getStartTime(){
		String startString = (String) ((JSONObject) EQJO.get("start")).get("dateTime");
		try {
			return DateParser.parse(startString);
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String toString() {
		return String.format("%-30s %-30s", getName(), getStartTime());
	}
}
