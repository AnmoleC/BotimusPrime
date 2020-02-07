package pso2;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
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
	
	public static void initialize(String API_key){
		EQRequestBuilder.setKey(API_key);
		fetchEQData();
	}
	
	private static void fetchEQData(){
		String url = EQRequestBuilder.getURL();
		HttpGet request = new HttpGet(url);
		
		try(CloseableHttpClient httpClient = HttpClients.createDefault();
			CloseableHttpResponse response = httpClient.execute(request)){
			
			System.out.println(response.getStatusLine().toString());
			
			HttpEntity entity = response.getEntity();
			if (entity != null){
				String json = EntityUtils.toString(entity, StandardCharsets.UTF_8);
				
				calendar = new CalendarBean(json);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	public static List<EQBean> getEQList(){
		return calendar.getEQs();
	}
}
