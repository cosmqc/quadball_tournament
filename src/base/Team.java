package base;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import exceptions.TeamFullException;


enum Position {
	CHASER, BEATER, KEEPER, SEEKER
}

public class Team {
	GameEnvironment game;
	String name;
	List<Athlete> athletes;
	List<Athlete> subs;
	Map<Position, Athlete> positionState;
	
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

	public void addAthlete(Athlete athlete) throws TeamFullException {
		// add a new athlete to the team
		if (game.numPlayers < 4) {
			athletes.add(athlete);
		} else if (game.numBench < 5) {
			subs.add(athlete);
		} else {
			throw new TeamFullException(game);
		}
	}
	
	public void removeAthlete(Athlete athlete) throws TeamFullException {
		// add a new athlete to the team
		if (athletes.contains(athlete)) {
			athletes.remove(athlete);
		} else if (subs.contains(athlete)) {
			subs.remove(athlete);
		} else {
			throw new IllegalArgumentException(athlete.name + " doesn't exist on the players team.");
		}
	}

	// TODO: I've left bench and promote alone while I sleep, but they'll be a massive pain
	// if left like this. Gonna implement a swap feature / waterfall effect between subs and players
	// so we're not stuck w a full bench and no team.
	public void bench(Athlete athlete) {
		// send an athlete to be a sub if it is in the team
		if (athletes.contains(athlete)) {
			subs.add(athlete);
			athletes.remove(athlete);
		} else {
			throw new IllegalArgumentException(athlete.name + " is not on the field.");
		}
	}

	public void promote(Athlete athlete) {
		// send a sub to be in the team if it exists
		if (subs.contains(athlete)) {
			athletes.add(athlete);
			subs.remove(athlete);
		} else {
			throw new IllegalArgumentException(athlete.name + " is not on the bench.");
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
