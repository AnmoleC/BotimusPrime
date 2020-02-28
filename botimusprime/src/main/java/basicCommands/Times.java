package basicCommands;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

import app.App;
import discord4j.core.event.domain.message.MessageCreateEvent;

public class Times extends Command {
	private static List<String> zones = new ArrayList<>();
	static{
		zones.add("Asia/Singapore");
		zones.add("America/New_York");
	}
	@Override
	protected void executeCommand(MessageCreateEvent event) {
		String output = "";

		for (String zoneName : zones) {
			TimeZone zone = TimeZone.getTimeZone(zoneName);
			Calendar c = new GregorianCalendar();
			c.setTime(new Date());
			c.setTimeZone(zone);
			
			SimpleDateFormat format = new SimpleDateFormat("hh:mm:ssXXX a ");
			format.setTimeZone(zone);
			output += format.format(c.getTime()) + zoneName +"\n";
		}
		
		channel.createMessage(output).block();
	}

	@Override
	public String prefix() {
		return "times";
	}

	@Override
	public String syntaxRegex() {
		return prefix();
	}

	@Override
	public String syntaxMsg() {
		return App.bot.BOT_PREFIX + prefix();
	}

	@Override
	protected String description() {
		return "Lists the current time for a bunch of timezones";
	}

}
