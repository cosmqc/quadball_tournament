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
	
	public String randomValidName(String filePath, int fileLength, List<String> containingList) {
		String name = randomName(filePath, fileLength);
		while (containingList.contains(name)) {
			name = randomName(filePath, fileLength);
		}
		containingList.add(name);
		return name;
	}

	String randomName(String filePath, int fileLength) {
		String text = "";
		try (Stream<String> file = Files.lines(Paths.get(filePath))) {
			text = file.skip(new Random().nextInt(fileLength)).findFirst().get();
		} catch (NoSuchElementException | IOException e) {
			System.out.println(e);
			System.exit(1);
		}
		return text;
	}
	
	public int randomResult(int stat) {
		return random.nextInt(Math.max(stat-1, 1), Math.min(stat+1, 9));
	}
	
	public int randomMoneyValue(int min, int max) {
		return random.nextInt(min, max);
	}
	
	int generateNum(int lower, int upper) {
		int stat = new Random().nextInt(lower, upper);
		return stat;
	}
}
