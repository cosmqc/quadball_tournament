package base;

import exceptions.NotEnoughMoneyException;

public class Item extends Purchasable {
	GameEnvironment game;
	String description;
	Athlete equippedTo;
	public int offence;
	public int defence;
	public int speed;
	public int stamina;
	// Lists effects on offence, defence, stamina and speed respectively
	int[] effect = new int[4];

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

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public int[] getEffect() {
		return effect;
	}

	public String toString() {
		return name;
	}

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

	public void sell() {
		game.itemsInInventory.remove(this);
		game.playerMoney += getPrice();
	}
}
