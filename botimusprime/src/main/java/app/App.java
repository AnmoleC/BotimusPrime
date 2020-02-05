package app;

import java.util.HashMap;
import java.util.Map;

import commands.Command;
import commands.EchoCommand;
import commands.PongCommand;
import commands.PostImageCommand;
import commands.RemindMeCommand;
import discord4j.core.DiscordClient;
import discord4j.core.DiscordClientBuilder;
import discord4j.core.event.domain.message.MessageCreateEvent;
import util.ReminderManager;
import util.ReminderService;

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
	    commands.put("post", new PostImageCommand());
	    
	    ReminderService.getInstance();
	    ReminderManager.getInstance();
	}
	
    public static void main( String[] args )
    {	
        System.out.println( "Hello World!" );
        final DiscordClient client = new DiscordClientBuilder(args[0]).build();
        
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
