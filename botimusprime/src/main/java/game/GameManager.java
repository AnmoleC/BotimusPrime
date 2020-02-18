package game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import discord4j.core.object.entity.Guild;
import discord4j.core.object.entity.MessageChannel;
import discord4j.core.object.util.Snowflake;
import game.RockPaperScissor.RockPaperScissors;

public class GameManager {
	private static Map<Long, Long> gameCategoryIDs = new HashMap<>();
	private static List<Game> games = new ArrayList<>();
	private static int counter = 1;
	
	static{
		gameCategoryIDs.put(167128445631856640L, 679363895810326549L); //Weeb Server
	}
	
	public static void createGame(Guild guild, String name){
		Game newGame = createGame(name);
		if(newGame == null)
			return;
		
		games.add(newGame);
		String channelName = counter + " - " + name;
		counter++;
		MessageChannel channel = guild.createTextChannel(spec -> {spec.setName(channelName).setParentId(Snowflake.of(gameCategoryIDs.get(guild.getId().asLong())));}).block();
		newGame.setChannel(channel);
	}
	
	private static Game createGame(String name){
		Game result = null;
		
		switch (name) {
		case "RPC":
			result = new RockPaperScissors();
			break;
		}
		
		return result;
	}
	
	public static List<Game> games(){
		return new ArrayList<>(games);
	}
	
}
