package gui;

import base.*;
import javax.swing.JFrame;

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
		game.shopManager.refreshShop(game);
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

	public static void main(String[] args) {
		// creates objects to save state, and to handle window management
		GameEnvironment game = new GameEnvironment();
		GUI gui = new GUI();

		gui.game = game;
		gui.launchGameOptions();
	}
}
