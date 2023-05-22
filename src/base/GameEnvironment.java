package base;

import java.util.List;
import java.util.ArrayList;

public class GameEnvironment {
	
	// hardcoded game options
	boolean isRunning = true;
	public int numPlayers = 4;
	public int numBench = 2;
	public int numShopAthleteSlots = 6;
	public int numShopItemSlots = 6;
	public int numMatches = 5;
	public int minAthletePrice = 10;
	public int maxAthletePrice = 20;
	public int maxTeamNameLength = 18; // dependant on size of boxes in Stadium
	public int maxAthleteNameLength = 21; // dependant on size of box in Stadium when debug string added
	
	
	// variable game options
	String difficulty;
	int totalWeeks;

	// keeps track of which player names and team names have been used so they don't repeat
	public List<String> namesInUse = new ArrayList<String>();
	public List<String> teamsInUse = new ArrayList<String>();
	
	// Random gets referenced by the others, so Random must be on the top.
	public RandomManager randomManager = new RandomManager(this);
	public ItemManager itemManager = new ItemManager(this);
	public ShopManager shopManager = new ShopManager(this);
	public MatchManager matchManager = new MatchManager(this);
	
	// game state
	int currentWeek = 1;
	// TODO: Implement money
	public int playerMoney = 10000;
	public int playerPoints = 0;
	public Team playerTeam = new Team(this);
	public Team enemyTeam;
	
	// inventory state
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
	
	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}
	
	public String getDifficulty() {
		return difficulty;
	}
	
	public void resetTeamStamina(Team team) {
		for (Athlete athlete: team.athletes) {
			athlete.resetStamina();
		}
	}
	
	public void nextWeek(int moneyGained) {
		currentWeek += 1;
		if (currentWeek > totalWeeks) {
			// TODO: End of game!!
		}
		playerMoney += moneyGained;
		shopManager.refreshShop(this);		
		matchManager.refreshMatches(this);
	}

}
