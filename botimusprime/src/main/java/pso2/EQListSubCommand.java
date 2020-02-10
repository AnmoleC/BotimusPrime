package pso2;

import java.util.List;

import basicCommands.Command;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.MessageChannel;

public class EQListSubCommand implements Command {

	@Override
	public void execute(MessageCreateEvent event) {
		MessageChannel channel = event.getMessage().getChannel().block();
		List<EQBean> EQlist = EQManager.getEQList();
		int listLen = Math.min(EQlist.size(), 20);
		channel.createMessage("Listing next 20 EQS on schedule").block();
		
		for (int i=0; i < listLen; i++) {
			channel.createMessage(EQlist.get(i).toString() + "\n").block();
		}
		channel.createMessage("Finished listing EQs\n").block();
	}

}
