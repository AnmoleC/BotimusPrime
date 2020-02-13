package basicCommands;

import java.util.Set;

import app.App;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.MessageChannel;

public class HelpCommand extends Command {

	@Override
	protected void executeCommand(MessageCreateEvent event) {
		MessageChannel channel = event.getMessage().getChannel().block();
		Set<Command> commandSet = App.CommandList();
		String result = "```";
		result += "Bot Prefix is " + App.BOT_PREFIX + "\n";
		for (Command command : commandSet) {
			result += String.format("%-10s \t %-40s \t %-50s \n", command.prefix(), command.syntaxMsg(), command.description());
		}
		result += "```";
		channel.createMessage(result).block();
		
	}

	@Override
	public String prefix() {
		return "help";
	}

	@Override
	public String syntaxRegex() {
		return prefix();
	}

	@Override
	public String syntaxMsg() {
		return syntaxRegex();
	}

	@Override
	protected String description() {
		return "Prints all commands and usage";
	}

}
