package gui;

import base.*;
import javax.swing.JFrame;

@SuppressWarnings("unused")
public class GUI {
	public int x = 100;
	public int y = 100;
	int w = 900;
	int h = 650;

	public GameEnvironment game;

	public void refresh(JFrame frame) {
		frame.revalidate();
		frame.repaint();
	}

	public void launchGameOptions() {
		GameOptionsWindow gameOptionWindow = new GameOptionsWindow(this);
	}

	public void closeGameOptions(GameOptionsWindow gameOptionWindow) {
		gameOptionWindow.closeWindow();
		game.shopManager.refreshShop(game);
		game.matchManager.refreshMatches(game);
		launchBaseWindow();
	}

	public void launchBaseWindow() {
		BaseWindow baseWindow = new BaseWindow(this);
	}

	public void launchShopWindow(BaseWindow baseWindow) {
		baseWindow.closeWindow();
		ShopWindow shopWindow = new ShopWindow(this);
	}

	public void closeShopWindow(ShopWindow shopWindow) {
		shopWindow.closeWindow();
		launchBaseWindow();
	}

	public void launchClubWindow(BaseWindow baseWindow) {
		baseWindow.closeWindow();
		ClubWindow clubWindow = new ClubWindow(this);
	}

	public void closeClubWindow(ClubWindow clubWindow) {
		clubWindow.closeWindow();
		launchBaseWindow();
	}
	
	public void launchStadiumWindow(BaseWindow baseWindow) {
		baseWindow.closeWindow();
		StadiumWindow stadiumWindow = new StadiumWindow(this);
	}
	
	public void closeStadiumWindow(StadiumWindow stadiumWindow) {
		stadiumWindow.closeWindow();
		launchBaseWindow();
	}
	
	public void launchRestWeekWindow(BaseWindow baseWindow) {
		baseWindow.closeWindow();
		RestWeekWindow restWeekWindow = new RestWeekWindow(this);
	}
	
	public void closeRestWeekWindow(RestWeekWindow restWeekWindow) {
		restWeekWindow.closeWindow();
		launchBaseWindow();
	}
	
	public void launchMatchWindow(StadiumWindow stadiumWindow) {
		stadiumWindow.closeWindow();
		MatchWindow matchWindow = new MatchWindow(this);
	}
	
	public void closeMatchWindow(MatchWindow matchWindow) {
		matchWindow.closeWindow();
		launchBaseWindow();
	}
	
	public void closeBaseWindow() {
		FinalWindow finalWindow = new FinalWindow(this);
	}
	
	public void closeBaseWindow(BaseWindow baseWindow) {
		baseWindow.closeWindow();
		FinalWindow finalWindow = new FinalWindow(this);
	}

	public static void main(String[] args) {
		// creates objects to save state, and to handle window management
		GameEnvironment game = new GameEnvironment();
		GUI gui = new GUI();

		gui.game = game;
		gui.launchGameOptions();
	}
}
