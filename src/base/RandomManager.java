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

	
	// TODO: Needs to be thoroughly tested, closing system is a bit scuffed.
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
	
	public int randomResult(int stat) {
		return random.nextInt(Math.max(stat-1, 1), Math.min(stat+1, 9));
	}
	
	int generateNum(int lower, int upper) {
		int stat = new Random().nextInt(lower, upper);
		return stat;
	}
}
