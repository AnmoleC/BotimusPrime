package game;

import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.MessageChannel;

public abstract class Game {
	protected MessageChannel channel;
	
	public abstract void execute(MessageCreateEvent event);
	public abstract String name();
	public abstract Long timeout();
	
	public void setChannel(MessageChannel channel){
		this.channel = channel;
	}
	
	public MessageChannel getChannel(){
		return channel;
	}
}
