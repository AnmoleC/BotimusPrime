package botimusprime.botimusprime.commands;

import java.util.Arrays;
import java.util.List;

import discord4j.core.event.domain.message.MessageCreateEvent;

public class EchoCommand implements Command {
	
	@Override
	public void execute(MessageCreateEvent event) {
		String content = event.getMessage().getContent().get();
		List<String> args = Arrays.asList(content.split(" "));
		
		if (args.size() != 3)
			return;
		
		String username = args.get(1);
		int repeats = Integer.parseInt(args.get(2));
		
		for (int i = 0; i < repeats; i++) {			
			event.getMessage()
	        .getChannel().block()
	        .createMessage(username).block();
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
