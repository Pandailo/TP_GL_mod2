package fr.univ.tp.TP1_GL_Mod2;
import junit.framework.*;
import java.util.logging.Logger;

public class MyFirstTest {
	private static Logger log=Logger.getLogger(MyFirstTest.class.getSimpleName());
	public static void firstInit()
	{
		log.info("Première méthode appelée");
	}
	public static void lastDestroy(){log.info("Dernière méthode appelée");}
	public void beforeTest(){log.info("Méthode appelée avant chaque test");}
	
	public void appTest()
	{
		log.info("Premier test");
		Assert.assertTrue( true );
		Assert.assertFalse( false );
	}
	
}
