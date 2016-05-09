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
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.sql.*;


public class Modifier extends JDialog {
	private static String user,passwd,url,bdd;

	private JLabel marqueLabel, modeleLabel, kilometrageLabel, mailLabel;
	private JTextField marque,mail;
	private JTextField  modele, kilometrage;
	public static String adr;
	public static InetAddress ip;
	public Modifier(JFrame parent, String title, boolean modal){
		super(parent, title, modal);
		this.setSize(700, 300);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.information();
		this.setVisible(true);
		
	}
	
	
	public Modifier(String adr){
		this.adr =adr;
	}
	/*public Ajouter(String bdd,String user,String passwd )throws RemoteException {
		
		this.user = user;
		this.bdd = bdd;
		this.passwd = passwd;
		
		
	};*/
	private void information(){

		
	
		//Le marque
		JPanel panmarque = new JPanel();
		panmarque.setBackground(Color.white);
		panmarque.setPreferredSize(new Dimension(220, 60));
		panmarque.setBorder(BorderFactory.createTitledBorder("Marque du vehicule"));
		marqueLabel = new JLabel("Marque : ");
		marque = new JTextField();
		marque.setPreferredSize(new Dimension(90, 25));
		panmarque.add(marqueLabel);
		panmarque.add(marque);
		
		//Le modele
		JPanel panmodele = new JPanel();
		panmodele.setBackground(Color.white);
		panmodele.setPreferredSize(new Dimension(220, 60));
		panmodele.setBorder(BorderFactory.createTitledBorder("Modèle du vehicule"));
		modeleLabel = new JLabel("Modèle : ");
		modele = new JTextField();
		modele.setPreferredSize(new Dimension(90, 25));
		panmodele.add(modeleLabel);
		panmodele.add(modele);
		
		//Le modele 2
		JPanel pankilometrage = new JPanel();
		pankilometrage.setBackground(Color.white);
		pankilometrage.setPreferredSize(new Dimension(220, 60));
		pankilometrage.setBorder(BorderFactory.createTitledBorder("Matricule du vehicule"));
		kilometrageLabel = new JLabel("Matricule : ");
		kilometrage = new JTextField();
		kilometrage.setPreferredSize(new Dimension(90, 25));
		pankilometrage.add(kilometrageLabel);
		pankilometrage.add(kilometrage);

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
		content.add(pankilometrage);
		//content.add(panmail);

		
		JPanel control = new JPanel();
		JButton okBouton = new JButton("Valider");
	
		okBouton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {				

				
				
			
				try {
					
				
					
					Statement state = (Statement) Connect.getInstance();
				String requete = "INSERT INTO vehicule(marque,modele,kilometrage,statut) VALUES ('"+marque.getText()+"', '"+modele.getText()+"', '"+kilometrage.getText()+"','disponible')";
					 int resultat = state.executeUpdate(requete);
							
					
						
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
					
			}}
		
			
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
		JLabel icon = new JLabel(new ImageIcon("admin.png"));
		JPanel panIcon = new JPanel();
		panIcon.setBackground(Color.white);
		panIcon.setLayout(new BorderLayout());
		panIcon.add(icon);

		this.getContentPane().add(panIcon, BorderLayout.EAST);
		this.getContentPane().add(content, BorderLayout.CENTER);
		this.getContentPane().add(control, BorderLayout.SOUTH);
		
	
	}

}
