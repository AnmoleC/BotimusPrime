package botimusprime.botimusprime.commands;

import discord4j.core.event.domain.message.MessageCreateEvent;

public class EchoCommand implements Command {

	@Override
	public void execute(MessageCreateEvent event) {
		System.out.println(event.getMessage().getContent());
//		event.getMessage().getUserMentions()
		
//		String[] args = event.getMessage().getContent().toString().split(" ");

//		System.out.println("ARGS:" + args.length);
//		if(args.length >= 1) System.out.println("COMMAND:" + args[0]);
//		if(args.length >= 2) System.out.println("USER:" + args[1]);
//		if(args.length >= 3) System.out.println("REPEATS:" + args[2]);
		
		
		
	}

}
