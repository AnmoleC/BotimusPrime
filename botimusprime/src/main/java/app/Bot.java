package app;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import basicCommands.Command;
import basicCommands.EchoCommand;
import basicCommands.HelpCommand;
import basicCommands.PongCommand;
import discord4j.core.DiscordClient;
import discord4j.core.DiscordClientBuilder;
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
