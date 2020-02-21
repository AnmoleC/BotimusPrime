package game.tictactoe;

import discord4j.core.event.domain.message.MessageCreateEvent;
import game.Game;

public class TicTacToeGame extends Game {
	private char[][] board = {{'1', '2', '3'},{'4', '5', '6'},{'7', '8', '9'}}; 
	private char botMark;
	private char playerMark;
	private boolean gameOver = false;
	
	@Override
	public void begin() {
		int firstTurn = (int) (Math.random()*2);
		if(firstTurn == 1){
			botMark = 'X';
			playerMark = 'O';
			botTurn();
		}else{
			botMark = 'O';
			playerMark = 'X';
		}
		
		channel.createMessage("Pick a square\n" + toString()).block();
	}

	@Override
	public void subExecute(MessageCreateEvent event) {
		if(gameOver) return;
		
		String content = event.getMessage().getContent().orElse("0");
		if(content.length() != 1){
			channel.createMessage("Invalid Choice").block();
			return;
		}
		
		int choice = Integer.parseInt(content)-1;
		char spot = board[choice/3][choice%3];
		if(spot != botMark && spot != playerMark){
			board[choice/3][choice%3] = playerMark;
			if(gameOver() == 1){
				gameOver = true;
				channel.createMessage("You Won\n" + toString()).block();
				return;
			}

			botTurn();
			if(gameOver() == 2){
				gameOver = true;
				channel.createMessage("You lost\n" + toString()).block();
				return;
			}			
		}else{
			channel.createMessage("Pick an empty spot").block();
			return;			
		}
		channel.createMessage(toString()).block();
	}
	
	private void botTurn(){
		boolean validChoice = false;
		int choice;
		while(!validChoice){
			choice = (int)(Math.random() * 9);
			
			char spot = board[choice/3][choice%3];
			
			if(spot != botMark && spot != playerMark){
				validChoice = true;
				board[choice/3][choice%3] = botMark;
			}
			
			
		}
	}

	private int gameOver(){
		//0 Not Game Over
		//1 Player Win
		//2 Bot Win
		
		//Check Rows
		if(		board[0][0] == board[0][1] && board[0][0] == board[0][2]){
			if(board[0][0] == playerMark)
				return 1;
			else
				return 2;
		}else if(board[1][0] == board[1][1] && board[1][0] == board[1][2]){
			if(board[1][0] == playerMark)
				return 1;
			else
				return 2;
		}else if(board[2][0] == board[2][1] && board[2][0] == board[2][2]){
			if(board[2][0] == playerMark)
				return 1;
			else
				return 2;
			
		//Check Columns
		}else if(board[0][0] == board[1][0] && board[0][0] == board[2][0]){
			if(board[0][0] == playerMark)
				return 1;
			else
				return 2;
		}else if(board[0][1] == board[1][1] && board[0][1] == board[2][1]){
			if(board[0][1] == playerMark)
				return 1;
			else
				return 2;
		}else if(board[2][0] == board[2][1] && board[2][0] == board[2][2]){
			if(board[2][0] == playerMark)
				return 1;
			else
				return 2;
		
		//Check Diagonals
		}else if(board[0][0] == board[1][1] && board[0][0] == board[2][2]){
			if(board[0][0] == playerMark)
				return 1;
			else
				return 2;
		}else if(board[0][2] == board[1][1] && board[0][2] == board[2][0]){
			if(board[0][2] == playerMark)
				return 1;
			else
				return 2;
		}
		return 0;
	}
	
	@Override
	public String name() {
		return "TicTacToeGame";
	}

	@Override
	public Long timeout() {
		return 10000L;
	}
	
	@Override
	public String toString(){
		String result = "";
		result += board[0][0];
		result += '|';
		result += board[0][1];
		result += '|';
		result += board[0][2];
		result += "\n-+-+-\n";
		result += board[1][0];
		result += '|';
		result += board[1][1];
		result += '|';
		result += board[1][2];
		result += "\n-+-+-\n";
		result += board[2][0];
		result += '|';
		result += board[2][1];
		result += '|';
		result += board[2][2];
		result += '\n';
		return result;
	}

}
