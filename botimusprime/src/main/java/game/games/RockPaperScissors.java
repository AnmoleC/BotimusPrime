package game.games;

import java.util.Random;

import discord4j.core.event.domain.message.MessageCreateEvent;
import game.Game;

public class RockPaperScissors extends Game {
	Random r = new Random();
	private int botScore = 0;
	private int playerScore = 0;
	
	@Override
	public void begin() {
		
	}
	
	@Override
	public void subExecute(MessageCreateEvent event) {
		String message = "";
		int choice = convertMessage(content);
		
		if(choice == 0){
			channel.createMessage("Invalid choice").block();
			return;
		}
		
		int botChoice = r.nextInt(3) + 1;
		if(botChoice == 1){
			message += "Bot picked Rock!\n";
		}else if(botChoice == 2){
			message += "Bot picked Paper!\n";
		}else if(botChoice == 3){
			message += "Bot picked Scissor!\n";
		}else{
			message += "Bot fucked up\n";
		}
		
		if (botChoice == choice){
			message += "It's a tie\n";
		}else if(	botChoice == 1 && choice == 3
				 || botChoice == 2 && choice == 1
				 || botChoice == 3 && choice == 2){
			message += "Bot wins\n";
			botScore++;
		}else if(	choice == 1 && botChoice == 3
				 || choice == 2 && botChoice == 1
				 || choice == 3 && botChoice == 2 ){
			message += "You won\n";
			playerScore++;
		}
		
		message += "Player - " + playerScore + "\nBot - " + botScore;		
		channel.createMessage(message).block();
	}

	@Override
	public String name() {
		return "Rock Paper Scissors";
	}

	private int convertMessage(String message){
		int choice = 0; 
		switch (message) {
		case "Rock":
		case "rock":
			choice = 1;
			break;

		case "Paper":
		case "paper":
			choice = 2;
			break;

		case "Scissor":
		case "scissor":
		case "Scissors":
		case "scissors":
			choice = 3;
			break;
		}
		return choice;
	}

	@Override
	public Long timeout() {
		return 20000L;
	}
}
