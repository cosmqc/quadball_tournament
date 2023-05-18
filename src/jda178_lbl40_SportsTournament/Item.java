package jda178_lbl40_SportsTournament;

public class Item {

	String name;
	String description;
	int price;
	// Lists effects on offence, defence, stamina and speed respectively
	int[] effect = new int[4];
	
	public Item(String name, String description, int price, int[] effect) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.effect = effect;
	}
	
	public String getName() {
        return name;
    }
	public String getDescription() {
		return description;
	}
	public int getPrice() {
		return price;
	}
	public int[] getEffect() {
		return effect;
	}
}
