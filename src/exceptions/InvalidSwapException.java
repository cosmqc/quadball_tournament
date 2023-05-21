package exceptions;

import base.*;

@SuppressWarnings("serial")
public class InvalidSwapException extends Exception {
	GameEnvironment game;
	
	public InvalidSwapException(GameEnvironment game) {
		super();
	}
	
	public InvalidSwapException(GameEnvironment game, String message) {
		super(message);
	}
	
	public String getMessage() {
		return super.getMessage();
	}
}
