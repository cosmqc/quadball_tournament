package jda178_lbl40_SportsTournament;

import java.util.Hashtable;
import java.util.Scanner;

public class GameEnvironment {
	
	Hashtable<String, Runnable> options;
	
	private void setupOptions() {
		options.put("quit", () -> System.exit(0));
	}

	public static void main(String[] args) {
		
		GameEnvironment game = new GameEnvironment();
		
		game.options = new Hashtable<String, Runnable>();
		game.setupOptions();
		
		Scanner inputScanner = new Scanner(System.in);
		System.out.println("Welcome to QUADBALL!!!");
		System.out.println("1, 2, 3, or quit");
		String userInput = inputScanner.nextLine();
		while (userInput != null) {
			// output hashtable keys as options
			// get whatever option is wanted, and .run() it
		}
		inputScanner.close();
	}

}
