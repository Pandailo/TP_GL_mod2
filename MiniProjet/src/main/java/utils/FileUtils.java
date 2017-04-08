package main.java.utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Logger;
import java.io.FileOutputStream;
import java.io.FileWriter;

import main.java.exceptions.FileException;
import tests.FileUtilsTest;

public class FileUtils {

		/**
		 * Utils style Singleton
		 * Permet l'�criture/lecture d'un fichier. <br />
		 */
		private static Logger log = Logger.getLogger(FileUtilsTest.class.getSimpleName());

		private FileUtils(){
			
		}
		
		private static class FileUtilsHolder {
			private final static FileUtils instance = new FileUtils();
			
		}
		
		public static FileUtils getInstance(){
			return FileUtilsHolder.instance;
		}
		
		public static void writeInFile(String nomFich,String entree) throws FileException
		{
			/*
			 * Ecrit et créé un fichier f qui contient la chaine entree
			 */
			File fich=new File(nomFich);
			try
			{
				fich.createNewFile();
				FileWriter fw=new FileWriter(fich);
				fw.write(entree);  // écrire une ligne dans le fichier fich.txt
				fw.close(); // ferme le fichier à la fin des traitements
			} 
			catch (Exception e) 
			{
				log.severe("IO exception, ecriture / creation de fichier.");
			}
			
		}
		
		
		public static String readFromFile(File fich)
		{
			/*
			 * Lit dans un fichier f, et retourne la globalité du fichier sous forme de chaine
			 */
			String s="";/*TODO A CHANGER*/
		    FileInputStream fis = null;
		    BufferedInputStream bis = null;
		    BufferedReader dis = null;
		 
		    try 
		    {
		    	fis = new FileInputStream(fich);
		 
		    	bis = new BufferedInputStream(fis);
		    	dis = new BufferedReader(new InputStreamReader(bis));
			 
		    	while (dis.readLine() != null) {
			 
			        s+=""+(dis.readLine());
		      }
		 
		      // fermeture de tous les flux ouverts
		      fis.close();
		      bis.close();
		      dis.close();
		 
		    } catch (FileNotFoundException e) {
		      e.printStackTrace();
		    } catch (IOException e) {
		      e.printStackTrace();
		    }
			return s;
		}

}
