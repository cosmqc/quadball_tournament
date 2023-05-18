package base;

import java.util.List;
import java.util.ArrayList;

public class GameEnvironment {

	public List<String> namesInUse = new ArrayList<String>();
	public ItemManager itemManager = new ItemManager(this);
	public ShopManager shopManager = new ShopManager(this);
	boolean isRunning = true;
	int currentWeek = 1;
	int totalWeeks;

	// inventory state
	public List<Athlete> athletesInTeam = new ArrayList<Athlete>();
	public List<Item> itemsInInventory = new ArrayList<Item>();

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
