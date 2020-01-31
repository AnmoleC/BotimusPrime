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
	
	private static PongCommand pong = new PongCommand();
	private static RemindMeCommand remindMe = new RemindMeCommand();
	private static EchoCommand echo = new EchoCommand();

	static {
	    commands.put("ping", event -> pong.execute(event));
	    commands.put("remindme", event -> remindMe.execute(event));
	    commands.put("echo", event -> echo.execute(event));
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
                if (content.startsWith('!' + entry.getKey())) {
                    entry.getValue().execute(event);
                    break;
                }
            }
        });
        
        client.login().block();
        

    }
}
