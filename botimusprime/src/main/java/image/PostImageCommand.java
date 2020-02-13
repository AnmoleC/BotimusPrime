package image;

import java.util.Map;

import app.App;
import basicCommands.Command;
import discord4j.core.event.domain.message.MessageCreateEvent;

public class PostImageCommand extends Command{
	
	@Override
	protected void executeCommand(MessageCreateEvent event) {
		Map<String, String> images = ImageManager.getImages();
		
		for (String key : images.keySet()) {
			if(key.equals(content))
				channel.createMessage(images.get(key)).block();
		}
	}

	@Override
	public String prefix() {
		return "image";
	}

	@Override
	public String syntaxRegex() {
		return prefix() + "[\\s][\\w]*";
	}

	@Override
	public String syntaxMsg() {
		return App.BOT_PREFIX + prefix() + " **keyword**";
	}

	@Override
	protected String description() {
		return "Posts an image from a saved list";
	}

}
