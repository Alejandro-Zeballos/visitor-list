package Logic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class Database {
	
	private Connection conn = null;
	private Statement stmt = null;
	private ResultSet rs = null;
	private String dbServer;
	private String user;
	private String password;
	private List<Person> list;
	
	public Database() {

		this.dbServer = "jdbc:mysql://localhost:3306/visitor_list";
		this.user= "root";
		this.password = "";
		list = new ArrayList<Person>();
		
		// Load the database driver
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance() ;
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			System.out.println("the error is in the class.forname1");
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			System.out.println("the error is in the class.forname2");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("the error is in the class.forname3");
			e.printStackTrace();
		}
		// Loop through the SQL Exceptions
	}
	
	public void addVisitor(String name, String passport, String priority) {
		
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		
		String query = "INSERT INTO people(name, passport, date, priority) VALUES ('" + name + "','"+ passport + "','"+ df.format(date)+"','"+ priority +"')";
		
		try {
			// Get a connection to the database
			conn = DriverManager.getConnection(this.dbServer, user, password) ;

			// Get a statement from the connection
			stmt = conn.createStatement() ;
			stmt.executeUpdate(query);
			stmt.close();
			conn.close();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public String getVisitorNumber(String passport) {
		
		String query = "SELECT id FROM people where passport ='" + passport + "'" ;
		String visitorNumber = "";
		
		try {
			// Get a connection to the database
			conn = DriverManager.getConnection(this.dbServer, user, password) ;

			// Get a statement from the connection
			stmt = conn.createStatement() ;
			rs = stmt.executeQuery(query);
			while (rs.next()){
				visitorNumber = rs.getString("id");
		     }
			
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return visitorNumber;
	}
	
	public Person getVisitorInformation(String id) {
		String query = "SELECT* FROM people where id='"+id+"'";
		Person newPerson = null;
		try {
			// Get a connection to the database
			conn = DriverManager.getConnection(this.dbServer, user, password) ;

			// Get a statement from the connection
			stmt = conn.createStatement() ;
			rs = stmt.executeQuery(query);
			
			newPerson = new Person(rs.getString("name"), rs.getString("passport"), rs.getString("date"), rs.getString("priority"), rs.getString("id"));
		
				
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return newPerson;
	}
	
	
	public List<Person> getVisitorList(){
		String query = "SELECT* FROM people";
		
		try {
			// Get a connection to the database
			conn = DriverManager.getConnection(this.dbServer, user, password) ;

			// Get a statement from the connection
			stmt = conn.createStatement() ;
			rs = stmt.executeQuery(query);
			while(rs.next()) {
				list.add(new Person(rs.getString("name"), rs.getString("passport"), rs.getString("date"), rs.getString("priority"), rs.getString("id")));
			}
				
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	public void removeVisitor(String id) {
		
		String query = "DELETE FROM people WHERE id =" + id;
		
		try {
			// Get a connection to the database
			conn = DriverManager.getConnection(this.dbServer, user, password) ;

			// Get a statement from the connection
			stmt = conn.createStatement() ;
			stmt.executeUpdate(query);
			stmt.close();
			conn.close();
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updatePriority(String id, String name, String passport, String priority) {
		removeVisitor(id);
		addVisitor(name,passport,priority);
	}
	
	//this method will only work if the priority does not change.
	
	public void updateVisitor(String id, String name, String passport, String priority) {
		String query = "UPDATE people SET name ='"+name+"', passport ='" + passport + "', priority ='" + priority +"' WHERE id = "+ id ;
		try {
			// Get a connection to the database
			conn = DriverManager.getConnection(this.dbServer, user, password) ;

			// Get a statement from the connection
			stmt = conn.createStatement() ;
			stmt.executeUpdate(query);
			stmt.close();
			conn.close();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	
	
}
