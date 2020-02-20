package game.games;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HangmanWordList {
	private static List<String> words = new ArrayList<>();
	private static String filePath = System.getProperty("user.dir") + "\\resources\\hangman.txt";
	
	public static List<String> getWordList(){
		if (words.size() == 0){
			parseWords();
		}
		return new ArrayList<>(words);
	}
	
	public static String getWord(int index){
		if (words.size() == 0){
			parseWords();
		}
		return words.get(index);
	}
	
	public static String getWord(){
		if (words.size() == 0){
			parseWords();
		}
		int index =(int) (Math.random() * words.size());
		return words.get(index);
	}
	
	private static void parseWords(){
		try(FileReader reader = new FileReader(filePath);
			BufferedReader br = new BufferedReader(reader)) {
			String word;
			while((word = br.readLine()) != null){
				words.add(word);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(words.size() + " words");
	}
}
