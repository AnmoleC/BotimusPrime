package pso2.Subcommands;

import java.util.List;

import app.App;
import basicCommands.Command;
import discord4j.core.event.domain.message.MessageCreateEvent;
import pso2.EQBean;
import pso2.EQManager;

public class EQListSubCommand extends Command {
	private static final int maxMsg = 20;
	public EQListSubCommand() {
		super(true);
	}

	@Override
	protected void executeCommand(MessageCreateEvent event) {
		List<EQBean> EQlist = EQManager.getUpcomingEQs();
		int listLen = Math.min(EQlist.size(), maxMsg);
		channel.createMessage("Listing next " + listLen + " EQS on schedule").block();
		for (int i=0; i < listLen; i++) {
			channel.createMessage(EQlist.get(i).toString() + "\n").block();
		}
		channel.createMessage("Finished listing Events\n").block();

	}

	@Override
	public String prefix() {
		return "list";
	}

	@Override
	public String syntaxRegex() {
		return prefix();
	}

	@Override
	public String syntaxMsg() {
		return App.bot.BOT_PREFIX + PSO2SubCommands.staticPrefix() + " " + prefix();
	}

	@Override
	protected String description() {
		return "Lists up to the next " + maxMsg + " EQs";
	}
}
