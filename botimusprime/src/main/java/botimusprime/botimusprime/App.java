package botimusprime.botimusprime;

import java.util.HashMap;
import java.util.Map;

import botimusprime.botimusprime.commands.Command;
import botimusprime.botimusprime.commands.EchoCommand;
import botimusprime.botimusprime.commands.PongCommand;
import botimusprime.botimusprime.commands.RemindMeCommand;
import discord4j.core.DiscordClient;
import discord4j.core.DiscordClientBuilder;
import discord4j.core.event.domain.message.MessageCreateEvent;

/**
 * Hello world!
 *
 */
public class App 
{
	private static final Map<String, Command> commands = new HashMap<>();
	public static final String BOT_PREFIX = "!";
	
	static {
	    commands.put("ping" , new PongCommand());
	    commands.put("remindme", new RemindMeCommand());
	    commands.put("echo", new EchoCommand());
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
                // We will be using ! as our "prefix" to any command in the system.
                if (content.startsWith(BOT_PREFIX + entry.getKey())) {
                    entry.getValue().execute(event);
                    break;
                }
            }
        });
        
        client.login().block();
    }
}
