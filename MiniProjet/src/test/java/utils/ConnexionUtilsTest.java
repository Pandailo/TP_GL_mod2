package test.java.utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;

import main.java.utils.ConnexionUtils;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ConnexionUtilsTest {
	private static Logger log = Logger.getLogger(FileUtilsTest.class
			.getSimpleName());

	@Test
	public void testGetInstance() {
		log.info("Execution test instance");
		ConnexionUtils con = ConnexionUtils.getInstance();
		Assert.assertEquals(ConnexionUtils.class, con.getClass());
		if (con.getClass() == ConnexionUtils.class) {
			log.info("testGetInstance reussi");
		}
	}

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void testGetConnexion() throws SQLException {
		log.info("test GetConnexion");
		Connection con = ConnexionUtils.getInstance().getConnexion();
		Assert.assertEquals(Connection.class, con.getClass());
		if(Connection.class.equals(con.getClass()))
		{
			log.info("testGetConnexion reussis.");
		}
	}
}
