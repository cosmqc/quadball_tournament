package base;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public class GameEnvironment {
	
	// hardcoded game options
	boolean isRunning = true;
	public int numPlayers = 4;
	public int numBench = 5;
	public int numShopAthleteSlots = 6;
	public int numShopItemSlots = 6;
	public int numMatches = 5;
	public int minAthletePrice = 10;
	public int maxAthletePrice = 20;
	public int maxTeamNameLength = 18; // dependant on size of boxes in Stadium
	public int maxAthleteNameLength = 12; // dependant on size of box in GameOptions
	
	// variable game options
	Difficulty difficulty;
	int totalWeeks;
	String gameOverMessage;
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
	public int playerMoney = 25;
	public int playerPoints = 0;
	public Team playerTeam = new Team(this);
	public Team enemyTeam;
	
	// inventory state
	public List<Item> itemsInInventory = new ArrayList<Item>();
	
	public HashMap<String, Difficulty> difficultyOptions = new HashMap<String, Difficulty>();
	
//	public int moneyOnWin;
//	public int moneyOnLoss;
//	public int staminaLossOnWin;
//	public int staminaLossOnLoss;
//	public int playerAvgStat;
//	public int enemyAvgStat;
//	public int pointsOnWin;
//	public int pointsOnLoss;
	
	public GameEnvironment() {
		difficultyOptions.put("easy", new Difficulty("Easy", 17, 7, 1, 2, 5, 5, 10, 3));
		difficultyOptions.put("hard", new Difficulty("Hard", 14, 5, 2, 3, 5, 5, 20, 7));
	}
	public int getCurrentWeek() {
		return currentWeek;
	}

	public void setTotalWeeks(int weeks) {
		totalWeeks = weeks;
	}

	public int getTotalWeeks() {
		return totalWeeks;
	}
	
	public void setDifficulty(String string) {
		if (difficultyOptions.containsKey(string)) {
			this.difficulty = difficultyOptions.get(string);
		} else {
			System.out.println("Difficulty " + string + " doesn't exist");
		}
	}
	
	public Difficulty getDifficulty() {
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
