package basicCommands;

import java.util.Set;

import app.App;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.MessageChannel;

public class HelpCommand extends AbstractCommand {

	@Override
	void executeCommand(MessageCreateEvent event) {
		MessageChannel channel = event.getMessage().getChannel().block();
		Set<AbstractCommand> commandSet = App.CommandList();
		String result = "```";
		result += "Bot Prefix is " + App.BOT_PREFIX + "\n";
		for (AbstractCommand command : commandSet) {
			result += String.format("%-10s \t %-40s \t %-50s \n", command.prefix(), command.helpMsg(), command.description());
		}
		result += "```";
		channel.createMessage(result).block();
		
	}

	@Override
	public String prefix() {
		return "help";
	}

	@Override
	String syntaxRegex() {
		return App.BOT_PREFIX+prefix();
	}

	@Override
	public String helpMsg() {
		return syntaxRegex();
	}

	@Override
	protected String description() {
		return "Prints all commands and usage";
	}

}
