package pso2;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.simple.parser.ParseException;

import app.App;
import discord4j.core.object.entity.MessageChannel;
import discord4j.core.object.util.Snowflake;
import reminder.ReminderBean;
import reminder.ReminderManager;

public class EQManager {
	private static CalendarBean calendar;
	private static Date updated;
	private static final long cacheDuration = 3600000; //milliseconds
	private static Map<String, Long> EQRoles = new HashMap<>();
	private static long EQChannel = 679691871672467506L;
	private static List<ReminderBean> EQReminders = new ArrayList<>();

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
		initEQRoles();
		createEQReminders();
	}
	
	private static void initEQRoles(){
		Set<String> EQNames = EQNames();
		App.bot.client().getGuilds().collectList().block().forEach(guild ->{
			guild.getRoles().collectList().block().forEach(role -> {
				if(EQNames.contains(role.getName())){
					EQRoles.put(role.getName(), role.getId().asLong());
				}
			});
		});
	}
	
	private static void createEQReminders(){
		for (ReminderBean reminder : EQReminders) {
			ReminderManager.removeReminder(reminder);
		}
		EQReminders.clear();
		
		List<EQBean> EQs = calendar.getUpcomingEQs();
		MessageChannel channel = (MessageChannel) App.bot.client().getChannelById(Snowflake.of(EQChannel)).block();
		for (EQBean EQ : EQs) {
			if(EQRoles.get(EQ.getName()) != null){
				long userID = EQRoles.get(EQ.getName());

				Date date = EQ.getStartTime();
				Calendar c = Calendar.getInstance();
				c.setTime(date);
				c.add(Calendar.MINUTE, -30);
				date = c.getTime();
				
				String message = EQ.getName() + " in 30 min";
				ReminderBean reminder = new ReminderBean(channel, userID, date, message, true);
				ReminderManager.addReminder(reminder);
				EQReminders.add(reminder);
//				System.out.println(reminder);
				
				date = EQ.getStartTime();
				message = EQ.getName() + " starting now";
				reminder = new ReminderBean(channel, userID, date, message, true);
				ReminderManager.addReminder(reminder);
				EQReminders.add(reminder);
//				System.out.println(reminder);
			}
		}
	}
	
	public static List<EQBean> getAllEQs(){
		if(new Date().getTime() - updated.getTime() > cacheDuration){
			fetchEQData();
		}
		return calendar.getAllEQs();
	}
	
	public static List<EQBean> getUpcomingEQs(){
		if((new Date().getTime() - updated.getTime()) > cacheDuration){
			fetchEQData();
		}
		return calendar.getUpcomingEQs();
	}
	
	public static Map<String, Long> EQRoles(){
		return new HashMap<String, Long>(EQRoles);
	}
	
	public static Set<String> EQNames(){
		Set<String> EQNames = new HashSet<>();
		for (EQBean EQ : getUpcomingEQs()) {
			EQNames.add(EQ.getName());
		}
		return EQNames;
	}
	
}
