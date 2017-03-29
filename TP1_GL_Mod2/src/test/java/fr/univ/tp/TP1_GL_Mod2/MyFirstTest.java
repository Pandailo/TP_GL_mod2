package fr.univ.tp.TP1_GL_Mod2;
import junit.framework.*;
import junit.framework.Assert;

import java.util.logging.Logger;

import org.junit.*;
import org.junit.Test;

public class MyFirstTest {
	private static Logger log=Logger.getLogger(MyFirstTest.class.getSimpleName());
	@BeforeClass
	public static void firstInit()
	{
		log.info("Première méthode appelée");
	}
	@AfterClass
	public static void lastDestroy(){log.info("Dernière méthode appelée");}
	@Before
	public void beforeTest(){log.info("Méthode appelée avant chaque test");}
	@After
	public void afterTest(){log.info("Méthode appelée avant chaque test");}
	@Test
	public void appTest()
	{
		log.info("Premier test");
		Assert.assertTrue( true );
	}
	@Test
	public void secondTest() {
		log.info("Mon deuxieme test");
		Assert.assertFalse(false);
	}
	
	@Ignore
	@Test
	public void testIgnore() {log.info("Test ignoré");}
	
	@Test
	public void testNull(){
		Assert.assertNull(null);
	}
}
