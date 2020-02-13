package basicCommands;

import app.App;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.MessageChannel;

public abstract class Command{
	protected abstract void executeCommand(MessageCreateEvent event);
	public abstract String prefix();
	public abstract String syntaxRegex();
	public abstract String syntaxMsg();
	protected abstract String description();
	protected MessageChannel channel;
	protected String fullContent;
	protected String content;
	protected boolean subCommand = false;

	public Command() {
		super();
	}

	public Command(boolean subCommand) {
		super();
		this.subCommand = subCommand;
	}

	public void execute(MessageCreateEvent event){
		channel = event.getMessage().getChannel().block();
		String fullContent = event.getMessage().getContent().get().trim().replaceAll("\\s+", " ");
		
		if(!fullContent.matches(App.BOT_PREFIX + syntaxRegex()) && !subCommand){
			channel.createMessage(parseErrorMsg()).block();
//			System.out.println(fullContent+"'");
			return;
		}
		content = fullContent.substring(prefix().length()+1).trim();
//		System.out.println(content + ":");
		executeCommand(event);
	}
	
	public String parseErrorMsg(){
		return "Invalid Syntax. Use as follows (Bold means argument)\n" + syntaxMsg();
	}
	
}
