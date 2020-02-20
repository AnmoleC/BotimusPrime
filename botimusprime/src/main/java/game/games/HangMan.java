package game.games;

import discord4j.core.event.domain.message.MessageCreateEvent;
import game.Game;

public class HangMan extends Game {
	private char[] word;
	private char[] guess;
	private String mistakes = "";
	
	public HangMan(){
		word = HangmanWordList.getWord().toCharArray();
		guess = new char[word.length];
		for (int i = 0; i < word.length; i++) {
			guess[i] = '*';
		}
	}

	@Override
	public void subExecute(MessageCreateEvent event) {
		String message = "";
		boolean letterWasPresent = false;
		if(content.length() == 1){
			char letter = content.charAt(0);
			for (int i = 0; i < word.length; i++) {
				if(word[i] == letter){
					guess[i] = letter;
					letterWasPresent = true;
				}
			}
			if(!letterWasPresent){
				mistakes += letter;
			}
			
		}else{
			if(content.equals(new String(word))){
				guess = word;
			}else{
				message = "Incorrect. Try again\n";
			}
		}
		
		if(new String(word).equals(new String(guess))){
			message = "Congratulations! You guessed the word!\n";
		}
		String guessString = "";
		for (int i = 0; i < guess.length; i++) {
			guessString += guess[i];
		}
		channel.createMessage(message + guessString + "\nMistakes: " + mistakes).block();
	}

	@Override
	public String name() {
		return "Hangman";
	}

	@Override
	public Long timeout() {
		return 10000L;
	}

}
