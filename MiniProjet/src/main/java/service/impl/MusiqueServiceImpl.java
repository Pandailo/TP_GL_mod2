package main.java.service.impl;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;

import main.java.dao.MusiqueDao;
import main.java.exceptions.FileException;
import main.java.exceptions.NoRowSelectedException;
import main.java.exceptions.WrongFormatException;
import main.java.service.MusiqueService;
import main.java.utils.ConnexionUtils;
import main.java.utils.FileUtils;
import oracle.jdbc.OraclePreparedStatement;
import oracle.jdbc.OracleResultSet;

public class MusiqueServiceImpl implements MusiqueService {
	/*
	 * (non-Javadoc)
	 * @see main.java.service.MusiqueService#lireFichier(java.lang.String)
	 * Lis un fichier et retourne la string représentant le contenu.
	 * Utilise la classe fileUtils
	 */
	@Override
	public String lireFichier(String path) throws FileNotFoundException {
		String s = "";
		FileUtils fu = FileUtils.getInstance();
		s = fu.readFromFile(path);
		return s;
	}

	/*
	 * (non-Javadoc)
	 * @see main.java.service.MusiqueService#ecrire(java.lang.String, java.lang.String)
	 *	Ecrit un contenu dans un fichier.
	 */
	@Override
	public void ecrire(String path,String contenu) throws FileException {
		FileUtils fu = FileUtils.getInstance();
		fu.writeInFile(path, contenu);

	}
	/*
	 * (non-Javadoc)
	 * @see main.java.service.MusiqueService#extraire(int)
	 * Extrait les valeurs des attributs de la table Musique dont l'attribut a 
	 * la valeur souhaitee
	 * Les retourne sous forme de string formatee avec 
	 * @see main.java.service.impl.MusiqueService#formerString(String) 
	 */
	@Override
	public String extraire(int id) throws NoRowSelectedException {
		String titre = "";
		String artiste = "";
		int duree = 0;
		String[] s = new String[3];
		String retour = "";
		try {
			MusiqueDao m=new MusiqueDao();
			OracleResultSet rs=m.recuperer(id,"PGL_Musique");
			if(rs.next())
			{
				titre = rs.getString(1);
				duree = rs.getInt(2);
				artiste = rs.getString(3);
				s[0] = titre;
				s[1] = "" + duree;
				s[2] = artiste;
				retour = formerString(s);
			}
			else 
				throw new NoRowSelectedException("Aucune ligne séléctionnée");

		} catch (SQLException | WrongFormatException e) {
			// TODO Bloc catch généré automatiquement
			e.printStackTrace();
		}
		return retour;
	}

	/*
	 * (non-Javadoc)
	 * @see main.java.service.MusiqueService#sauvegarder(java.lang.String)
	 * Sauvegarde dans la BDD le contenu de la string passee en parametres.
	 * La chaine est formatee grace a la methode
	 * @see main.java.service.impl.MusiqueService#formerTable(String)
	 *  
	 */
	@Override
	public void sauvegarder(String contenu) {
		MusiqueDao d=new MusiqueDao();
		try {
			String[][] content=formerTable(contenu);
			d.enregistrer(content,"PGL_Musique");
		} catch (SQLException | WrongFormatException e) {
			e.printStackTrace();
		}
			


	}
	/*
	 * Convertit une chaine composee d'attributs en tableau de chaines 
	 * 1 = titre, 2 = duree, 3 = artiste.
	 */
	private String formerString(String[] base) throws WrongFormatException {
		if(base.length%3==0)
		{
			String r = "";
			for (int i = 0; i < base.length-1; i++) {
				r += base[i] + ",";
			}
			r +=base[base.length-1]+ "\n";
			return r;
		}
		else
			throw new WrongFormatException("Le format du tableau est erroné");
		
	}
	 /*Convertit une chaine de caractères sous format csv en tableau a 2D 
	 * 1ere dim : numero de tuple
	 * 2eme dim : numero d'attribut 1 = titre, 2 = duree, 3 = artiste.
	 * (si string bien formee)
	 */
	private String[][] formerTable(String b) throws WrongFormatException
	{
		String[] split=b.split(",");
		if(split.length%3==0)
		{
			String[][] retour=new String[split.length/3][3];
			int cpti=0;
			int cptj=0;
			for(int i=0;i<split.length;i++)
			{
				if(i%3==0&&i!=0)
				{
					cpti++;
					cptj=0;
				}
				retour[cpti][cptj]=split[i];
				cptj++;
			}
			return retour;
		}
		else
			throw new WrongFormatException("Le format de la chaine de caractère est erroné");
		
	}
}
