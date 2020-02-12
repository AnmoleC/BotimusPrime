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
	private static final String invalidArgsMessage = "Invalid call. Please use the command in one of the following formats:\n"
														+ App.BOT_PREFIX + "remindme <waitTime(hours:minutes)> Message\n"
														+ App.BOT_PREFIX + "remindme <Date (Weekday hour:minute) Message>";
	
	private static ReminderManager manager = ReminderManager.getInstance();	
	
	public void execute(MessageCreateEvent event) {
		MessageChannel channel = event.getMessage().getChannel().block();
		long username = event.getMessage().getAuthor().get().getId().asLong();
		String content = event.getMessage().getContent().get().replaceAll(" +", " ");

		if (!content.matches("!remindme[\\s]*<[\\d]?[\\d]:[\\d]?[\\d]>[\\w\\s]*")){
			channel.createMessage(invalidArgsMessage).block();
			return;
		}
		String payload = content.substring(content.indexOf('<')+1, content.indexOf('>'));
		String message = content.substring(content.indexOf('>')+1).trim();
		
		try {
			Date reminderTime = DateParser.parse(payload);
			if (reminderTime != null){
				ReminderBean reminder = new ReminderBean(channel, username, reminderTime, message);
				manager.addReminder(reminder);
				channel.createMessage("Reminder created for " + reminderTime).block();
			}else{
				channel.createMessage(parseErrMessage).block();
			}
		} catch (ParseException e1) {
			channel.createMessage(parseErrMessage).block();
		}	
	}
	
	
}
