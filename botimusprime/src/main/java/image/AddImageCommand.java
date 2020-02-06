package image;

import java.util.Arrays;
import java.util.List;

import app.App;
import basicCommands.Command;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.MessageChannel;

public class AddImageCommand implements Command {
	private static final String invalidArgsMessage = "Invalid call. Please use the command as follows \n"
			+ App.BOT_PREFIX + "addImage <name> <link>";
	
	@Override
	public void execute(MessageCreateEvent event) {
		MessageChannel channel = event.getMessage().getChannel().block();
		String content = event.getMessage().getContent().get();
		List<String> args = Arrays.asList(content.split(" "));
		
		if (args.size() < 3){
			channel.createMessage(invalidArgsMessage).block();
			return;
		}
		
		String name = args.get(1);
		String link = args.get(2);
		
		ImageManager.addImage(name, link);
		channel.createMessage(link).block();
	}

}
