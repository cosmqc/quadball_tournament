package base;

import exceptions.NotEnoughMoneyException;

/**
 * The Class Item.
 * @author Jake Dalton
 * @author Leo Black
 */
public class Item extends Purchasable {
	
	/** The game. */
	GameEnvironment game;
	
	/** The description. */
	String description;
	
	/** The equipped to. */
	Athlete equippedTo;
	
	/** The offence. */
	public int offence;
	
	/** The defence. */
	public int defence;
	
	/** The speed. */
	public int speed;
	
	/** The stamina. */
	public int stamina;
	
	/** The effect. */
	// Lists effects on offence, defence, stamina and speed respectively
	int[] effect = new int[4];

	/**
	 * Instantiates a new item.
	 *
	 * @param game the game
	 * @param name the name
	 * @param effect the effect
	 * @param price the price
	 * @param description the description
	 */
	public Item(GameEnvironment game, String name, int[] effect, int price, String description) {
		this.game = game;
		this.name = name;
		this.effect = effect;
		this.offence = effect[0];
		this.defence = effect[1];
		this.speed = effect[2];
		this.stamina = effect[3];
		this.price = price;
		this.description = description;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Gets the effect.
	 *
	 * @return the effect
	 */
	public int[] getEffect() {
		return effect;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	public String toString() {
		return name;
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
		text += "\n" + getDescription();
		text += "\n\nEffects: ";
		text += formatStat("Offence", offence);
		text += formatStat("Defence", defence);
		text += formatStat("Speed", speed);
		text += formatStat("Stamina", stamina);
		return text;
	}

//	+2 Offence
	
	/**
 * Format stat.
 *
 * @param statString the stat string
 * @param stat the stat
 * @return the string
 */
String formatStat(String statString, int stat) {
		String text = "";
		if (stat != 0) {
			text += "\n";
			if (stat > 0) {
				text += "+";
			}
			text += stat + " " + statString;
		}
		return text;
	}
	
	/**
	 * Buy.
	 *
	 * @throws NotEnoughMoneyException the not enough money exception
	 */
	public void buy() throws NotEnoughMoneyException {
		if (game.playerMoney >= getPrice()) {
			game.itemsInInventory.add(this);
			game.shopManager.itemsInShop.remove(this);
			game.playerMoney -= getPrice();
		} else {
			throw new NotEnoughMoneyException(game,
					String.format("You don't have enough money to buy the %s", getName()));
		}
	}

	/**
	 * Sell.
	 */
	public void sell() {
		game.itemsInInventory.remove(this);
		game.playerMoney += getPrice();
	}
}
