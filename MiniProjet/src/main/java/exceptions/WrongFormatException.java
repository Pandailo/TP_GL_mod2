package main.java.exceptions;

public class WrongFormatException extends Exception {

	/**
	 * Exception levée si le format de fichier, de tableau etc... n'est pas le bon
	 * (mauvais nombre de colonnes, ...)
	 */
	private static final long serialVersionUID = 6086199746462576624L;
	public WrongFormatException()
	{
		super();
	}
	public WrongFormatException(String message)
	{
		super(message);
	}

}
