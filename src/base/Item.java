package base;

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

	public void buy() {
		System.out.println(String.format("%s - Bought Item", name));
		game.itemsInInventory.add(this);
		game.shopManager.itemsInShop.remove(this);
	}

	public void sell() {
		// TODO: Currently, if an item is sold to shop, it cannot be bought back.
		// Thoughts?
		System.out.println(String.format("%s - Sell Item", name));
		game.itemsInInventory.remove(this);
	}
}
