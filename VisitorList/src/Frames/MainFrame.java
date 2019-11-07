package Frames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Logic.DList;
import Logic.Person;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Window;

public class MainFrame {

	private JFrame frame;
	private DList list;
	final JLabel lblNPeople = new JLabel("");
	static MainFrame window;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new MainFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	/**
	 * Create the application.
	 */
	public MainFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		list = new DList();
		frame = new JFrame();
	

		
		frame.setBounds(100, 100, 562, 458);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnCheckPosition = new JButton("Check Visitor Position");
		btnCheckPosition.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = JOptionPane.showInputDialog(frame, "Insert the visitor's id to check position");
				int position = list.checkPosition(id);
				JOptionPane.showMessageDialog(frame, "The visitor with the id " + id + " is in the position: " + position );
			}
		});
		btnCheckPosition.setBounds(337, 290, 158, 52);
		frame.getContentPane().add(btnCheckPosition);
		
		JButton btnReduceQueue = new JButton("Reduce queue");
		btnReduceQueue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int quantity = Integer.parseInt(JOptionPane.showInputDialog(frame, "How many visitor you want to remove fromm the queue?"));
				/////input validation here//////////////
				
				int answer = JOptionPane.showConfirmDialog(frame, "Confirm removing " + quantity + " visitors from the queue" );
				if(answer == 0) {
					for(int k = 0 ; k<quantity; k++) {
						list.removeTail(list.getTail().getPerson().getId());
					}
					JOptionPane.showMessageDialog(frame, quantity + " visitors has been removed from queue");
					updateNumberVisitors();
				}
		
			}
		});
		btnReduceQueue.setBounds(337, 149, 158, 52);
		frame.getContentPane().add(btnReduceQueue);
		
		JButton btnUpdate = new JButton("Update Visitor Information");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = JOptionPane.showInputDialog(frame, "Insert the visitor's id to be updated");
				new UpdateVisitor(window, list.getPerson(id));
			}
		});
		btnUpdate.setBounds(337, 236, 158, 52);
		frame.getContentPane().add(btnUpdate);
		
		JButton btnVisitorServed = new JButton("Add Visitor");
		btnVisitorServed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AddVisitor(window);
				
				
			}
		});
		btnVisitorServed.setBounds(44, 95, 220, 106);
		frame.getContentPane().add(btnVisitorServed);
		
		JButton btnDeleteVisitor = new JButton("Delete Visitor");
		btnDeleteVisitor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String id = JOptionPane.showInputDialog(frame, "What is the id number of the Visitor to remove?");
				/////input validation here//////////////
				Person thePerson = list.getPerson(id);
				int answer = JOptionPane.showConfirmDialog(frame, "Confirm removing " + thePerson );
				if(answer == 0) {
					list.removeVisitor(id);
					JOptionPane.showMessageDialog(frame, "Visitor removed from queue");
					updateNumberVisitors();
				}
		
			}
		});
		btnDeleteVisitor.setBounds(337, 95, 158, 52);
		frame.getContentPane().add(btnDeleteVisitor);
		
		JButton button = new JButton("Visitor Looked After");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(list.getSize()!= 0) {
					list.lookAfter();
					JOptionPane.showMessageDialog(frame, "Visitor looked after!");
					updateNumberVisitors();
			
				}else {
					JOptionPane.showMessageDialog(frame, "There is no person in the queue");
				}
			}
		});
		button.setBounds(44, 236, 220, 106);
		frame.getContentPane().add(button);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(1);
			}
		});
		btnExit.setBounds(117, 374, 89, 23);
		frame.getContentPane().add(btnExit);
		
		
		lblNPeople.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 36));
		lblNPeople.setForeground(Color.BLUE);
		lblNPeople.setBounds(283, 27, 89, 42);
		frame.getContentPane().add(lblNPeople);
		lblNPeople.setText(String.valueOf(list.getSize()));
		
		JLabel lblPeopleInThe = new JLabel("People in the queue:");
		lblPeopleInThe.setBounds(176, 40, 118, 14);
		frame.getContentPane().add(lblPeopleInThe);
		

		
		
		list.iterateForward();
	}
		public void updateNumberVisitors() {
			list = new DList();
			lblNPeople.setText(String.valueOf(list.getSize()));
		}

}
