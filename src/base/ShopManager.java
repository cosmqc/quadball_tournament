package base;

import java.util.ArrayList;
import java.util.List;

public class ShopManager {
	public GameEnvironment game;
	public List<Athlete> athletesInShop = new ArrayList<Athlete>();
	public List<Item> itemsInShop = new ArrayList<Item>();
	
	public ShopManager(GameEnvironment game) {
		this.game = game;
		refreshShop(game);
	}
	
	// TODO: could take an argument to pass to the athlete & item constructors to change difficulty
	public void refreshShop(GameEnvironment game) {
		// remove current purchasables
		athletesInShop.removeAll(athletesInShop);
		itemsInShop.removeAll(itemsInShop);
		for (int i = 0; i <= 3; i++) {
			athletesInShop.add(new Athlete(game));
		}
		for (int i = 0; i <= 3; i++) {
			itemsInShop.add(game.itemManager.randomItem());
		}
	}
	
}
