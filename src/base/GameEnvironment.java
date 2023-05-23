package base;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * The Class GameEnvironment.
 * @author Jake Dalton
 * @author Leo Black
 */
public class GameEnvironment {
	
	/** The is running. */
	// hardcoded game options
	boolean isRunning = true;
	
	/** The num players. */
	public int numPlayers = 4;
	
	/** The num bench. */
	public int numBench = 5;
	
	/** The num shop athlete slots. */
	public int numShopAthleteSlots = 6;
	
	/** The num shop item slots. */
	public int numShopItemSlots = 6;
	
	/** The num matches. */
	public int numMatches = 5;
	
	/** The min athlete price. */
	public int minAthletePrice = 10;
	
	/** The max athlete price. */
	public int maxAthletePrice = 20;

	/** The max team name length. */
	public int maxTeamNameLength = 18; // dependant on size of boxes in Stadium
	
	/** The max athlete name length. */
	public int maxAthleteNameLength = 12; // dependant on size of box in GameOptions
	
	/** The difficulty. */
	// variable game options
	Difficulty difficulty;
	
	/** The total weeks. */
	int totalWeeks;
	
	/** The game over message. */
	String gameOverMessage;
	
	/** The names in use. */
	// keeps track of which player names and team names have been used so they don't repeat
	public List<String> namesInUse = new ArrayList<String>();
	
	/** The teams in use. */
	public List<String> teamsInUse = new ArrayList<String>();
	
	/** The random manager. */
	// Random gets referenced by the others, so Random must be on the top.
	public RandomManager randomManager = new RandomManager(this);
	
	/** The item manager. */
	public ItemManager itemManager = new ItemManager(this);
	
	/** The shop manager. */
	public ShopManager shopManager = new ShopManager(this);
	
	/** The match manager. */
	public MatchManager matchManager = new MatchManager(this);
	
	/** The current week. */
	// game state
	int currentWeek = 1;
	
	/** The player money. */
	public int playerMoney = 25;
	
	/** The player points. */
	public int playerPoints = 0;
	
	/** The player team. */
	public Team playerTeam = new Team(this);
	
	/** The enemy team. */
	public Team enemyTeam;
	
	/** The items in inventory. */
	// inventory state
	public List<Item> itemsInInventory = new ArrayList<Item>();
	
	/** The difficulty options. */
	public HashMap<String, Difficulty> difficultyOptions = new HashMap<String, Difficulty>();
	
	/**
 * Instantiates a new game environment.
 */
public GameEnvironment() {
		difficultyOptions.put("easy", new Difficulty("Easy", 17, 7, 1, 2, 5, 5, 10, 3));
		difficultyOptions.put("hard", new Difficulty("Hard", 14, 5, 2, 3, 5, 5, 20, 7));
	}
	
	/**
	 * Gets the current week.
	 *
	 * @return the current week
	 */
	public int getCurrentWeek() {
		return currentWeek;
	}

	/**
	 * Sets the total weeks.
	 *
	 * @param weeks the new total weeks
	 */
	public void setTotalWeeks(int weeks) {
		totalWeeks = weeks;
	}

	/**
	 * Gets the total weeks.
	 *
	 * @return the total weeks
	 */
	public int getTotalWeeks() {
		return totalWeeks;
	}
	
	/**
	 * Sets the difficulty.
	 *
	 * @param string the new difficulty
	 */
	public void setDifficulty(String string) {
		if (difficultyOptions.containsKey(string)) {
			this.difficulty = difficultyOptions.get(string);
		} else {
			System.out.println("Difficulty " + string + " doesn't exist");
		}
	}
	
	/**
	 * Gets the difficulty.
	 *
	 * @return the difficulty
	 */
	public Difficulty getDifficulty() {
		return difficulty;
	}
	
	/**
	 * Reset team stamina.
	 *
	 * @param team the team
	 */
	public void resetTeamStamina(Team team) {
		for (Athlete athlete: team.athletes) {
			athlete.resetStamina();
		}
	}
	
	/**
	 * Next week.
	 *
	 * @param moneyGained the money gained
	 */
	public void nextWeek(int moneyGained) {
		currentWeek += 1;
		playerMoney += moneyGained;
		shopManager.refreshShop(this);		
		matchManager.refreshMatches(this);
	}

}
