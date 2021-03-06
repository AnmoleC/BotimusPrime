package app;

import discord4j.core.DiscordClient;

public class App 
{
	public static Bot bot;
	public static void main( String[] args )
    {	
        System.out.println( "Hello World!" );
        
        if (args.length != 2){
        	System.out.println("Please run with the following 2 arguments in the following order\n"
        			+ "DiscordBot Token\n"
        			+ "Google Calendar API key"
        			);
        	return;
        }
        
        bot = new Bot(args[0]);
        if (bot == null)
        	return;
        final DiscordClient client = bot.client();
        
        Modules loader = new Modules();
        loader.setGoogleAPIKey(args[1]);
        Thread thread = new Thread(loader);
        thread.start();
        
        client.login().block();
        System.out.println("Logged Out");
        System.exit(0);
    }

}
