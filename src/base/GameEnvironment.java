package base;

import java.util.List;
import java.util.ArrayList;
public class GameEnvironment {
	
	public List<String> namesInUse = new ArrayList<String>();
	public ItemManager itemManager = new ItemManager(this);
	boolean isRunning = true;
	int currentWeek = 1;
	int totalWeeks;
	
	// store state
	List<Athlete> athletesInShop = new ArrayList<Athlete>();
	List<Item> itemsInShop = new ArrayList<Item>();
	
	public int getCurrentWeek() {
		return currentWeek;
	}
	
	public void setTotalWeeks(int weeks) {
		totalWeeks = weeks;
	}
	
	public int getTotalWeeks() {
		return totalWeeks;
	}

}
