import java.awt.BorderLayout;
import java.awt.Component;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

import org.postgresql.util.PSQLException;
public class Modifier_user {



	public Modifier_user() throws SQLException {
	
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
	ImageIcon img = new ImageIcon("images/admin.png");
	String login = (String)jop.showInputDialog(null, 
									"Veuillez choisir l'utilisateur !",
									"Utilisateurs CSL  !",
									JOptionPane.QUESTION_MESSAGE,
									img,
									 data,
									null);
	
	//PreparedStatement prepare = Connect.getInstance().prepareStatement("UPDATE professeur set prof_prenom = ? WHERE prof_nom = 'MAMOU'");

	try{ 
	res = state.executeQuery("SELECT * FROM utilisateurs WHERE log = '"+login+"'");
	
	res.first();
	String log = res.getString("log");
	int id = res.getInt("id");

	Ajouter_user a = new Ajouter_user(log,id);
	Ajouter_user zd = new Ajouter_user(null, "Modifier un utilisateur CSAL", true);
	
	}catch(NullPointerException e) {
		
	}catch(PSQLException p) {}
	res.close();
	state.close();
	

	
	}
	

}
