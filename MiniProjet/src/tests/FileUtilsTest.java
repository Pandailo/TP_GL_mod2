package tests;
import java.io.*;
import java.util.logging.Logger;
import org.junit.*;
import org.junit.rules.ExpectedException;

import main.java.exceptions.FileException;
import main.java.utils.FileUtils;

public class FileUtilsTest {
	private static Logger log = Logger.getLogger(FileUtilsTest.class.getSimpleName());
	@Rule
	public ExpectedException exception=ExpectedException.none();
	@Test
	public void testWriteInFile()
	{
		/*
		 * V�rifie la bonne execution de la m�thode d'�criture de {@link FileUTils} 
		 *<br />
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
					log.info("Test writeInFile r�ussi avec une utilisation normale");
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
	}
	
	/*
	 * Creation des jeux de test
	 */
	@BeforeClass
	public static void creationJeuTest()
	{
		log.info("Creation des jeux de test");
		String t="test\n";
		File fich=new File("files/testR.txt");
		try
		{
			fich.createNewFile();
			FileWriter fw=new FileWriter(fich);
			fw.write(t); 
			fw.close(); 
		} 
		catch (Exception e) 
		{
			log.severe("IO exception, ecriture / creation de fichier.");
		}
	}
	/*
	 * Destruction des jeux de test
	 */
	@AfterClass
	public static void destructionJeuTest()
	{
		log.info("Destruction des jeux de test");
		new File("files/testW.txt").delete();
		new File("files/testR.txt").delete();
	}
	/*
	 * Verifie la bonne execution de la methode de lecture de {@link FileUTils}<br />
	 *
	 */
	@Test
	public void testReadFromFile()
	{
		/*
		 * Utilisation normale
		 */
		String fich="files/testR.txt";
		String resultat="";
		String resultatexpected="test";
		FileUtils fu=FileUtils.getInstance();
		try{
			resultat=fu.readFromFile(fich);
		}
		catch(FileNotFoundException e)
		{
			log.severe(e.getMessage());
		}
		Assert.assertEquals(resultatexpected, resultat);
		if(resultatexpected.equals(resultat))
			log.info("Test writeInFile r�ussi avec une utilisation normale");

	}
	/*
	 * Test la robustesse de la methode de lecture si le fichier est inexistant
	 */
	
	@Test
	public void testReadFromNullFile() throws FileNotFoundException
	{
		exception.expect(FileNotFoundException.class);
		String fich="fichierquinexistepas.abc";
		FileUtils fu=FileUtils.getInstance();
		String resultat="";
			try {
				resultat=fu.readFromFile(fich);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				throw new FileNotFoundException("Reussite");
			}

	}
	/*
	 * Test avec ecriture de string null dans fichier
	 */
	@Test
	public void testWriteNullInFile() throws FileException
	{
		log.info("Execution testWriteNullInFile() ");
		exception.expect(FileException.class);
		String t=null;
		FileUtils fu=FileUtils.getInstance();
		try{
			FileUtils.writeInFile("files/testW.txt", t);
		}
		catch (FileException e)
		{
			throw new FileException(e.getMessage());
		}
	}
}
