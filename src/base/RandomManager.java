package base;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Stream;
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
			int freeSlots = game.numBench - game.playerTeam.getNumBenched();
			if (generateNum(1, 15 * (game.playerTeam.getNumBenched()+1)) == 1) {
				if (game.playerTeam.getNumBenched() != game.numBench) {
					Athlete newAthlete = newReserveJoins();
					eventMessage = newAthlete.getName() + " has decided to join your team. They've been added to your bench.";
				}
			}
		}
		return eventMessage;
	}
	
	Athlete newReserveJoins() {
		return new Athlete(game);
	}
}
