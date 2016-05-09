import java.awt.BorderLayout;
import java.awt.Component;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
public class Supprimer_user {



	public Supprimer_user() throws SQLException {
	
		Statement state = Connect.getInstance()
		.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY
		);
		ResultSet res = state.executeQuery("SELECT log FROM utilisateurs");
		
	
	

		res.last();
		int rowCount = res.getRow();
		System.out.println(rowCount);

		res.beforeFirst();
		Object[] data = new Object[rowCount];
	
		int i=0;
		while(res.next()){
			
				data[i] = res.getString("log");
	i++;
		
		}
		
		
		

		

		
		
	JOptionPane jop = new JOptionPane(), jop2 = new JOptionPane();
	ImageIcon img = new ImageIcon("images/suppression.png");
	String login = (String)jop.showInputDialog(null, 
									"Veuillez choisir l'utilisateur a supprimer!",
									"Utilisateurs CSL !",
									JOptionPane.QUESTION_MESSAGE,
									img,
									 data,
									null);
	try{
		if(login !=null) {jop2 = new JOptionPane();	
	//PreparedStatement prepare = Connect.getInstance().prepareStatement("UPDATE professeur set prof_prenom = ? WHERE prof_nom = 'MAMOU'");

		int option = jop.showConfirmDialog(null, "êtes-vous sûr de vouloir supprimer l'utilisateur "+login+" ?", "Confirmation de suppression", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		

		if(option == JOptionPane.OK_OPTION)
		{
	res = state.executeQuery("SELECT id FROM utilisateurs WHERE log = '"+login+"'");
	
	res.first();
	
	int id = res.getInt("id");
	System.out.println(id);
	
	String query = "DELETE FROM utilisateurs WHERE id = '"+id+"'";
	res = state.executeQuery(query);
	}}}
	catch(Exception e ){
	 JOptionPane confirmation = new JOptionPane();
		confirmation.showMessageDialog(null, "L'utilisateur a été supprimer ", "Mise à ajour", JOptionPane.INFORMATION_MESSAGE, null);

	}
	res.close();
	state.close();

	
	
	}
	

}
