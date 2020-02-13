package pso2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.App;
import basicCommands.Command;
import discord4j.core.event.domain.message.MessageCreateEvent;
import pso2.Subcommands.EQListSubCommand;
import pso2.Subcommands.EQNextSubCommand;
import pso2.Subcommands.EQTodaySubCommand;

public class PSO2SubCommands extends Command {
	private static final List<Command> subCommandSet = new ArrayList<>();
	private static final Map<String, Command> subCommands = new HashMap<>();
	private static final String prefix = "pso2";
	static {
		subCommandSet.add(new EQListSubCommand());
		subCommandSet.add(new EQNextSubCommand());
		subCommandSet.add(new EQTodaySubCommand());
				
		for (Command command : subCommandSet) {
			subCommands.put(command.prefix(), command);
		}
	}
	
	@Override
	protected void executeCommand(MessageCreateEvent event) {
//		System.out.println(content);
		for (final Map.Entry<String, Command> entry : subCommands.entrySet()) {
			if (content.startsWith(entry.getKey()) & content.matches(entry.getValue().syntaxRegex())) {
//				System.out.println(content);
				if (content.length() <= entry.getValue().prefix().length()){
					content = "";
				}else{
					content = content.substring(entry.getValue().prefix().length()+1);
				}
				entry.getValue().execute(event);
				break;
			}			
		}
	}

	@Override
	public String prefix() {
		return prefix;
	}

	public static String staticPrefix(){
		return prefix;
	}
	
	@Override
	public String syntaxRegex() {
		return prefix() + "[\\s\\w]*";
	}

	@Override
	public String syntaxMsg() {
		return App.BOT_PREFIX + prefix() + " **subCommands**";
	}

	@Override
	protected String description() {
		return "PSO2 command hub";
	}

}
