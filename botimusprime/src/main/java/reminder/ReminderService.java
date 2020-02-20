package reminder;

import java.util.Date;
import java.util.List;

public class ReminderService implements Runnable{
	private static ReminderService service;
	private static int UPDATE = 10000;
	private static boolean running = true;
	
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
	
	public static void stop(){
		running = false;
	}
	
	public void run() {
		List<ReminderBean> reminders;
		while(running){
			reminders = ReminderManager.getReminders();
//			System.out.println(reminders.size() + " Reminders");
			Date now = new Date();
			for (ReminderBean reminder : reminders) {
//				System.out.println(reminder);
				if(reminder.getDate().getTime() <= now.getTime())
					ReminderManager.printReminder(reminder);
			}
			try {
				Thread.sleep(UPDATE);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
						
		}
	}

}
