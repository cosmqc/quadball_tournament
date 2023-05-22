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

	// TODO: Item string in Shop needs to show all the info in the commented string.
	// needs button design?
	public String toString() {
		// return String.format("%s (%s, %s, %s, %s) %d %s", name, effect[0], effect[1],
		// effect[2], effect[3], price, description);
		return String.format("%s OFF%d DEF%d SPE%d STA%d", name, effect[0], effect[1], effect[2], effect[3]);
	}

	public String getClubString() {
		return String.format("Name: %s \n%s \nEffects: \nOffence: %s\nDefence: %s\nSpeed: %s\nStamina: %s", name,
				description, offence, defence, speed, stamina);
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
