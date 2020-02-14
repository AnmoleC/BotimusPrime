package basicCommands;

import java.util.List;

import app.App;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.MessageChannel;

public class HelpCommand extends Command {

	@Override
	protected void executeCommand(MessageCreateEvent event) {
		MessageChannel channel = event.getMessage().getChannel().block();
		List<Command> commandList = App.bot.commandList();
		String result = "```";
		result += "Bot Prefix is " + App.bot.BOT_PREFIX + "\n";
		for (Command command : commandList) {
			if(!content.equals("")){
				if (command.prefix().equals(content.trim())){
					result += command.description() + ". Syntax:\n" + command.syntaxMsg();
				}
			}else{
				result += String.format("%-10s \t %-40s \t %-50s \n", command.prefix(), command.syntaxMsg(), command.description());
			}
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
		return prefix() + "[\\s\\w]*";
	}

	@Override
	public String syntaxMsg() {
		return App.bot.BOT_PREFIX + prefix();
	}

	@Override
	protected String description() {
		return "Prints all commands and usage";
	}

}
