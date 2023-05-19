package base;

import java.util.Random;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.*;
import java.lang.Math;

enum Position {
	CHASER, BEATER, KEEPER, SEEKER
}

public class Athlete extends Purchasable {

	public GameEnvironment game;
	String name;
	String nickname = null;
	Position position;
	int offence;
	int defence;
	int speed;
	int stamina;
	int maxStamina;
	Item equippedItem;

	public Athlete(GameEnvironment game) {
		this.game = game;
		this.name = generateValidName();
		this.offence = generateStat(1, 10);
		this.defence = generateStat(1, 10);
		this.speed = generateStat(1, 10);
		this.stamina = this.maxStamina = generateStat(5, 10);
		this.equippedItem = game.itemManager.randomItem();
	}

	public Athlete(GameEnvironment game, String name, int offence, int defence, int speed, int stamina,
			int maxStamina) {
		this.game = game;
		this.name = name;
		this.offence = offence;
		this.defence = defence;
		this.speed = speed;
		this.stamina = this.maxStamina = stamina;
	}


	String generateValidName() {
		String firstName = game.randomManager.randomValidName("src/resources/firstnames", 2738, game.namesInUse);
		String lastName = game.randomManager.randomValidName("src/resources/lastnames", 1000, game.namesInUse);
		return firstName + " " + lastName;
	}

	private int generateStat(int lower, int upper) {
		int stat = new Random().nextInt(lower, upper);
		return stat;
	}

	public String toDebugString() {
		if (position != null) {
			return String.format("Athlete %s in position %s with stats (%d, %d, %d, %d)", name, position, offence,
					defence, speed, stamina);
		} else {
			return String.format("Athlete %s without a position with stats (%d, %d, %d, %d)", name, offence, defence,
					speed, stamina);
		}
	}

	public String toString() {
		return String.format("%s OFF%d DEF%d SPE%d STA%d", name, offence, defence, speed, stamina);
	}

	public String getName() {
		return name;
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
		return stamina;
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
		return maxStamina;
	}

	public void decreaseStamina(int staminaLoss) {
		stamina -= staminaLoss;
	}

	public void resetStamina() {
		stamina = maxStamina;
	}

	public Item getEquippedItem() {
		return equippedItem;
	}

	public void equipItem(Item item) {
		if (equippedItem != null) {
			unequipItem();
		}
		equippedItem = item;
	}

	public void unequipItem() {
		if (equippedItem == null) {
			throw new NoSuchElementException(String.format("Athlete %s has no item, so none can be removed.", name));
		}
		equippedItem = null;
	}

	public void buy() {
		System.out.println(String.format("%s - Bought Athlete", name));
		game.athletesInTeam.add(this);
		game.shopManager.athletesInShop.remove(this);
	}

	public void sell() {
		// TODO: Currently, if an athlete is drafted to shop, they cannot be bought
		// back. Thoughts?
		// could add them to be bought back, but increase their price by 25% or
		// something
		System.out.println(String.format("%s - Sell Athlete", name));
		game.athletesInTeam.remove(this);
	}
	
	public String toClubString() {
		String text = "";
		text += "Name: " + getName();
		text += "\nOffence: " + getOffence();
		if (getOffence() != getRawOffence()) {
			text += " (";
			if (equippedItem.offence > 0) { text += "+"; }
			text += equippedItem.offence + ")";
		}
		text += "\nDefence: " + getDefence();
		if (getDefence() != getRawDefence()) {
			text += " (";
			if (equippedItem.defence > 0) { text += "+"; }
			text += equippedItem.defence + ")";
		}
		text += "\nSpeed: " + getSpeed();
		if (getSpeed() != getRawSpeed()) {
			text += " (";
			if (equippedItem.speed > 0) { text += "+"; }
			text += equippedItem.speed + ")";
		}
		text += "\nStamina: " + getStamina() + "/" + getMaxStamina();
		if (getStamina() != getRawStamina()) {
			text += " (";
			if (equippedItem.stamina > 0) { text += "+"; }
			text += equippedItem.stamina + ")";
		}
		text += "\n\nEquipped Item: " + equippedItem.name;
		text += "\n" + equippedItem.description;
		return text;
//		Name: %s \nOffence: %s \nDefence: %s \nSpeed: %s \nStamina: %s/%s"
	}
}
