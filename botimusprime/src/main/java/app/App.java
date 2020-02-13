package app;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import basicCommands.AbstractCommand;
import basicCommands.Command;
import basicCommands.EchoCommand;
import basicCommands.HelpCommand;
import basicCommands.PongCommand;
import discord4j.core.DiscordClient;
import discord4j.core.DiscordClientBuilder;
import discord4j.core.event.domain.message.MessageCreateEvent;
import image.AddImageCommand;
import image.PostImageCommand;
import pso2.PSO2SubCommands;
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
	public static final char BOT_PREFIX = '!';
	private static final List<AbstractCommand> commandSet = new ArrayList<>();
	private static final Map<String, AbstractCommand> commands = new LinkedHashMap<>();
	static {
		commandSet.add(new HelpCommand());
		commandSet.add(new PongCommand());
		commandSet.add(new EchoCommand());
		commandSet.add(new PostImageCommand());
		commandSet.add(new AddImageCommand());
		
		for (AbstractCommand command : commandSet) {
			commands.put(command.prefix(), command);
		}

//	    commands.put("remindme", new RemindMeCommand());
//	    commands.put("pso2", new PSO2SubCommands());
//	    
	    ReminderService.getInstance();
	    ReminderManager.getInstance();
	}
	
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
        
        final DiscordClient client = new DiscordClientBuilder(args[0]).build();
//        EQManager.initialize(args[1]);
        
        client.getEventDispatcher().on(MessageCreateEvent.class)
        // subscribe is like block, in that it will *request* for action
        // to be done, but instead of blocking the thread, waiting for it
        // to finish, it will just execute the results asynchronously.
        .subscribe(event -> {
            final String content = event.getMessage().getContent().orElse("");
            for (final Entry<String, AbstractCommand> entry : commands.entrySet()) {
                if (content.startsWith(BOT_PREFIX + entry.getKey())) {
                    entry.getValue().execute(event);
                    break;
                }
            }
        });
        
        client.login().block();
    }
    
    public static Set<AbstractCommand> CommandList(){
    	return new HashSet<>(commandSet);
    }
}
