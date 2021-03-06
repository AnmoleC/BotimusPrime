package game;

import java.util.Date;
import java.util.List;

public class GameService implements Runnable {
	private static GameService service;
	private static int UPDATE = 30000;
	private static boolean running = true;
	
	private GameService(){
		super();
	}
	
	public static GameService getInstance(){
		if(service == null){
			service = new GameService();
			Thread thread = new Thread(service);
			thread.start();
		}
		return service;
	}
	
	public static void stop(){
		running = false;
	}
	
	public void run(){
		List<Game> games;
		while(running){
			games = GameManager.games();
			for (Game game : games) {
				Date now = new Date();
				if(game.updated().getTime() + game.timeout() <= now.getTime()){
					GameManager.removeGame(game);
				}
			}
			
			try {
				Thread.sleep(UPDATE);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}	
		}
	}
}
