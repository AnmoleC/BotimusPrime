package app;

import java.util.Map.Entry;

import basicCommands.Command;
import discord4j.core.DiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import pso2.EQManager;
import reminder.ReminderManager;
import reminder.ReminderService;

/**
 * Hello world!
 *
 */
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
        
        EQManager.initialize(args[1]);
	    ReminderService.getInstance();
	    ReminderManager.getInstance();
        
        client.getEventDispatcher().on(MessageCreateEvent.class)
        // subscribe is like block, in that it will *request* for action
        // to be done, but instead of blocking the thread, waiting for it
        // to finish, it will just execute the results asynchronously.
        .subscribe(event -> {
            final String content = event.getMessage().getContent().orElse("");
            for (final Entry<String, Command> entry : bot.commandMap().entrySet()) {
                if (content.startsWith(bot.BOT_PREFIX + entry.getKey())) {
                    entry.getValue().execute(event);
                    break;
                }
            }
           
        });

        client.login().block();
    }

}
