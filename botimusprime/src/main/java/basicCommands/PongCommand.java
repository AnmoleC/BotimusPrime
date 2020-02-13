package basicCommands;

import app.App;
import discord4j.core.event.domain.message.MessageCreateEvent;

public class PongCommand extends Command{

	@Override
	protected void executeCommand(MessageCreateEvent event) {
		event.getMessage()
			.getChannel().block()
			.createMessage("Pong!").block();		
	}

	@Override
	public String prefix() {
		return "ping";
	}

	@Override
	public String syntaxRegex() {
		return prefix();
	}

	@Override
	public String syntaxMsg() {
		return App.BOT_PREFIX + syntaxRegex();
	}

	@Override
	protected String description() {
		return "Prints 'Pong!' to the same channel";
	}
}
