package pso2;

import java.util.HashMap;
import java.util.Map;

import basicCommands.Command;
import discord4j.core.event.domain.message.MessageCreateEvent;

public class PSO2SubCommands implements Command {
	private static final Map<String, Command> subCommands = new HashMap<>();
	static {
		subCommands.put("next", new EQListCommand());
	}
	
	@Override
	public void execute(MessageCreateEvent event) {
		System.out.println("PSO2");
		String message = event.getMessage().getContent().get();
		message = message.substring(message.indexOf(' ')+1, message.length());
		System.out.println(message);
		for (final Map.Entry<String, Command> entry : subCommands.entrySet()) {
            if (message.startsWith(entry.getKey())) {
                entry.getValue().execute(event);
                break;
            }
        }
//		MessageChannel channel = event.getMessage().getChannel().block();
//		channel.createMessage("PSO2 EQ!").block();
//		List<EQBean> EQlist = EQManager.getEQList();
//		channel.createMessage(EQlist.get(0).getname() + "\n" + EQlist.get(0).getStartTime()).block();
////		for (EQBean EQ : EQlist) {
////			channel.createMessage(EQ.getname() + "\n" + EQ.getStartTime()).block();
////		}
//		
//		channel.createMessage("Those are all the EQs on the current schedule").block();
	}

}
