
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JToolBar;


public class facture_liste extends JFrame  {

	
	private JToolBar tool = new JToolBar();
	private JButton load = new JButton(new ImageIcon("img/load.png"));
	private JSplitPane split;
	private JPanel result = new JPanel();
	private String requete = "SELECT nom FROM facture ";

		
		
	public facture_liste(String requete) {
		
		setSize(600, 500);
		setTitle("Liste des factures");
		setLocationRelativeTo(null);

		
		this.requete=requete;
		initContent();
		initTable();
		
	}

	
	public facture_liste(){
		setSize(600, 300);
		setTitle("Liste des factures");
		setLocationRelativeTo(null);

		
	
		initContent();
		initTable();
	}

	

	private void initToolbar(){
		load.setPreferredSize(new Dimension(30, 35));
		load.setBorder(null);
		load.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				initTable();
			}
		});
		
		tool.add(load);
	
	}
	
	
	public void initContent(){
		//Vous connaissez ça...
		result.setLayout(new BorderLayout());
		JLabel jlb = new JLabel(new ImageIcon("images/600x120.jpg"));
		jlb.setPreferredSize(new Dimension(600, 120));
		JScrollPane dd = new JScrollPane(jlb);
		split = new JSplitPane(JSplitPane.VERTICAL_SPLIT,jlb , result);
		split.setDividerLocation(100);
		getContentPane().add(split, BorderLayout.CENTER);		
	}
	
	/**
	 * 
	 * @param query
	 */
	public void initTable(){
		
		try {
			
			long start = System.currentTimeMillis();
			Statement state = Connect.getInstance()
											.createStatement(
														ResultSet.TYPE_SCROLL_INSENSITIVE, 
														ResultSet.CONCUR_READ_ONLY
											);
			
			
			ResultSet res = state.executeQuery(requete);
			ResultSetMetaData meta = res.getMetaData();
			Object[] column = new Object[meta.getColumnCount()];
			
			for(int i = 1 ; i <= meta.getColumnCount(); i++){
				column[i-1] = meta.getColumnName(i);
			}
			res.last();
			int rowCount = res.getRow();
			Object[][] data = new Object[res.getRow()][meta.getColumnCount()];

			res.beforeFirst();
			int j = 1;
			
			while(res.next()){
				for(int i = 1 ; i <= meta.getColumnCount(); i++){
					data[j-1][i-1] = res.getObject(i);
				System.out.println(res.getObject(i));
				}
				j++;
			}
			
			                     
			res.close();
			state.close();

			long totalTime = System.currentTimeMillis() - start;
			
			
			result.removeAll();
			result.add(new JScrollPane(new JTable(data, column)), BorderLayout.CENTER);
			result.add(new JLabel("La demande à été exécuter en " + totalTime + " ms et a retourné " + rowCount + " ligne(s)"), BorderLayout.SOUTH);
			result.revalidate();
			
		} catch (SQLException e) {		
			result.removeAll();
			result.add(new JScrollPane(new JTable()), BorderLayout.CENTER);
			result.revalidate();
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERREUR ! ", JOptionPane.ERROR_MESSAGE);
		}	
		
	}

}
