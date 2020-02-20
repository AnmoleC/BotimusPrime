package danbooru;

import basicCommands.Command;
import discord4j.core.event.domain.message.MessageCreateEvent;

public class DanbooruGetCommand extends Command {

	@Override
	protected void executeCommand(MessageCreateEvent event) {
		String tag;
		int imgNum;
		if(content.matches("[\\w_]+\\s[\\d]+")) {
			tag = content.substring(0, content.indexOf(' '));
			imgNum = Integer.parseInt(content.substring(content.indexOf(' ')+1));
		}else {
			tag = content;
			imgNum = (int) (Math.random()*1000);
		}
		String url = DanbooruRequestBuilder.getURL(tag, imgNum);
		DanbooruResultBean resultSet = DanbooruManager.fetchResults(url);
		
		DanbooruPostBean postBean = resultSet.getPost(imgNum%20);
		channel.createMessage(postBean.getURL()).block();
	}

	@Override
	public String prefix() {
		return "danbooru";
	}

	@Override
	public String syntaxRegex() {
		return prefix() + "\\s[\\w_]+[\\s]*[\\d]*";
	}

	@Override
	public String syntaxMsg() {
		return prefix() + " **tag_name** **<image_Number>** ";
	}

	@Override
	protected String description() {
		return "Fetches an image from Danbooru. If no number is specified it picks a random image with that tag";
	}

}
