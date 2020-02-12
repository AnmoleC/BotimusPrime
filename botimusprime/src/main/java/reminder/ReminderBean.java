package reminder;

import java.util.Date;

import discord4j.core.object.entity.MessageChannel;

public class ReminderBean {
	private MessageChannel channel;
	private long userID;
	private String message;
	private Date date;
	
	
	public ReminderBean(MessageChannel channel, long userID, Date date, String message) {
		super();
		this.channel = channel;
		this.userID = userID;
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

	public long getUserID(){
		return userID;
	}
	
	public String toLongString() {
		return "ReminderBean [channel=" + channel.getId() + ", message=" + message + ", date=" + date + "]";
	}

	@Override
	public String toString() {
		return "ReminderBean [message=" + message + ", date=" + date + "]";
	}
	
}
