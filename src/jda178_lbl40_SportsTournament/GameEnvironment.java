package jda178_lbl40_SportsTournament;

public class GameEnvironment {
	
	boolean isRunning = true;
	
	public static void main(String[] args) {
		GameEnvironment game = new GameEnvironment();
		CLI ui = new CLI();
		ui.run(game);
	}
}
