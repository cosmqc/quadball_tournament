package base;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

import exceptions.TeamFullException;

import java.util.Random;


/**
 * The Class RandomManager.
 * @author Jake Dalton
 * @author Leo Black
 */
public class RandomManager {
	
	/** The game. */
	GameEnvironment game;
	
	/** The random. */
	Random random;
	
	/**
	 * Instantiates a new random manager.
	 *
	 * @param game the game
	 */
	public RandomManager(GameEnvironment game) {
		this.game = game;
		this.random = new Random();
	}
	
	/**
	 * Random valid name.
	 *
	 * @param filePath the file path
	 * @param fileLength the file length
	 * @param containingList the containing list
	 * @return the string
	 */
	public String randomValidName(String filePath, int fileLength, List<String> containingList) {
		String name = randomName(filePath, fileLength);
		containingList.add(name);
		return name;
	}

	/**
	 * Random name.
	 *
	 * @param filePath the file path
	 * @param fileLength the file length
	 * @return the string
	 */
	String randomName(String filePath, int fileLength) {
		Stream<String> file = null;
		String text = "";
		try {
			file = Files.lines(Paths.get(filePath));
			text = file.skip(new Random().nextInt(fileLength)).findFirst().get();
		} catch (NoSuchElementException | IOException e) {
			e.printStackTrace();
			System.exit(1);
		} finally {
			file.close();
		}
		return text;
	}
	
	/**
	 * Random stamina loss.
	 *
	 * @param athleteWon the athlete won
	 * @return the int
	 */
	public int randomStaminaLoss(boolean athleteWon) {
		if (athleteWon) {
			return random.nextInt(game.difficulty.staminaLossOnWin-1,game.difficulty.staminaLossOnWin+1);
		} else {
			return random.nextInt(game.difficulty.staminaLossOnLoss-1,game.difficulty.staminaLossOnLoss+1);
		}
	}
	
	/**
	 * Random result.
	 *
	 * @param stat the stat
	 * @return the int
	 */
	public int randomResult(int stat) {
		return random.nextInt(Math.max(stat-1, 1), Math.min(stat+1, 9));
	}
	
	/**
	 * Random money value.
	 *
	 * @param min the min
	 * @param max the max
	 * @return the int
	 */
	public int randomMoneyValue(int min, int max) {
		return random.nextInt(min, max);
	}
	
	/**
	 * Generate num.
	 *
	 * @param lower the lower
	 * @param upper the upper
	 * @return the int
	 */
	int generateNum(int lower, int upper) {
		int stat = new Random().nextInt(lower, upper);
		return stat;
	}
	
	/**
	 * Gets the random event.
	 *
	 * @return the random event
	 */
	public String getRandomEvent() {
		String eventMessage = "";
		if (generateNum(1, 25) == 1) {
			// reserve joins randomly
			if (generateNum(1, 2 * (game.playerTeam.getNumBenched()+1)) == 1) {
				Athlete newAthlete = newReserveJoins();
				if (newAthlete != null) {
					return newAthlete.getName() + " has decided to join your team. They've been added to your bench.";
				}
			}
			
			// athlete quits randomly if injured
			Athlete possRemovedAthlete = game.playerTeam.getAthleteAtIndex(generateNum(0,game.playerTeam.getNumActive()));
			if (possRemovedAthlete.wasInjuredPreviously) {
				game.playerTeam.removeAthlete(possRemovedAthlete);
				return possRemovedAthlete.getName() + "has decided to quit after being injured last game.";
			}
			
			// athlete stat increase
			Athlete possStatIncreaseAthlete = game.playerTeam.getAthleteAtIndex(generateNum(0,game.playerTeam.getNumActive()));
			if (!possStatIncreaseAthlete.wasInjuredPreviously) {
				possStatIncreaseAthlete.boostStats();
				return possRemovedAthlete.getName() + "learnt a new technique and their stats got boosted.";
			}
		}
		return eventMessage;
	}
	
	/**
	 * New reserve joins.
	 *
	 * @return the athlete
	 */
	Athlete newReserveJoins() {
		Athlete newReserve = new Athlete(game);
		try {
			game.playerTeam.addAthlete(newReserve);
		} catch (TeamFullException e) {
			return null;
		}
		return newReserve;
	}
}
