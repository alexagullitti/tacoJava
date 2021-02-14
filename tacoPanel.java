import java.awt.Color;
import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import com.mysql.cj.util.StringUtils;



public class tacoPanel extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel thePanel;
	private JTextField firstName;
	private JTextField lastName;
	private JTextField address;
	private JTable table;
	
	
	public static void main(String[] args) {
		
		        
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    tacoPanel frame = new tacoPanel();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
	
	/**
	 * Create the panel.
	 * @param doc 
	 */
	public tacoPanel() {
		getContentPane().setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(450, 190, 1014, 597);
        setResizable(false);
		
		thePanel = new JPanel();
		thePanel.setBounds(24, 11, 382, 273);
		//add(thePanel);
		thePanel.setLayout(null);
		setContentPane(thePanel);
		
		firstName = new JTextField();
		firstName.setBounds(3, 14, 86, 20);
		thePanel.add(firstName);
		firstName.setColumns(10);
		
		lastName = new JTextField();
		lastName.setBounds(3, 59, 86, 20);
		thePanel.add(lastName);
		lastName.setColumns(10);
		
		address = new JTextField();
		address.setBounds(3, 104, 86, 20);
		thePanel.add(address);
		address.setColumns(10);
		
		JButton addButton = new JButton("Add");
		addButton.setBounds(0, 148, 89, 23);
		thePanel.add(addButton);
		
		JButton updateButton = new JButton("Update");
		updateButton.setBounds(0, 182, 89, 23);
		thePanel.add(updateButton);
		
		JButton deleteButton = new JButton("Delete");
		deleteButton.setBounds(0, 216, 89, 23);
		thePanel.add(deleteButton);
		
		table = new JTable();
		table.setBounds(245, 204, 137, -139);
		thePanel.add(table);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		JLabel lblNewJgoodiesLabel = DefaultComponentFactory.getInstance().createLabel("First Name");
		lblNewJgoodiesLabel.setBounds(3, 0, 92, 14);
		thePanel.add(lblNewJgoodiesLabel);
		
		JLabel lblLastName = DefaultComponentFactory.getInstance().createLabel("Last Name");
		lblLastName.setBounds(3, 45, 92, 14);
		thePanel.add(lblLastName);
		
		JLabel lblAddress = DefaultComponentFactory.getInstance().createLabel("Address");
		lblAddress.setBounds(3, 90, 92, 14);
		thePanel.add(lblAddress);
		
		JButton searchAll = new JButton("Search All");
		searchAll.setBounds(262, 13, 89, 23);
		thePanel.add(searchAll);
		
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//make sure the primary key firstName is not empty
		         if (StringUtils.isNullOrEmpty(firstName.getText()))
		         {
		        	 JOptionPane.showMessageDialog(thePanel, "Please enter a First Name");
		            //MessageBox.Show("Please enter a First Name");
		         }
		         else
		         {
		            //build the insert statement
		        	 try {
						Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tacoDB");

						String query = "insert into Customers values('" + firstName.getText() + "','" + lastName.getText() + "','" + address.getText() + "')";
						Statement sta = connection.createStatement();
						int success = sta.executeUpdate(query);
						
						if(success == 0){
						   JOptionPane.showMessageDialog(thePanel, "Delivery Successfully added");
						}
						else
						{
						   JOptionPane.showMessageDialog(thePanel, "Please check your entries, delivery was not scheduled");
						}
						connection.close();
					} catch (HeadlessException e) {
						// TODO Auto-generated catch block
						
						e.printStackTrace();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						
						e.printStackTrace();
					}
		         }
				
			}
		});
		
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//check if fields are entered
				if (StringUtils.isNullOrEmpty(firstName.getText()) && StringUtils.isNullOrEmpty(lastName.getText()) && StringUtils.isNullOrEmpty(address.getText()))
		         {
					JOptionPane.showMessageDialog(thePanel, "all Fields are empty please enter something");
		         }
		         else if(StringUtils.isNullOrEmpty(firstName.getText()))
		         {
		        	 JOptionPane.showMessageDialog(thePanel, "You must enter a first name to update that delivery");
		         }
		         else
		         {
		            String tempString = "Update Customers set";
		            if (!StringUtils.isNullOrEmpty(lastName.getText()))
		            {
		               tempString += " lastName = '" + lastName.getText() + "' ,";
		            }
		            if (!StringUtils.isNullOrEmpty(address.getText()))
		            {
		               tempString += " address = '" + address.getText() + "'";
		            }
		            if (tempString.endsWith(","))//removes ',' if at the end
		            {
		               tempString = tempString.substring(0, tempString.length() - 1);
		            }

		            tempString += " where firstName = '" + firstName.getText() + "'";

		            try
		            {
		            	Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tacoDB");

		            	Statement sta = connection.createStatement();
						int success = sta.executeUpdate(tempString);
						
						if(success == 0){
							JOptionPane.showMessageDialog(thePanel, "Delivery Successfully updated");
						}
						else
						{
							JOptionPane.showMessageDialog(thePanel, "Please check your entries, delivery was not updated");
						}
		               
		               connection.close();
		            }
		            catch (SQLException e)
		            {
		               JOptionPane.showMessageDialog(thePanel, "Please close application try again");
		            }
		         }
			}
		});
		
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
		         //cannot delete if nothing is entered
		         if (StringUtils.isNullOrEmpty(firstName.getText()) && StringUtils.isNullOrEmpty(lastName.getText()) && StringUtils.isNullOrEmpty(address.getText()))
		         {
		        	 JOptionPane.showMessageDialog(thePanel, "all Fields are empty please enter something");
		         }
		         else
		         { // creates delete statement depending on input
		            String tempString = "Delete from Customers where ";
		            if (!StringUtils.isNullOrEmpty(firstName.getText()))
		            {
		               tempString += " firstName = '" + firstName.getText() + "' and";
		            }
		            if (!StringUtils.isNullOrEmpty(lastName.getText()))
		            {
		               tempString += " lastName = '" + lastName.getText() + "' and";
		            }
		            if (!StringUtils.isNullOrEmpty(address.getText()))
		            {
		               tempString += " address = '" + address.getText() + "'";
		            }
		            if(tempString.endsWith("and"))//removes 'and' if at the end
		            {
		               tempString = tempString.substring(0, tempString.length() - 3);
		            }
		            
		            try
		                 {
		                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tacoDB");

			            	Statement sta = connection.createStatement();
							int success = sta.executeUpdate(tempString);
							
							if(success == 0)
							{
								JOptionPane.showMessageDialog(thePanel,"Delivery Successfully Removed");
		                    }
							else
							{
								JOptionPane.showMessageDialog(thePanel,"Please check your entries, delivery was not removed");
							}
		                    connection.close();
		                 }
		                 catch (SQLException e)
		                 {
		                    JOptionPane.showMessageDialog(thePanel,"Please check your entries, delivery was not removed");
		                 }
		         }
			}
		});
		
		searchAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
		            
		            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/linda","root","");
		            PreparedStatement insert = connection.prepareStatement("SELECT * FROM customers");
		            ResultSet Rs = insert.executeQuery();
		            
		            ResultSetMetaData RSMD = Rs.getMetaData();
		            int CC = RSMD.getColumnCount();
		            DefaultTableModel DFT = (DefaultTableModel) table.getModel();
		            DFT.setRowCount(0);
		 
		            while (Rs.next()) {
		                Vector<String> v2 = new Vector<String>();
		           
		                for (int ii = 1; ii <= CC; ii++) {
		                    v2.add(Rs.getString("firstName"));
		                    v2.add(Rs.getString("lastName"));
		                    v2.add(Rs.getString("address"));
		                }
		                DFT.addRow(v2);
		            }
		        } catch (Exception e) {
		        }
			}
		});
	}
}
