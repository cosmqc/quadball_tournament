package base;

import java.util.Random;

public class Item extends Purchasable {

	String description;
	// Lists effects on offence, defence, stamina and speed respectively
	int[] effect = new int[4];
	
	public Item() {
		
	}
	public Item(String name, int[] effect, int price, String description) {
		this.name = name;
		this.effect = effect;
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
		return String.format("%s (%s, %s, %s, %s) %d %s", name, effect[0], effect[1], effect[2], effect[3], price, description);
	}
}
