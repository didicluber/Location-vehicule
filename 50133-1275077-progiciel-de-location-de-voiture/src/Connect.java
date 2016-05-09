import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Connect{

	private static String url, bdd="Location";
	private static String user="postgres";
	private static String passwd="postgres";
	private static Connection connect;
	
	public Connect(String bdd, String user, String passwd) {
		// TODO Auto-generated constructor stub
		this.bdd = bdd;
		this.user = user;
		this.passwd = passwd;
	}

	/**
	 * @return
	 */
	public static Connection getInstance(){
		url = "jdbc:postgresql://localhost:5432/"+bdd;
		if(connect == null){
			try {
				connect = DriverManager.getConnection(url, user, passwd);
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "ERREUR DE CONNEXION ! ", JOptionPane.ERROR_MESSAGE);
			}
		}		
		return connect;	
	}
}
