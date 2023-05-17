package jda178_lbl40_SportsTournament;

import java.util.Random;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.*;

public class Athlete {

	String name;
	String position;
	int offence;
	int defence;
	int stamina;
	int maxStamina;
	
	public Athlete() {
		this.name = generateName();
    }
	
	public Athlete(String name, String position, int offence, int defence, int stamina, int maxStamina) {
        this.name = name;
        this.position = position;
        this.offence = offence;
        this.defence = defence;
        this.stamina = stamina;
        this.maxStamina = maxStamina;
    }
	
	public String getName() {
        return name;
    }
	public String getPosition() {
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
	
	public static void main(String[] args) {
		Athlete joe = new Athlete();
		System.out.println(joe.name);
	}
}
