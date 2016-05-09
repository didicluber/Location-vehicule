import java.net.InetAddress;
import java.net.MalformedURLException;
import java.rmi.*;
import java.io.*;
import java.rmi.server.*;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class Verification  {

private static String user,passwd,url,bdd;
private static Connection con;
public Verification()throws RemoteException {};
public Verification(String bdd,String user,String passwd )throws RemoteException {
	
	this.user = user;
	this.bdd = bdd;
	this.passwd = passwd;
	
	
};


synchronized static public boolean Connexion_utilisateur(String login, String pass)
{
	
	boolean valide;
    	
    	
        valide=false;
    	
		try {
			   
				Statement state = Connect.getInstance().createStatement();
				String requete = "SELECT log,passe FROM utilisateurs";
				ResultSet resultat = state.executeQuery(requete);
			
		
			while(resultat.next() && !valide){
				if(login.equals(resultat.getString("log")) && pass.equals(resultat.getString("passe"))) {
					
					valide =true;
				}
							
			}

                        resultat.close();
                        state.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
return  valide;
}
}