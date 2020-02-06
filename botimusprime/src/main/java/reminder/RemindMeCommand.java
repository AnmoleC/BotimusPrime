package reminder;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import app.App;
import basicCommands.Command;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.MessageChannel;
import util.DateParser;

public class RemindMeCommand implements Command {
	private static final String parseErrMessage = "Invalid time format. Please specify a wait time in the format <Hours:minutes>";
	private static final String invalidArgsMessage = "Invalid call. Please use the command as follows \n"
														+ App.BOT_PREFIX + "remindme <waitTime(hours:minutes)> or"
														+ App.BOT_PREFIX + "remindme <Date (Weekday hour:minute)>";
	
	private static ReminderManager manager = ReminderManager.getInstance();	
	
	@Override
	public void execute(MessageCreateEvent event) {
		MessageChannel channel = event.getMessage().getChannel().block();
		long username = event.getMessage().getAuthor().get().getId().asLong();
		String content = event.getMessage().getContent().get().replaceAll(" +", " ");
		List<String> args = Arrays.asList(content.split(" "));
		
		if (args.size() < 2){
			channel.createMessage(invalidArgsMessage);
			return;
		}
		
		String payload = content.substring(content.indexOf(' ')).trim();
		try {
			Date reminderTime = DateParser.parse(payload);
			if (reminderTime != null){
				ReminderBean reminder = new ReminderBean(channel, "<@"+username+">", reminderTime);
				manager.addReminder(reminder);
			}else{
				channel.createMessage(parseErrMessage).block();
			}
		} catch (ParseException e1) {
			channel.createMessage(parseErrMessage).block();
		}	
	}
	
	
}
