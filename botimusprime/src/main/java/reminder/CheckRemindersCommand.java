package reminder;

import java.util.List;

import app.App;
import basicCommands.Command;
import discord4j.core.event.domain.message.MessageCreateEvent;

public class CheckRemindersCommand extends Command {

	@Override
	protected void executeCommand(MessageCreateEvent event) {
		long userID = event.getMessage().getAuthor().get().getId().asLong();
		List<ReminderBean> reminders = ReminderManager.getInstance().getReminders();
		for (ReminderBean reminder : reminders) {
			if(reminder.getUserID() == userID){
				channel.createMessage(reminder.toString()).block();
			}
		}
		channel.createMessage("You have no more reminders").block();
	}

	@Override
	public String prefix() {
		return "checkReminders";
	}

	@Override
	public String syntaxRegex() {
		return prefix();
	}

	@Override
	public String syntaxMsg() {
		return App.BOT_PREFIX + prefix();
	}

	@Override
	protected String description() {
		return "Prints all waiting reminders for this user";
	}

}
