package main.java.facade;

import java.io.FileNotFoundException;

import main.java.exceptions.FileException;
import main.java.exceptions.NoRowSelectedException;
import main.java.service.MusiqueService;
import main.java.service.impl.MusiqueServiceImpl;

public class MusiqueFacadeImpl {
	/*
	 * Enregistre un fichier dans la BDD
	 */
	public void saveInDb(String path) throws NoRowSelectedException, FileNotFoundException {
		MusiqueService serv = new MusiqueServiceImpl();
		String contenu=serv.lireFichier(path);
		serv.sauvegarder(contenu);
		
	}
	/*
	 * Enregistre une ligne de la BDD dans un fichier
	 */
	public void saveInFile(String path, int id) throws NoRowSelectedException, FileException{
		MusiqueService serv=new MusiqueServiceImpl();
		String content=serv.extraire(id);
		serv.ecrire(path,content);
	}

}
