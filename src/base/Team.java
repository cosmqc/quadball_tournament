package base;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import exceptions.TeamFullException;
import exceptions.InvalidSwapException;

/**
 * The Class Team.
 * @author Jake Dalton
 * @author Leo Black
 */
public class Team {
	
	/** The game. */
	GameEnvironment game;
	
	/** The name. */
	String name;
	
	/** The athletes. */
	Athlete[] athletes;
	
	/** The subs. */
	List<Athlete> subs;

	/**
	 * Instantiates a new team.
	 *
	 * @param game the game
	 */
	// completely random - for enemy team creation.
	public Team(GameEnvironment game) {
		this.game = game;
		this.name = generateValidName();
		this.athletes = generatePlayers(game.numPlayers);
		this.subs = Arrays.asList(generatePlayers(game.numBench));
	}

	/**
	 * Instantiates a new team.
	 *
	 * @param game the game
	 * @param name the name
	 * @param athletes the athletes
	 * @param subs the subs
	 */
	// with given values - for player team creation.
	public Team(GameEnvironment game, String name, Athlete[] athletes, List<Athlete> subs) {
		this.game = game;
		this.name = name;
		this.athletes = athletes;
		this.subs = subs;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the num active.
	 *
	 * @return the num active
	 */
	public int getNumActive() {
		int num = 0;
		for (Athlete athlete : getAthletes()) {
			if (athlete != null) {
				num++;
			}
		}
		return num;
	}

	/**
	 * Gets the num benched.
	 *
	 * @return the num benched
	 */
	public int getNumBenched() {
		return subs.size();
	}

	/**
	 * Gets the num total.
	 *
	 * @return the num total
	 */
	public int getNumTotal() {
		return getNumActive() + getNumBenched();
	}

	/**
	 * Gets the athletes.
	 *
	 * @return the athletes
	 */
	public Athlete[] getAthletes() {
		return (Athlete[]) athletes;
	}

	/**
	 * Gets the athletes list.
	 *
	 * @return the athletes list
	 */
	public List<Athlete> getAthletesList() {
		return Arrays.asList(getAthletes());
	}

	/**
	 * Gets the subs.
	 *
	 * @return the subs
	 */
	public List<Athlete> getSubs() {
		return subs;
	}

	/**
	 * Gets the all players.
	 *
	 * @return the all players
	 */
	public List<Athlete> getAllPlayers() {
		List<Athlete> total = new ArrayList<Athlete>();
		total.addAll(Arrays.asList(getAthletes()));
		total.addAll(getSubs());
		return total;
	}

	/**
	 * Swap up.
	 *
	 * @param athlete the athlete
	 * @throws InvalidSwapException the invalid swap exception
	 */
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
		if (getAthleteAtIndex(game.numPlayers - 1).getEquippedItem() != null) {
			game.itemsInInventory.add(getAthleteAtIndex(game.numPlayers - 1).unequipItem());
		}
		subs.add(getAthleteAtIndex(game.numPlayers - 1));
		athletes[game.numPlayers - 1] = athlete;
	}

	/**
	 * Swap down.
	 *
	 * @param athlete the athlete
	 * @throws InvalidSwapException the invalid swap exception
	 */
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
		if (athlete.getEquippedItem() != null) {
			game.itemsInInventory.add(athlete.unequipItem());
		}
		athletes[game.numPlayers - 1] = subs.remove(0);
		subs.add(athlete);
	}

	/**
	 * Gets the athlete at index.
	 *
	 * @param index the index
	 * @return the athlete at index
	 * @throws IndexOutOfBoundsException the index out of bounds exception
	 */
	public Athlete getAthleteAtIndex(int index) throws IndexOutOfBoundsException {
		if (0 <= index && index < game.numPlayers) {
			return (Athlete) athletes[index];
		} else {
			throw new IndexOutOfBoundsException();
		}
	}

	/**
	 * Gets the athlete index.
	 *
	 * @param athlete the athlete
	 * @return the athlete index
	 */
	public int getAthleteIndex(Athlete athlete) {
		return Arrays.asList(athletes).indexOf(athlete);
	}

	/**
	 * Adds the athlete.
	 *
	 * @param athlete the athlete
	 * @throws TeamFullException the team full exception
	 */
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

	/**
	 * Removes the athlete.
	 *
	 * @param athlete the athlete
	 */
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

	/**
	 * Generate valid name.
	 *
	 * @return the string
	 */
	String generateValidName() {
		String country = game.randomManager.randomValidName("src/resources/countrynames", 198, game.teamsInUse);
		String team = game.randomManager.randomValidName("src/resources/teamnames", 93, game.teamsInUse);
		while (country.length() + team.length() > game.maxTeamNameLength) {
			country = game.randomManager.randomValidName("src/resources/countrynames", 198, game.teamsInUse);
			team = game.randomManager.randomValidName("src/resources/teamnames", 93, game.teamsInUse);
		}
		return country + " " + team;
	}

	/**
	 * Generate players.
	 *
	 * @param numPlayers the num players
	 * @return the athlete[]
	 */
	Athlete[] generatePlayers(int numPlayers) {
		Athlete[] players = new Athlete[numPlayers];
		for (int i = 0; i < numPlayers; i++) {
			players[i] = new Athlete(game);
		}
		return players;
	}

}
