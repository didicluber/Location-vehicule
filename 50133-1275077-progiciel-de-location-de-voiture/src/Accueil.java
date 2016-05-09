import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.UnknownHostException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.border.Border;


public class Accueil extends JFrame {


	private static final JMenuItem Bon_de_reservation = null;
	private JMenuBar menuBar = new JMenuBar();
	private JMenu edition = new JMenu("Edition");
	private JMenu location = new JMenu("Vehicule");
	private JMenu clients = new JMenu("Clients");
	private JMenu facture = new JMenu("Facture");
	private JMenu reservation = new JMenu("Réservation");
	private JMenu contrat = new JMenu("Contrat");
	private JMenuItem bdd = new JMenuItem("Base de données");
	private JMenu aide = new JMenu("Aide");
	private JMenu user = new JMenu("Utilisateurs");
	public static String adr;
	private JMenu config = new JMenu("Paramètres");
	private JMenuItem serveur = new JMenuItem("Envoyer message au serveur");
	private JMenuItem factura = new JMenuItem("Facture");
	private JMenuItem aide_support = new JMenuItem("Aide et Support");
	private static JMenuItem liste_poste = new JMenuItem("Liste des membres CSL connectés");
	private JMenuItem liste_tout = new JMenuItem("Liste du groupe CSL");
	private JMenuItem liste_client = new JMenuItem("Liste de tout les clients");
	private JMenuItem liste_client_attente = new JMenuItem("Liste des clients en attente");
	private JMenuItem liste_client_cours = new JMenuItem("Liste des clients sous contrat");
	static JMenuItem liste_voiture_libre = new JMenuItem("Liste des vehicules disponible");
	static JMenuItem liste_voiture_sorties = new JMenuItem("Liste des vehicule sorties");
	static JMenuItem liste_voiture_reserve = new JMenuItem("Liste des vehicule réservés");
	private JMenuItem liste_voiture_retard = new JMenuItem("Liste des vehicule en retard");
	private JMenuItem tout_voiture = new JMenuItem("Liste de toutes les voitures");
	private JMenuItem nouvelle_facture = new JMenuItem("Nouvelle Facture");
	private JMenuItem annuler_contrat = new JMenuItem("Annuler un contrat");
	private JMenuItem contrat_listes = new JMenuItem("Liste des contrats");
	private JMenuItem facture_listes = new JMenuItem("Liste des factures");
	private JMenuItem contrat_de_location = new JMenuItem("Contrat de location");
	private JMenuItem bon_de_reservation = new JMenuItem("Bon de reservation");
	private JMenuItem liste_des_reservation = new JMenuItem("Liste des réservation");
	private JMenuItem fermer = new JMenuItem("Fermer");
	private JMenu maj_voiture = new JMenu("Mise à jour");
	private JMenuItem consulter_vehicule = new JMenuItem("Consulter");
	private JMenuItem ajouter_vehicule = new JMenuItem("Créer");
	private JMenuItem modifier_vehicule = new JMenuItem("Modifier");
	private JMenuItem supprimer_vehicule = new JMenuItem("Supprimer");
	private JMenu maj_user = new JMenu("Mise à jour");
	private JMenuItem consulter = new JMenuItem("Consulter");
	private JMenuItem ajouter = new JMenuItem("Créer");
	private JMenuItem modifier = new JMenuItem("Modifier");
	private JMenuItem supprimer = new JMenuItem("Supprimer");
	final Background contient= new Background();
	final Background2 contient2= new Background2();
	private static JSplitPane split;
	private static JPanel menu = new JPanel();
	private JTabbedPane p;
	
	private static boolean ok =false, gauche=true, droite=false,haut=false,bas=false;
	private static String rowCount,rowCount2,rowCount3,rowCount4,rowCount5,rowCount6;
	private  int row1,row2,row3,row4,row6;
	private JTextField text3,text1,text2,text4,text6;
	public static int onglet = JTabbedPane.LEFT;
	
	public Accueil(boolean ok){
		this.ok=ok;
		
	}
	public Accueil(){
		
		this.setTitle("Chabanus Sheep Auto Location ");
		this.setSize(new Dimension(800, 600));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
	
		fermer.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}				
		});
		this.config.add(bdd);
		this.config.add(fermer);
		
	
		// NOUVELLE FACTURE
		nouvelle_facture.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				
				facture();
				
			}
			
		});
		
		icone_facture.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				
				facture();
				
			}
			
		});
		
		
	
		contrat_de_location.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
			
				
				
				try {

					marques();
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			
				
			
			}
			
		});
		
		icone_contrat.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
			
				
				
				try {

					marques();
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			
				
			
			}
			
		});
		annuler_contrat.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				
				annuler_contra();
				
			}

			
			
		});
		
		contrat_listes.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				String requete = "SELECT id,nom,prenom,nss,marque,modele FROM facture WHERE statut='sortie' ORDER BY id ";
				contrat_list(requete);
			}
			
		});
		
		liste_des_reservation.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				String requete = "SELECT id,nom,prenom,nss,marque,modele FROM facture WHERE statut='attente' ORDER BY id ";
				reservation_list(requete);
			}
			
		});
		
		connecte.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				String requete = "SELECT id,nom,prenom,nss,marque,modele FROM facture WHERE statut='sortie' ORDER BY id ";
				contrat_list(requete);
			}
			
		});
		
		facture1.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				String requete = "SELECT id,nom,prenom,nss,marque,modele FROM facture WHERE statut='regler' ORDER BY id ";
				facture_list(requete);
			}
			
		});
		
		bon_de_reservation.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				try {
					try {
						Reserva l = new Reserva(null, "Réservation : Informations Véhicule", true);
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		 			
		 		} catch (MalformedURLException e) {
		 			// TODO Auto-generated catch block
		 			e.printStackTrace();
		 		} catch (SQLException e) {
		 			// TODO Auto-generated catch block
		 			e.printStackTrace();
		 		} catch (NotBoundException e) {
		 			// TODO Auto-generated catch block
		 			e.printStackTrace();
		 		}
			}
			
		});
		
		icone_reserva.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				try {
					try {
						Reserva l = new Reserva(null, "Réservation : Informations Véhicule", true);
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		 			
		 		} catch (MalformedURLException e) {
		 			// TODO Auto-generated catch block
		 			e.printStackTrace();
		 		} catch (SQLException e) {
		 			// TODO Auto-generated catch block
		 			e.printStackTrace();
		 		} catch (NotBoundException e) {
		 			// TODO Auto-generated catch block
		 			e.printStackTrace();
		 		}
			}
			
		});
		
		reservee.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				String requete = "SELECT id,nom,prenom,nss,marque,modele FROM facture WHERE statut='attente' ORDER BY id ";
				reservation_list(requete);
			}
			
		});
		
		sortie1.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
		String requete = "SELECT marque,modele FROM vehicule WHERE statut = 'sortie'  ORDER BY marque";
		vehicule(requete);
		
	}
			
		});
		facture_listes.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				String requete = "SELECT id,nom,prenom,nss,marque,modele FROM facture WHERE statut='regler' ORDER BY id ";
				facture_list(requete);
			}
			
		});
		
		liste_client.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				String requete = "SELECT nom,prenom,nss,adresse,tel,permis,etat FROM client ORDER BY id ";
				clients(requete);
			}
			
		});
		
		client.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				String requete = "SELECT nom,prenom,nss,adresse,tel,permis,etat FROM client ORDER BY id ";
				clients(requete);
			}
			
		});
		liste_client_cours.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				String requete = "SELECT nom,prenom,etat FROM client WHERE etat='servis' ORDER BY id ";
				clients(requete);
			}
			
		});
		liste_client_attente.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				String requete = "SELECT nom,prenom,etat FROM client WHERE etat='reserve' ORDER BY id ";
				clients(requete);
			}
			
		});
		// LISTE VOITURES DISPONIBLE
		liste_voiture_libre.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				String requete = "SELECT marque,modele FROM vehicule WHERE statut = 'disponible'  ORDER BY marque ";
				vehicule(requete);
			}
			
		});
		
		disponible.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				String requete = "SELECT marque,modele FROM vehicule WHERE statut = 'disponible'  ORDER BY marque ";
				vehicule(requete);
			}
			
		});
		
		liste_voiture_sorties.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				String requete = "SELECT marque,modele FROM vehicule WHERE statut = 'sortie'  ORDER BY marque";
				vehicule(requete);
			}
			
		});
		
	
		liste_voiture_reserve.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				String requete = "SELECT marque,modele FROM vehicule WHERE statut = 'reserve'  ORDER BY marque";
vehicule(requete);
			}
			
		});
		
		reserve.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				String requete = "SELECT marque,modele FROM vehicule WHERE statut = 'reserve'  ORDER BY marque";
vehicule(requete);
			}
			
		});
		
		liste_voiture_retard.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				String requete = "SELECT marque,modele FROM vehicule WHERE statut = 'retard'  ORDER BY marque";
				vehicule(requete);
			}
			
		});
		
		tout_voiture.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				vehicule_tout();
			}
			
		});
		
		getListe_poste().addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				String requete = "SELECT log FROM utilisateurs WHERE statut = 'en ligne' ";
				user(requete);
			}
			
		});
		
		
		liste_tout.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				user();
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
		config.setBackground(Color.white);
		location.setBackground(Color.white);
		clients.setBackground(Color.white);
		reservation.setBackground(Color.white);
		contrat.setBackground(Color.white);
		facture.setBackground(Color.white);
		user.setBackground(Color.white);
		liste_voiture_libre.setBackground(Color.white);
		config.setMnemonic('P');
		menuBar.add(config);
		
		location.setMnemonic('V');
		menuBar.add(location);
		    	
		clients.setMnemonic('l');
		menuBar.add(clients);
		
		
		
		contrat.setMnemonic('C');
		menuBar.add(contrat);
		reservation.setMnemonic('R');
		menuBar.add(reservation);
		facture.setMnemonic('F');
		menuBar.add(facture);
		
		user.setMnemonic('U');
		menuBar.add(user);
		
	
		this.setVisible(true);
			
this.user.add(maj_user);
		
		maj_user.add(consulter);
		maj_user.add(ajouter);
		maj_user.add(modifier);
		maj_user.add(supprimer);
		maj_user.setBackground(Color.white);
		consulter.setBackground(Color.white);
		ajouter.setBackground(Color.white);
		modifier.setBackground(Color.white);
		supprimer.setBackground(Color.white);
		
		
		
		
		
		// /////////////////////////////////////////////////////
		
		
		
		
		
		
	
		this.location.add(liste_voiture_libre);
		this.location.add(liste_voiture_sorties);
		this.location.add(liste_voiture_reserve);
		this.location.add(tout_voiture);
		this.location.addSeparator();
		this.location.add(maj_voiture);
	
		liste_voiture_sorties.setBackground(Color.white);
		liste_voiture_reserve.setBackground(Color.white);
		tout_voiture.setBackground(Color.white);
		maj_voiture.setBackground(Color.white);
		
		
		liste_voiture_libre.setIcon(new ImageIcon("images/disponible.jpg"));
		liste_voiture_sorties.setIcon(new ImageIcon("images/sortie.jpg"));
		liste_voiture_reserve.setIcon(new ImageIcon("images/reserve.jpg"));
		tout_voiture.setIcon(new ImageIcon("images/list_voiture.jpg"));
		maj_voiture.setIcon(new ImageIcon("images/maj.jpg"));
		maj_user.setIcon(new ImageIcon("images/maj.jpg"));
		liste_voiture_libre.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_4,
                KeyEvent.CTRL_MASK));
		liste_voiture_sorties.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_5,
                KeyEvent.CTRL_MASK));
		liste_voiture_reserve.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_6,
                KeyEvent.CTRL_MASK));
		liste_voiture_retard.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_7,
                KeyEvent.CTRL_MASK));
		tout_voiture.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_8,
                KeyEvent.CTRL_MASK));
		maj_voiture.setBackground(Color.white);
		maj_voiture.add(consulter_vehicule);
		maj_voiture.add(ajouter_vehicule);
		maj_voiture.add(modifier_vehicule);
		maj_voiture.add(supprimer_vehicule);
	
		consulter_vehicule.setBackground(Color.white);
		modifier_vehicule.setBackground(Color.white);
		ajouter_vehicule.setBackground(Color.white);
		supprimer_vehicule.setBackground(Color.white);
		bdd.setBackground(Color.white);
		fermer.setBackground(Color.white);
		liste_client.setBackground(Color.white);
		liste_client_attente.setBackground(Color.white);
		liste_client_cours.setBackground(Color.white);
		
		bdd.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1,
                KeyEvent.CTRL_DOWN_MASK + KeyEvent.SHIFT_DOWN_MASK));
		fermer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2,
                KeyEvent.CTRL_DOWN_MASK + KeyEvent.SHIFT_DOWN_MASK));
		consulter_vehicule.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,
                KeyEvent.CTRL_DOWN_MASK + KeyEvent.SHIFT_DOWN_MASK));
		ajouter_vehicule.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,
                KeyEvent.CTRL_DOWN_MASK + KeyEvent.SHIFT_DOWN_MASK));
		modifier_vehicule.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M,
                KeyEvent.CTRL_DOWN_MASK + KeyEvent.SHIFT_DOWN_MASK));
		supprimer_vehicule.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
                KeyEvent.CTRL_DOWN_MASK + KeyEvent.SHIFT_DOWN_MASK));
		
		consulter.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,
                KeyEvent.CTRL_DOWN_MASK + KeyEvent.ALT_DOWN_MASK));
		ajouter.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,
                KeyEvent.CTRL_DOWN_MASK + KeyEvent.ALT_DOWN_MASK));
		modifier.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M,
                KeyEvent.CTRL_DOWN_MASK + KeyEvent.ALT_DOWN_MASK));
		supprimer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
                KeyEvent.CTRL_DOWN_MASK + KeyEvent.ALT_DOWN_MASK));
		
		this.clients.add(liste_client);
		this.clients.add(liste_client_attente);
		this.clients.add(liste_client_cours);
		
		
		liste_client_attente.setIcon(new ImageIcon("images/client_res.jpg"));
		liste_client_cours.setIcon(new ImageIcon("images/client_loc.jpg"));
		liste_client.setIcon(new ImageIcon("images/client.jpg"));
		liste_client.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1,
                KeyEvent.CTRL_MASK));
		liste_client_attente.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2,
                KeyEvent.CTRL_MASK));
		liste_client_cours.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3,
                KeyEvent.CTRL_MASK));
		
			 contrat.addMouseMotionListener(new MouseMotionListener(){
					public void mouseDragged(MouseEvent e) {
						 try
						    {
							 p.setSelectedIndex(1);
						    }catch(Exception e11 ){}
					}

					@Override
					public void mouseMoved(MouseEvent arg0) {
						// TODO Auto-generated method stub
						p.setSelectedIndex(1);
					}
					}
		 );
			 location.addMouseMotionListener(new MouseMotionListener(){
					public void mouseDragged(MouseEvent e) {
						 try
						    {
							 p.setSelectedIndex(5);
						    }catch(Exception e11 ){}
					}

					@Override
					public void mouseMoved(MouseEvent arg0) {
						// TODO Auto-generated method stub
						p.setSelectedIndex(5);
					}
					}
		 );
			 
			 clients.addMouseMotionListener(new MouseMotionListener(){
					public void mouseDragged(MouseEvent e) {
						 try
						    {
							 p.setSelectedIndex(4);
						    }catch(Exception e11 ){}
					}

					@Override
					public void mouseMoved(MouseEvent arg0) {
						// TODO Auto-generated method stub
						p.setSelectedIndex(4);
					}
					}
		 );
			 facture.addMouseMotionListener(new MouseMotionListener(){
					public void mouseDragged(MouseEvent e) {
						 try
						    {
							 p.setSelectedIndex(3);
						    }catch(Exception e11 ){}
					}

					@Override
					public void mouseMoved(MouseEvent arg0) {
						// TODO Auto-generated method stub
						p.setSelectedIndex(3);
					}
					}
		 );
				
			 reservation.addMouseMotionListener(new MouseMotionListener(){
					public void mouseDragged(MouseEvent e) {
						 try
						    {
							 p.setSelectedIndex(2);
						    }catch(Exception e11 ){}
					}

					@Override
					public void mouseMoved(MouseEvent arg0) {
						// TODO Auto-generated method stub
						p.setSelectedIndex(2);
					}
					}
		 );
			 /*
			  disponible = new JButton(new ImageIcon("images/disponible.jpg")),
			  sortie1 = new JButton(new ImageIcon("images/sortie.jpg")),
			  reserve = new JButton(new ImageIcon("images/reserve.jpg")),
			  retard = new JButton(new ImageIcon("images/retard.jpg")),
			  client = new JButton(new ImageIcon("images/client.jpg")),
			  client_contrat = new JButton(new ImageIcon("images/client_loc.jpg")),
			  client_attente = new JButton(new ImageIcon("images/client_res.jpg")),
			  facture1 = new JButton(new ImageIcon("images/facture.jpg")),
			  reservee = new JButton(new ImageIcon("images/reservee.jpg")),
			  change_onglet = new JButton(new ImageIcon("images/change_onglet.jpg")),
			  change_onglet_haut = new JButton(new ImageIcon("images/change_onglet.jpg")),
			  change_onglet_droit= new JButton(new ImageIcon("images/change_onglet.jpg")),
			  change_onglet_bas = new JButton(new ImageIcon("images/change_onglet.jpg")),
			  icone_contrat = new JButton(new ImageIcon("images/icone_contra.jpg")),
			  icone_reserva = new JButton(new ImageIcon("images/icone_reserva.jpg")),
			  icone_facture = new JButton(new ImageIcon("images/icone_facture.jpg"));
			*/
			 nouvelle_facture.setIcon(new ImageIcon("images/icone_facture.jpg"));
			 facture_listes.setIcon(new ImageIcon("images/facture.jpg"));
			 contrat_de_location.setIcon(new ImageIcon("images/icone_contra.jpg"));
			 contrat_listes.setIcon(new ImageIcon("images/connecte.jpg"));
			 bon_de_reservation.setIcon(new ImageIcon("images/icone_reserva.jpg"));
			 liste_des_reservation.setIcon(new ImageIcon("images/reservee.jpg"));
			
		this.facture.add(nouvelle_facture);
		this.facture.add(facture_listes);
		this.contrat.add(contrat_de_location);
		this.reservation.add(bon_de_reservation);
		this.reservation.add(liste_des_reservation);
		this.contrat.add(annuler_contrat);
		this.contrat.add(contrat_listes);
	
		
			
	
		
		   

		this.menuBar.add(contrat);
		this.menuBar.add(reservation);
		this.menuBar.add(facture);
			
			this.menuBar.add(clients);
			this.menuBar.add(location);
			this.menuBar.add(user);
			
	
			
			this.setJMenuBar(menuBar);
			
			bon_de_reservation.setBackground(Color.white);
	
			nouvelle_facture.setBackground(Color.white);
			facture_listes.setBackground(Color.white);
			contrat_de_location.setBackground(Color.white);
			annuler_contrat.setBackground(Color.white);
			liste_des_reservation.setBackground(Color.white);
			contrat_listes.setBackground(Color.white);
			
			annuler_contrat.setIcon(new ImageIcon("images/annula.jpg"));
			bon_de_reservation.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B,
                    KeyEvent.CTRL_MASK));
			liste_des_reservation.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,
                    KeyEvent.CTRL_MASK));
			
			nouvelle_facture.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,
                    KeyEvent.CTRL_MASK));
			facture_listes.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,
                    KeyEvent.CTRL_MASK));
			
			contrat_de_location.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M,
                    KeyEvent.CTRL_MASK));
			contrat_listes.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
                    KeyEvent.CTRL_MASK));
			annuler_contrat.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
                    KeyEvent.CTRL_MASK));
			facture.add(nouvelle_facture);
			Border[] listBorder = {	
					BorderFactory.createEtchedBorder(Color.green, Color.GRAY),
					BorderFactory.createLineBorder(Color.green),
					BorderFactory.createLineBorder(Color.orange),
					BorderFactory.createLineBorder(Color.red),
					BorderFactory.createMatteBorder(5, 2, 5, 2, Color.black),
					BorderFactory.createRaisedBevelBorder(),
					BorderFactory.createTitledBorder("Titre")
					
						
				};
			
		
			rowCount = "SELECT id FROM facture WHERE statut = 'sortie' ";
			nombre_vehicule(rowCount);

			rowCount2 = "SELECT modele FROM vehicule WHERE statut = 'disponible' ";
			nombre_vehicule(rowCount2);
			
			rowCount3 = "SELECT modele FROM vehicule WHERE statut = 'sortie' ";
			nombre_vehicule(rowCount3);

			rowCount4 = "SELECT modele FROM vehicule WHERE statut = 'reserve' ";
			nombre_vehicule(rowCount4);
			
			rowCount5 = "SELECT modele FROM vehicule WHERE statut = 'retard' ";
			nombre_vehicule(rowCount5);
			rowCount6 = "SELECT id FROM facture WHERE statut = 'attente' ";
			
		
			 row1 = nombre_vehicule(rowCount);
			row2 = nombre_vehicule(rowCount2);
			 row3 = nombre_vehicule(rowCount3);
			row4 = nombre_vehicule(rowCount4);
		
			 row6 = nombre_vehicule(rowCount6);

			 JButton actualiser = new JButton("Actualiser");
			 actualiser.setPreferredSize(new Dimension(200, 20));
			 
			 actualiser.addActionListener(new ActionListener(){

			 	public void actionPerformed(ActionEvent arg0) {
			 		
			 		 row1 = nombre_vehicule(rowCount);
			 			row2 = nombre_vehicule(rowCount2);
			 			 row3 = nombre_vehicule(rowCount3);
			 			text3.setText(""+row3);
			 			text2.setText(""+row2);
			 			text1.setText(""+row1);
			 			row4 = nombre_vehicule(rowCount4);
			 			 row6 = nombre_vehicule(rowCount6);
			 			text4.setText(""+row4);
			 			text6.setText(""+row6);
			 			 System.out.println(row4);
			 	}
			 	
			 });
		
	

JLabel lib = new JLabel("Nombre de contrat:");
JLabel lib2 = new JLabel("Vehicule disponible:");
JLabel lib4 = new JLabel("Vehicule reservés:");
JLabel lib3 = new JLabel("Vehicule sorties:" );
JLabel lib6 = new JLabel("Nombre de réservation:");

JButton facture = new JButton("Nouvelle facture");
facture.setPreferredSize(new Dimension(200, 20));
JButton contra = new JButton("Nouveau contrat");
contra.setPreferredSize(new Dimension(200, 20));
JButton reserva = new JButton("Nouvelle reservation");
reserva.setPreferredSize(new Dimension(200, 20));



facture.addActionListener(new ActionListener(){

	public void actionPerformed(ActionEvent arg0) {
		
		facture();
		
	}
	
});

reserva.addActionListener(new ActionListener(){

	public void actionPerformed(ActionEvent arg0) {
		
		reserva();
		
	}

	private void reserva() {
		// TODO Auto-generated method stub
		try {
			try {
				Reserva l = new Reserva(null, "Réservation : Informations Véhicule", true);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
 			
 		} catch (MalformedURLException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		} catch (SQLException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		} catch (NotBoundException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
	}
	
});
JPanel panlib = new JPanel();
lib.setPreferredSize(new Dimension(160, 20));
panlib.setBorder(listBorder[0]);
lib.setAlignmentX(JLabel.CENTER);
text1 =  new JTextField(""+row1);
text1.setPreferredSize(new Dimension(50, 20));
JLabel iconlib = new JLabel(new ImageIcon("images/connecte.jpg"));
JButton boutonlib = new JButton("Voir");
panlib.add(iconlib);
panlib.add(lib);
panlib.add(text1);
panlib.add(boutonlib);
panlib.setBackground(Color.white);



JPanel panlib2 = new JPanel();
lib2.setPreferredSize(new Dimension(160, 20));
panlib2.setBorder(listBorder[1]);
lib2.setAlignmentX(JLabel.CENTER);
text2 =  new JTextField(""+row2);
text2.setPreferredSize(new Dimension(50, 20));
JLabel iconlib2 = new JLabel(new ImageIcon("images/disponible.jpg"));
JButton boutonlib2 = new JButton("Voir");
panlib2.add(iconlib2);
panlib2.add(lib2);
panlib2.add(text2);
panlib2.add(boutonlib2);
panlib2.setBackground(Color.white);


JPanel panlib3 = new JPanel();
lib3.setPreferredSize(new Dimension(160, 20));
panlib3.setBorder(listBorder[3]);
lib3.setAlignmentX(JLabel.CENTER);
text3 =  new JTextField(""+row3);
text3.setPreferredSize(new Dimension(50, 20));
JLabel iconlib3 = new JLabel(new ImageIcon("images/sortie.jpg"));
JButton boutonlib3 = new JButton("Voir");
panlib3.add(iconlib3);
panlib3.add(lib3);
panlib3.add(text3);
panlib3.add(boutonlib3);
panlib3.setBackground(Color.white);

JPanel panlib4 = new JPanel();
lib4.setPreferredSize(new Dimension(160, 20));
panlib4.setBorder(listBorder[2]);
lib4.setAlignmentX(JLabel.CENTER);
text4 =  new JTextField(""+row4);
text4.setPreferredSize(new Dimension(50, 20));
JLabel iconlib4 = new JLabel(new ImageIcon("images/reserve.jpg"));
JButton boutonlib4 = new JButton("Voir");
panlib4.add(iconlib4);
panlib4.add(lib4);
panlib4.add(text4);
panlib4.add(boutonlib4);
panlib4.setBackground(Color.white);

JPanel panlib6 = new JPanel();
lib6.setPreferredSize(new Dimension(160, 20));
panlib6.setBorder(listBorder[0]);
lib6.setAlignmentX(JLabel.CENTER);
text6 =  new JTextField(""+row6);
text6.setPreferredSize(new Dimension(50, 20));
JLabel iconlib6 = new JLabel(new ImageIcon("images/reservee.jpg"));
JButton boutonlib6 = new JButton("Voir");
panlib6.add(iconlib6);
panlib6.add(lib6);
panlib6.add(text6);
panlib6.add(boutonlib6);
panlib6.setBackground(Color.white);

JPanel premiere_couche = new JPanel();
premiere_couche.setPreferredSize(new Dimension(350, 380));
premiere_couche.setBackground(Color.white);
if(row1 == 0) {
			boutonlib.setEnabled(false);
			getListe_poste().setEnabled(false);
			facture1.setEnabled(false);
		
}
if(row2 == 0){
			boutonlib2.setEnabled(false);
			liste_voiture_libre.setEnabled(false);
			disponible.setEnabled(false);
}
if(row3== 0){
			boutonlib3.setEnabled(false);
liste_voiture_reserve.setEnabled(false);

sortie1.setEnabled(false);}
if(row4 == 0){
			boutonlib4.setEnabled(false);
liste_voiture_sorties.setEnabled(false);
reserve.setEnabled(false);}

if(row6 == 0) {
	boutonlib.setEnabled(false);
	Accueil.getListe_poste().setEnabled(false);
}

JLabel vide = new JLabel("                                    ");
vide.setPreferredSize(new Dimension(600, 20));
premiere_couche.add(vide);
premiere_couche.add(contra);
premiere_couche.add(reserva);
premiere_couche.add(facture);
premiere_couche.add(actualiser);
premiere_couche.add(panlib);
premiere_couche.add(panlib6);
premiere_couche.add(panlib2);
premiere_couche.add(panlib4);
premiere_couche.add(panlib3);

//premiere_couche.add(panlib5);



// LISTE VOITURES DISPONIBLE
boutonlib2.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				String requete = "SELECT marque,modele FROM vehicule WHERE statut = 'disponible' ORDER BY marque ";
				vehicule(requete);
			}
			
});

boutonlib3.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				String requete = "SELECT marque,modele FROM vehicule WHERE statut = 'sortie'  ORDER BY marque";
				vehicule(requete);
			}
			
});

boutonlib.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				String requete = "SELECT id,nom,prenom,nss,marque,modele FROM facture WHERE statut='sortie' ORDER BY id ";
				contrat_list(requete);
			}
			
});

boutonlib6.addActionListener(new ActionListener(){

	public void actionPerformed(ActionEvent arg0) {
		String requete = "SELECT id,nom,prenom,nss,marque,modele FROM facture WHERE statut='attente' ORDER BY id ";
		reservation_list(requete);
	}
	
});
boutonlib4.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				String requete = "SELECT marque,modele FROM vehicule WHERE statut = 'reserve' ORDER BY marque";
				vehicule(requete);
			}
			
});








//premiere_couche.add(imprimer);


  
contra.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
			
				
				try {
					marques();
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			
				
			
			}
			
});

client_contrat.addActionListener(new ActionListener(){

	public void actionPerformed(ActionEvent arg0) {
	
		
		String requete = "SELECT nom,prenom FROM client WHERE etat = 'servis' ORDER BY id ";
		try {
			vehicule(requete);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		
	
	}
	
});

client_attente.addActionListener(new ActionListener(){

	public void actionPerformed(ActionEvent arg0) {
	
		
		String requete = "SELECT nom,prenom FROM client WHERE etat = 'reserve'  ORDER BY id";
		try {
			vehicule(requete);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		
	
	}
	
});

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
		BDD zd = new BDD(null, "Paramètres : Informations base de donnée", true);
		
	
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

change_onglet.addActionListener(new ActionListener(){
	public void actionPerformed(ActionEvent arg0) {
		if(gauche) {
		p.setTabPlacement(1);
		gauche=false;
		haut = true;
		
		}
		else if(haut){
			p.setTabPlacement(2);
			haut=false;
			gauche=false;
			droite=false;
			bas=true;
			
		}
		else if(bas){
			p.setTabPlacement(3);
			bas=false;
			haut=false;
			gauche=false;
			droite=true;
			
		}
		else if(droite){
			p.setTabPlacement(4);
			droite=false;
			bas=false;
			haut=false;
			gauche=true;
			
		}
	}				
});




JLabel jlb = new JLabel(new ImageIcon("images/accueil_cls.jpg"));
jlb.setPreferredSize(new Dimension(300, 320));
contient.setBackground(Color.white);
JPanel doubl = new JPanel();
doubl.add(jlb);
doubl.add(premiere_couche);
doubl.setBackground(Color.white);
initToolBar();
contient.add(doubl,  BorderLayout.CENTER);
contient.setBackground(Color.white);



p = new JTabbedPane(onglet);

p.add("",contient);
p.setIconAt(0, new ImageIcon("images/tbord.png"));
p.add("", new Contrat());
p.setIconAt(1, new ImageIcon("images/contrat.png"));
p.add("", new Onglet_reservation());
p.setIconAt(2, new ImageIcon("images/reserva.png"));
p.add("", new Onglet_facture());
p.setIconAt(3, new ImageIcon("images/facture.png"));
p.add("", new Onglet_Client());
p.setIconAt(4, new ImageIcon("images/client.png"));
p.add("", new Onglet_Voiture());
p.setIconAt(5, new ImageIcon("images/vehicule.png"));
menu.setBackground(Color.white);
p.setBackground(Color.white);
split = new JSplitPane(JSplitPane.VERTICAL_SPLIT, menu,p);
split.setOneTouchExpandable(true);
split.setBackground(Color.white);


//contient.add(new JLabel("Progiciel répartie - Programmer en java évenementielle par REFES Chabane - Copie non autorisé"), BorderLayout.SOUTH);
this.setContentPane(split);

	
	}
	public class Background extends JPanel {
		 
	    public void paintComponent(Graphics g){
	            try {
	                    Image img = ImageIO.read(new File("images/fond2.jpg"));
	                     g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
	            } catch (IOException e) {
	                    // TODO Auto-generated catch block
	                    e.printStackTrace();
	            }
	            
	    }               
	}	
	
	public class Background2 extends JPanel {
		 
	                
	}	
	public static void main(String[] args) throws UnknownHostException, MalformedURLException, RemoteException, NotBoundException, InterruptedException{
		BDD zd = new BDD(null, "Etape 1/2 : Configuration de la base de donnée", true);
		new Client(null, "Etape 2/2 : Informations utilisateur", true);
		if(true){
	chargement wind = new chargement();
		wind.setVisible(true);
		Thread.sleep(4500);
		wind.setVisible(false);
		
		
	
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
			
				new Accueil();
			}
		});
		}
	}
	  private JToolBar toolBar = new JToolBar();
	  private JButton 	connecte = new JButton(new ImageIcon("images/connecte.jpg")),
	  disponible = new JButton(new ImageIcon("images/disponible.jpg")),
	  sortie1 = new JButton(new ImageIcon("images/sortie.jpg")),
	  reserve = new JButton(new ImageIcon("images/reserve.jpg")),
	  retard = new JButton(new ImageIcon("images/retard.jpg")),
	  client = new JButton(new ImageIcon("images/client.jpg")),
	  client_contrat = new JButton(new ImageIcon("images/client_loc.jpg")),
	  client_attente = new JButton(new ImageIcon("images/client_res.jpg")),
	  facture1 = new JButton(new ImageIcon("images/facture.jpg")),
	  reservee = new JButton(new ImageIcon("images/reservee.jpg")),
	  change_onglet = new JButton(new ImageIcon("images/change_onglet.jpg")),
	  change_onglet_haut = new JButton(new ImageIcon("images/change_onglet.jpg")),
	  change_onglet_droit= new JButton(new ImageIcon("images/change_onglet.jpg")),
	  change_onglet_bas = new JButton(new ImageIcon("images/change_onglet.jpg")),
	  icone_contrat = new JButton(new ImageIcon("images/icone_contra.jpg")),
	  icone_reserva = new JButton(new ImageIcon("images/icone_reserva.jpg")),
	  icone_facture = new JButton(new ImageIcon("images/icone_facture.jpg"));
	  
	 
private Color fondBouton = Color.white;

	 private void initToolBar(){
	    	
	    	//this.contrat_listes.setBackground(fondBouton);
		 icone_facture.setToolTipText("Faire une nouvelle facture");
		 icone_contrat.setToolTipText("Faire une nouveau contrat");
		 icone_reserva.setToolTipText("Faire une nouvelle réservation");
		 change_onglet.setToolTipText("Changer la position de l'onglet");
		 reservee.setToolTipText("Afficher la liste des réservation");
		 facture1.setToolTipText("Afficher la liste des factures");
		 client_attente.setToolTipText("Afficher la liste des clients ayant réservé");
		 client_contrat.setToolTipText("Afficher la liste des clients sous contrats");
		 reserve.setToolTipText("Afficher la liste des vehicules en réservation");
		 sortie1.setToolTipText("Afficher la liste des vehicules non disponible");
		 disponible.setToolTipText("Afficher la liste des vehicules disponible");
		 
		 this.toolBar.add(icone_contrat);
	    	this.toolBar.add(icone_reserva);
	    	this.toolBar.add(icone_facture);
		 this.icone_contrat.setBackground(fondBouton);
	    	this.icone_reserva.setBackground(fondBouton);
	    	this.icone_facture.setBackground(fondBouton);
	    	this.toolBar.addSeparator();
	    	this.toolBar.addSeparator();
	    	this.toolBar.addSeparator();
		
	    	
	    	
	   	this.toolBar.add(change_onglet);
		 
	
		 this.change_onglet.setBackground(fondBouton);
	   	
			this.toolBar.addSeparator();
	    	this.toolBar.addSeparator();
	    	this.toolBar.addSeparator();
	    	this.toolBar.add(connecte);
	    	this.toolBar.add(reservee);
	    	this.toolBar.add(facture1);
	    	this.toolBar.addSeparator();
	    	this.toolBar.addSeparator();
	    	this.toolBar.addSeparator();
	    	this.connecte.setBackground(fondBouton);
	    	this.reservee.setBackground(fondBouton);
	    	this.facture1.setBackground(fondBouton);
	    	this.disponible.setBackground(fondBouton);
	    	this.toolBar.add(disponible);
	    
	    	
	    	//Ajout des Listeners
	    	
	    	this.reserve.setBackground(fondBouton);
	    	this.toolBar.add(reserve);
	    	
	    	this.sortie1.setBackground(fondBouton);
	    	this.toolBar.add(sortie1);
	    	
	    	
	    	
	    	this.toolBar.addSeparator();
	    	this.toolBar.addSeparator();
	    	this.toolBar.addSeparator();
	    	
	    	this.client.setBackground(fondBouton);
	    	this.toolBar.add(client);
	    	this.client_contrat.setBackground(fondBouton);
	    	this.toolBar.add(client_contrat);
	    	this.client_attente.setBackground(fondBouton);
	    	this.toolBar.add(client_attente);
	    //	this.retard.setBackground(fondBouton);
	    	//this.toolBar.add(retard);
	    	
	    
	    	toolBar.setBackground(Color.white);
	    	menu.add(toolBar, BorderLayout.CENTER);
	    	menu.setPreferredSize(new Dimension(600, 50));
	    }

	 
	 
	 
	 // CONTRAT CONTRAT CONTRAT CONTRAT CONTRAT CONTRAT CONTRAT CONTRAT CONTRAT CONTRAT CONTRAT 
	 // CONTRAT CONTRAT CONTRAT CONTRAT CONTRAT CONTRAT CONTRAT CONTRAT CONTRAT CONTRAT CONTRAT 
	 
	 
	 

 	 synchronized static public  int nombre_vehicule(String requete)  {
 	 	// TODO Auto-generated method stub
 	 	Statement state;
 	 	int rowCount = 0;
 	 	try {
 	 		state = Connect.getInstance().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

 	 	ResultSet res = state.executeQuery(requete);
 	 	res.last();
 	 	rowCount = res.getRow();                    
 	 	res.close();
 	 	state.close();
 	 	} catch (SQLException e) {
 	 		// TODO Auto-generated catch block
 	 		e.printStackTrace();
 	 	}
 	 	return rowCount;
 	 }
 	 
 	synchronized public void marques() throws RemoteException {
 		// TODO Auto-generated method stub
 		
 		try {
 			Location l = new Location(null, "Contrat : Informations Véhicule", true);
 			
 		} catch (MalformedURLException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		} catch (SQLException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		} catch (NotBoundException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}

 	}
 	synchronized public void user(String requete) {
 		// TODO Auto-generated method stub
 	Liste_connecte c = new Liste_connecte(requete);
 		
 		
 		c.setVisible(true);
 	}
 	synchronized public static void vehicule(String requete)  {
 		// TODO Auto-generated method stub
 		
 		Liste_vehicule c = new Liste_vehicule(requete);
 		
 		
 		c.setVisible(true);
 	}
 	synchronized public void clients(String requete)  {
 		// TODO Auto-generated method stub
 		
 		Liste_clients c = new Liste_clients(requete);
 		
 		
 		c.setVisible(true);
 	}
 	synchronized public void user()  {
 		// TODO Auto-generated method stub
 		Liste_user c = new Liste_user();
 		c.setVisible(true);
 	}


 	synchronized public void vehicule_tout()  {
 		// TODO Auto-generated method stub
 		Liste_vehicule c = new Liste_vehicule();
 		c.setVisible(true);
 		
 	}
 	
 	synchronized public static void facture()  {
 		// TODO Auto-generated method stub
 		try {
			facture c = new facture();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 	
 		
 	}
 	synchronized public static void annuler_contra() {
 	// TODO Auto-generated method stub
 		try {
			annule_contrat c = new annule_contrat();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
 	synchronized static public void contrat_list(String requete)  {
 		contrat_liste c = new contrat_liste(requete);
 		c.setVisible(true);
 		
 	}
 	synchronized static public void facture_list(String requete)  {
 		facture_liste c = new facture_liste(requete);
 		c.setVisible(true);
 		
 	}

	synchronized static public void reservation_list(String requete)  {
		reservation_liste c = new reservation_liste(requete);
 		c.setVisible(true);
 		
 	}
 	
	public void setListe_poste(JMenuItem liste_poste) {
		this.liste_poste = liste_poste;
	}




	public static JMenuItem getListe_poste() {
		return liste_poste;
	}
	
	 }
	

