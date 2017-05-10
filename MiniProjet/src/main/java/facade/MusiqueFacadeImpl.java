package main.java.facade;

import main.java.exceptions.NoRowSelectedException;
import main.java.service.MusiqueService;
import main.java.service.impl.MusiqueServiceImpl;

public class MusiqueFacadeImpl {

	public void saveInDb(String path) throws NoRowSelectedException {
		MusiqueService serv = new MusiqueServiceImpl();
		String contenu=serv.lireFichier(path);
		serv.sauvegarder(contenu);
		
	}
	public void saveInFile(String path, int id) throws NoRowSelectedException{
		MusiqueService serv=new MusiqueServiceImpl();
		String content=serv.extraire(id);
		serv.ecrire(content, path);
	}

}
