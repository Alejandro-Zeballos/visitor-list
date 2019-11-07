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
				Database db = new Database();
				db.updateVisitor(person.getId(), nameField.getText(), passportField.getText(), (String)comboBox.getSelectedItem() );
				JOptionPane.showMessageDialog(frame, "Visitor updated successfully");
				mainframe.updateNumberVisitors();
				frame.dispose();
			}
		});
		btnUpdate.setBounds(138, 192, 148, 35);
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
	}

}
