package reminder;

import java.util.Date;

import discord4j.core.object.entity.MessageChannel;

public class ReminderBean {
	private MessageChannel channel;
	private long userID;
	private String message;
	private Date date;
	private boolean isRole;
	
	public ReminderBean(MessageChannel channel, long userID, Date date, String message) {
		super();
		this.channel = channel;
		this.userID = userID;
		this.message = message;
		this.date = date;
		if(this.message == null){
			this.message = "";
		}
		this.isRole = false;
	}
	
	public ReminderBean(MessageChannel channel, long userID, Date date, String message, boolean role) {
		this(channel, userID, date, message);
		this.isRole = role;
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
	
	public boolean isRole(){
		return isRole;
	}
	
	public String getMention(){
		String mention = "<@";
		if(isRole){
			mention += "&";
		}
		mention += userID + ">";
		
		return mention;
	}
	public String toLongString() {
		return "ReminderBean [channel=" + channel.getId() + ", message=" + message + ", date=" + date + "]";
	}

	@Override
	public String toString() {
		return "On " + date + " do " + message;
	}
	
}
