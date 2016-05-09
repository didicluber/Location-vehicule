import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;






  class Imprimer extends JFrame implements  ActionListener
  {  
  
	  private static Object marque, modele;
	   private static String etat,
		 kilometrage,  prix, nom,  prenom,  securite_social,
		 adresse,  telephone,  numero_permis, numero,statut;
	
	   
    JButton imprimer = new JButton("imprimer");
    ButtonGroup choix = new ButtonGroup();
    JRadioButton choixTout = new JRadioButton("tout", false);
    JRadioButton choixDessin = new JRadioButton("dessin", true);
    JPanel pan = new JPanel();
    
    JPanel p = new JPanel();
    Imprimer()
    {
    	   this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    		this.setLocationRelativeTo(null);
    	setTitle("R�servation d'un vehicule");
    	Border[] listBorder = {	
				BorderFactory.createEtchedBorder(Color.black, Color.GRAY),
				
				
					
			};
    	
    	 Statement state;
         try {
   			state = Connect.getInstance().createStatement();
   			String requete = "INSERT INTO facture(nom,prenom,nss,adresse,tel,permis,etat,marque,modele,numero_meneralogique,kilometrage,prix,statut) VALUES ('"+nom+"', '"+prenom+"', '"+securite_social+"','"+adresse+"','"+telephone+"', '"+numero_permis+"','"+statut+"','"+marque+"', '"+modele+"', '"+numero+"','"+kilometrage+"', '"+prix+"','"+etat+"')";
   			int resultat = state.executeUpdate(requete);
   			requete = "INSERT INTO client(nom,prenom,nss,adresse,tel,permis,etat) VALUES ('"+nom+"', '"+prenom+"', '"+securite_social+"','"+adresse+"','"+telephone+"', '"+numero_permis+"','"+statut+"')";
   			resultat = state.executeUpdate(requete);
   			requete = "UPDATE vehicule SET statut = 'reserve' WHERE modele = '"+modele+"'";
   			resultat = state.executeUpdate(requete);
   			state.close();
   			/*
   			*/
   		} catch (SQLException e1) {
   			// TODO Auto-generated catch block
   			e1.printStackTrace();
   		}
      
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
		JLabel nss = new JLabel("Num�ro S�curit� Social :"+securite_social);
		JLabel tel = new JLabel("T�l�phone :"+telephone);
		JLabel permis = new JLabel("Num�ro du permis :"+numero_permis);
		
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
		Object[][] data = {	{"R�servation de voiture", marque,modele,prix+",00",qte,tva+",00",prixht+",00", ttc+",00"}
			
		};

		String  title[] = {"Designation","marque","Mod�le","Prix Unitaire", "Qt�","TVA","Prix HT","TTC"};
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
		pane.setBackground(Color.white);
		info_vehicule.add(pane );
		info_vehicule.add(pane2 );
		info_client.setBackground(Color.white);
		info_vehicule.setBackground(Color.white);
		
		
		JPanel condition = new JPanel();
		String str = "Remarque : La soci�t� n'est pas responsable de la non ";
		String str1 = "disponibilt� de la voiture � cause d'un retard faite par un client ";
		String str2 = "En cas ou le locataire annule sa r�servation, la soci�t� ";
		String str3 = "a le droit de garder 40% du montant pay� ";


			
		JLabel bel = new JLabel(str);
		JLabel bel1 = new JLabel(str1);
		JLabel bel2 = new JLabel(str2);
		JLabel bel3 = new JLabel(str3);

		
		
		
		condition.setPreferredSize(new Dimension(500, 300));
		condition.add(bel);
		condition.add(bel1);
		condition.add(bel2);
		condition.add(bel3);
	
		condition.setBackground(Color.white);
		
      pan.add(info_societe);
      pan.add(info_client);
      pan.add(info_vehicule);
      pan.add(condition);
      
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
  			setVisible(false);
  		  }
  	      });
    }

    public Imprimer(String numero, Object marque, Object modele,
			String etat, String kilometrage, String prix, String nom,
			String prenom, String adresse, String tel, String permis, String nss) {
		// TODO Auto-generated constructor stub
    	this.numero = numero;
		this.marque = marque;
		this.modele = modele;
		this.kilometrage = kilometrage;
		this.prix = prix;
		this.etat = etat;
		this.statut ="reserve";
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.telephone = tel;
		this.numero_permis = permis;
		this.securite_social = nss;
	}



	public Imprimer(String nom, String prenom, String nss,
			String adresse, String tel, String permis, String etat,
			String marque, String modele, String numero, String kilometrage,
			String prix, String statut) {
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
	}

	public void actionPerformed(ActionEvent e)
    {
      Properties props = new Properties();

      props.put("awt.print.paperSize", "a4");
      props.put("awt.print.destination", "printer");

      
      PrintJob pJob = getToolkit().getPrintJob(this,
  			  nom+prenom, props);
      if (pJob != null)
        {
          Graphics pg = pJob.getGraphics();
          if (choixTout.isSelected()) printAll(pg);
          else pan.printAll(pg);
          pg.dispose();
          pJob.end();
        }
     
    }
 
  }