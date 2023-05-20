package base;

import java.util.List;
import java.util.ArrayList;

public class GameEnvironment {

	public List<String> namesInUse = new ArrayList<String>();
	public List<String> teamsInUse = new ArrayList<String>();
	
	// Random gets referenced by the others, so Random must be on the top.
	public RandomManager randomManager = new RandomManager(this);
	public ItemManager itemManager = new ItemManager(this);
	public ShopManager shopManager = new ShopManager(this);

	boolean isRunning = true;
	int currentWeek = 1;
	int totalWeeks;
	public int numPlayers = 4;
	public int numBench = 2;

	public int playerMoney = 200;
	public int playerPoints = 0;
	public Team playerTeam = new Team(this);
	
	// inventory state
//	public List<Athlete> athletesInTeam = new ArrayList<Athlete>();
	public List<Item> itemsInInventory = new ArrayList<Item>();

	public int getCurrentWeek() {
		return currentWeek;
	}

	public void setTotalWeeks(int weeks) {
		totalWeeks = weeks;
	}

	public int getTotalWeeks() {
		return totalWeeks;
	}

}
