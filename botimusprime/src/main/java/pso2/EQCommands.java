package pso2;

import java.util.List;

import basicCommands.Command;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.MessageChannel;

public class EQCommands implements Command {

	@Override
	public void execute(MessageCreateEvent event) {
		MessageChannel channel = event.getMessage().getChannel().block();
		channel.createMessage("PSO2 EQ!").block();
		List<EQBean> EQlist = EQManager.getEQList();
		channel.createMessage(EQlist.get(0).getname() + "\n" + EQlist.get(0).getStartTime()).block();
//		for (EQBean EQ : EQlist) {
//			channel.createMessage(EQ.getname() + "\n" + EQ.getStartTime()).block();
//		}
		
		channel.createMessage("Those are all the EQs on the current schedule").block();
	}

}
