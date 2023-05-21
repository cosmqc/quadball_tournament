package base;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Arrays;

import exceptions.TeamFullException;
import exceptions.InvalidSwapException;

enum Position {
	CHASER, BEATER, KEEPER, SEEKER
}

public class Team {
	GameEnvironment game;
	String name;
	Object[] athletes;
	List<Athlete> subs;
	Map<Position, Athlete> positionState;

	// completely random - for enemy team creation.
	public Team(GameEnvironment game) {
		this.game = game;
		this.name = generateValidName();
		this.athletes = generatePlayers(game.numPlayers).toArray();
		this.subs = generatePlayers(game.numBench);
	}

	// with given values - for player team creation.
	public Team(GameEnvironment game, String name, Athlete[] athletes, List<Athlete> subs) {
		this.game = game;
		this.name = name;
		this.athletes = athletes;
		this.subs = subs;
	}

	public String getName() {
		return name;
	}

	public int getNumActive() {
		int num = 0;
		for (Athlete athlete : getAthletes()) {
			if (athlete != null) {
				num++;
			}
		}
		return num;
	}

	public int getNumBenched() {
		return subs.size();
	}

	public int getNumTotal() {
		return getNumActive() + getNumBenched();
	}

	public Athlete[] getAthletes() {
		return (Athlete[]) athletes;
	}

	public List<Athlete> getSubs() {
		return subs;
	}

	public List<Athlete> getAllPlayers() {
		List<Athlete> total = new ArrayList<Athlete>();
		total.addAll(Arrays.asList(getAthletes()));
		total.addAll(getSubs());
		return total;
	}

	public void swapUp(Athlete athlete) throws InvalidSwapException {
		int athleteIndex = getAthleteIndex(athlete);
		if (athleteIndex == -1 && !subs.contains(athlete)) {
			// if in neither list
			throw new IllegalArgumentException(athlete.name + " doesn't exist on the players team.");
		}
		if (athleteIndex == 0) {
			// if athlete is in top position on field
			throw new InvalidSwapException(game, "Cannot move this athlete any higher");
		}
		if (Arrays.asList(athletes).contains(athlete)) {
			// if both athletes are on the field
			athletes[athleteIndex] = athletes[athleteIndex - 1];
			athletes[athleteIndex - 1] = athlete;
			return;
		}
		// if one team, one bench
		subs.remove(athlete);
		game.itemsInInventory.add(getAthleteAtIndex(game.numPlayers - 1).unequipItem());
		subs.add(getAthleteAtIndex(game.numPlayers - 1));
		athletes[game.numPlayers - 1] = athlete;
	}

	public void swapDown(Athlete athlete) throws InvalidSwapException {
		int athleteIndex = getAthleteIndex(athlete);
		if (athleteIndex == -1 && !subs.contains(athlete)) {
			throw new IllegalArgumentException(athlete.name + " doesn't exist on the players team.");
		}
		if (subs.contains(athlete) || (subs.isEmpty() && athleteIndex == game.numPlayers - 1)) {
			// if athlete is in reserves
			throw new InvalidSwapException(game, "Cannot move this athlete any lower");
		}
		if (athleteIndex < game.numPlayers - 1) {
			// swap between team
			athletes[athleteIndex] = athletes[athleteIndex + 1];
			athletes[athleteIndex + 1] = athlete;
			return;
		}
		// if one team, one bench
		game.itemsInInventory.add(athlete.unequipItem());
		athletes[game.numPlayers - 1] = subs.remove(0);
		subs.add(athlete);
	}

	public Athlete getAthleteAtIndex(int index) throws IndexOutOfBoundsException {
		if (0 <= index && index < game.numPlayers) {
			return (Athlete) athletes[index];
		} else {
			throw new IndexOutOfBoundsException();
		}
	}
	
	public int getAthleteIndex(Athlete athlete) {
		return Arrays.asList(athletes).indexOf(athlete);
	}

	public void addAthlete(Athlete athlete) throws TeamFullException {
		// try adding to empty slot on field
		for (int i = 0; i < game.numPlayers; i++) {
			if (athletes[i] == null) {
				athletes[i] = athlete;
				return;
			}
		}
		// if no empty slot, check if any empty bench slots
		if (getNumBenched() < game.numBench) {
			subs.add(athlete);
			return;
		}
		// if all slots full
		throw new TeamFullException(game);
	}

	public void removeAthlete(Athlete athlete) {
		if (Arrays.asList(athletes).contains(athlete)) {
			// if the sold athlete is on the field
			int slot = getAthleteIndex(athlete);
			if (subs.size() > 0) {
				athletes[slot] = subs.remove(0); // replace with sub
			} else {
				athletes[slot] = null;
			}
			game.namesInUse.remove(athlete.firstName);
			game.namesInUse.remove(athlete.lastName);

		} else if (subs.contains(athlete)) {
			subs.remove(athlete);
		} else {
			throw new IllegalArgumentException(athlete.name + " doesn't exist on the players team.");
		}
	}

	String generateValidName() {
		String country = game.randomManager.randomValidName("src/resources/countrynames", 198, game.teamsInUse);
		String team = game.randomManager.randomValidName("src/resources/teamnames", 105, game.teamsInUse);
		return country + " " + team;
	}

	List<Athlete> generatePlayers(int numPlayers) {
		ArrayList<Athlete> players = new ArrayList<Athlete>();
		for (int i = 0; i < numPlayers; i++) {
			players.add(new Athlete(game));
		}
		return players;
	}

}
