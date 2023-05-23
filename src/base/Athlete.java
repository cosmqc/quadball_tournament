package base;
/**
 * @author Jake Dalton
 * @author Leo Black
 **/


import java.util.NoSuchElementException;
import exceptions.TeamFullException;
import exceptions.NotEnoughMoneyException;
import java.lang.Math;

/**
 * The Class Athlete.
 *
 * @author Jake Dalton
 * @author Leo Black
 */
public class Athlete extends Purchasable {

	/** The game. */
	public GameEnvironment game;
	
	/** The first name. */
	String firstName;
	
	/** The nick name. */
	String nickName;
	
	/** The last name. */
	String lastName;
	
	/** The offence. */
	int offence;
	
	/** The defence. */
	int defence;
	
	/** The speed. */
	int speed;
	
	/** The stamina. */
	int stamina;
	
	/** The max stamina. */
	int maxStamina;
	
	/** The equipped item. */
	Item equippedItem;
	
	/** The was injured previously. */
	boolean wasInjuredPreviously = false;

	/**
	 * Generates an athlete object given the GameEnvironment game (to track gamestate), and the current set difficulty.
	 *
	 * @param game the game
	 * @param difficulty the difficulty
	 * @return Randomly generated Athlete object with stats based on the given difficulty
	 */
	// randomly generated
	public Athlete(GameEnvironment game, Difficulty difficulty) {
		this.game = game;
		
		// ensures length of full name will fit on a line
		this.firstName = game.randomManager.randomValidName("/resources/firstnames", 685, game.namesInUse);
		this.lastName = game.randomManager.randomValidName("/resources/lastnames", 502, game.namesInUse);
		this.offence = game.randomManager.generateNum(game.difficulty.playerAvgStat-2, game.difficulty.playerAvgStat+2);
		this.defence = game.randomManager.generateNum(game.difficulty.playerAvgStat-2, game.difficulty.playerAvgStat+2);
		this.speed = game.randomManager.generateNum(game.difficulty.playerAvgStat-2, game.difficulty.playerAvgStat+2);
		this.stamina = this.maxStamina = Math.max(0, Math.min(10, game.randomManager.generateNum(game.difficulty.playerAvgStat-2, game.difficulty.playerAvgStat+2)));
		this.price = game.randomManager.generateNum(game.minAthletePrice, game.maxAthletePrice);

	}
	
	/**
	 * Generates an athlete object given the GameEnvironment game (to track gamestate).
	 * This constructor is used at the start of the game to generate athletes when a difficulty hasn't been set.
	 *
	 * @param game the game
	 * @return Randomly generated Athlete object.
	 */
	public Athlete(GameEnvironment game) {
		this.game = game;
		
		// ensures length of full name will fit on a line
		this.firstName = game.randomManager.randomValidName("src/resources/firstnames", 685, game.namesInUse);
		this.lastName = game.randomManager.randomValidName("src/resources/lastnames", 502, game.namesInUse);
		while (getRawName().length() > game.maxAthleteNameLength) {
			this.firstName = game.randomManager.randomValidName("src/resources/firstnames", 685, game.namesInUse);
			this.lastName = game.randomManager.randomValidName("src/resources/lastnames", 502, game.namesInUse);
		}
		this.offence = game.randomManager.generateNum(1,10);
		this.defence = game.randomManager.generateNum(1,10);
		this.speed = game.randomManager.generateNum(1,10);
		this.stamina = this.maxStamina = game.randomManager.generateNum(5,10);
		this.price = game.randomManager.generateNum(game.minAthletePrice, game.maxAthletePrice);

	}

/**
 * Gets the raw name.
 *
 * @return Athletes first and last name - without nickname
 */
	public String getRawName() {
		return firstName + " " + lastName;
	}

/**
 * Gets the name.
 *
 * @return Athletes first and last name - with nickname (if it has one)
 */
	public String getName() {
		String name = firstName;
		if (nickName != null) {
			name += " \"" + nickName + "\"";
		}
		name += " " + lastName;
		return name;
	}
	
	/**
	 * Gets the nickname.
	 *
	 * @return the nickname
	 */
	public String getNickname() {
		return nickName;
	}
	
	/**
	 * Sets the nickname.
	 *
	 * @param nickName the new nickname
	 */
	public void setNickname(String nickName) {
		this.nickName = nickName;
	}

	/**
	 * Gets the raw offence.
	 *
	 * @return the raw offence
	 */
	public int getRawOffence() {
		return offence;
	}

	/**
	 * Gets the raw defence.
	 *
	 * @return the raw defence
	 */
	public int getRawDefence() {
		return defence;
	}

	/**
	 * Gets the raw speed.
	 *
	 * @return the raw speed
	 */
	public int getRawSpeed() {
		return speed;
	}

	/**
	 * Gets the raw stamina.
	 *
	 * @return the raw stamina
	 */
	public int getRawStamina() {
		return stamina;
	}

	/**
	 * Gets the max stamina after the item has been applied.
	 *
	 * @return maxStamina
	 */
	public int getRawMaxStamina() {
		return maxStamina;
	}

	/**
	 * Gets the offence after the item has been applied.
	 *
	 * @return offence
	 */
	public int getOffence() {
		int item_val = 0;
		if (getEquippedItem() != null) {
			item_val = getEquippedItem().effect[0];
		}
		return Math.max(0, getRawOffence() + item_val);
	}

	/**
	 * Gets the defence after the item has been applied.
	 *
	 * @return defence
	 */
	public int getDefence() {
		int item_val = 0;
		if (getEquippedItem() != null) {
			item_val = getEquippedItem().effect[1];
		}
		return Math.max(0, getRawDefence() + item_val);
	}

	/**
	 * Gets the speed after the item has been applied.
	 *
	 * @return speed
	 */
	public int getSpeed() {
		int item_val = 0;
		if (getEquippedItem() != null) {
			item_val = getEquippedItem().effect[2];
		}
		return Math.max(0, getRawSpeed() + item_val);
	}

	/**
	 * Gets the stamina after the item has been applied.
	 *
	 * @return stamina
	 */
	public int getStamina() {
		int item_val = 0;
		if (getEquippedItem() != null) {
			item_val = getEquippedItem().effect[3];
		}
		return Math.max(0, getRawStamina() + item_val);
	}

	/**
	 * Gets the max stamina.
	 *
	 * @return max stamina
	 */
	public int getMaxStamina() {
		int item_val = 0;
		if (getEquippedItem() != null) {
			item_val = getEquippedItem().effect[3];
		}
		return Math.max(0, getRawMaxStamina() + item_val);
	}

	/**
	 * Decrease stamina.
	 *
	 * @param hasWon boolean checking whether or not the athlete won their matchup
	 */
	public void decreaseStamina(boolean hasWon) {
		int staminaLoss = game.randomManager.randomStaminaLoss(hasWon);
		stamina = Math.max(0,  stamina - staminaLoss);
	}

	/**
	 * Reset stamina back to maximum.
	 */
	public void resetStamina() {
		stamina = maxStamina;
	}

	/**
	 * Boost stats.
	 */
	public void boostStats() {
		if (offence < 9) {
			offence += 1;
		}
		if (defence < 9) {
			defence += 1;
		}
		if (speed < 9) {
			speed += 1;
		}
	}

	/**
	 * Gets the equipped item.
	 *
	 * @return the equipped item
	 */
	public Item getEquippedItem() {
		return equippedItem;
	}

	/**
	 * Equip item.
	 *
	 * @param the item to equip
	 * @return the item that was already equipped, or null if none.
	 */
	public Item equipItem(Item item) {
		Item oldItem = null;
		if (equippedItem != null) {
			oldItem = unequipItem();
		}
		equippedItem = item;
		return oldItem;
	}

	/**
	 * Unequip item.
	 *
	 * @return the item that got removed.
	 */
	public Item unequipItem() {
		if (equippedItem == null) {
			throw new NoSuchElementException(
					String.format("Athlete %s has no item, so none can be removed.", getName()));
		}
		Item temp = equippedItem;
		equippedItem = null;
		return temp;
	}

	/**
	 * Buy this athlete from the shop and add to the players team.
	 *
	 * @throws NotEnoughMoneyException the not enough money exception
	 * @throws TeamFullException the team full exception
	 */
	public void buy() throws NotEnoughMoneyException, TeamFullException {
		if (game.playerMoney >= getPrice()) {
			game.playerTeam.addAthlete(this);
			game.shopManager.athletesInShop.remove(this);
			game.playerMoney -= getPrice();
		} else {
			throw new NotEnoughMoneyException(game, String.format("You don't have enough money to buy %s", getName()));
		}
	}

	/**
	 * Sell this athlete to the shop, removing from players team.
	 */
	public void sell() {
		game.playerTeam.removeAthlete(this);
		game.playerMoney += getPrice();
	}
	
	/**
	 * To shop string.
	 *
	 * @return the string
	 */
	public String toShopString() {
		String text = "";
		text += "$" + getPrice();
		text += "\n" + getName();
		text += formatStat("Offence", getOffence(), getRawOffence());
		text += formatStat("Defence", getDefence(), getRawDefence());
		text += formatStat("Speed", getSpeed(), getRawSpeed());
		text += formatStat("Stamina", getStamina(), getRawStamina());
		return text;
	}
	
	/**
	 * To club string.
	 *
	 * @return the string
	 */
	public String toClubString() {
		String text = "";
		text += "Name: " + getName();
		text += formatStat("Offence", getOffence(), getRawOffence());
		text += formatStat("Defence", getDefence(), getRawDefence());
		text += formatStat("Speed", getSpeed(), getRawSpeed());
		text += "\nStamina: " + getStamina() + "/" + getMaxStamina();
		if (getStamina() != getRawStamina()) {
			text += " (";
			int itemModifer = getStamina() - getRawStamina();
			if (itemModifer > 0) {
				text += "+";
			}
			text += itemModifer + ")";
		}
		if (equippedItem != null) {
			text += "\n\nEquipped Item: " + equippedItem.name;
			text += "\n" + equippedItem.description;
		}
		return text;
	}

	/**
	 * Format stat.
	 *
	 * @param stat the stat
	 * @param curr the curr
	 * @param rawCurr the raw curr
	 * @return the string
	 */
	String formatStat(String stat, int curr, int rawCurr) {
		String text = "\n" + stat + ": " + curr;
		if (curr != rawCurr) {
			text += " (";
			int itemModifer = curr - rawCurr;
			if (itemModifer > 0) {
				text += "+";
			}
			text += itemModifer + ")";
		}
		return text;
	}

	/**
	 * To debug string.
	 *
	 * @return the string
	 */
	public String toDebugString() {
		return String.format("OFF%d DEF%d SPE%d STA%d/%d", getOffence(), getDefence(), getSpeed(), getStamina(), getMaxStamina());
	}
	
	/**
	 * To shop list string.
	 *
	 * @return the string
	 */
	public String toShopListString() {
		return String.format("%s - $%d", getName(), getPrice());
	}
	
	/**
	 * To stadium string.
	 *
	 * @return the string
	 */
	public String toStadiumString() {
		if (nickName != null) {
			return nickName + " " + toDebugString();
		} else {
			return getRawName() + " " + toDebugString();
		}
	}
	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	public String toString() {
		return getName();
	}
}
