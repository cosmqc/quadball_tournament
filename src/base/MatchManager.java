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
	
	public boolean matchWon(Team yourTeam, Team enemyTeam) {
		/* Calculates the winner based on one stat per player, with a small random element */
		int points = 0;
		int yourChaserValue = yourTeam.getAthleteAtIndex(0).getOffence();
		yourChaserValue = game.randomManager.randomResult(yourChaserValue);
		int enemyChaserValue = enemyTeam.getAthleteAtIndex(0).getOffence();
		enemyChaserValue = game.randomManager.randomResult(enemyChaserValue);
		if (yourChaserValue > enemyChaserValue) {
			points += 1;
		} else if (yourChaserValue < enemyChaserValue) {
			points -= 1;
		}
		int yourBeaterValue = yourTeam.getAthleteAtIndex(0).getOffence();
		yourBeaterValue = game.randomManager.randomResult(yourBeaterValue);
		int enemyBeaterValue = enemyTeam.getAthleteAtIndex(0).getOffence();
		enemyBeaterValue = game.randomManager.randomResult(enemyBeaterValue);
		if (yourBeaterValue > enemyBeaterValue) {
			points += 1;
		} else if (yourBeaterValue < enemyBeaterValue) {
			points -= 1;
		}
		int yourKeeperValue = yourTeam.getAthleteAtIndex(0).getDefence();
		yourKeeperValue = game.randomManager.randomResult(yourKeeperValue);
		int enemyKeeperValue = enemyTeam.getAthleteAtIndex(0).getDefence();
		enemyKeeperValue = game.randomManager.randomResult(enemyKeeperValue);
		if (yourKeeperValue > enemyKeeperValue) {
			points += 1;
		} else if (yourKeeperValue < enemyKeeperValue) {
			points -= 1;
		}
		int yourSeekerValue = yourTeam.getAthleteAtIndex(0).getSpeed();
		yourSeekerValue = game.randomManager.randomResult(yourSeekerValue);
		int enemySeekerValue = enemyTeam.getAthleteAtIndex(0).getSpeed();
		enemySeekerValue = game.randomManager.randomResult(enemySeekerValue);
		if (yourSeekerValue > enemySeekerValue) {
			points += 1;
		} else if (yourSeekerValue < enemySeekerValue) {
			points -= 1;
		}
		if (points >= 0) {
			return true;
		}
		return false;
	}
}
