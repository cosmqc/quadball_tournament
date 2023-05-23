package base;

public class Difficulty {
	public String name;
	public int moneyOnWin;
	public int moneyOnLoss;
	public int staminaLossOnWin;
	public int staminaLossOnLoss;
	public int playerAvgStat;
	public int enemyAvgStat;
	public int pointsOnWin;
	public int pointsOnLoss;

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
	
	public String toString() {
		return name;
	}
}
