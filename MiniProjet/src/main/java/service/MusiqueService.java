package main.java.service;

public interface MusiqueService {

	public String lireFichier(String path);

	public void ecrire(String contenu, String path);

	public String extraire(int id);

	public void sauvegarder(String contenu);
}
