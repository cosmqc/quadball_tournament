package exceptions;

import base.*;

public class TeamFullException extends Exception {
	GameEnvironment game;
	
	public TeamFullException(GameEnvironment game) {
		super(String.format("Team cannot have more than %d on the field, and %d on the bench", game.numPlayers, game.numBench));
	}
	
	public TeamFullException(GameEnvironment game, String message) {
		super(message);
	}
}
