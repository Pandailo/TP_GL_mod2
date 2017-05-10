package main.java.service;

import main.java.exceptions.NoRowSelectedException;

public interface MusiqueService {

	public String lireFichier(String path);

	public void ecrire(String contenu, String path);

	public String extraire(int id) throws NoRowSelectedException;

	public void sauvegarder(String contenu);
}
