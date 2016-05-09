import javax.swing.ImageIcon;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.Socket;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.sql.*;


public class Ajouter extends JDialog {
	
	private JLabel marqueLabel, modeleLabel, statutLabel,numero_meneralogiqueLabel, prixLabel, prix2Label, kilometrageLabel, kilometrage2Label;
	private JTextField marque, modele, numero_meneralogique, kilometre, prix;
	private static String marque1=null, modele1=null, numero_meneralogique1=null, kilometre1=null, prix1=null;
	private static int id = 0;
	public static String adr;
	public static InetAddress ip;
	private JComboBox statut;
	
	public Ajouter(JFrame parent, String title, boolean modal){
		super(parent, title, modal);
		this.setSize(700, 270);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.information();
		this.setVisible(true);
		
	}
	
	public static String getKilometre1() {
		return kilometre1;
	}

	public static String getPrix1() {
		return prix1;
	}

	public Ajouter(String marque, String modele, String numero_meneralogique, String kilometre, String prix, int id) {
		// TODO Auto-generated constructor stub
		marque1 = marque;
		modele1 = modele;
		numero_meneralogique1 = numero_meneralogique;
		kilometre1=kilometre;
		prix1=prix;
		this.id = id;
	}
	public Ajouter(String marque, String modele, String numero_meneralogique, String kilometre, String prix) {
		// TODO Auto-generated constructor stub
		marque1 = marque;
		modele1 = modele;
		numero_meneralogique1 = numero_meneralogique;
		kilometre1=kilometre;
		prix1=prix;
		
	}




	/*public Ajouter(String bdd,String user,String passwd )throws RemoteException {
		
		this.user = user;
		this.bdd = bdd;
		this.passwd = passwd;
		
		
	};*/

	public String getMarque1() {
		return marque1;
	}



	public String getModele1() {
		return modele1;
	}



	public String getNumero_meneralogique1() {
		return numero_meneralogique1;
	}
	
	public int getId() {
		return id;
	}
	private void information(){

		
	System.out.println(getMarque1());
		//Le marque
		JPanel panmarque = new JPanel();
		panmarque.setBackground(Color.white);
		panmarque.setPreferredSize(new Dimension(220, 60));
		panmarque.setBorder(BorderFactory.createTitledBorder("Marque du vehicule"));
		marqueLabel = new JLabel("Marque : ");
		marque = new JTextField(getMarque1());
		marque.setPreferredSize(new Dimension(90, 25));
		panmarque.add(marqueLabel);
		panmarque.add(marque);
		
		//Le modele
		JPanel panmodele = new JPanel();
		panmodele.setBackground(Color.white);
		panmodele.setPreferredSize(new Dimension(220, 60));
		panmodele.setBorder(BorderFactory.createTitledBorder("Modèle du vehicule"));
		modeleLabel = new JLabel("Modèle : ");
		modele = new JTextField(getModele1());
		modele.setPreferredSize(new Dimension(90, 25));
		panmodele.add(modeleLabel);
		panmodele.add(modele);
		
		//Le modele 2
		JPanel pannumero_meneralogique = new JPanel();
		pannumero_meneralogique.setBackground(Color.white);
		pannumero_meneralogique.setPreferredSize(new Dimension(220, 60));
		pannumero_meneralogique.setBorder(BorderFactory.createTitledBorder("Matricule du vehicule"));
		numero_meneralogiqueLabel = new JLabel("Matricule : ");
		numero_meneralogique = new JTextField(getNumero_meneralogique1());
		numero_meneralogique.setPreferredSize(new Dimension(90, 25));
		pannumero_meneralogique.add(numero_meneralogiqueLabel);
		pannumero_meneralogique.add(numero_meneralogique);

		JPanel panStatut = new JPanel();
		panStatut.setBackground(Color.white);
		panStatut.setPreferredSize(new Dimension(220, 60));
		panStatut.setBorder(BorderFactory.createTitledBorder("Statut vehicule"));
		statut = new JComboBox();
		statut.addItem("disponible");
		statut.addItem("reserve");
		statut.addItem("sortie");
		statutLabel = new JLabel("Etat : ");
		panStatut.add(statutLabel);
		panStatut.add(statut);
		
		//Le kilometrage
		JPanel panKilometrage = new JPanel();
		panKilometrage.setBackground(Color.white);
		panKilometrage.setPreferredSize(new Dimension(220, 60));
		panKilometrage.setBorder(BorderFactory.createTitledBorder("Kilometrage du vehicule"));
		kilometrageLabel = new JLabel("Kilometrage : ");
		kilometrage2Label = new JLabel(" KM");
		kilometre= new JTextField(getKilometre1());
		kilometre.setPreferredSize(new Dimension(90, 25));
		panKilometrage.add(kilometrageLabel);
		panKilometrage.add(kilometre);
		panKilometrage.add(kilometrage2Label);
		
		//Le prix
		JPanel panPrix = new JPanel();
		panPrix.setBackground(Color.white);
		panPrix.setPreferredSize(new Dimension(220, 60));
		panPrix.setBorder(BorderFactory.createTitledBorder("Prix du vehicule"));
		prixLabel = new JLabel("Prix : ");
		prix2Label = new JLabel(" Da");
		prix= new JTextField(getPrix1());
		prix.setPreferredSize(new Dimension(90, 25));
		panPrix.add(prixLabel);
		panPrix.add(prix);
		panPrix.add(prix2Label);
		
		
		/* Le mail
		JPanel panmail = new JPanel();
		panmail.setBackground(Color.white);
		panmail.setPreferredSize(new Dimension(220, 60));
		panmail.setBorder(BorderFactory.createTitledBorder("Adresse email"));
		mailLabel = new JLabel("Mail : ");
		mail = new JTextField();
		mail.setPreferredSize(new Dimension(90, 25));
		panmail.add(mailLabel);
		panmail.add(mail);*/
		
		JPanel content = new JPanel();
		content.setBackground(Color.white);
		content.add(panmarque);
		content.add(panmodele);
		content.add(pannumero_meneralogique);
		content.add(panKilometrage);
		content.add(panPrix);
	if(marque1 != null) {
			
		content.add(panStatut);
		this.setSize(700, 270);
		}
		//content.add(panmail);

		
		JPanel control = new JPanel();
		JButton okBouton = new JButton("Valider");
	
		okBouton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {				

				
				if(marque1 == null) {
			
				try {
				
					
					Statement state = Connect.getInstance()
					.createStatement();
				String requete = "INSERT INTO vehicule(marque,modele,numero_meneralogique,statut,kilometrage,prix) VALUES ('"+marque.getText()+"', '"+modele.getText()+"', '"+numero_meneralogique.getText()+"','disponible','"+kilometre.getText()+"', '"+prix.getText()+"')";
					 int resultat = state.executeUpdate(requete);
							
					 marque1=null; modele1=null; numero_meneralogique1=null; kilometre1=null; prix1=null;
					 Ajouter a = new Ajouter(marque1,modele1,numero_meneralogique1,kilometre1,prix1);
					 JOptionPane confirmation = new JOptionPane();
						confirmation.showMessageDialog(null, "Le vehicule a été ajouter ", "Mise à ajour", JOptionPane.INFORMATION_MESSAGE, null);

						
			
			 state.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 catch (Exception e) {
				e.printStackTrace();
			}
			 setVisible(false);
				}
				else {

					try {
					
						
						Statement state = Connect.getInstance().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
						String query = "UPDATE vehicule SET marque = '"+marque.getText()+"', modele = '"+modele.getText()+"', numero_meneralogique = '"+numero_meneralogique.getText()+"', statut = '"+(String)statut.getSelectedItem()+"', kilometrage='"+kilometre.getText()+"', prix='"+prix.getText()+"' WHERE id = '"+id+"'";
						
					
						state.executeUpdate(query);

			                        state.close();
			                        JOptionPane confirmation = new JOptionPane();
									confirmation.showMessageDialog(null, "Le vehicule a été modifier ", "Mise à ajour", JOptionPane.INFORMATION_MESSAGE, null);

						
							
					
					}  catch (Exception e) {
			            				e.printStackTrace();
			            			}
			            			 setVisible(false);
					}            				
					}
		/*

				try {
					Class.forName("org.postgresql.Driver");
					
					    String url = "jdbc:postgresql://localhost:5432/Location";
						String user = "postgres";
						String passwd = "postgres";
						
						Connection con = DriverManager.getConnection(url, user, passwd);
						Statement state = con.createStatement();
						String requete = "SELECT modele FROM vehicule";
						ResultSet resultat = state.executeQuery(requete);
					
				
					while(resultat.next()){
						 System.out.println(resultat.getString("modele"));
					}

		                        resultat.close();
		                        state.close();
					
				} catch (Exception e) {
					e.printStackTrace();
				}*/	
					
			}
		
			
		);
		
		JButton cancelBouton = new JButton("Annuler");
		cancelBouton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					
					setVisible(false);
				}catch(NullPointerException n) {
					System.exit(0);	
					
				}
			}			
		});
		
		control.add(okBouton);
		control.add(cancelBouton);
		JLabel icon = new JLabel(new ImageIcon("images/vehicule.jpg"));
		JPanel panIcon = new JPanel();
		panIcon.setBackground(Color.white);
		panIcon.setLayout(new BorderLayout());
		panIcon.add(icon);

		this.getContentPane().add(panIcon, BorderLayout.WEST);
		this.getContentPane().add(content, BorderLayout.CENTER);
		this.getContentPane().add(control, BorderLayout.SOUTH);
		
	
	}




}
