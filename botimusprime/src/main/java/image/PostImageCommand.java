package image;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import app.App;
import basicCommands.Command;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.MessageChannel;

public class PostImageCommand implements Command {
	private static final String invalidArgsMessage = "Invalid call. Please use the command as follows \n"
			+ App.BOT_PREFIX + "image <name>";
	
	@Override
	public void execute(MessageCreateEvent event) {
		MessageChannel channel = event.getMessage().getChannel().block();
		String content = event.getMessage().getContent().get();
		List<String> args = Arrays.asList(content.split(" "));
		
		if (args.size() < 2){
			channel.createMessage(invalidArgsMessage).block();
			return;
		}
		
		String name = args.get(1);
		Map<String, String> images = ImageManager.getImages();
		
		for (String key : images.keySet()) {
			if(key.equals(name))
				channel.createMessage( images.get(key)).block();
		}
	}

}
