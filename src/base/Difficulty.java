package base;


/**
 * The Class Difficulty.
 * @author Jake Dalton
 * @author Leo Black
 */
public class Difficulty {
	
	/** The name. */
	public String name;
	
	/** The money on win. */
	public int moneyOnWin;
	
	/** The money on loss. */
	public int moneyOnLoss;
	
	/** The stamina loss on win. */
	public int staminaLossOnWin;
	
	/** The stamina loss on loss. */
	public int staminaLossOnLoss;
	
	/** The player avg stat. */
	public int playerAvgStat;
	
	/** The enemy avg stat. */
	public int enemyAvgStat;
	
	/** The points on win. */
	public int pointsOnWin;
	
	/** The points on loss. */
	public int pointsOnLoss;

	/**
	 * Instantiates a new difficulty.
	 *
	 * @param name the name
	 * @param moneyOnWin the money on win
	 * @param moneyOnLoss the money on loss
	 * @param staminaLossOnWin the stamina loss on win
	 * @param staminaLossOnLoss the stamina loss on loss
	 * @param playerAvgStat the player avg stat
	 * @param enemyAvgStat the enemy avg stat
	 * @param pointsOnWin the points on win
	 * @param pointsOnLoss the points on loss
	 */
	public Difficulty(String name, int moneyOnWin, int moneyOnLoss, int staminaLossOnWin, int staminaLossOnLoss,
			int playerAvgStat, int enemyAvgStat, int pointsOnWin, int pointsOnLoss) {
		this.name = name;
		this.moneyOnWin = moneyOnWin;
		this.moneyOnLoss = moneyOnLoss;
		this.staminaLossOnWin = staminaLossOnWin;
		this.staminaLossOnLoss = staminaLossOnLoss;
		this.playerAvgStat = playerAvgStat;
		this.enemyAvgStat = enemyAvgStat;
		this.pointsOnWin = pointsOnWin;
		this.pointsOnLoss = pointsOnLoss;
	}
	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	public String toString() {
		return name;
	}
}
