package exceptions;

import base.*;

@SuppressWarnings("serial")
public class NotEnoughMoneyException extends Exception {
	GameEnvironment game;
	
	public NotEnoughMoneyException(GameEnvironment game) {
		super("You don't have enough money.");
	}
	
	public NotEnoughMoneyException(GameEnvironment game, String message) {
		super(message);
	}
	
	public String getMessage() {
		return super.getMessage();
	}
}
