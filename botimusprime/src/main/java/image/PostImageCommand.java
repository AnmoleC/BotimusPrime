package image;

import java.util.Map;

import app.App;
import basicCommands.AbstractCommand;
import discord4j.core.event.domain.message.MessageCreateEvent;

public class PostImageCommand extends AbstractCommand{
	
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
	protected String syntaxRegex() {
		return App.BOT_PREFIX + prefix() + "[\\s][\\w]*";
	}

	@Override
	public String helpMsg() {
		return App.BOT_PREFIX + prefix() + " **keyword**";
	}

	@Override
	protected String description() {
		return "Posts an image from a saved list";
	}

}
