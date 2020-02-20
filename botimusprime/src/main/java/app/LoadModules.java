package app;

import game.GameService;
import pso2.EQManager;
import reminder.ReminderService;

public class LoadModules implements Runnable {
	private String GoogleAPIKey;
	
	public void setGoogleAPIKey(String key){
		this.GoogleAPIKey = key;
	}
	
	@Override
	public void run() {
		if(GoogleAPIKey == null){
			System.out.println("Set  Google API Key before starting thread");
			return;
		}
        EQManager.initialize(GoogleAPIKey);
	    ReminderService.getInstance();
	    GameService.getInstance();
	    System.out.println("All Modules Loaded");
	}

}
