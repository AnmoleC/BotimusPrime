package util.beans;

import java.util.Date;

import discord4j.core.object.entity.MessageChannel;

public class ReminderBean {
	private MessageChannel channel;
	private String message;
	private Date date;
	
	public ReminderBean(MessageChannel channel, String message, Date date) {
		super();
		this.channel = channel;
		this.message = message;
		this.date = date;
	}

	public MessageChannel getChannel() {
		return channel;
	}

	public String getMessage() {
		return message;
	}

	public Date getDate() {
		return date;
	}
	
}
