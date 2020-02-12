package basicCommands;

import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.MessageChannel;

public abstract class AbstractCommand implements Command {
	abstract void executeCommand(MessageCreateEvent event);
	
	public abstract String prefix();
	abstract String syntaxRegex();
	public abstract String helpMsg();
	protected abstract String description();
	protected MessageChannel channel;
	protected String fullContent;
	protected String content;
	
	public void execute(MessageCreateEvent event){
		channel = event.getMessage().getChannel().block();
		String fullContent = event.getMessage().getContent().get().trim();
		
		if(!fullContent.matches(syntaxRegex())){
			channel.createMessage(parseErrorMsg()).block();
			System.out.println(fullContent);
			return;
		}
		content = fullContent.substring(prefix().length()+1).trim();
		executeCommand(event);
	}
	
	public String parseErrorMsg(){
		return "Invalid Syntax. Use as follows (Bold means argument)\n" + helpMsg();
	}
	
}
