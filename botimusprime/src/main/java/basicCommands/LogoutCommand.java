package basicCommands;

import app.App;
import app.Modules;
import discord4j.core.event.domain.message.MessageCreateEvent;

public class LogoutCommand extends Command {

	@Override
	protected void executeCommand(MessageCreateEvent event) {
		channel.createMessage("Logging out").block();
		Modules.stop();
		App.bot.client().logout().block();
	}

	@Override
	public String prefix() {
		return "logout";
	}

	@Override
	public String syntaxRegex() {
		return prefix();
	}

	@Override
	public String syntaxMsg() {
		return prefix() ;
	}

	@Override
	protected String description() {
		return "Logs the bot out";
	}

}
