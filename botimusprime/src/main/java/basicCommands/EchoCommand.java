package basicCommands;

import app.App;
import discord4j.core.event.domain.message.MessageCreateEvent;

public class EchoCommand extends Command {
	private final int maxRepeats = 10;
	@Override
	protected void executeCommand(MessageCreateEvent event) {
		String message = content.substring(0, content.lastIndexOf(' ')).trim();
		int repeats = Math.min(Integer.parseInt(content.substring(content.lastIndexOf(' '), content.length()).trim()), maxRepeats);
		
		for (int i = 0; i < repeats; i++) {
			channel.createMessage(message).block();
		}
	}

	@Override
	public String prefix() {
		return "echo";
	}

	@Override
	public String syntaxRegex() {
		return prefix() + "[\\s][\\w<@!>]*[\\s][\\d]+";
	}

	@Override
	public String syntaxMsg() {
		return App.BOT_PREFIX + prefix() + " **Message** **Repeats**";
	}

	@Override
	protected String description() {
		return "Repeats **Message** **Repeats** number of times (Max " + maxRepeats + " repeats)" ;
	}

}
