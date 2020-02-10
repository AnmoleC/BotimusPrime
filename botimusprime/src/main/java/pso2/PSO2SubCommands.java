package pso2;

import java.util.HashMap;
import java.util.Map;

import basicCommands.Command;
import discord4j.core.event.domain.message.MessageCreateEvent;

public class PSO2SubCommands implements Command {
	private static final Map<String, Command> subCommands = new HashMap<>();
	static {
		subCommands.put("list", new EQListSubCommand());
		subCommands.put("next", new EQNextSubCommand());
		subCommands.put("today", new EQTodaySubCommand());
	}
	
	@Override
	public void execute(MessageCreateEvent event) {
		String message = event.getMessage().getContent().get();
		message = message.substring(message.indexOf(' ')+1, message.length());
		for (final Map.Entry<String, Command> entry : subCommands.entrySet()) {
            if (message.startsWith(entry.getKey())) {
                entry.getValue().execute(event);
                break;
            }
        }

	}

}
