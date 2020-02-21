package game;

import java.util.Date;

import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.MessageChannel;

public abstract class Game {
	public abstract void begin(); 
	protected MessageChannel channel;
	private Date updated;
	protected String content;
	
	public Game(){
		updated = new Date();
	}
	
	public void execute(MessageCreateEvent event){
		updated = new Date();
		content = event.getMessage().getContent().orElse("");
		subExecute(event);	
	}
	public abstract void subExecute(MessageCreateEvent event);
	public abstract String name();
	public abstract Long timeout();
	
	public void setChannel(MessageChannel channel){
		this.channel = channel;
	}
	
	public MessageChannel getChannel(){
		return channel;
	}
	
	public Date updated(){
		return updated;
	}
}
