package jda178_lbl40_SportsTournament;

import java.util.Random;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.*;

enum Position { CHASER, BEATER, KEEPER, SEEKER }

public class Athlete extends Purchasable {
	
	public GameEnvironment game;
	String name;
	String nickname = null;
	Position position;
	int offence;
	int defence;
	int speed;
	int stamina;
	int maxStamina;
	
	public Athlete(GameEnvironment game) {
		this.game = game;
		this.name = generateValidName();
		this.offence = generateStat(1, 10);
		this.defence = generateStat(1, 10);
		this.speed = generateStat(1, 10);
		this.stamina = this.maxStamina = generateStat(5, 10);
    }
	
	public Athlete(GameEnvironment game, String name, int offence, int defence, int speed, int stamina, int maxStamina) {
        this.game = game;
		this.name = name;
        this.offence = offence;
        this.defence = defence;
        this.speed = speed;
        this.stamina = this.maxStamina = stamina;
    }
	
	public String toString() {
		if (position != null) {
			return String.format("Athlete %s in position %s with stats (%d, %d, %d, %d)", name, position, offence, defence, speed, stamina);
			}
		else {
			return String.format("Athlete %s without a position with stats (%d, %d, %d, %d)", name, offence, defence, speed, stamina);
			}
	}
	public String getName() {
        return name;
    }
	public Position getPosition() {
		return position;
	}
	public int getOffence() {
		return offence;
	}
	public int getDefence() {
		return defence;
	}
	public int getStamina() {
		return stamina;
	}
	
	public void decreaseStamina(int staminaLoss) {
		stamina -= staminaLoss;
	}
	public void resetStamina() {
		stamina = maxStamina;
	}
	
	private String generateName() {
		String line = "";
		try (Stream<String> firstnames = Files.lines(Paths.get("src/jda178_lbl40_SportsTournament/firstnames"));
			Stream<String> lastnames = Files.lines(Paths.get("src/jda178_lbl40_SportsTournament/lastnames"))) {
			
			int firstN = new Random().nextInt(2739);
	        line += firstnames.skip(firstN).findFirst().get();
	        
			int lastN = new Random().nextInt(1000);
	        line = line + " " + lastnames.skip(lastN).findFirst().get();
	    }
		catch(IOException | NoSuchElementException e){
	        System.out.println(e);
	        System.exit(1);
	    }
		return line;
	}
	private String generateValidName() {
		String name = generateName();
		while (game.namesInUse.contains(name)) {
			name = generateName();
		}
		game.namesInUse.add(name);
		return name;
	}
	
	private int generateStat(int lower, int upper) {
		int stat = new Random().nextInt(lower, upper);
		return stat;
	}
}
