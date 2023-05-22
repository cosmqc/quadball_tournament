package base;

import java.util.ArrayList;
import java.util.List;

public class MatchManager {
	public GameEnvironment game;
	public RandomManager randomManager;
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
		int points = 0;
		int yourChaserValue = yourTeam.getAthleteAtIndex(0).getOffence();
		yourChaserValue = randomManager.randomResult(yourChaserValue);
		int enemyChaserValue = enemyTeam.getAthleteAtIndex(0).getOffence();
		enemyChaserValue = randomManager.randomResult(enemyChaserValue);
		if (yourChaserValue > enemyChaserValue) {
			points += 1;
		} else if (yourChaserValue < enemyChaserValue) {
			points -= 1;
		}
		return true;
	}
}
