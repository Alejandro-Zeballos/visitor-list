package Frames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Logic.DList;
import Logic.Database;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddVisitor {

	private JFrame frame;
	private JTextField name;
	private JTextField surname;
	private JTextField passport;

	/**
	 * Create the application.
	 */
	public AddVisitor(MainFrame mainFrame) {
		frame = new JFrame();
		frame.setVisible(true);
		frame.setBounds(100, 100, 403, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblAddVisitor = new JLabel("Add Visitor");
		lblAddVisitor.setBounds(188, 11, 108, 14);
		frame.getContentPane().add(lblAddVisitor);
		
		JLabel lblName = new JLabel("Name");
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblName.setBounds(-14, 40, 108, 14);
		frame.getContentPane().add(lblName);
		
		JLabel lblSurname = new JLabel("Surname");
		lblSurname.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSurname.setBounds(-14, 85, 108, 14);
		frame.getContentPane().add(lblSurname);
		
		JLabel lblPassportN = new JLabel("Passport N.");
		lblPassportN.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassportN.setBounds(-14, 130, 108, 14);
		frame.getContentPane().add(lblPassportN);
		
		JLabel lblPriori = new JLabel("Priority Level");
		lblPriori.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPriori.setBounds(-14, 174, 108, 14);
		frame.getContentPane().add(lblPriori);
		
		name = new JTextField();
		name.setBounds(114, 36, 227, 33);
		frame.getContentPane().add(name);
		name.setColumns(10);
		
		surname = new JTextField();
		surname.setColumns(10);
		surname.setBounds(114, 80, 227, 33);
		frame.getContentPane().add(surname);
		
		passport = new JTextField();
		passport.setColumns(10);
		passport.setBounds(114, 127, 227, 33);
		frame.getContentPane().add(passport);
		
		JComboBox priority = new JComboBox();
		priority.setModel(new DefaultComboBoxModel(new String[] {"LOW", "MEDIUM", "HIGH"}));
		priority.setBounds(114, 171, 227, 20);
		frame.getContentPane().add(priority);
		
		JButton btnAddVisitor = new JButton("Add Visitor");
		btnAddVisitor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Database db = new Database();
				db.addVisitor(name.getText()+" "+ surname.getText(), passport.getText(), (String)priority.getSelectedItem());
				JOptionPane.showMessageDialog(frame, "Visitor added succesfully");
				String visitorNumber = db.getVisitorNumber(passport.getText());
				JOptionPane.showMessageDialog(frame, "Visitor id = " + visitorNumber);
				mainFrame.updateNumberVisitors();
				frame.dispose();
				
			}
			
		});
		btnAddVisitor.setBounds(153, 227, 89, 23);
		frame.getContentPane().add(btnAddVisitor);
	}


}
