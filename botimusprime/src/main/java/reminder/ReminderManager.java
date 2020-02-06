package reminder;

import java.util.ArrayList;
import java.util.List;

public class ReminderManager {

	private static ReminderManager reminderManager;
	private static List<ReminderBean> reminders = new ArrayList<ReminderBean>();

	private ReminderManager() {
		super();
	}

	public static ReminderManager getInstance(){
		if (reminderManager == null){
			reminderManager = new ReminderManager();
		}
		return reminderManager;
	}
	
	public void addReminder(ReminderBean reminder){
		reminders.add(reminder);
	}

	public void removeReminder(ReminderBean reminder){
		reminders.remove(reminder);
	}
	
	public void printReminder(ReminderBean r){
		if(!reminders.contains(r)) 
			return;
		r.getChannel().createMessage(r.getMessage()).block();
		reminders.remove(r);
	}
	
	public List<ReminderBean> getReminders(){
		return new ArrayList<ReminderBean>(reminders);
	}
}
