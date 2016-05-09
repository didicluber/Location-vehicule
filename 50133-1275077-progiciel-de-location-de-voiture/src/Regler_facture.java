import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;





  class Regler_facture extends JFrame implements  ActionListener
  {  
  
	  private static Object marque, modele;
	   private static String etat,
		 kilometrage,  prix, nom,  prenom,  securite_social,
		 adresse,  telephone,  numero_permis, numero,statut;
	   private static int id;
    JButton imprimer = new JButton("imprimer");
    ButtonGroup choix = new ButtonGroup();
    JRadioButton choixTout = new JRadioButton("tout", false);
    JRadioButton choixDessin = new JRadioButton("dessin", true);
    JPanel pan = new JPanel();
    
    JPanel p = new JPanel();
    Regler_facture()
    {
    	setTitle("Facture pour "+nom+" "+prenom);
    	Border[] listBorder = {	
				BorderFactory.createEtchedBorder(Color.black, Color.GRAY),
				
				
					
			};
    	
       
	
		JPanel info_societe = new JPanel();
		
		
		JLabel jlb = new JLabel(new ImageIcon("images/enfer.jpg"));
		jlb.setPreferredSize(new Dimension(300, 320));
		jlb.setBackground(Color.white);
	
		//info societe
		info_societe.add(jlb);
		info_societe.setBackground(Color.white);
		
		
		JPanel info_client = new JPanel();
		
		JLabel nom_prenom = new JLabel("Client :"+nom+ " "+prenom);
		JLabel adresse1 = new JLabel("Adresse :"+adresse);
		JLabel nss = new JLabel("Numéro Sécurité Social :"+securite_social);
		JLabel tel = new JLabel("Téléphone :"+telephone);
		JLabel permis = new JLabel("Numéro du permis :"+numero_permis);
		
//info client
		info_client.add(nom_prenom);
		info_client.add(adresse1);
		info_client.add(nss);
		info_client.add(tel);
		info_client.add(permis);
	
		
		info_client.setBackground(Color.white);
		info_client.setBorder(listBorder[0]);
		info_client.setPreferredSize(new Dimension(200, 150));
		
		//info vehicule
		
		JPanel info_vehicule = new JPanel();
		int pri = Integer.parseInt(prix);
		int tva = (pri * 17) / 100;
		int qte = 1;
		int prixht = qte*pri;
	    int ttc = tva+prixht;
		Object[][] data = {	{id, "Réservation de voiture", "marque","Modèle",prix+",00",qte,tva+",00",prixht+",00", ttc+",00"}
			
		};

		String  title[] = {"N°","Designation","marque","Modèle","Prix Unitaire", "Qté","TVA","Prix HT","TTC"};
		int Timbre = 400;
		int total = (int) (Timbre+ttc);
		JTable tableau = new JTable(data, title);
		
		info_vehicule.setPreferredSize(new Dimension(600, 300));
		JScrollPane pane = new JScrollPane(tableau);
		pane.setPreferredSize(new Dimension(580, 35));
		pane.setBackground(Color.white);

		
		Object[][] data2 = {	{"TOTAL HT",prixht+",00"},{"HT avec Remise", prixht+",00"},
				{"TOTAL TVA", tva+",00"},{"Timbre",Timbre },{"Total TTC ",total },
		};

		String  title2[] = {"",""};
		
    JTable tableau2 = new JTable(data2, title2);
		
		
		JScrollPane pane2 = new JScrollPane(tableau2);
		pane2.setPreferredSize(new Dimension(300, 83));
		info_vehicule.add(pane );
		info_vehicule.add(pane2 );
		
		info_client.setBackground(Color.white);
		info_vehicule.setBackground(Color.white);
      pan.add(info_societe);
      pan.add(info_client);
      pan.add(info_vehicule);
     // pan.add(condition);
      
      pan.setBackground(Color.white);
      Container interieur = getContentPane();
      
      choix.add(choixTout);
      choix.add(choixDessin);
    
      imprimer.setPreferredSize(new Dimension(100, 20));
      JPanel impr = new JPanel();
      impr.add(imprimer);
      pan.setPreferredSize(new Dimension(600, 800));
      interieur.add(pan, BorderLayout.CENTER);
      interieur.add(impr, BorderLayout.SOUTH);

      imprimer.addActionListener(this);
      addWindowListener(new WindowAdapter()
  	      {
  		public void windowClosing(WindowEvent evt)
  		  {
  		    System.exit(0);
  		  }
  	      });
    }





	public Regler_facture(String nom, String prenom, String nss,
			String adresse, String tel, String permis, String etat,
			Object marque, Object modele, String numero, String kilometrage,
			String prix, String statut,int id) {
		// TODO Auto-generated constructor stub
		
		this.numero = numero;
		this.marque = marque;
		this.modele = modele;
		this.kilometrage = kilometrage;
		this.prix = prix;
		this.etat = etat;
		this.statut =statut;
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.telephone = tel;
		this.numero_permis = permis;
		this.securite_social = nss;
		this.id = id;
	}

	public void actionPerformed(ActionEvent e)
    {
      Properties props = new Properties();

      props.put("awt.print.paperSize", "a4");
      props.put("awt.print.destination", "printer");

      
      PrintJob pJob = getToolkit().getPrintJob(this,
  			  "facture_"+id, props);
      if (pJob != null)
        {
          Graphics pg = pJob.getGraphics();
          if (choixTout.isSelected()) printAll(pg);
          else pan.printAll(pg);
          pg.dispose();
          pJob.end();
        }Statement state;
      try {
			state = Connect.getInstance().createStatement();
			String requete = "UPDATE facture SET statut = '"+statut+"', etat = '"+etat+"' WHERE id = '"+id+"'";
			int resultat = state.executeUpdate(requete);
			requete = "DELETE FROM client  WHERE id = '"+id+"'";
			resultat = state.executeUpdate(requete);
			requete = "UPDATE vehicule SET statut = 'disponible' WHERE modele = '"+modele+"'";
			resultat = state.executeUpdate(requete);
			state.close();
			/*
			*/
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    }

 
  }