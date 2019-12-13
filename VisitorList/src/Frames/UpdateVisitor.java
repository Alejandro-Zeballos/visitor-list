package Frames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import Logic.Database;
import Logic.Person;
import Logic.Validator;

import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UpdateVisitor{

	private JFrame frame;
	private JTextField passportField;
	private JTextField nameField;
	private JComboBox comboBox;

	/**
	 * Initialize the contents of the frame.
	 */
	public UpdateVisitor(MainFrame mainframe, Person person) {
		frame = new JFrame();
		frame.setVisible(true);
		frame.setBounds(100, 100, 450, 284);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Validator.isText(nameField.getText())==false || Validator.isNumber(passportField.getText())==false){
					JOptionPane.showMessageDialog(frame, "You entered wrong data types, input Name and Passport again please");
					clearFields();
					return;
				}
				Database db = new Database();
				
				//this if statement will check if the customer changed the priority combobox, if so it will erase the person from the db
				//and add him again with new information to avoid him to remain in top of the queue based on db position.
				
				if(person.getPriority().equals((String)comboBox.getSelectedItem())) {
					db.updateVisitor(person.getId(), nameField.getText(), passportField.getText(), (String)comboBox.getSelectedItem() );
				}else {
					db.updatePriority(person.getId(), nameField.getText(), passportField.getText(), (String)comboBox.getSelectedItem());
				}
				
				JOptionPane.showMessageDialog(frame, "Visitor updated successfully");
				mainframe.updateNumberVisitors();
				frame.dispose();
			}
		});
		btnUpdate.setBounds(213, 184, 148, 35);
		frame.getContentPane().add(btnUpdate);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"LOW", "MEDIUM", "HIGH"}));
		comboBox.setSelectedItem(person.getPriority());
		comboBox.setBounds(137, 137, 227, 20);
		frame.getContentPane().add(comboBox);
		
		JLabel label = new JLabel("Priority Level");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setBounds(9, 140, 108, 14);
		frame.getContentPane().add(label);
		
		JLabel label_2 = new JLabel("Passport N.");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setBounds(9, 87, 108, 14);
		frame.getContentPane().add(label_2);
		
		passportField = new JTextField();
		passportField.setColumns(10);
		passportField.setText(person.getPassport());
		passportField.setBounds(137, 84, 227, 33);
		frame.getContentPane().add(passportField);
		
		JLabel lblFullName = new JLabel("Full name");
		lblFullName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFullName.setBounds(10, 40, 108, 14);
		frame.getContentPane().add(lblFullName);
		
		nameField = new JTextField();
		nameField.setColumns(10);
		nameField.setText(person.getName());
		nameField.setBounds(138, 36, 227, 33);
		frame.getContentPane().add(nameField);
		
		JLabel lblUpdateVisitor = new JLabel("Update Visitor");
		lblUpdateVisitor.setBounds(179, 9, 108, 14);
		frame.getContentPane().add(lblUpdateVisitor);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnBack.setBounds(84, 186, 119, 33);
		frame.getContentPane().add(btnBack);
	}
	
	private void clearFields() {
		nameField.setText("");
		passportField.setText("");
	}

}
