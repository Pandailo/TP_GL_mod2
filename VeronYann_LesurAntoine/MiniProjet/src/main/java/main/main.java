package main.java.main;

import java.io.FileNotFoundException;

import main.java.exceptions.FileException;
import main.java.exceptions.NoRowSelectedException;
import main.java.facade.MusiqueFacadeImpl;

public class main {
	public void main() throws FileNotFoundException, NoRowSelectedException, FileException
	{
		MusiqueFacadeImpl impl=new MusiqueFacadeImpl();
		impl.saveInDb("fichier_exemple.txt");
		impl.saveInFile("UnFichier", 0);
	}

}
