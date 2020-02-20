package reminder;

import java.text.ParseException;
import java.util.Date;

import app.App;
import basicCommands.Command;
import discord4j.core.event.domain.message.MessageCreateEvent;
import util.DateParser;

public class RemindMeCommand extends Command {
	
	@Override
	protected void executeCommand(MessageCreateEvent event) {
		long username = event.getMessage().getAuthor().get().getId().asLong();
		String time = content.substring(content.indexOf('<')+1, content.indexOf('>'));
		String message = content.substring(content.indexOf('>')+1).trim();
				
		try{
			Date reminderTime = DateParser.parse(time);
			if(reminderTime != null){
				ReminderBean reminder = new ReminderBean(channel, username, reminderTime, message);
				ReminderManager.addReminder(reminder);
				channel.createMessage("Reminder created for " + reminderTime).block();
			}
		}catch (ParseException e) {
			channel.createMessage(e.getMessage()).block();
		}
	}

	@Override
	public String prefix() {
		return "remindme";
	}

	@Override
	public String syntaxRegex() {
		return prefix() + "[\\s]<[\\w:\\s]*>[\\s]?[\\w\\.\\?\\s]*";
	}

	@Override
	public String syntaxMsg() {
		return App.bot.BOT_PREFIX + prefix() + " <**hours**:**minutes**> **<Message>**";
	}

	@Override
	protected String description() {
		return "Creates a reminder up to a week in advance which pings you and prints an optional message";
	}
		
}
