package main.java.exceptions;

public class NoRowSelectedException extends Exception {

	/**
	 * Exception levée si la requête ne retourne aucune colonne 
	 */
	private static final long serialVersionUID = -6916954820331775017L;

	public NoRowSelectedException() {
		super();
	}

	public NoRowSelectedException(String message) {
		super(message);
	}

}
