import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JTextField;


public class Clients_reservation extends JDialog {

	private ClientInfo zInfo = new ClientInfo();
	private boolean sendData;
	private JLabel nomLabel, prenomLabel, nssLabel, adresseLabel, telephoneLabel, npLabel, icon;
	private JTextField nom,prenom,securite_social,adresse,telephone,numero_permis;
	private static String numero, kilometrage, prix,etat;
	private static Object marque,modele;
	/**
	 * Constructeur
	 * @param parent
	 * @param title
	 * @param modal
	 */
	public Clients_reservation(JFrame parent, String title, boolean modal){
		super(parent, title, modal);
		this.setSize(700, 270);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.initComponent();
		this.setVisible(true);	
	}
	
	public Clients_reservation(String numero, Object marque, Object modele,  String etat,
			 String kilometrage, String prix) {
		// TODO Auto-generated constructor stub
		this.numero = numero;
		this.marque = marque;
		this.modele = modele;
		this.kilometrage = kilometrage;
		this.prix = prix;
		this.etat = etat;
	}

	

	/**
	 * Méthode appelée pour utiliser la boîte 
	 * @return zInfo
	 */

	
	/**
	 * Initialise le contenu de la boîte
	 */
	private void initComponent(){
		//Icone
		icon = new JLabel(new ImageIcon("images.jpg"));
		JPanel panIcon = new JPanel();
		panIcon.setBackground(Color.white);
		panIcon.setLayout(new BorderLayout());
		panIcon.add(icon);
		
	
		
		//Le nom
		JPanel panNom = new JPanel();
		panNom.setBackground(Color.white);
		panNom.setPreferredSize(new Dimension(220, 60));
		panNom.setBorder(BorderFactory.createTitledBorder("Nom du client "));
		nomLabel = new JLabel("Nom : ");
		nom= new JTextField();
		nom.setPreferredSize(new Dimension(90, 25));
		panNom.add(nomLabel);
		panNom.add(nom);
	
		//Le prenom
		JPanel panPrenom = new JPanel();
		panPrenom.setBackground(Color.white);
		panPrenom.setPreferredSize(new Dimension(220, 60));
		panPrenom.setBorder(BorderFactory.createTitledBorder("Prénom du client "));
		prenomLabel = new JLabel("Prénom : ");
		prenom= new JTextField();
		prenom.setPreferredSize(new Dimension(90, 25));
		panPrenom.add(prenomLabel);
		panPrenom.add(prenom);
	
		//Le numéro de sécurité Social
		JPanel panNss = new JPanel();
		panNss.setBackground(Color.white);
		panNss.setPreferredSize(new Dimension(220, 60));
		panNss.setBorder(BorderFactory.createTitledBorder("Numéro de sécurité Social du client "));
		nssLabel = new JLabel("NSS : ");
		securite_social= new JTextField();
		securite_social.setPreferredSize(new Dimension(90, 25));
		panNss.add(nssLabel);
		panNss.add(securite_social);
	
		//L'adresse
		JPanel panAdresse = new JPanel();
		panAdresse.setBackground(Color.white);
		panAdresse.setPreferredSize(new Dimension(220, 60));
		panAdresse.setBorder(BorderFactory.createTitledBorder("Adresse du client "));
		adresseLabel = new JLabel("Adresse : ");
		adresse= new JTextField();
		adresse.setPreferredSize(new Dimension(90, 25));
		panAdresse.add(adresseLabel);
		panAdresse.add(adresse);
		
		//Le telephone
		JPanel panTelephone = new JPanel();
		panTelephone.setBackground(Color.white);
		panTelephone.setPreferredSize(new Dimension(220, 60));
		panTelephone.setBorder(BorderFactory.createTitledBorder("Numéro de télephone du client "));
		telephoneLabel = new JLabel("Télephone : ");
		telephone= new JTextField();
		telephone.setPreferredSize(new Dimension(90, 25));
		panTelephone.add(telephoneLabel);
		panTelephone.add(telephone);
		
		//Numéro du permis de conduire
		JPanel panNumero_permis = new JPanel();
		panNumero_permis.setBackground(Color.white);
		panNumero_permis.setPreferredSize(new Dimension(220, 60));
		panNumero_permis.setBorder(BorderFactory.createTitledBorder("Numéro du permis de conduire "));
		npLabel = new JLabel("Numéro : ");
		numero_permis= new JTextField();
		numero_permis.setPreferredSize(new Dimension(90, 25));
		panNumero_permis.add(npLabel);
		panNumero_permis.add(numero_permis);
	
		JPanel content = new JPanel();
		content.setBackground(Color.white);
		content.add(panNom);
		content.add(panPrenom);
		content.add(panNss);
		content.add(panAdresse);
		content.add(panTelephone);
		content.add(panNumero_permis);
		
		JPanel control = new JPanel();
		JButton okBouton = new JButton("Valider");
		
		okBouton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {		
				
				setVisible(false);
			      Imprimer i = new Imprimer( numero,marque, modele,etat,kilometrage, prix, nom.getText(), prenom.getText(), securite_social.getText(), adresse.getText(), telephone.getText(), numero_permis.getText());
				Imprimer monCadre = new Imprimer();
			      monCadre.pack();
			      monCadre.setVisible(true);
				
			      
			      
				
			  	
			}
			
		
				
		});
		
		JButton cancelBouton = new JButton("Annuler");
		cancelBouton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}			
		});
		
		JLabel icon = new JLabel(new ImageIcon("images/contro.jpg"));
		JPanel panIcon2 = new JPanel();
		panIcon2.setBackground(Color.white);
		panIcon2.setLayout(new BorderLayout());
		panIcon2.add(icon);
		control.add(okBouton);
		control.add(cancelBouton);
		
		this.getContentPane().add(panIcon2, BorderLayout.WEST);
		this.getContentPane().add(content, BorderLayout.CENTER);
		this.getContentPane().add(control, BorderLayout.SOUTH);
	}
	
	
}
