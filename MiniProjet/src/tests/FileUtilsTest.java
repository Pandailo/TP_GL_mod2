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
		 * V�rifie la bonne execution de la m�thode d'�criture de {@link FileUTils} <br />
		 * Cr�� le ficheir test.txt par la m�thode et v�rifie s'il est cr�e <br />
		 * Ensuite v�rifie si le contenu du fichier correspond � ce qu'on a �crit dedans. <br />
		 */
		log.info("Execution test creation et �criture fichier");
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
					log.info("Test writeInFile r�ussi !");
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
		//Destruction du fichier � la fermeture du test.
		new File("files/testW.txt").delete();
	}
	
	@Test
	public void testReadFromFile()
	{
		
	}
}
