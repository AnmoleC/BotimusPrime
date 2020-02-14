package pso2.Subcommands;

import java.util.Date;
import java.util.List;

import app.App;
import basicCommands.Command;
import discord4j.core.event.domain.message.MessageCreateEvent;
import pso2.EQBean;
import pso2.EQManager;
import pso2.PSO2SubCommands;

public class EQTodaySubCommand extends Command {

	public EQTodaySubCommand() {
		super(true);
	}

	@Override
	protected void executeCommand(MessageCreateEvent event) {
		List<EQBean> EQlist = EQManager.getAllEQs();
		channel.createMessage("Listing all EQS on schedule").block();
		Date endOfToday = new Date();
		endOfToday.setDate(endOfToday.getDate() + 1);
		endOfToday.setHours(0);
		endOfToday.setMinutes(0);
		endOfToday.setSeconds(0);
		for (EQBean eqBean : EQlist) {
			if (eqBean.getStartTime().getTime() < endOfToday.getTime()){
				channel.createMessage(eqBean.toString() + "\n").block();
			}
		}
		channel.createMessage("Finished listing all EQs for today").block();
	}

	@Override
	public String prefix() {
		return "today";
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
		return "Lists all EQs happening today";
	}

}
