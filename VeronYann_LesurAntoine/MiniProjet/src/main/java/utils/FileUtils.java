package main.java.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Logger;

import main.java.exceptions.FileException;

public class FileUtils {

	/**
	 * Utils style Singleton Permet l'�criture/lecture d'un fichier. <br />
	 */
	private static Logger log = Logger.getLogger(FileUtils.class
			.getSimpleName());

	private FileUtils() {

	}

	private static class FileUtilsHolder {
		private final static FileUtils instance = new FileUtils();

	}

	public static FileUtils getInstance() {
		return FileUtilsHolder.instance;
	}

	public static void writeInFile(String nomFich, String entree)
			throws FileException {
		/*
		 * Ecrit et créé un fichier f qui contient la chaine entree
		 */
		if (entree == null||nomFich==null) 
		{
			throw new FileException("Tentative de remplissage avec null");
			
		}
		 else {
			try {
					
						File fich = new File(nomFich);
						fich.createNewFile();
						FileWriter fw = new FileWriter(fich);
						fw.write(entree); // écrire une ligne dans le fichier fich.txt
						fw.close(); // ferme le fichier à la fin des traitements
			
			} catch (Exception e) {
				log.severe("IO exception, ecriture / creation de fichier.");
			}
		}

	}

	public static String readFromFile(String fich) throws FileNotFoundException {
		/*
		 * Lit dans un fichier f, et retourne la globalité du fichier sous forme
		 * de chaine
		 */
		String s = "";/* TODO A CHANGER */
		try {
			InputStream ips = new FileInputStream(fich);
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			String ligne;
			while ((ligne = br.readLine()) != null) {
				s += ligne + "\n";
			}
			br.close();
			ips.close();
			ipsr.close();
		} catch (FileNotFoundException e) {
			log.severe(e.getMessage());
			throw new FileNotFoundException("Fichier inconnu");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		s = s.substring(0, s.length() - 1);
		return s;
	}

}
