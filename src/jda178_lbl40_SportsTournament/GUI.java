package jda178_lbl40_SportsTournament;

public class GUI {
	int x = 100;
	int y = 100;
	int w = 900;
	int h = 650;
	
	public GameEnvironment game;
	
	public void launchGameOptions() {
		GameOptionsWindow gameOptionWindow = new GameOptionsWindow(this);
	}
	
	public void closeGameOptions(GameOptionsWindow gameOptionWindow) {
		gameOptionWindow.closeWindow();
		launchDebugWindow();
	}
	
	public void launchDebugWindow() {
		DebugWindow debugWindow = new DebugWindow(this);
	}
	
	public void closeDebugWindow(DebugWindow debugWindow) {
		debugWindow.closeWindow();
		launchGameOptions();
	}
	
	public void closeGameOptions(GameOptionsWindow gameOptionWindow, String teamName) {
		gameOptionWindow.closeWindow();
		launchBaseWindow();
	}
	
	public void launchBaseWindow() {
		BaseWindow baseWindow = new BaseWindow(this);
	}
	
	public void goToShop(BaseWindow baseWindow) {
		baseWindow.closeWindow();
		Shop shop = new Shop(this);
	}
	
	public void returnFromShop(Shop shop) {
		shop.closeWindow();
		launchBaseWindow();
	}

	
	public static void main(String[] args) {
		// creates objects to save state, and to handle window management
		GameEnvironment game = new GameEnvironment();
		GUI gui = new GUI();
		
		gui.game = game;
		gui.launchGameOptions();
	}
}
