import java.awt.BorderLayout;
import java.awt.Component;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
public class Modifier_vehicule {



	public Modifier_vehicule() throws SQLException {
	
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
	ImageIcon img = new ImageIcon("images/vehicule.jpg");
	String modele = (String)jop.showInputDialog(null, 
									"Veuillez choisir le modèle !",
									"Modification d'un vehicule !",
									JOptionPane.QUESTION_MESSAGE,
									img,
									 data,
									null);
	
	//PreparedStatement prepare = Connect.getInstance().prepareStatement("UPDATE professeur set prof_prenom = ? WHERE prof_nom = 'MAMOU'");
try{
    String[] str = modele.split("\\ > ");
    
	res = state.executeQuery("SELECT * FROM vehicule WHERE modele = '"+str[1]+"'");
	
	res.first();
	String marque = res.getString("marque");
	String modele1 = res.getString("modele");
	String kilometrage = res.getString("kilometrage");
	String prix = res.getString("prix");
	int id = res.getInt("id");
	System.out.println(modele1);
	String numero_meneralogique = res.getString("numero_meneralogique");
	Ajouter a = new Ajouter(marque,modele1,numero_meneralogique,kilometrage,prix,id);
	Ajouter zd = new Ajouter(null, "Modifier un vehicule", true);
}catch(NullPointerException e) {}
	
	res.close();
	state.close();
	

	
	}
	

}
