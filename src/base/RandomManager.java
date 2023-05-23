package base;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

import exceptions.TeamFullException;

import java.util.Random;
public class RandomManager {
	
	GameEnvironment game;
	Random random;
	
	public RandomManager(GameEnvironment game) {
		this.game = game;
		this.random = new Random();
	}
	
	public String randomValidName(String filePath, List<String> containingList) {
		String name = randomName(filePath);
		while (containingList.contains(name)) {
			name = randomName(filePath);
		}
		containingList.add(name);
		return name;
	}

	String randomName(String filePath) {
		Stream<String> file = null;
		String text = "";
		try {
			int fileLength = (int) Files.lines(Paths.get(filePath)).count();
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
	
	public int randomStaminaLoss(boolean athleteWon) {
		if (athleteWon) {
			return random.nextInt(game.difficulty.staminaLossOnWin-1,game.difficulty.staminaLossOnWin+1);
		} else {
			return random.nextInt(game.difficulty.staminaLossOnLoss-1,game.difficulty.staminaLossOnLoss+1);
		}
	}
	
	public int randomResult(int stat) {
		System.out.println(stat);
		return random.nextInt(Math.max(stat-1, 1), Math.min(stat+1, 9));
	}
	
	public int randomMoneyValue(int min, int max) {
		return random.nextInt(min, max);
	}
	
	int generateNum(int lower, int upper) {
		int stat = new Random().nextInt(lower, upper);
		return stat;
	}
	
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
