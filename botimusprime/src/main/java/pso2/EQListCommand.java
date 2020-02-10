package pso2;

import java.util.List;

import basicCommands.Command;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.MessageChannel;

public class EQListCommand implements Command {

	@Override
	public void execute(MessageCreateEvent event) {
		MessageChannel channel = event.getMessage().getChannel().block();
		List<EQBean> EQlist = EQManager.getEQList();
		
		channel.createMessage("Listing all EQS on schedule").block();
		for (EQBean eqBean : EQlist) {
			channel.createMessage(eqBean.toString() + "\n").block();
		}
		channel.createMessage("Finished listing all EQs\n").block();
	}

}
