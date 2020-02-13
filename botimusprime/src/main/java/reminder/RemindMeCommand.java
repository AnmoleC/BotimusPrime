package reminder;

import java.text.ParseException;
import java.util.Date;

import app.App;
import basicCommands.Command;
import discord4j.core.event.domain.message.MessageCreateEvent;
import util.DateParser;

public class RemindMeCommand extends Command {
	private static ReminderManager manager = ReminderManager.getInstance();	
	
	@Override
	protected void executeCommand(MessageCreateEvent event) {
		System.out.println("SYNTAX PASS");
		long username = event.getMessage().getAuthor().get().getId().asLong();
		String time = content.substring(content.indexOf('<')+1, content.indexOf('>'));
		String message = content.substring(content.indexOf('>')+1).trim();
		
		try{
			Date reminderTime = DateParser.parse(time);
			if(reminderTime != null){
				ReminderBean reminder = new ReminderBean(channel, username, reminderTime, message);
				manager.addReminder(reminder);
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
		return App.BOT_PREFIX + prefix() + " <**Time**> **Message (Optional)**";
	}

	@Override
	protected String description() {
		return "Created a reminder up to a week in advance which pings you and prints an optional message";
	}
		
}
