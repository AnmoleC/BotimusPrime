package pso2;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.simple.parser.ParseException;

public class EQManager {
	private static CalendarBean calendar;
	private static Date updated;
	private static final long cacheDuration = 3600000; //milliseconds
	
	public static void initialize(String API_key){
		EQRequestBuilder.setKey(API_key);
		fetchEQData();
	}
	
	private static void fetchEQData(){
		System.out.println("UPDATED EQ Data");
		String url = EQRequestBuilder.getURL();
		HttpGet request = new HttpGet(url);
		
		try(CloseableHttpClient httpClient = HttpClients.createDefault();
			CloseableHttpResponse response = httpClient.execute(request)){
			
			System.out.println(response.getStatusLine().toString());
			
			HttpEntity entity = response.getEntity();
			if (entity != null){
				String json = EntityUtils.toString(entity, StandardCharsets.UTF_8);
				
				calendar = new CalendarBean(json);
				updated = new Date();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	public static List<EQBean> getAllEQs(){
		if(new Date().getTime() - updated.getTime() > cacheDuration){
			System.out.println(new Date().getTime() - updated.getTime());
			fetchEQData();
		}
		return calendar.getAllEQs();
	}
	
	public static List<EQBean> getUpcomingEQs(){
		if((new Date().getTime() - updated.getTime()) > cacheDuration){
			System.out.println(new Date().getTime() - updated.getTime());
			fetchEQData();
		}
		return calendar.getUpcomingEQs();
	}
	
}
