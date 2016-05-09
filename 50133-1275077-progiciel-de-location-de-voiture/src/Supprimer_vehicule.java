import java.awt.BorderLayout;
import java.awt.Component;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
public class Supprimer_vehicule {



	public Supprimer_vehicule() throws SQLException {
	
		Statement state = Connect.getInstance()
		.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY
		);
		ResultSet res = state.executeQuery("SELECT marque,modele FROM vehicule");
		
	
	

		res.last();
		int rowCount = res.getRow();
		System.out.println(rowCount);

		res.beforeFirst();
		Object[] data = new Object[rowCount];
	
		int i=0;
		while(res.next()){
			
			data[i] = res.getString("marque")+" > "+res.getString("modele");
	i++;
		
		}
		
		
		

		

		
		
	JOptionPane jop = new JOptionPane(), jop2 = new JOptionPane();
	ImageIcon img = new ImageIcon("images/suppression.png");
	String modele = (String)jop.showInputDialog(null, 
									"Veuillez choisir le modèle !",
									"Suppression d'un vehicule !",
									JOptionPane.QUESTION_MESSAGE,
									img,
									 data,
									null);
	try{
	if(modele !=null) {jop2 = new JOptionPane();			
	int option = jop.showConfirmDialog(null, "êtes-vous sûr de vouloir supprimer le modèle "+modele+" ?", "Confirmation de suppression", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
	

	if(option == JOptionPane.OK_OPTION)
	{

		
	

	//PreparedStatement prepare = Connect.getInstance().prepareStatement("UPDATE professeur set prof_prenom = ? WHERE prof_nom = 'MAMOU'");

	String[] str = modele.split("\\ > ");
	System.out.println(str[1]);
	res = state.executeQuery("SELECT id FROM vehicule WHERE modele = '"+str[1]+"'");
	
	res.first();
	
	int id = res.getInt("id");
	System.out.println(id);
	try{
	String query = "DELETE FROM vehicule WHERE id = '"+id+"'";
	res = state.executeQuery(query);
	}
	catch(Exception e ){
	 JOptionPane confirmation = new JOptionPane();
		confirmation.showMessageDialog(null, "Le vehicule a été supprimer ", "Mise à ajour", JOptionPane.INFORMATION_MESSAGE, null);

	}
	}}
	res.close();
	state.close();

	}catch(NullPointerException e){}
	
	
	}
	

}
