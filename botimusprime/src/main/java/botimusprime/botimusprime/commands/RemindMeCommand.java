package botimusprime.botimusprime.commands;

import discord4j.core.event.domain.message.MessageCreateEvent;

public class RemindMeCommand implements Command {
	
	@Override
	public void execute(MessageCreateEvent event) {
		System.out.println(event.getMessage().getAuthor());
		//TODO
	}

}
