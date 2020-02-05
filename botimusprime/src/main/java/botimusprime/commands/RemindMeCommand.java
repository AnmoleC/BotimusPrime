package botimusprime.commands;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import botimusprime.App;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.MessageChannel;

public class RemindMeCommand implements Command {
	private static final String parseErrMessage = "Invalid time format. Please specify a wait time in the format <Hours:minutes>";
	private static final String invalidArgsMessage = "Invalid call. Please use the command as follows \n"
														+ App.BOT_PREFIX + "remindme <waitTime>";
	
	@Override
	public void execute(MessageCreateEvent event) {
		MessageChannel channel = event.getMessage().getChannel().block();
		long username = event.getMessage().getAuthor().get().getId().asLong();
		String content = event.getMessage().getContent().get();
		List<String> args = Arrays.asList(content.split(" "));
		
		if (args.size() != 2){
			channel.createMessage(invalidArgsMessage);
			return;
		}
		
		DateFormat dateformat = new SimpleDateFormat("HH:mm");
		try {
			Date reference = dateformat.parse("00:00:00");
			Date wake = dateformat.parse(args.get(1));
			long miliseconds = wake.getTime() - reference.getTime();
//			System.out.println(miliseconds);
			
			Thread.sleep(miliseconds);

			channel.createMessage("<@"+username+">").block();
		} catch (ParseException e1) {
//			e1.printStackTrace();
			channel.createMessage(parseErrMessage).block();
			
		} catch (InterruptedException e2) {
			e2.printStackTrace();
		}
		
		
		
	}

}
