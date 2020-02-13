package basicCommands;

import app.App;
import discord4j.core.event.domain.message.MessageCreateEvent;

public class PongCommand extends AbstractCommand{

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
	protected String syntaxRegex() {
		return App.BOT_PREFIX + prefix();
	}

	@Override
	public String helpMsg() {
		return syntaxRegex();
	}

	@Override
	protected String description() {
		return "Prints 'Pong!' to the same channel";
	}
}
