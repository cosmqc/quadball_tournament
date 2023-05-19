package base;

import java.util.List;
import java.util.ArrayList;

public class Team {
	GameEnvironment game;
	String name;
	List<Athlete> athletes;
	List<Athlete> subs;
	
	// completely random - for enemy team creation.
	public Team(GameEnvironment game) {
		this.game = game;
		this.name = generateValidName();
		this.athletes = generatePlayers(game.numPlayers);
		this.subs = generatePlayers(game.numBench);
	}
	
	// with given values - for player team creation.
	public Team(GameEnvironment game, String name, List<Athlete> athletes, List<Athlete> subs) {
		this.game = game;
		this.name = name;
		this.athletes = athletes;
		this.subs = subs;
	}

	public String getName() {
		return name;
	}

	public int getNumOnField() {
		return athletes.size();
	}

	public int getNumOnBench() {
		return subs.size();
	}
	
	public int getNumTotal() {
		return getNumOnField() + getNumOnBench();
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
	
	String generateValidName() {
		String country = game.randomManager.randomValidName("src/resources/countrynames", 198, game.teamsInUse);
		String team = game.randomManager.randomValidName("src/resources/teamnames", 105, game.teamsInUse);
		return country + " " + team;
	}
	
	List<Athlete> generatePlayers(int numPlayers) {
		ArrayList<Athlete> players = new ArrayList<Athlete>();
		for (int i=0; i < numPlayers; i++) {
			players.add(new Athlete(game));
		}
		return players;
	}
}
