package test.java.dao;

import java.sql.Connection;
import java.sql.SQLException;

import main.java.dao.MusiqueDao;
import main.java.utils.ConnexionUtils;

import oracle.jdbc.OraclePreparedStatement;
import oracle.jdbc.OracleResultSet;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class TestMusiqueDao {
	@Rule
	public ExpectedException exception=ExpectedException.none();
	
	
	
	@Test
	public void testSauvegarderInNull() throws SQLException
	{
		exception.expect(SQLException.class);
		MusiqueDao m=new MusiqueDao();
		String[][] s=new String[1][3];
		s[0][0]="titre";
		s[0][1]=""+2;
		s[0][2]="artiste";
		m.enregistrer(s, null);
	}
	
	@Test
	public void testSauvegarder() throws SQLException
	{
		MusiqueDao m=new MusiqueDao();
		String[][] s=new String[1][3];
		s[0][0]="titre";
		s[0][1]=""+2;
		s[0][2]="artiste";
		m.enregistrer(s, "PGL_Musique");
		Connection con=ConnexionUtils.getInstance().getConnexion();
		OraclePreparedStatement ps=(OraclePreparedStatement) con.prepareStatement("SELECT titre,duree,artiste,id FROM PGL_Musique WHERE titre=? AND duree=? AND artiste = ?");
		ps.setString(1,s[0][0]);
		ps.setInt(2,Integer.parseInt(s[0][1]));
		ps.setString(3, s[0][2]);
		OracleResultSet rs=(OracleResultSet) ps.executeQuery();
		rs.next();
		Assert.assertEquals(s[0][0], rs.getString(1));
		Assert.assertEquals(Integer.parseInt(s[0][1]),rs.getInt(2));
		Assert.assertEquals(s[0][2], rs.getString(3));
		ps=(OraclePreparedStatement) con.prepareStatement("DELETE FROM PGL_Musique WHERE titre=?");
		ps.setString(1, rs.getString(1));
		ps.execute();
		con.commit();
	}
	
	@Test
	public void testExtraireFromNull() throws SQLException
	{
		exception.expect(SQLException.class);
		MusiqueDao m=new MusiqueDao();
		m.recuperer(0,"tableQuiNexistePas");
	}

}
