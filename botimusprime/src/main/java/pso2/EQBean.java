package pso2;

import org.json.simple.parser.ParseException;

import java.util.Date;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class EQBean {
	private JSONObject EQJO;
	
	public EQBean(String jsonData) throws ParseException {
		super();
		EQJO = (JSONObject) new JSONParser().parse(jsonData);
	}
	
	public String getname(){
		return (String) EQJO.get("summary");
	}
	
	public String getStartTime(){
		String startString = (String) ((JSONObject) EQJO.get("start")).get("dateTime");
		
		return startString;
	}
}
