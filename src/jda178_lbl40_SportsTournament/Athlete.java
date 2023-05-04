package jda178_lbl40_SportsTournament;

public class Athlete {

	String name;
	String position;
	int offence;
	int defence;
	int stamina;
	int maxStamina;
	
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
}
