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

 
	 public class Contrat extends JPanel {
			private static String rowCount,rowCount2,rowCount3,rowCount4,rowCount5,rowCount6;
			private  int row1,row2,row3,row4,row6;
			private JTextField text3,text1,text2,text4,text6;
	 	public Contrat(){

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
				 		 row1 = nombre_vehicule(rowCount);
				 		
				 			text1.setText(""+row1);
				 		
				 	} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				 	}
				 	
				 });
				rowCount = "SELECT id FROM facture WHERE statut = 'sortie' ";
				row1 = nombre_vehicule(rowCount);
			
				
	
				JLabel lib = new JLabel("Nombre de contrat:");
			
		
		
			JButton contra = new JButton("Nouveau contrat");
			contra.setPreferredSize(new Dimension(200, 20));

			JButton annule = new JButton("Annuler un contrat");
			annule.setPreferredSize(new Dimension(200, 20));
			
			annule.addActionListener(new ActionListener(){

				public void actionPerformed(ActionEvent arg0) {
				
					
					
					Accueil.annuler_contra();
					
				
					
				
				}
				
	});
			
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
			
			
	
		
			
			JPanel premiere_couche = new JPanel();
			premiere_couche.setPreferredSize(new Dimension(350, 320));
			premiere_couche.setBackground(Color.white);
			if(nombre_vehicule(rowCount) == 0) {
				boutonlib.setEnabled(false);
				Accueil.getListe_poste().setEnabled(false);
			}
		
			JLabel vide = new JLabel("                                    ");
			vide.setPreferredSize(new Dimension(600, 20));
			premiere_couche.add(vide);
			premiere_couche.add(contra);
			premiere_couche.add(annule);
			premiere_couche.add(actualiser);
			premiere_couche.add(panlib);
		
			
			boutonlib.addActionListener(new ActionListener(){

				public void actionPerformed(ActionEvent arg0) {
					String requete = "SELECT id,nom,prenom,nss,marque,modele FROM facture WHERE statut='sortie' ORDER BY id ";
					Accueil.contrat_list(requete);
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
			
		
			
			JLabel jlb = new JLabel(new ImageIcon("images/contra_cls.jpg"));
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