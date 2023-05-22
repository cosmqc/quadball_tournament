package base;

public class Purchasable {
	int price;
	String name;

	public String getName() {
		return name;
	}

	public int getPrice() {
		return price;
	}
	
	public String toShopString() {
		return String.format("Name: %s \nPrice:%d", getName(), getPrice()); 
	}
}
