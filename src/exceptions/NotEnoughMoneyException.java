package exceptions;

import base.*;

// TODO: Auto-generated Javadoc
/**
 * The Class NotEnoughMoneyException.
 */
@SuppressWarnings("serial")
public class NotEnoughMoneyException extends Exception {
	
	/** The game. */
	GameEnvironment game;
	
	/**
	 * Instantiates a new not enough money exception.
	 *
	 * @param game the game
	 */
	public NotEnoughMoneyException(GameEnvironment game) {
		super("You don't have enough money.");
	}
	
	/**
	 * Instantiates a new not enough money exception.
	 *
	 * @param game the game
	 * @param message the message
	 */
	public NotEnoughMoneyException(GameEnvironment game, String message) {
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
