package base;

import java.util.ArrayList;
import java.util.List;

public class MatchManager {
	public GameEnvironment game;
	public List<Team> matchOptions = new ArrayList<Team>();
	
	public MatchManager(GameEnvironment game) {
		this.game = game;
		refreshMatches(game);
	}

	public void refreshMatches(GameEnvironment game) {
		matchOptions.removeAll(matchOptions);
		for (int i = 0; i <= game.numMatches; i++) {
			matchOptions.add(new Team(game));
		}
	}
	
	public int winMoney() {
		return game.randomManager.randomMoneyValue(15, 20);
	}
	
	public int lossMoney() {
		return game.randomManager.randomMoneyValue(3, 7);
	}
	
	public boolean matchWon(Team yourTeam, Team enemyTeam) {
		/* Calculates the winner based on one stat per player, with a small random element 
		 * Also updates the stamina of each player in yourTeam
		 */
		int points = 0;
		Athlete yourChaser = yourTeam.getAthleteAtIndex(0);
		Athlete yourBeater = yourTeam.getAthleteAtIndex(1);
		Athlete yourKeeper = yourTeam.getAthleteAtIndex(2);
		Athlete yourSeeker = yourTeam.getAthleteAtIndex(3);
		
		int yourChaserValue = game.randomManager.randomResult(yourChaser.getOffence());
		int yourBeaterValue = game.randomManager.randomResult(yourBeater.getOffence());
		int yourKeeperValue = game.randomManager.randomResult(yourKeeper.getDefence());
		int yourSeekerValue = game.randomManager.randomResult(yourSeeker.getSpeed());
		
		Athlete enemyChaser = enemyTeam.getAthleteAtIndex(0);
		Athlete enemyBeater = enemyTeam.getAthleteAtIndex(1);
		Athlete enemyKeeper = enemyTeam.getAthleteAtIndex(2);
		Athlete enemySeeker = enemyTeam.getAthleteAtIndex(3);
		
		int enemyChaserValue = game.randomManager.randomResult(enemyChaser.getOffence());
		int enemyBeaterValue = game.randomManager.randomResult(enemyBeater.getOffence());
		int enemyKeeperValue = game.randomManager.randomResult(enemyKeeper.getDefence());
		int enemySeekerValue = game.randomManager.randomResult(enemySeeker.getSpeed());	
		
		
		if (yourChaserValue > enemyChaserValue) {
			points += 1;
			yourChaser.decreaseStamina(game.randomManager.randomStaminaLoss(true));
		} else if (yourChaserValue < enemyChaserValue) {
			points -= 1;
			yourChaser.decreaseStamina(game.randomManager.randomStaminaLoss(false));
		} else {
			yourChaser.decreaseStamina(game.randomManager.randomStaminaLoss(true));
		}
		
		if (yourBeaterValue > enemyBeaterValue) {
			points += 1;
			yourBeater.decreaseStamina(game.randomManager.randomStaminaLoss(true));
		} else if (yourBeaterValue < enemyBeaterValue) {
			points -= 1;
			yourBeater.decreaseStamina(game.randomManager.randomStaminaLoss(false));
		} else {
			yourBeater.decreaseStamina(game.randomManager.randomStaminaLoss(true));
		}
		
		if (yourKeeperValue > enemyKeeperValue) {
			points += 1;
			yourKeeper.decreaseStamina(game.randomManager.randomStaminaLoss(true));
			
		} else if (yourKeeperValue < enemyKeeperValue) {
			points -= 1;
			yourKeeper.decreaseStamina(game.randomManager.randomStaminaLoss(false));
		} else {
			yourKeeper.decreaseStamina(game.randomManager.randomStaminaLoss(true));
		}
		
		if (yourSeekerValue > enemySeekerValue) {
			points += 1;
			yourSeeker.decreaseStamina(game.randomManager.randomStaminaLoss(true));
		} else if (yourSeekerValue < enemySeekerValue) {
			points -= 1;
			yourSeeker.decreaseStamina(game.randomManager.randomStaminaLoss(false));
		} else {
			yourSeeker.decreaseStamina(game.randomManager.randomStaminaLoss(true));
		}
		
		if (points >= 0) {
			return true;
		}
		return false;
	}
}
