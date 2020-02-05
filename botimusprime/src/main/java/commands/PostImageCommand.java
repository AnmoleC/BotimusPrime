package commands;

import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.MessageChannel;

public class PostImageCommand implements Command {

	@Override
	public void execute(MessageCreateEvent event) {
		MessageChannel channel = event.getMessage().getChannel().block();
		
	}

}
