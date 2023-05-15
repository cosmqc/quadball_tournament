package jda178_lbl40_SportsTournament;

import java.util.List;

public class Team {
	
	String name;
	int numPlayers;
	int numSubs;
	List<Athlete> athletes;
	List<Athlete> subs;
	
	public Team(String name, int numPlayers, int numSubs, List<Athlete> athletes, List<Athlete> subs) {
		this.name = name;
		this.numPlayers = numPlayers;
		this.numSubs = numSubs;
		this.athletes = athletes;
		this.subs = subs;
	}
	
	public String getName() {
        return name;
    }
	public int getNumPlayers() {
		return numPlayers;
	}
	public int getNumSubs() {
		return numSubs;
	}
	public List<Athlete> getAthletes() {
		return athletes;
	}
	public List<Athlete> getSubs() {
		return subs;
	}
	
	public void addAthlete(Athlete athlete) {
		// add a new athlete to the team
		athletes.add(athlete);
	}
	public boolean subOutAthlete(Athlete athlete) {
		// send an athlete to be a sub if it is in the team
		if (athletes.contains(athlete)) {
			subs.add(athlete);
			athletes.remove(athlete);
			return true;
		} else {
			return false;
		}
	}
	public boolean subInAthlete(Athlete athlete) {
		// send a sub to be in the team if it exists
		if (subs.contains(athlete)) {
			athletes.add(athlete);
			subs.remove(athlete);
			return true;
		} else {
			return false;
		}
	}
}
