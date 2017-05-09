package main.java.service.impl;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;

import oracle.jdbc.OraclePreparedStatement;
import oracle.jdbc.OracleResultSet;

import main.java.exceptions.FileException;
import main.java.service.MusiqueService;
import main.java.utils.ConnexionUtils;
import main.java.utils.FileUtils;

public class MusiqueServiceImpl implements MusiqueService {

	public String lireFichier(String path) {
		String s = "";
		FileUtils fu = FileUtils.getInstance();
		try {
			s = fu.readFromFile(path);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return s;
	}

	public void ecrire(String contenu, String path) {
		FileUtils fu = FileUtils.getInstance();
		try {
			fu.writeInFile(contenu, path);
		} catch (FileException e) {
			// TODO Bloc catch généré automatiquement
			e.printStackTrace();
		}

	}

	public String extraire(int id) {
		String titre="";
		String artiste="";
		int duree=0;
		String[] s=new String[3];
		String retour="";
		try {
			Connection con = ConnexionUtils.getInstance().getConnexion();
			OraclePreparedStatement st=(OraclePreparedStatement)con.prepareStatement("SELECT titre,duree,artiste FROM PGL_musique WHERE id=?");
			st.setInt(1, id);
			OracleResultSet rs=(OracleResultSet) st.executeQuery();
			rs.next();
			titre=rs.getString(1);
			duree=rs.getInt(2);
			artiste=rs.getString(3);
			s[0]=titre;
			s[1]=""+duree;
			s[2]=artiste;
			retour=formerString(s);
			
			
		} catch (SQLException e) {
			// TODO Bloc catch généré automatiquement
			e.printStackTrace();
		}
		return retour;
	}

	public void sauvegarder(String contenu) {
		// TODO Stub de la méthode généré automatiquement

	}
	
	private String formerString(String[] base)
	{
		String r="";
		for(int i=0;i<base.length;i++)
		{
			r+=base[i]+",";
		}
		r+="\n";
		return r;
	}
}
