package image;

import app.App;
import basicCommands.AbstractCommand;
import discord4j.core.event.domain.message.MessageCreateEvent;

public class AddImageCommand extends AbstractCommand {

	@Override
	protected void executeCommand(MessageCreateEvent event) {
		String command = content.substring(0, content.indexOf(' '));
		String link = content.substring(content.indexOf(' ')+1, content.length());
		
		ImageManager.addImage(command, link);
		channel.createMessage("Image added").block();
	}

	@Override
	public String prefix() {
		return "addImage";
	}

	@Override
	protected String syntaxRegex() {
		return App.BOT_PREFIX + prefix() + "[\\s][\\w]*[\\s][\\w\\.\\\\:&\\?\\/=]*";
	}

	@Override
	public String helpMsg() {
		return App.BOT_PREFIX + prefix() + " **keyword** **url**";
	}

	@Override
	protected String description() {
		return "Adds an image to the saved list";
	}

}
