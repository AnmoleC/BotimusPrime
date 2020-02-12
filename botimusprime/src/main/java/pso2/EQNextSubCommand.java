package pso2;

import java.util.Date;
import java.util.List;

import basicCommands.Command;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.MessageChannel;

public class EQNextSubCommand implements Command {

	@Override
	public void execute(MessageCreateEvent event) {
		MessageChannel channel = event.getMessage().getChannel().block();
		List<EQBean> EQlist = EQManager.getAllEQs();
		int index = 0;
		while (EQlist.get(index).getStartTime().getTime() < new Date().getTime()) {
			index++;
		}
		channel.createMessage("The next EQ is \n" + EQlist.get(index) ).block();
	}

}
