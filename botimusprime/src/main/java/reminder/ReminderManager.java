package reminder;

import java.util.ArrayList;
import java.util.List;

public class ReminderManager {
	private static List<ReminderBean> reminders = new ArrayList<ReminderBean>();
	
	public static void addReminder(ReminderBean reminder){
		reminders.add(reminder);
	}

	public static void removeReminder(ReminderBean reminder){
		reminders.remove(reminder);
	}
	
	public static void printReminder(ReminderBean r){
		if(!reminders.contains(r)) 
			return;
		
		r.getChannel().createMessage(r.getMention() + "\n" + r.getMessage()).block();
		reminders.remove(r);
	}
	
	public static List<ReminderBean> getReminders(){
		return new ArrayList<ReminderBean>(reminders);
	}
}
