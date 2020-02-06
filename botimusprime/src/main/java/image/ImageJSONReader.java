package image;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ImageJSONReader {
	private static Map<String, String> images = new HashMap<>();
	
	public ImageJSONReader() {
		super();
		parse();
	}
	
	public static void parse(){
		String imageFilePath = System.getProperty("user.dir") + "\\resources\\images.json";
		try(FileReader reader = new FileReader(imageFilePath)) {
			Object imageObj = new JSONParser().parse( reader );
			JSONObject imageJO = (JSONObject) imageObj;
			JSONArray imageJA = (JSONArray)imageJO.get("images");
			Iterator<?> itr = imageJA.iterator();
			images.clear();
			
			while(itr.hasNext()){
				JSONObject item = (JSONObject)itr.next();
				String key = (String)item.get("command");
				String value = (String)item.get("link");
				images.put(key, value);
			}
			
		} catch (IOException | ParseException e) {
			e.printStackTrace();
			System.out.println("Failed to reload image map. File not found");
		}
		
	}

	public static Map<String, String> getImages(){
		if (images.size() == 0) 
			parse();
		return new HashMap<>(images);
	}
	
}
