package test.java.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Logger;

import main.java.dao.MusiqueDao;
import main.java.exceptions.FileException;
import main.java.exceptions.NoRowSelectedException;
import main.java.service.impl.MusiqueServiceImpl;
import main.java.utils.ConnexionUtils;
import main.java.utils.FileUtils;

import oracle.jdbc.OraclePreparedStatement;
import oracle.jdbc.OracleResultSet;

import org.junit.*;
import org.junit.rules.ExpectedException;

import test.java.utils.FileUtilsTest;


public class MusiqueServiceImplTest {
	private static Logger log = Logger.getLogger(MusiqueServiceImplTest.class
			.getSimpleName());
	@Rule
	public ExpectedException exception=ExpectedException.none();
	
	@BeforeClass
	public static void init()
	{
		log.info("Creation des jeux de test");
		String t = "Oui,8,Non";
		File fich = new File("testOK.txt");
		String t2="yaquunevaleur";
		File fich2=new File("testPasOk.txt");
		try {
			fich.createNewFile();
			FileWriter fw = new FileWriter(fich);
			fw.write(t);
			fw.close();
			fich2.createNewFile();
			FileWriter fw2 = new FileWriter(fich2);
			fw2.write(t2);
			fw2.close();
		} catch (Exception e) {
			log.severe("IO exception, ecriture / creation de fichier.");
		}
	}
	@AfterClass
	public static void destruct()
	{
		log.info("Destruction des jeux de test");
		new File("testOK.txt").delete();
		new File("testPasOk.txt").delete();
	}
	@Test
	public void testEcrireFichier() throws FileNotFoundException, FileException
	{
		String juste="oui";
		MusiqueServiceImpl serv=new MusiqueServiceImpl();
		serv.ecrire("oui","testWOK.txt");
		String in=FileUtils.getInstance().readFromFile("testWOK");
		Assert.assertEquals(juste,in);
		new File("testWOK.txt").delete();
	}
	
	@Test
	public void testEcrireNullFichier() throws FileException 
	{
		exception.expect(FileException.class);
		MusiqueServiceImpl serv=new MusiqueServiceImpl();
			serv.ecrire("testWPASOK.txt",null);
	}
	
	@Test
	public void testEcrireFichierNull() throws FileException
	{
		exception.expect(FileException.class);
		MusiqueServiceImpl serv=new MusiqueServiceImpl();
		serv.ecrire(null, "gagaga");
	}
	
	@Test
	public void testLireFichier() throws FileNotFoundException
	{
		log.info("test lire fichier");
		MusiqueServiceImpl serv=new MusiqueServiceImpl();
		String juste="Oui,8,Non";
		Assert.assertEquals(juste,serv.lireFichier("testOK.txt"));
	}

	@Test
	public void testLireFichierNull() throws FileNotFoundException
	{
		exception.expect(FileNotFoundException.class);
		log.info("test lire fichier null");
		MusiqueServiceImpl serv=new MusiqueServiceImpl();
		String juste="Oui,8,Non";
		String temp=serv.lireFichier("fichierquinexistetoujourspas.abc");
	}
		
	@Test
	public void testSauvegarder() throws SQLException
	{
		MusiqueServiceImpl serv=new MusiqueServiceImpl();
		serv.sauvegarder("test2,2,test3");
		Connection con=ConnexionUtils.getInstance().getConnexion();
		OraclePreparedStatement ps=(OraclePreparedStatement) con.prepareStatement("SELECT titre,duree,artiste,id FROM PGL_Musique WHERE titre=? AND duree=? AND artiste = ?");
		ps.setString(1,"test2");
		ps.setInt(2,2);
		ps.setString(3,"test3");
		OracleResultSet rs=(OracleResultSet) ps.executeQuery();
		rs.next();
		Assert.assertEquals("test2", rs.getString(1));
		Assert.assertEquals(2,rs.getInt(2));
		Assert.assertEquals("test3", rs.getString(3));
		ps=(OraclePreparedStatement) con.prepareStatement("DELETE FROM PGL_Musique WHERE titre=?");
		ps.setString(1, rs.getString(1));
		ps.execute();
		con.commit();
	}
	
	@Test
	public void testSauvegarderNull()
	{
		exception.expect(NullPointerException.class);
		MusiqueServiceImpl service=new MusiqueServiceImpl();
		service.sauvegarder(null);
	}
	
	@Test
	public void testExtraire() throws NoRowSelectedException
	{
		MusiqueServiceImpl service=new MusiqueServiceImpl();
		String s = service.extraire(0);
		String[] split=s.split(",");
		Assert.assertEquals("Oui", split[0]);
		Assert.assertEquals("2", split[1]);
		Assert.assertEquals("Non\n", split[2]);		
	}
	
	@Test
	public void testExtraireIdNotFound() throws NoRowSelectedException
	{
		exception.expect(NoRowSelectedException.class);
		MusiqueServiceImpl service=new MusiqueServiceImpl();
		service.extraire(1000);
	}
	
	/* ==> Seule SQLEx venant de MusiqueDao -> deja testée ??
	@Test
	public void testExtraireSQLException()
	{
		exception.expect(SQLException.class);
		MusiqueServiceImpl service=new MusiqueServiceImpl();	
	}*/
	
	@Test
	public void testExtraireFormatException() throws NoRowSelectedException, SQLException
	{
		MusiqueServiceImpl service=new MusiqueServiceImpl();
		String s = service.extraire(0);
		String[] split=s.split(",");
		Connection con=ConnexionUtils.getInstance().getConnexion();
		OraclePreparedStatement ps=(OraclePreparedStatement) con.prepareStatement("SELECT titre,duree,artiste FROM PGL_Musique");
		OracleResultSet rs=(OracleResultSet) ps.executeQuery();
		ResultSetMetaData metadata = rs.getMetaData(); 
		int nombreColonnes = metadata.getColumnCount();
		Assert.assertEquals(nombreColonnes,split.length);	
	}
}
