package exceptions;

import base.*;

@SuppressWarnings("serial")
public class TeamFullException extends Exception {
	GameEnvironment game;
	String description;
	
	public TeamFullException(GameEnvironment game) {
		super(String.format("Team cannot have more than %d on the field, and %d on the bench", game.numPlayers, game.numBench));

	}
	
	public TeamFullException(GameEnvironment game, String message) {
		super(message);
	}
	
	public String getMessage() {
		return super.getMessage();
	}
}
