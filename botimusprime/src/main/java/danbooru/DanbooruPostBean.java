package danbooru;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class DanbooruPostBean {
	private JSONObject postJO;
	
	public DanbooruPostBean(String jsonData) throws ParseException{
		postJO = (JSONObject) new JSONParser().parse(jsonData);
	}
	
	public String getURL(){
		return (String) postJO.get("file_url");
	}
}
