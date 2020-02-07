package basicCommands;

import java.util.Arrays;
import java.util.List;

import app.App;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.MessageChannel;

public class EchoCommand implements Command {
	private static final String invalidArgsMessage = "Invalid call. Please use the command as follows \n" + 
														App.BOT_PREFIX + "echo <username(pinged)> <repeats>";

	public void execute(MessageCreateEvent event) {
		MessageChannel channel = event.getMessage().getChannel().block();
		String content = event.getMessage().getContent().get();
		List<String> args = Arrays.asList(content.split(" "));
		
		if (args.size() != 3){
			channel.createMessage(invalidArgsMessage).block();
			return;
		}
		
		try{
			String username = args.get(1);
			int repeats = Integer.parseInt(args.get(2));
			for (int i = 0; i < repeats; i++) {			
				channel.createMessage(username).block();				
			}
			
		}catch(NumberFormatException e){
			channel.createMessage("<repeats> was not a number\n" + invalidArgsMessage).block();
		}
		
	}

}
