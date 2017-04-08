package tests;
import java.io.*;
import java.util.logging.Logger;
import org.junit.*;

import main.java.exceptions.FileException;
import main.java.utils.FileUtils;

public class FileUtilsTest {
	private static Logger log = Logger.getLogger(FileUtilsTest.class.getSimpleName());
	@Test
	public void testWriteInFile()
	{
		/*
		 * Vérifie la bonne execution de la méthode d'écriture de {@link FileUTils} <br />
		 * Créé le ficheir test.txt par la méthode et vérifie s'il est crée <br />
		 * Ensuite vérifie si le contenu du fichier correspond à ce qu'on a écrit dedans. <br />
		 */
		log.info("Execution test creation et écriture fichier");
		String t="test\n";
		FileUtils fu=FileUtils.getInstance();
		try{
			FileUtils.writeInFile("files/testW.txt", t);
			String chaine="";
			try{
					InputStream ips=new FileInputStream("files/testW.txt"); 
					InputStreamReader ipsr=new InputStreamReader(ips);
					BufferedReader br=new BufferedReader(ipsr);
					String ligne;
					while ((ligne=br.readLine())!=null){
						chaine+=ligne+"\n";
					}
					br.close(); 
					Assert.assertEquals(t,chaine);
					log.info("Test writeInFile réussi !");
			}
			catch(FileNotFoundException e)
			{
				log.severe(e.getMessage());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		catch (FileException e)
		{
			log.severe(e.getMessage());
		}
		//Destruction du fichier à la fermeture du test.
		new File("files/testW.txt").delete();
	}
	
	@Test
	public void testReadFromFile()
	{
		
	}
}
