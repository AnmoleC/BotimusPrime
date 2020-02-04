package botimusprime.botimusprime.commands;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import discord4j.core.event.domain.message.MessageCreateEvent;

public class RemindMeCommand implements Command {
	private static final String parseErrMessage = "Invalid time format. Please specify a wait time as follows HH:mm";
	
	@Override
	public void execute(MessageCreateEvent event) {
		long username = event.getMessage().getAuthor().get().getId().asLong();
		String content = event.getMessage().getContent().get();
		List<String> args = Arrays.asList(content.split(" "));
		
		if (args.size() != 2)
			return;
		
		DateFormat dateformat = new SimpleDateFormat("HH:mm");
		try {
			Date reference = dateformat.parse("00:00:00");
			Date wake = dateformat.parse(args.get(1));
			long miliseconds = wake.getTime() - reference.getTime();
			System.out.println(miliseconds);
			
			Thread.sleep(miliseconds);

			event.getMessage()
	        .getChannel().block()
	        .createMessage("<@"+username+">").block();
		} catch (ParseException e1) {
//			e1.printStackTrace();
			
			event.getMessage()
	        .getChannel().block()
	        .createMessage(parseErrMessage).block();
		} catch (InterruptedException e2) {
			e2.printStackTrace();
		}
		
		
		
	}

}
