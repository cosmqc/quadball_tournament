package base;

import java.util.NoSuchElementException;
import exceptions.TeamFullException;
import java.lang.Math;


public class Athlete extends Purchasable {

	public GameEnvironment game;
	String firstName;
	String nickName;
	String lastName;
	Position position;
	int offence;
	int defence;
	int speed;
	int stamina;
	int maxStamina;
	Item equippedItem;

	public Athlete(GameEnvironment game) {
		this.game = game;
		this.firstName = game.randomManager.randomValidName("src/resources/firstnames", 2738, game.namesInUse);
		this.lastName = game.randomManager.randomValidName("src/resources/lastnames", 1000, game.namesInUse);
		this.offence = game.randomManager.generateNum(1, 10);
		this.defence = game.randomManager.generateNum(1, 10);
		this.speed = game.randomManager.generateNum(1, 10);
		this.stamina = this.maxStamina = game.randomManager.generateNum(5, 10);
		
	}

	public Athlete(GameEnvironment game, String firstName, String lastName, int offence, int defence, int speed, int stamina,
			int maxStamina) {
		this.game = game;
		this.firstName = firstName;
		this.lastName = lastName;
		this.offence = offence;
		this.defence = defence;
		this.speed = speed;
		this.stamina = this.maxStamina = stamina;
	}

	public String getRawName() {
		return firstName + " " + lastName;
	}
	
	public String getName() {
		String name = firstName;
		if (nickName != null) {
			name += " \"" + nickName + "\"";
		}
		name += " " + lastName;
		return name;
	}
	
	public String getNickname() {
		return nickName;
	}
	
	public void setNickname(String nickName) {
		this.nickName = nickName;
	}
	public Position getPosition() {
		return position;
	}

	public int getRawOffence() {
		return offence;
	}

	public int getRawDefence() {
		return defence;
	}

	public int getRawSpeed() {
		return speed;
	}

	public int getRawStamina() {
		return stamina;
	}
	
	public int getRawMaxStamina() {
		return maxStamina;
	}
	

	public int getOffence() {
		int item_val = 0;
		if (getEquippedItem() != null) {
			item_val = getEquippedItem().effect[0];
		}
		return Math.max(0, getRawOffence() + item_val);
	}

	public int getDefence() {
		int item_val = 0;
		if (getEquippedItem() != null) {
			item_val = getEquippedItem().effect[1];
		}
		return Math.max(0, getRawDefence() + item_val);
	}

	public int getSpeed() {
		int item_val = 0;
		if (getEquippedItem() != null) {
			item_val = getEquippedItem().effect[2];
		}
		return Math.max(0, getRawSpeed() + item_val);
	}

	public int getStamina() {
		int item_val = 0;
		if (getEquippedItem() != null) {
			item_val = getEquippedItem().effect[3];
		}
		return Math.max(0, getRawStamina() + item_val);
	}
	
	public int getMaxStamina() {
		int item_val = 0;
		if (getEquippedItem() != null) {
			item_val = getEquippedItem().effect[3];
		}
		return Math.max(0, getRawMaxStamina() + item_val);
	}

	public void decreaseStamina(int staminaLoss) {
		stamina -= staminaLoss;
	}

	public void resetStamina() {
		stamina = maxStamina;
	}
	
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

	public Item getEquippedItem() {
		return equippedItem;
	}

	public Item equipItem(Item item) {
		Item oldItem = null;
		if (equippedItem != null) {
			oldItem = unequipItem();
		}
		equippedItem = item;
		return oldItem;
	}

	public Item unequipItem() {
		if (equippedItem == null) {
			throw new NoSuchElementException(String.format("Athlete %s has no item, so none can be removed.", getName()));
		}
		Item temp = equippedItem;
		equippedItem = null;
		return temp;
	}

	public void buy() {
		System.out.println(String.format("%s - Bought Athlete", getName()));
		try {
			game.playerTeam.addAthlete(this);
		} catch (TeamFullException e){
			// TODO: output error to player (popup?)
			System.out.println(e);
		}
		game.shopManager.athletesInShop.remove(this);
	}

	public void sell() {
		// TODO: Athlete/Item sold reappears in shop, but at a higher price
		System.out.println(String.format("%s - Sell Athlete", getName()));
		try {
			game.playerTeam.removeAthlete(this);
		} catch (IllegalArgumentException e) {
			// TODO: output error to player (popup?)
			System.out.println(e);
		}
	}
	
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
	
	public String toDebugString() {
		if (position != null) {
			return String.format("Athlete %s in position %s with stats (%d, %d, %d, %d)", getName(), position, offence,
					defence, speed, stamina);
		} else {
			return String.format("Athlete %s without a position with stats (%d, %d, %d, %d)", getName(), offence, defence,
					speed, stamina);
		}
	}

	public String toString() {
		return String.format("%s OFF%d DEF%d SPE%d STA%d", getName(), offence, defence, speed, stamina);
	}
}
