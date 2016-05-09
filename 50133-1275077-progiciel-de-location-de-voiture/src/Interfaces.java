import java.net.InetAddress;
import java.rmi.*;
import java.sql.*;

import javax.swing.JDialog;
public interface Interfaces  {

	//utilisateur (serveur)
public boolean  Connexion_utilisateur(String login, String pass) throws RemoteException;
public boolean Deconnexion_utilisateur(String login) throws RemoteException;
public boolean ajouter_utilisateur(String login, String pass) throws RemoteException;
public boolean modifier_utilisateur(String login) throws RemoteException;
public void consulter_utilisateur() throws RemoteException;
public boolean supprimer_utilisateur(String login) throws RemoteException;

// vehicule (serveur)
public boolean ajouter_vehicule(String marque, String modele) throws RemoteException;
public boolean modifier_vehicule(String marque, String modele) throws RemoteException;
public void consulter_vehicule() throws RemoteException;
public boolean supprimer_vehicule(String marque, String modele) throws RemoteException;

//utilisateur(serveur & membre)

public void user(String requete) throws RemoteException;
public void user() throws RemoteException;

public int nombre_vehicule(String requete);
//voiture (membre)

public void vehicule(String requete) throws RemoteException;
public void vehicule_tout() throws RemoteException;

//client (membre)

public boolean terminer_location_client(String numero_permis) throws RemoteException;
public boolean valider_location_client(String numero_permis) throws RemoteException;
public void client_attente() throws RemoteException;
public void client_encours() throws RemoteException;
public void client_tout() throws RemoteException;

public void marques() throws RemoteException;

//facture(membre)

public boolean annuler_facture(String numero_permis) throws RemoteException;
public boolean nouvelle_facture(String nom, String prenom, String securite_social, String adresse,
		String telephone, String numero_permis, String numeromeneralogique, String marque, String modele,
		String prix, String kilometrage) throws RemoteException;
public boolean ajouter_client(String nom, String prenom, String securite_social, String adresse,
		String telephone, String numero_permis) throws RemoteException;
public String selectionner_marque(String marque) throws RemoteException;
public String selectionner_modele(String modele) throws RemoteException;


}