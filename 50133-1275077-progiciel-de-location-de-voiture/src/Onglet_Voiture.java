import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

 
	 public class Onglet_Voiture extends JPanel {
			private static String rowCount,rowCount2,rowCount3,rowCount4,rowCount5,rowCount6;
			private  int row1,row2,row3,row4,row6;
			private JTextField text3,text1,text2,text4,text6;
	 	public Onglet_Voiture(){

			try {
				Border[] listBorder = {	
						BorderFactory.createEtchedBorder(Color.green, Color.GRAY),
						BorderFactory.createLineBorder(Color.green),
						BorderFactory.createLineBorder(Color.orange),
						BorderFactory.createLineBorder(Color.red),
						BorderFactory.createMatteBorder(5, 2, 5, 2, Color.black),
						BorderFactory.createRaisedBevelBorder(),
						BorderFactory.createTitledBorder("Titre")
						
							
					};
				 JButton actualiser = new JButton("Actualiser");
				 actualiser.setPreferredSize(new Dimension(200, 20));
				 
				 actualiser.addActionListener(new ActionListener(){

				 	public void actionPerformed(ActionEvent arg0) {
				 		try{
				 	
				 			row2 = nombre_vehicule(rowCount2);
				 			 row3 = nombre_vehicule(rowCount3);
				 			text3.setText(""+row3);
				 			text2.setText(""+row2);
				 		
				 			row4 = nombre_vehicule(rowCount4);
				 			
				 			text4.setText(""+row4);
				 	
				 	} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				 	}
				 	
				 });
				rowCount2 = "SELECT modele FROM vehicule WHERE statut = 'disponible' ";
				row2 = nombre_vehicule(rowCount2);
				
				rowCount3 = "SELECT modele FROM vehicule WHERE statut = 'sortie' ";
				row3 =nombre_vehicule(rowCount3);

				rowCount4 = "SELECT modele FROM vehicule WHERE statut = 'reserve' ";
				row4 =nombre_vehicule(rowCount4);
				
				JLabel lib2 = new JLabel("Vehicule disponible:");
				JLabel lib3 = new JLabel("Vehicule sorties:");
				JLabel lib4 = new JLabel("Vehicule reservés:");
				

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
			
				JPanel premiere_couche = new JPanel();
			JLabel vide = new JLabel("                                    ");
			premiere_couche.setPreferredSize(new Dimension(350, 320));
			premiere_couche.setBackground(Color.white);
	if(nombre_vehicule(rowCount2) == 0){
				boutonlib2.setEnabled(false);
				Accueil.liste_voiture_libre.setEnabled(false);
			
	}
	if(nombre_vehicule(rowCount3) == 0){
				boutonlib3.setEnabled(false);
	Accueil.liste_voiture_reserve.setEnabled(false);

	}
	if(nombre_vehicule(rowCount4) == 0){
				boutonlib4.setEnabled(false);
				Accueil.liste_voiture_sorties.setEnabled(false);}
	


	vide.setPreferredSize(new Dimension(600, 20));
	premiere_couche.add(vide);

	JButton consulter = new JButton("Consulter");
	consulter.setPreferredSize(new Dimension(200, 20));
	JButton ajouter = new JButton("Ajouter");
	ajouter.setPreferredSize(new Dimension(200, 20));
	JButton modifier = new JButton("Modifier");
	modifier.setPreferredSize(new Dimension(200, 20));
	JButton supprimer = new JButton("Supprimer");
	supprimer.setPreferredSize(new Dimension(200, 20));
	
	ajouter.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent arg0) {
			Ajouter zd = new Ajouter(null, "Ajouter un vehicule", true);
		}				
	});

	//CONSULTER VEHICULE

	consulter.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent arg0) {
			Consulter c = new Consulter();
			c.setVisible(true);
		}				
	});

	//MODIFIER VEHICULE
	modifier.addActionListener(new ActionListener(){
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
	supprimer.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent arg0) {
			try {
				Supprimer_vehicule s = new Supprimer_vehicule();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}				
	});
	premiere_couche.add(consulter);
	premiere_couche.add(ajouter);
	premiere_couche.add(modifier);
	premiere_couche.add(supprimer);
	premiere_couche.add(actualiser);
	premiere_couche.add(panlib2);
	premiere_couche.add(panlib4);
	premiere_couche.add(panlib3);

			
			
			
		
			//premiere_couche.add(imprimer);
		
			
		  
	boutonlib2.addActionListener(new ActionListener(){

		public void actionPerformed(ActionEvent arg0) {
			String requete = "SELECT marque,modele FROM vehicule WHERE statut = 'disponible' ORDER BY marque ";
			try {
				Accueil.vehicule(requete);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
});

boutonlib3.addActionListener(new ActionListener(){

		public void actionPerformed(ActionEvent arg0) {
			String requete = "SELECT marque,modele FROM vehicule WHERE statut = 'sortie'  ORDER BY marque";
			try {
				Accueil.vehicule(requete);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
});


boutonlib4.addActionListener(new ActionListener(){

		public void actionPerformed(ActionEvent arg0) {
			String requete = "SELECT marque,modele FROM vehicule WHERE statut = 'reserve' ORDER BY marque";
			try {
				Accueil.vehicule(requete);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
});

		
			
			JLabel jlb = new JLabel(new ImageIcon("images/voiture_cls.jpg"));
			jlb.setPreferredSize(new Dimension(300, 320));
			this.setBackground(Color.white);
			JPanel doubl = new JPanel();
			doubl.add(jlb);
			doubl.add(premiere_couche);
			doubl.setBackground(Color.white);
		//	initToolBar();
			this.add(doubl,  BorderLayout.CENTER);
			this.setBackground(Color.white);

		
			  
	 
			
	 	
	 	
	 	  }catch(Exception e){}
	 	}
	 	  public void paintComponent(Graphics g){
	            try {
	                    Image img = ImageIO.read(new File("images/fond.jpg"));
	                     g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
	            } catch (IOException e) {
	                    // TODO Auto-generated catch block
	                    e.printStackTrace();
	            }
	            
	    } 
	 	  

	 	 synchronized public  int nombre_vehicule(String requete) throws RemoteException {
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
	 			Location l = new Location(null, "Informations Véhicule", true);
	 			
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
	 	synchronized public void user(String requete) throws RemoteException {
	 		// TODO Auto-generated method stub
	 	Liste_connecte c = new Liste_connecte(requete);
	 		
	 		
	 		c.setVisible(true);
	 	}
	 	synchronized public void vehicule(String requete) throws RemoteException {
	 		// TODO Auto-generated method stub
	 		
	 		Liste_vehicule c = new Liste_vehicule(requete);
	 		
	 		
	 		c.setVisible(true);
	 	}
	 
	 	synchronized public void user() throws RemoteException {
	 		// TODO Auto-generated method stub
	 		Liste_user c = new Liste_user();
	 		c.setVisible(true);
	 	}


	 	synchronized public void vehicule_tout() throws RemoteException {
	 		// TODO Auto-generated method stub
	 		Liste_vehicule c = new Liste_vehicule();
	 		c.setVisible(true);
	 		
	 	}
	 }