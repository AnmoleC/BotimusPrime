package pso2.Subcommands;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import app.App;
import basicCommands.Command;
import discord4j.core.event.domain.message.MessageCreateEvent;
import pso2.EQBean;
import pso2.EQManager;

public class EQTodaySubCommand extends Command {

	public EQTodaySubCommand() {
		super(true);
	}

	@Override
	protected void executeCommand(MessageCreateEvent event) {
		List<EQBean> EQlist = EQManager.getAllEQs();
		channel.createMessage("Listing all EQS on schedule").block();
		Date endOfToday = new Date();
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, 1);
		c.set(Calendar.HOUR, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		endOfToday = c.getTime();
		
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
