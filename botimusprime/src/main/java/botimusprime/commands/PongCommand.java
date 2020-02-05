package botimusprime.commands;

import discord4j.core.event.domain.message.MessageCreateEvent;

public class PongCommand implements Command {
	
	@Override
	public void execute(MessageCreateEvent event) {
		event.getMessage()
        .getChannel().block()
        .createMessage("Pong!").block();
	}

}
