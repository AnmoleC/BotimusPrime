package reminder;

import java.util.Date;
import java.util.List;

public class ReminderService implements Runnable{

	private static ReminderService service;
	private static ReminderManager manager = ReminderManager.getInstance();
	private static int UPDATE = 60000;
	private ReminderService() {
		super();
	}

	public static ReminderService getInstance(){
		if (service == null){
			service = new ReminderService();
			Thread thread = new Thread(service);
			thread.start();
		}
		return service;
	}
	
	@Override
	public void run() {
		List<ReminderBean> reminders;
		while(true){
			reminders = manager.getReminders();
			Date now = new Date();
			for (ReminderBean reminder : reminders) {
				if(reminder.getDate().getTime() <= now.getTime())
					manager.printReminder(reminder);
			}
			try {
				Thread.sleep(UPDATE);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
						
		}
	}

}
