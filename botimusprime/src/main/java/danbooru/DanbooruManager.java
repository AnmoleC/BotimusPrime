package danbooru;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.simple.parser.ParseException;

public class DanbooruManager {
	private static DanbooruResultBean result;
	
	public static DanbooruResultBean fetchResults(String url){
		HttpGet request = new HttpGet(url);
		
		try(CloseableHttpClient httpClient = HttpClients.createDefault();
				CloseableHttpResponse response = httpClient.execute(request)){				
				System.out.println(response.getStatusLine().toString());
				HttpEntity entity = response.getEntity();
				if (entity != null){
					String json = EntityUtils.toString(entity, StandardCharsets.UTF_8);
					result = new DanbooruResultBean(json);
					return result;
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}
		return null;
	}

}
