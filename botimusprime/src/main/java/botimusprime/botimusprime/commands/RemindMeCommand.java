package botimusprime.botimusprime.commands;

import discord4j.core.event.domain.message.MessageCreateEvent;

public class RemindMeCommand implements Command {
	
	@Override
	public void execute(MessageCreateEvent event) {
//		System.out.println(event.getMessage().getAuthor().get().getUsername());
		long username = event.getMessage().getAuthor().get().getId().asLong();
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		event.getMessage()
        .getChannel().block()
        .createMessage("<@"+username+">").block();
	}

}
