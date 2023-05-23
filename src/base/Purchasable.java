package base;

/**
 * The Class Purchasable.
 * @author Jake Dalton
 * @author Leo Black
 */
public class Purchasable {
	
	/** The price. */
	int price;
	
	/** The name. */
	String name;

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the price.
	 *
	 * @return the price
	 */
	public int getPrice() {
		return price;
	}
	
	/**
	 * To shop string.
	 *
	 * @return the string
	 */
	public String toShopString() {
		return String.format("Name: %s \nPrice:%d", getName(), getPrice()); 
	}
}
