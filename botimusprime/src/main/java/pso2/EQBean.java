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
	
	public String getname(){
		return (String) EQJO.get("summary");
	}
	
	public String getStartTime(){
		String startString = (String) ((JSONObject) EQJO.get("start")).get("dateTime");
		try {
			Date startdate = DateParser.parse(startString);
			System.out.println(startdate);
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}
//		System.out.println(startString);
		return startString;
	}
}
