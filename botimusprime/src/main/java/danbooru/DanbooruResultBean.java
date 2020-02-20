package danbooru;

import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class DanbooruResultBean {
	private String jsonData;
	private JSONArray resultJA;
	
	public DanbooruResultBean(String jsonData) throws ParseException{
		resultJA = (JSONArray) new JSONParser().parse(jsonData);
		this.jsonData = jsonData;
	}
	
	public DanbooruPostBean getPost(int postNum){
		Iterator<?> itr = resultJA.iterator();
		int current = 0;
		JSONObject postJO = null;
		while(itr.hasNext() && current <= postNum){
			postJO = (JSONObject) itr.next();
			current++;
		}
		if(postJO == null){
			return null;
		}
			
		DanbooruPostBean postBean = null;
		try {
			postBean = new DanbooruPostBean(postJO.toJSONString());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return postBean;
	}
	
	public String json() {
		return jsonData;
	}
}
