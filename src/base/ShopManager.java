package base;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class ShopManager.
 * @author Jake Dalton
 * @author Leo Black
 */
public class ShopManager {
	
	/** The game. */
	public GameEnvironment game;
	
	/** The athletes in shop. */
	public List<Athlete> athletesInShop = new ArrayList<Athlete>();
	
	/** The items in shop. */
	public List<Item> itemsInShop = new ArrayList<Item>();

	/**
	 * Instantiates a new shop manager.
	 *
	 * @param game the game
	 */
	public ShopManager(GameEnvironment game) {
		this.game = game;
		refreshShop(game);
	}

	/**
	 * Refresh shop.
	 *
	 * @param game the game
	 */
	// change difficulty
	public void refreshShop(GameEnvironment game) {
		// remove current purchasables
		athletesInShop.removeAll(athletesInShop);
		itemsInShop.removeAll(itemsInShop);
		for (int i = 0; i <= game.numShopAthleteSlots; i++) {
			athletesInShop.add(new Athlete(game));
		}
		for (int i = 0; i <= game.numShopItemSlots; i++) {
			itemsInShop.add(game.itemManager.randomItem());
		}
	}

}
