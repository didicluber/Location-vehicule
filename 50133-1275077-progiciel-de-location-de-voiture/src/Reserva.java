import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JTextField;


public class Reserva extends JDialog {

	private LocationInfo zInfo = new LocationInfo();
	private String str;
	private boolean sendData;
	private JLabel numeromeneralogiquelabel, marqueLabel, modeleLabel, prixLabel, prix2Label, kilometrageLabel, kilometrage2Label, icon;
	private JRadioButton tranche1, tranche2, tranche3, tranche4;
	private static String numero1=null, kilometre1=null, prix1=null;
	private JComboBox modele, marques;
	private JTextField numero, kilometre, prix;
	public static Interfaces Location;
	private static ResultSet local;
	private static String adr;
	private static boolean fini = false;
	
	/**
	 * Constructeur
	 * @param parent
	 * @param title
	 * @param modal
	 * @throws SQLException 
	 * @throws RemoteException 
	 * @throws NotBoundException 
	 * @throws MalformedURLException 
	 */
	public Reserva(JFrame parent, String title, boolean modal) throws RemoteException, SQLException, MalformedURLException, NotBoundException{
		super(parent, title, modal);
		this.setSize(700, 270);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.initComponent();
	
	
			this.setVisible(true);	
	}
	
	public Reserva(String adr, Interfaces Location) {
		// TODO Auto-generated constructor stub
		this.adr = adr;
		this.Location = Location;
		//System.out.println(this.adr+Location);
	}



	public String getAdr() {
		return adr;
	}

	/**
	 * Méthode appelée pour utiliser la boîte 
	 * @return zInfo
	 */
	
	
	/**
	 * Initialise le contenu de la boîte
	 * @throws RemoteException 
	 * @throws SQLException 
	 * @throws NotBoundException 
	 * @throws MalformedURLException 
	 */
	private void initComponent() throws RemoteException, SQLException, MalformedURLException, NotBoundException{
		//Icone
		icon = new JLabel(new ImageIcon("images.jpg"));
		JPanel panIcon = new JPanel();
		panIcon.setBackground(Color.white);
		panIcon.setLayout(new BorderLayout());
		panIcon.add(icon);
		
		
		
		
	
		//La marque
		JPanel panMarque = new JPanel();
		panMarque.setBackground(Color.white);
		panMarque.setPreferredSize(new Dimension(220, 60));
		panMarque.setBorder(BorderFactory.createTitledBorder("Marque du véhicule"));
		marques = new JComboBox();
	    marques.addItem("Choisir");

        String requete = "SELECT marque FROM vehicule WHERE statut = 'disponible'  ORDER BY marque";

        try {
    		Statement state = Connect.getInstance().createStatement();

         ResultSet res = state.executeQuery(requete); 
         
       //  String[] marquee = null;
        // marquee[0]=  res.getString("marque");
        // marques.addItem(marquee[0]);
        //int i =1;
    while(res.next()) {
   //marquee[i]= 
    //if (!marquee[i].equals(marquee[i-1])){
    marques.addItem(res.getString("marque"));
    //}
    //else marquee[i]= null;
    //i++;
    }
        }catch(SQLException e ){}
   
	
		
		marques.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				modele.removeAllItems();
				try{
				
						 String requete2 = "SELECT modele,kilometrage,prix,numero_meneralogique FROM vehicule WHERE statut = 'disponible' AND marque='"+(String)marques.getSelectedItem()+"'  ORDER BY marque";
							Statement state = Connect.getInstance().createStatement();

					         ResultSet res2 = state.executeQuery(requete2); 
					         
						 while(res2.next()) {
							 modele.addItem (res2.getString("modele"));
							 kilometre.setText(res2.getString("kilometrage"));
							 prix.setText(res2.getString("prix"));
							 numero.setText(res2.getString("numero_meneralogique"));
						 }
                    
					
				
				}catch(Exception e) {} 
			
			}
		});   
		
		marqueLabel = new JLabel("Marque : ");
		panMarque.add(marqueLabel);
		panMarque.add(marques);
		
		//Le modele
		JPanel panModele = new JPanel();
		panModele.setBackground(Color.white);
		panModele.setPreferredSize(new Dimension(220, 60));
		panModele.setBorder(BorderFactory.createTitledBorder("Modele du véhicule"));
		modele = new JComboBox();
        
		modeleLabel = new JLabel("Modele : ");
		panModele.add(modeleLabel);
		panModele.add(modele);
		
		//Le numero meneralogique
		JPanel panmeneralogique = new JPanel();
		panmeneralogique.setBackground(Color.white);
		panmeneralogique.setPreferredSize(new Dimension(220, 60));
		panmeneralogique.setBorder(BorderFactory.createTitledBorder("Numéro Meneralogique "));
		numeromeneralogiquelabel = new JLabel("Numéro : ");
		numero= new JTextField(numero1);
		numero.setPreferredSize(new Dimension(90, 25));
		panmeneralogique.add(numeromeneralogiquelabel);
		panmeneralogique.add(numero);
	
		
	
		
		//Le kilometrage
		JPanel panKilometrage = new JPanel();
		panKilometrage.setBackground(Color.white);
		panKilometrage.setPreferredSize(new Dimension(220, 60));
		panKilometrage.setBorder(BorderFactory.createTitledBorder("Kilometrage du vehicule"));
		kilometrageLabel = new JLabel("Kilometrage : ");
		kilometrage2Label = new JLabel(" KM");
		kilometre= new JTextField(kilometre1);
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
		prix= new JTextField(prix1);
		prix.setPreferredSize(new Dimension(90, 25));
		panPrix.add(prixLabel);
		panPrix.add(prix);
		panPrix.add(prix2Label);
		
		
		JPanel content = new JPanel();
		content.setBackground(Color.white);
		content.add(panMarque);
		content.add(panModele);
		content.add(panmeneralogique);
		content.add(panKilometrage);
		content.add(panPrix);
		
		JPanel control = new JPanel();
		JButton okBouton = new JButton("Suivant");
		
		okBouton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {	
				setVisible(false);
				 Clients_reservation c = new Clients_reservation( numero.getText(),marques.getSelectedItem(), modele.getSelectedItem(),"attente",kilometre.getText(), prix.getText());
				 Clients_reservation l = new Clients_reservation(null, "Réservation : Informations Clients", true);
				
				
			}
			
			public String getType(){
				return (tranche1.isSelected()) ? tranche1.getText() : (tranche2.isSelected()) ? tranche2.getText() : (tranche3.isSelected()) ? tranche3.getText() : (tranche4.isSelected()) ? tranche4.getText() : tranche1.getText();   
			}
				
		});
		
		JButton cancelBouton = new JButton("Annuler");
		cancelBouton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}			
		});
		
		JLabel icon = new JLabel(new ImageIcon("images/vehicule.jpg"));
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
	public static void fermer(){
		
		((JComponent) Location).setVisible(false);
	}

	}	 
	   


