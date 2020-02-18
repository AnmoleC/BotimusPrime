package image;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ImageManager {
	private static Map<String, String> images = new HashMap<>();
	private static String imageFilePath = System.getProperty("user.dir") + "\\resources\\images.json";
	
	public ImageManager() {
		super();
		parse();
	}
	
	public static void parse(){
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
	
	@SuppressWarnings("unchecked")
	public static void write(){
		JSONObject imageJO = new JSONObject();
		JSONArray imageJA = new JSONArray();
		
		for (String key : images.keySet()) {
			JSONObject item = new JSONObject();
			item.put("command", key);
			item.put("link", images.get(key));
			imageJA.add(item);
		}
		imageJO.put("images", imageJA);
				
		try(PrintWriter writer = new PrintWriter(imageFilePath)){
			writer.write(imageJO.toJSONString());
			writer.flush();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		System.out.println("Wrote imageMap to disc");
	}
	
	public static Map<String, String> getImages(){
		if (images.size() == 0) 
			parse();
		return new HashMap<>(images);
	}
	
	public static void addImage(String key, String value){
		images.put(key, value);
		write();
	}
}
