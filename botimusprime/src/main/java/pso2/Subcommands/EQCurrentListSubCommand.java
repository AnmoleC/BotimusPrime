package pso2.Subcommands;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import app.App;
import basicCommands.Command;
import discord4j.core.event.domain.message.MessageCreateEvent;
import pso2.EQBean;
import pso2.EQManager;

public class EQCurrentListSubCommand extends Command {
	
	public EQCurrentListSubCommand(){
		super(true);
	}

	@Override
	protected void executeCommand(MessageCreateEvent event) {
		List<EQBean> EQlist = EQManager.getUpcomingEQs();
		Set<String> EQNames = new HashSet<>();
		String message = "These are the events currently on the schedule\n";
		for (EQBean EQ : EQlist) {
			EQNames.add(EQ.getName());
		}
		
		for (String string : EQNames) {
			message += string + "\n";
		}
		channel.createMessage(message).block();
	}

	@Override
	public String prefix() {
		return "current";
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
		return "Lists the EQs currently on the schedule";
	}

}
