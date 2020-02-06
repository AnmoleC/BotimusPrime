package app;

import java.util.HashMap;
import java.util.Map;

import basicCommands.Command;
import basicCommands.EchoCommand;
import basicCommands.PongCommand;
import discord4j.core.DiscordClient;
import discord4j.core.DiscordClientBuilder;
import discord4j.core.event.domain.message.MessageCreateEvent;
import image.AddImageCommand;
import image.PostImageCommand;
import pso2.EQManager;
import reminder.RemindMeCommand;
import reminder.ReminderManager;
import reminder.ReminderService;

/**
 * Hello world!
 *
 */
public class App 
{
	private static final Map<String, Command> commands = new HashMap<>();
	public static final char BOT_PREFIX = '!';
	
	static {
	    commands.put("ping" , new PongCommand());
	    commands.put("remindme", new RemindMeCommand());
	    commands.put("echo", new EchoCommand());
	    commands.put("image", new PostImageCommand());
	    commands.put("addImage", new AddImageCommand());
	    
	    ReminderService.getInstance();
	    ReminderManager.getInstance();
	}
	
    public static void main( String[] args )
    {	
        System.out.println( "Hello World!" );
        
        if (args.length != 2){
        	System.out.println("Please run with the following 2 arguments in the following order\n"
        			+ "DiscordBot Token"
        			+ "Google Calendar API key"
        			);
        	
        	return;
        }
        
        final DiscordClient client = new DiscordClientBuilder(args[0]).build();
        EQManager.getInstance(args[1]);
        
        client.getEventDispatcher().on(MessageCreateEvent.class)
        // subscribe is like block, in that it will *request* for action
        // to be done, but instead of blocking the thread, waiting for it
        // to finish, it will just execute the results asynchronously.
        .subscribe(event -> {
            final String content = event.getMessage().getContent().orElse("");
            for (final Map.Entry<String, Command> entry : commands.entrySet()) {
                if (content.startsWith(BOT_PREFIX + entry.getKey())) {
                    entry.getValue().execute(event);
                    break;
                }
            }
        });
        
        client.login().block();
    }
}
