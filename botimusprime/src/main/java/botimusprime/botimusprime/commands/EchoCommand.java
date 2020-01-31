package botimusprime.botimusprime.commands;

import discord4j.core.event.domain.message.MessageCreateEvent;

public class EchoCommand implements Command {

	@Override
	public void execute(MessageCreateEvent event) {
		System.out.println(event.getMessage().getContent());

		String content = event.getMessage().getContent().toString();
		String[] arguments = content.substring(content.indexOf('!'), content.length()-1).split(" ");
		int repeats = Integer.parseInt(arguments[arguments.length-1]);
		String username = arguments[1];
		
		for (int i = 0; i < repeats; i++) {			
			event.getMessage()
	        .getChannel().block()
	        .createMessage(username).block();
		}
	}

}
