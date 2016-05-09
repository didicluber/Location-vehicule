import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

public class facture {



	public facture() throws SQLException {
	
		Statement state = Connect.getInstance()
		.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY
		);
		ResultSet res = state.executeQuery("SELECT id,nom,prenom FROM facture WHERE statut='sortie' OR statut='attente'");
		
	
	

		res.last();
		int rowCount = res.getRow();
		System.out.println(rowCount);

		res.beforeFirst();
		Object[] data = new Object[rowCount];
	
		int i=0;
		while(res.next()){
			
				data[i] = res.getString("id")+" - "+res.getString("nom")+" - "+res.getString("prenom");
	i++;
		
		}
		
		
		

		

		
		
	JOptionPane jop = new JOptionPane(), jop2 = new JOptionPane();
	jop.setPreferredSize(new Dimension(500, 200));
	ImageIcon img = new ImageIcon("images/facturess.jpg");

	String id = (String)jop.showInputDialog(null, 
									"Veuillez choisir le contrat a régler !",
									"Facturation !",
									JOptionPane.QUESTION_MESSAGE,
									img,
									 data,
									 null);
	
	//PreparedStatement prepare = Connect.getInstance().prepareStatement("UPDATE professeur set prof_prenom = ? WHERE prof_nom = 'MAMOU'");
	try{ 
	String str[] = id.split("\\ - ");
	int idu = Integer.parseInt(str[0]);

	res = state.executeQuery("SELECT * FROM facture WHERE id = '"+idu+"'");
	
	res.first();


	
    Regler_facture is1 = new Regler_facture( res.getString("nom"),res.getString("prenom"), res.getString("nss"),res.getString("adresse"),res.getString("tel"), res.getString("permis"), "terminer", res.getObject("marque"), res.getObject("modele"), res.getString("numero_meneralogique"), res.getString("kilometrage"), res.getString("prix"),"regler",res.getInt("id"));
    Regler_facture monCadre = new Regler_facture();
    monCadre.pack();
    monCadre.setVisible(true);
	
	
	}catch(NullPointerException e) {}
	res.close();
	state.close();
	

	
	}
	

}
