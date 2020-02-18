package game.commands;

import basicCommands.Command;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Guild;
import game.GameManager;

public class CreateGameCommand extends Command {

	@Override
	protected void executeCommand(MessageCreateEvent event) {
		Guild guild = event.getGuild().block();
		GameManager.createGame(guild, content);
	}

	@Override
	public String prefix() {
		return "createGame";
	}

	@Override
	public String syntaxRegex() {
		return prefix() + "\\s[\\w]+";
	}

	@Override
	public String syntaxMsg() {
		return prefix() + " **Game Name**";
	}

	@Override
	protected String description() {
		return "Creates a channel and starts a game in that channel";
	}

}
