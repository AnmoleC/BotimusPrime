package botimusprime.botimusprime.commands;

import discord4j.core.event.domain.message.MessageCreateEvent;

public class PongCommand implements Command {
	
	public PongCommand(MessageCreateEvent event){
		this.execute(event);
	}
	
	@Override
	public void execute(MessageCreateEvent event) {
		event.getMessage()
        .getChannel().block()
        .createMessage("Pong2!").block();
	}

}
