import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.net.*;
import java.net.UnknownHostException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import javax.swing.*;
import javax.swing.border.Border;

import com.sun.java.swing.plaf.motif.MotifBorders.BevelBorder;


public class Serveur extends JFrame {

	private JMenuBar menuBar = new JMenuBar();
	private JMenu fichier = new JMenu("Base de données");
	private JMenu user = new JMenu("Utilisateurs");
	private JMenu voiture = new JMenu("Vehicule");

		
	private JMenuItem bdd = new JMenuItem("Configuration");

	
	private JMenuItem liste_vehicule = new JMenuItem("Liste des vehicules");
	private JMenu maj_voiture = new JMenu("Mise à jour");
	private JMenuItem consulter_vehicule = new JMenuItem("Consulter");
	private JMenuItem ajouter_vehicule = new JMenuItem("Créer");
	private JMenuItem modifier_vehicule = new JMenuItem("Modifier");
	private JMenuItem supprimer_vehicule = new JMenuItem("Supprimer");
	private JMenuItem  liste_user = new JMenuItem("Liste des utilisateurs");
	private JMenuItem liste_connecte = new JMenuItem("Liste des connectés");
	private JMenu maj_user = new JMenu("Mise à jour");
	private JMenuItem consulter = new JMenuItem("Consulter");
	private JMenuItem ajouter = new JMenuItem("Créer");
	private JMenuItem modifier = new JMenuItem("Modifier");
	private JMenuItem supprimer = new JMenuItem("Supprimer");
	private JMenuItem fermer = new JMenuItem("Déconnecter");
	private static ServerSocket s;


public Serveur(){
		
		this.setTitle("Serveur Chabanus Sheep Location");
		this.setSize(600, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		this.fichier.add(bdd);

		//AJOUTER VEHICULE
		ajouter_vehicule.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				Ajouter zd = new Ajouter(null, "Ajouter un vehicule", true);
			}				
		});
		
	//CONSULTER VEHICULE
		
		consulter_vehicule.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				Consulter c = new Consulter();
				c.setVisible(true);
			}				
		});
	// LISTE VEHICULE
		
		liste_vehicule.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				Liste_vehicule c = new Liste_vehicule();
				c.setVisible(true);
			}				
		});
		//MODIFIER VEHICULE
		modifier_vehicule.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				try {
					Modifier_vehicule m = new Modifier_vehicule();
				
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}				
		});
		
		//SUPPRIMER VEHICULE
		supprimer_vehicule.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				try {
					Supprimer_vehicule s = new Supprimer_vehicule();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			}				
		});
		
		//FERMER VEHICULE
		
		fermer.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}				
		});
		
		//CONFIGURATION VEHICULE
		bdd.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				BDD zd = new BDD(null, "Informations base de donnée", true);
				
			
			}
			
		});
		//LISTE UTILISATEUR
		
		liste_user.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				Liste_user c = new Liste_user();
				c.setVisible(true);
			}				
		});
		
	//LISTE UTILISATEUR NON CONNECTE
		
		liste_connecte.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				Liste_connecte c = new Liste_connecte();
				c.setVisible(true);
			}				
		});
	
    //AJOUTER UN UTILISATEUR
		ajouter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				Ajouter_user c = new Ajouter_user(null, "Ajouter un utilisateur", true);
				c.setVisible(true);
			}				
		});
		
		//CONSULTER UTILISATEUR
		
		consulter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				Consulter_user c = new Consulter_user();
				c.setVisible(true);
			}				
		});
		
		// MODIFIER UTILISATEUR
		modifier.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					Modifier_user c1 = new Modifier_user();
				
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}	
			
		});
		
		supprimer.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				try {
					Supprimer_user s = new Supprimer_user();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			}				
		});
	/*commander.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				Client zd = new Client(null, "Commander Véhicule", true);
				ClientInfo zInfo = zd.showZDialog(); 
				JOptionPane jop = new JOptionPane();
				jop.showMessageDialog(null, zInfo.toString(), "Commander vehicule", JOptionPane.INFORMATION_MESSAGE);
			}
			
		});*/
		fichier.setMnemonic('B');
		menuBar.add(fichier);
    	
		user.setMnemonic('U');
		menuBar.add(user);
		    	
		voiture.setMnemonic('V');
		menuBar.add(voiture);
		
		
	
		
		this.voiture.add(liste_vehicule);
		this.voiture.add(maj_voiture);
		
	
        		maj_voiture.add(consulter_vehicule);
        		maj_voiture.add(ajouter_vehicule);
        		maj_voiture.add(modifier_vehicule);
        		maj_voiture.add(supprimer_vehicule);
      

		this.user.add(liste_user);
		this.user.add(liste_connecte);
		this.user.add(maj_user);
		
		maj_user.add(consulter);
		maj_user.add(ajouter);
		maj_user.add(modifier);
		maj_user.add(supprimer);

		/*this.maj.add(consulter);
		this.maj.add(ajouter);
		this.maj.add(modifier);
		this.maj.add(supprimer);

		
			
	*/
			this.menuBar.add(fichier);
			this.menuBar.add(voiture);
			this.menuBar.add(user);

			
			this.setJMenuBar(menuBar);
		

			/*ajouter.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,
                    KeyEvent.CTRL_MASK));
			maj.add(ajouter);*/
			Border[] listBorder = {	
					BorderFactory.createEtchedBorder(Color.black, Color.GRAY),
					BorderFactory.createLineBorder(Color.blue),
					BorderFactory.createMatteBorder(5, 2, 5, 2, Color.black),
					BorderFactory.createRaisedBevelBorder(),
					BorderFactory.createTitledBorder("Titre")
					
						
				};
			try{
				
Statement state = Connect.getInstance().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
ResultSet res = state.executeQuery("SELECT log FROM utilisateurs WHERE statut = 'en ligne' ");
res.last();
int rowCount = res.getRow();                    
res.close();
state.close();

		
Statement state2 = Connect.getInstance().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
ResultSet res2 = state2.executeQuery("SELECT modele FROM vehicule WHERE statut = 'disponible' ");
res2.last();
int rowCount2 = res2.getRow();                    
res2.close();
state2.close();
			

Statement state3 = Connect.getInstance().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
ResultSet res3 = state3.executeQuery("SELECT modele FROM vehicule WHERE statut = 'sortie' ");
res3.last();
int rowCount3 = res3.getRow();             
res3.close();
state3.close();

Statement state4 = Connect.getInstance().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
ResultSet res4 = state4.executeQuery("SELECT modele FROM vehicule WHERE statut = 'reserve' ");
res4.last();
int rowCount4 = res4.getRow();             
res4.close();
state4.close();

Statement state5 = Connect.getInstance().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
ResultSet res5 = state5.executeQuery("SELECT modele FROM vehicule WHERE statut = 'retard' ");
res5.last();
int rowCount5 = res5.getRow();             
res5.close();
state5.close();

			JPanel pan = new JPanel();
			JLabel lib = new JLabel("Membres CSL connectés : "+ rowCount );
			JLabel lib2 = new JLabel("Nombre de vehicule disponible : "+ rowCount2 );
			JLabel lib3 = new JLabel("Nombre de vehicule sorties : "+ rowCount3 );
			JLabel lib4 = new JLabel("Nombre de vehicule reservés : "+ rowCount4 );
			JLabel lib5 = new JLabel("Nombre de vehicule en retard : "+ rowCount5 );
		
			lib.setPreferredSize(new Dimension(350, 50));
			lib.setBorder(listBorder[0]);
			lib.setAlignmentX(JLabel.CENTER);
			lib.setHorizontalAlignment(JLabel.CENTER);
			lib2.setPreferredSize(new Dimension(250, 50));
			lib2.setBorder(listBorder[1]);
			lib2.setAlignmentX(JLabel.CENTER);
			lib2.setHorizontalAlignment(JLabel.CENTER);
			lib3.setPreferredSize(new Dimension(250, 50));
			lib3.setBorder(listBorder[1]);
			lib3.setAlignmentX(JLabel.CENTER);
			lib3.setHorizontalAlignment(JLabel.CENTER);
			lib4.setPreferredSize(new Dimension(250, 50));
			lib4.setBorder(listBorder[1]);
			lib4.setAlignmentX(JLabel.CENTER);
			lib4.setHorizontalAlignment(JLabel.CENTER);
			lib5.setPreferredSize(new Dimension(250, 50));
			lib5.setBorder(listBorder[1]);
			lib5.setAlignmentX(JLabel.CENTER);
			lib5.setHorizontalAlignment(JLabel.CENTER);
			JPanel labelpan = new JPanel();
			JLabel label2 = new JLabel("Informations CLS :");
			label2.setBackground(Color.red);
			label2.setPreferredSize(new Dimension(250, 20));
			label2.setAlignmentX(JLabel.CENTER);
			label2.setHorizontalAlignment(JLabel.CENTER);
			pan.add(label2);
			pan.add(lib);
		    pan.add(lib2);
		    pan.add(lib3);
			pan.add(lib4);
			pan.add(lib5);
			JLabel label = new JLabel("Pour débuter consulter la barre d'outil !");
			label.setPreferredSize(new Dimension(400, 50));
			label.setAlignmentX(JLabel.CENTER);
			label.setHorizontalAlignment(JLabel.CENTER);

			
			
	   
			labelpan.add(label);
		
			this.getContentPane().add(labelpan , BorderLayout.NORTH);
			this.getContentPane().add(pan,  BorderLayout.CENTER);
			this.getContentPane().add(new JLabel("Progiciel répartie - Programmer en java évenementielle par REFES Chabane - Copie non autorisé"), BorderLayout.SOUTH);
			this.setVisible(true);
			}catch(Exception e){}
}
	/*
public static void main(String [] args)
{
 try {
	 LocateRegistry.createRegistry(1099);
	 ImageIcon img = new ImageIcon("images/succes.png");
	 try {
	 Naming.rebind("Location", new Verification());
	} catch (RemoteException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (MalformedURLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 JOptionPane confirmation = new JOptionPane();
		confirmation.showMessageDialog(null, "Le serveur a démarrer ", "Connexion établie", JOptionPane.INFORMATION_MESSAGE, img);
	
	
	BDD zd = new BDD(null, "Configuration de la base de donnée", true);
	
	 javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Serveur();
			}
		});
	 s = new ServerSocket(2000);
	 Thread t = new Thread(new Communication(s.accept()));
	 t.start();
	
	 
	
 }
 catch(Exception e) {System.out.println("Erreur dans le serveur "+e);}
 }
 */
 }
 
