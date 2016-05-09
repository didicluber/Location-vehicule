import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class Client extends JDialog {

	
	private boolean sendData;
	private JLabel loginLabel, passLabel;
	private JTextField login;
	private JPasswordField  pass;
	public static String adr;
	
	public Client(JFrame parent, String title, boolean modal){
		super(parent, title, modal);
		this.setSize(400, 200);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		
		this.information();
		this.setVisible(true);
	}
	
	
	
	private void information(){

	
		//Le login
		JPanel panLogin = new JPanel();
		panLogin.setBackground(Color.white);
		panLogin.setPreferredSize(new Dimension(220, 60));
		panLogin.setBorder(BorderFactory.createTitledBorder("Identifiant"));
		loginLabel = new JLabel("Login : ");
		login = new JTextField();
		login.setPreferredSize(new Dimension(90, 25));
		panLogin.add(loginLabel);
		panLogin.add(login);
		
		//Le login
		JPanel panPass = new JPanel();
		panPass.setBackground(Color.white);
		panPass.setPreferredSize(new Dimension(220, 60));
		panPass.setBorder(BorderFactory.createTitledBorder("Mot de passe"));
		passLabel = new JLabel("Passe : ");
		pass = new JPasswordField();
		pass.setPreferredSize(new Dimension(90, 25));
		panPass.add(passLabel);
		panPass.add(pass);


		JPanel content = new JPanel();
		content.setBackground(Color.white);
		content.add(panLogin);
		content.add(panPass);

		
		JPanel control = new JPanel();
		JButton okBouton = new JButton("Valider");
		final JButton insBouton = new JButton("Inscription");
		insBouton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {	
				Inscription s = new Inscription(adr);
				Inscription inscription = new Inscription(null, "Inscription Client", true);
			    
			}
		});
		okBouton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {				

				try{
					
					
	
			JOptionPane confirmation = new JOptionPane();
			JOptionPane erreur = new JOptionPane();
			if(Verification.Connexion_utilisateur(login.getText(), pass.getText())==true) {
				ImageIcon img = new ImageIcon("images/succes.png");

			confirmation.showMessageDialog(null, "Vous êtes connecter", "Connexion établie", JOptionPane.INFORMATION_MESSAGE, img);
			new Accueil(true);
			}
			else {
				
				ImageIcon img = new ImageIcon("images/erreur.png");
			erreur.showMessageDialog(null, "Erreur dans le login et le mot de passe", "Connexion échoué", JOptionPane.ERROR_MESSAGE, img);
			

			}
			

			}catch(Exception e5){System.out.println(e5);}
				
				
				setVisible(false);
			}
				
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
		//control.add(insBouton);
		JLabel icon = new JLabel(new ImageIcon("images/green.png"));
		JPanel panIcon = new JPanel();
		panIcon.setBackground(Color.white);
		panIcon.setLayout(new BorderLayout());
		panIcon.add(icon);

		this.getContentPane().add(panIcon, BorderLayout.EAST);
		this.getContentPane().add(content, BorderLayout.CENTER);
		this.getContentPane().add(control, BorderLayout.SOUTH);
		
	
	}
public static void main(String [] args) throws UnknownHostException{
		
	ImageIcon image = new ImageIcon(Toolkit.getDefaultToolkit().getImage("images/edit_f2.png"));
	adr = (String) JOptionPane.showInputDialog(null,"Entrer @IP du serveur : ","IP Serveur",1,image,null,"localhost");
	
		Client cl = new Client(null, "Connexion du client", true);
		
		
		
		
	}

}
