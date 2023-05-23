package base;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class MatchManager.
 * @author Jake Dalton
 * @author Leo Black
 */
public class MatchManager {
	
	/** The game. */
	public GameEnvironment game;
	
	/** The match options. */
	public List<Team> matchOptions = new ArrayList<Team>();

	/**
	 * Instantiates a new match manager.
	 *
	 * @param game the game
	 */
	public MatchManager(GameEnvironment game) {
		this.game = game;
		refreshMatches(game);
	}

	/**
	 * Refresh matches.
	 *
	 * @param game the game
	 */
	public void refreshMatches(GameEnvironment game) {
		matchOptions.removeAll(matchOptions);
		for (int i = 0; i <= game.numMatches; i++) {
			matchOptions.add(new Team(game));
		}
	}

	/**
	 * Win money.
	 *
	 * @return the int
	 */
	public int winMoney() {
		return game.randomManager.randomMoneyValue(15, 20);
	}

	/**
	 * Loss money.
	 *
	 * @return the int
	 */
	public int lossMoney() {
		return game.randomManager.randomMoneyValue(3, 7);
	}

	/**
	 * Match won.
	 *
	 * @param enemyTeam the enemy team
	 * @return true, if successful
	 */
	public boolean matchWon(Team enemyTeam) {
		int[] playerScores = new int[game.numPlayers];
		int[] enemyScores = new int[game.numPlayers];
		for (int i = 0; i < game.numPlayers; i++) {
			Athlete playerAthlete = game.playerTeam.getAthleteAtIndex(i);
			Athlete enemyAthlete = enemyTeam.getAthleteAtIndex(i);
			int playerScore;
			int enemyScore;
			if (i == 0 || i == 1) {
				playerScore = game.randomManager.randomResult(playerAthlete.getOffence());
				enemyScore = game.randomManager.randomResult(enemyAthlete.getOffence());
			} else if (i == 2) {
				playerScore = game.randomManager.randomResult(playerAthlete.getDefence());
				enemyScore = game.randomManager.randomResult(enemyAthlete.getDefence());
			} else {
				playerScore = game.randomManager.randomResult(playerAthlete.getSpeed());
				enemyScore = game.randomManager.randomResult(enemyAthlete.getSpeed());
			}
			playerScores[i] = playerScore;
			enemyScores[i] = enemyScore;

			playerAthlete.decreaseStamina(playerScore >= enemyScore);
		}

		boolean allInjured = true;
		for (Athlete athlete : game.playerTeam.getAthletesList()) {
			if (athlete.getStamina() > 0) {
				allInjured = false;
			}
		}

		int playerWins = 0;
		int enemyWins = 0;
		for (int i = 0; i < game.numPlayers; i++) {
			if (playerScores[i] >= enemyScores[i]) {
				playerWins++;
			} else {
				enemyWins++;
			}
		}

		if (!allInjured) {
			if (playerWins >= enemyWins) {
				game.playerMoney += game.difficulty.moneyOnWin;
				game.playerPoints += game.difficulty.pointsOnWin;
			} else {
				game.playerMoney += game.difficulty.moneyOnLoss;
				game.playerPoints += game.difficulty.pointsOnLoss;
			}
		}

		return playerWins >= enemyWins;
	}
}
