package main.java.service;

import java.io.FileNotFoundException;

import main.java.exceptions.FileException;
import main.java.exceptions.NoRowSelectedException;

public interface MusiqueService {

	public String lireFichier(String path) throws FileNotFoundException;

	public void ecrire(String path,String contenu) throws FileException;

	public String extraire(int id) throws NoRowSelectedException;

	public void sauvegarder(String contenu);
}
