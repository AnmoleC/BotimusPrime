package app;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import basicCommands.Command;
import basicCommands.EchoCommand;
import basicCommands.HelpCommand;
import basicCommands.PongCommand;
import discord4j.core.DiscordClient;
import discord4j.core.DiscordClientBuilder;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.util.Snowflake;
import image.AddImageCommand;
import image.PostImageCommand;
import pso2.PSO2SubCommands;
import reminder.CheckRemindersCommand;
import reminder.RemindMeCommand;

public class Bot {
	public final char BOT_PREFIX = '!';
	private final List<Command> commandList = new ArrayList<>();
	private final Map<String, Command> commandMap = new LinkedHashMap<>();

	private DiscordClient client;
	private Snowflake userID;
		
	public Bot(String botToken) {
		super();
		client = new DiscordClientBuilder(botToken).build();
		initilizeCommands();
		listeners();
		userID = client.getSelf().block().getId();
	}
	
	private void initilizeCommands(){
		commandList.add(new HelpCommand());
		commandList.add(new PongCommand());
		commandList.add(new EchoCommand());
		commandList.add(new PostImageCommand());
		commandList.add(new AddImageCommand());
		commandList.add(new RemindMeCommand());
		commandList.add(new CheckRemindersCommand());
		commandList.add(new PSO2SubCommands());
		
		for (Command command : commandList) {
			commandMap.put(command.prefix(), command);
		}
	}
	
	private void listeners(){
		 client.getEventDispatcher().on(MessageCreateEvent.class)
	        // subscribe is like block, in that it will *request* for action
	        // to be done, but instead of blocking the thread, waiting for it
	        // to finish, it will just execute the results asynchronously.
	        .subscribe(event -> {
	            final String content = event.getMessage().getContent().orElse("");
	            for (final Entry<String, Command> entry : commandMap().entrySet()) {
	                if (content.startsWith(BOT_PREFIX + entry.getKey())) {
	                    entry.getValue().execute(event);
	                    break;
	                }
	            }
	        });
	}
	
	public DiscordClient client(){
		return client;
	}
	
	public Snowflake ID(){
		return userID;
	}
	
    public List<Command> commandList(){
    	return new ArrayList<>(commandList);
    }

    public Map<String, Command> commandMap(){
    	return new LinkedHashMap<>(commandMap);
    }
    
}
