package main.java.dao;


import java.sql.Connection;
import java.sql.SQLException;

import main.java.utils.ConnexionUtils;
import oracle.jdbc.OraclePreparedStatement;
import oracle.jdbc.OracleResultSet;

public class MusiqueDao {
	
	public void enregistrer (String[][] enr,String table) throws SQLException
	{
		Connection con=ConnexionUtils.getInstance().getConnexion();
		OraclePreparedStatement st=(OraclePreparedStatement)con.prepareStatement("INSERT INTO "+table+" VALUES(0,?,?,?)");
		for(int i=0;i<enr.length;i++)
		{
			st.setString(1,enr[i][0]);
			st.setInt(2, Integer.parseInt(enr[i][1]));
			st.setString(3, enr[i][2]);
			st.execute();
		}
	}
	
	public OracleResultSet recuperer(int id,String table) throws SQLException
	{
		Connection con = ConnexionUtils.getInstance().getConnexion();
		OraclePreparedStatement st = (OraclePreparedStatement) con.prepareStatement("SELECT titre,duree,artiste FROM "+table+" WHERE id=?");
		st.setInt(1, id);
		OracleResultSet rs = (OracleResultSet) st.executeQuery();
		return rs;
	}
}
