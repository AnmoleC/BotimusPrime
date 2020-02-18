package reminder;

import java.util.ArrayList;
import java.util.List;

public class ReminderManager {
	private static List<ReminderBean> reminders = new ArrayList<ReminderBean>();
	
	public void addReminder(ReminderBean reminder){
		reminders.add(reminder);
	}

	public void removeReminder(ReminderBean reminder){
		reminders.remove(reminder);
	}
	
	public void printReminder(ReminderBean r){
		if(!reminders.contains(r)) 
			return;
		r.getChannel().createMessage("<@" + r.getUserID() + ">\n" + r.getMessage()).block();
		reminders.remove(r);
	}
	
	public List<ReminderBean> getReminders(){
		return new ArrayList<ReminderBean>(reminders);
	}
}
