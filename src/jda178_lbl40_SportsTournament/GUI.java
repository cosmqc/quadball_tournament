package jda178_lbl40_SportsTournament;

public class GUI {
	int x = 100;
	int y = 100;
	int w = 900;
	int h = 650;
	
	public GameEnvironment game;
	
	public void launchGameOptions() {
		GameOptions gameOptionWindow = new GameOptions(this);
	}
	
	public void closeGameOptions(GameOptions gameOptionWindow) {
		gameOptionWindow.closeWindow();
		launchMainWindow();
	}
	
	public void launchMainWindow() {
		BaseWindow baseWindow = new BaseWindow(this);
	}
//	
//	public void closeMainWindow() {
//		SetupScreen gameOptionWindow = new SetupScreen();
//	}
	
	public static void main(String[] args) {
		// creates objects to save state, and to handle window management
		GameEnvironment game = new GameEnvironment();
		GUI gui = new GUI();
		
		gui.launchGameOptions();
	}
}
