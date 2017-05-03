package main.java.facade;

import main.java.service.MusiqueService;
import main.java.service.impl.MusiqueServiceImpl;

public class MusiqueFacadeImpl {

	public void methode() {
		MusiqueService serv = new MusiqueServiceImpl();
		serv.lireFichier("");
	}

}
