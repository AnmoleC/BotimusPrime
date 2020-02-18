package pso2.Subcommands;

import java.util.Date;
import java.util.List;

import app.App;
import basicCommands.Command;
import discord4j.core.event.domain.message.MessageCreateEvent;
import pso2.EQBean;
import pso2.EQManager;

public class EQNextSubCommand extends Command {
	
	public EQNextSubCommand() {
		super(true);
	}
	
	@Override
	protected void executeCommand(MessageCreateEvent event) {
		List<EQBean> EQlist = EQManager.getAllEQs();
		int index = 0;
		while (EQlist.get(index).getStartTime().getTime() < new Date().getTime()) {
			index++;
		}
		channel.createMessage("The next EQ is \n" + EQlist.get(index) ).block();
	}

	@Override
	public String prefix() {
		return "next";
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
		return "Prints the next EQ";
	}
}
