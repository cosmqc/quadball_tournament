package exceptions;

import base.*;

// TODO: Auto-generated Javadoc
/**
 * The Class TeamFullException.
 */
@SuppressWarnings("serial")
public class TeamFullException extends Exception {
	
	/** The game. */
	GameEnvironment game;

	/**
	 * Instantiates a new team full exception.
	 *
	 * @param game the game
	 */
	public TeamFullException(GameEnvironment game) {
		super(String.format("Team cannot have more than %d on the field, and %d on the bench", game.numPlayers, game.numBench));

	}
	
	/**
	 * Instantiates a new team full exception.
	 *
	 * @param game the game
	 * @param message the message
	 */
	public TeamFullException(GameEnvironment game, String message) {
		super(message);
	}
	
	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return super.getMessage();
	}
}
