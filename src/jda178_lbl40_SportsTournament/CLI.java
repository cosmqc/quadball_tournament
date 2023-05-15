package jda178_lbl40_SportsTournament;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CLI {
	Map<String, Runnable> options = new HashMap<String, Runnable>();
	
	private void setupOptions() {
		options.put("quit", () -> System.exit(0));
		options.put("run", () -> System.exit(0));
		options.put("jump", () -> System.exit(0));
	}

	private void showOptions() {
		for (String option : options.keySet()) {
			// output hashtable keys as options
			// get whatever option is wanted, and .run() it
			System.out.printf(" > %s%n", option);
		}
	}
	
	private void runOption(Scanner inputScanner) {
		String userInput = inputScanner.nextLine();
		while (!options.containsKey(userInput)) {
			System.out.printf("Invalid option, please try again.%nPlease choose from below:%n");
			showOptions();
			userInput = inputScanner.nextLine();
		}
		options.get(userInput).run();
	}
	public void run(GameEnvironment game) {
		setupOptions();
		game.isRunning = true;
		Scanner inputScanner = new Scanner(System.in);
		
		System.out.println("Welcome to QUADBALL!!!");
		while (game.isRunning) {
			showOptions();
			runOption(inputScanner);
		}
		inputScanner.close();
	}
}
