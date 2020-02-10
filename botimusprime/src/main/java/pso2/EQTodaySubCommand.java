package pso2;

import java.util.Date;
import java.util.List;

import basicCommands.Command;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.MessageChannel;

public class EQTodaySubCommand implements Command {

	@Override
	public void execute(MessageCreateEvent event) {
		MessageChannel channel = event.getMessage().getChannel().block();
		List<EQBean> EQlist = EQManager.getEQList();
		
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

}
