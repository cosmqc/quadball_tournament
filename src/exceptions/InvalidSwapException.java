package exceptions;

import base.*;

// TODO: Auto-generated Javadoc
/**
 * The Class InvalidSwapException.
 */
@SuppressWarnings("serial")
public class InvalidSwapException extends Exception {
	
	GameEnvironment game;
	
	/**
	 * Instantiates a new invalid swap exception.
	 *
	 * @param game the game
	 */
	public InvalidSwapException(GameEnvironment game) {
		super();
	}
	
	/**
	 * Instantiates a new invalid swap exception.
	 *
	 * @param game the game
	 * @param message the message
	 */
	public InvalidSwapException(GameEnvironment game, String message) {
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
