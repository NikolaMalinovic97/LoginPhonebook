package loginPhonebook;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DAOImplementation implements UserDAOInterface, ContactDAOInterface {

	Connection connection = ConnectionManager.getInstance().getConenction();

	@Override
	public ArrayList<User> getUsers() throws SQLException {
		ArrayList<User> users = new ArrayList<User>();
		
		String querry = "SELECT * FROM user";
		
		ResultSet rs = null;
		
		try (Statement statement = connection.createStatement();) {
			rs = statement.executeQuery(querry);
			while(rs.next()) {
				users.add(new User(rs.getString("username"), rs.getString("password"), rs.getString("name"),
						rs.getString("surname"), rs.getString("dob"), rs.getString("email"), rs.getString("phone")));
			}
		} 
		
		return users;
	}
	
	@Override
	public User getUser(String username) throws SQLException {
		User user = null;
		
		String querry = "SELECT * FROM user WHERE username = ?";
		
		ResultSet rs = null;
		
		try (PreparedStatement statement = connection.prepareStatement(querry); ) {
			statement.setString(1, username);
			rs = statement.executeQuery();
			if(rs.next()) {
				user = new User(rs.getString("username"),
								rs.getString("password"), 
								rs.getString("name"), 
								rs.getString("surname"), 
								rs.getString("dob"),
								rs.getString("email"), 
								rs.getString("phone"));
			} else
				return null;
		}
		
		return user;
	}

	@Override
	public void updateUser(User oldUser, User newUser) throws SQLException {
		if(oldUser != null) {
			String querry = "UPDATE user SET name = ?, surname = ?, dob = ?, email = ?, phone = ?"
					+ "WHERE username = ?";
			
			try (PreparedStatement statement = connection.prepareStatement(querry); ) {
				statement.setString(1, newUser.getName());
				statement.setString(2, newUser.getSurname());
				statement.setString(3, newUser.getDob());
				statement.setString(4, newUser.getEmail());
				statement.setString(5, newUser.getPhone());
				statement.setString(6, oldUser.getUsername());
				
				statement.executeQuery();
			}
		}
	}

	@Override
	public void addUser(User user) throws SQLException {
		String querry = "INSERT INTO user VALUES (?, ?, ?, ?, ?, ?, ?)";	
		
		try (PreparedStatement statement = connection.prepareStatement(querry);) {
			statement.setString(1, user.getUsername());
			statement.setString(2, user.getPassword());
			statement.setString(3, user.getName());
			statement.setString(4, user.getSurname());
			statement.setString(5, user.getDob());
			statement.setString(6, user.getEmail());
			statement.setString(7, user.getPhone());
			statement.executeUpdate();
		}  
	}

	@Override
	public ArrayList<Contact> getAllContactsForUser(User user) throws SQLException {
		ArrayList<Contact> contacts = new ArrayList<Contact>();
		
		String querry = "SELECT username, contactName, contactSurname, contactPhone FROM contact "
				   	+ "WHERE username = ?";
		
		ResultSet rs = null;
		
		try (PreparedStatement statement = connection.prepareStatement(querry); ) {
			statement.setString(1, user.getUsername());
			rs = statement.executeQuery();
			while(rs.next()) {
				contacts.add(new Contact(rs.getString("username"), rs.getString("contactName"),
						rs.getString("contactSurname"), rs.getString("contactPhone")));
			}
		} 
		
		return contacts;
	}

	@Override
	public ArrayList<Contact> getContactsByName(User user, String name) throws SQLException {
		ArrayList<Contact> contacts = new ArrayList<Contact>();

		String querry = "SELECT username, contactName, contactSurname, contactPhone FROM contact "
					+ "WHERE username = ? AND contactName = ?";
	
		ResultSet rs = null;
		
		try (PreparedStatement statement = connection.prepareStatement(querry); ) {
			statement.setString(1, user.getUsername());
			statement.setString(2, name);
			rs = statement.executeQuery();
			while(rs.next()) {
				contacts.add(new Contact(rs.getString("username"), rs.getString("contactName"),
						rs.getString("contactSurname"), rs.getString("contactPhone")));
			}
		}
		
		return contacts;
	}

	@Override
	public ArrayList<Contact> getContactsBySurname(User user, String surname) throws SQLException {
		ArrayList<Contact> contacts = new ArrayList<Contact>();

		String querry = "SELECT username, contactName, contactSurname, contactPhone FROM contact "
					+ "WHERE username = ? AND contactSurname = ?";
	
		ResultSet rs = null;
		
		try (PreparedStatement statement = connection.prepareStatement(querry); ) {
			statement.setString(1, user.getUsername());
			statement.setString(2, surname);
			rs = statement.executeQuery();
			while(rs.next()) {
				contacts.add(new Contact(rs.getString("username"), rs.getString("contactName"),
						rs.getString("contactSurname"), rs.getString("contactPhone")));
			}
		}
		
		return contacts;
	}

	@Override
	public void addContact(Contact contact) throws SQLException {
		String querry = "INSERT INTO contact(username, contactName, contactSurname, contactPhone) VALUES (?, ?, ?, ?)";
		
		try (PreparedStatement statement = connection.prepareStatement(querry);) {
			statement.setString(1, contact.getUsername());
			statement.setString(2, contact.getContactName());
			statement.setString(3, contact.getContactSurname());
			statement.setString(4, contact.getContactPhone());
			statement.executeUpdate();
		} 
	}

	@Override
	public void updateContact(Contact oldContact, Contact newContact) throws SQLException {
		String querry = "UPDATE contact SET contactName = ?, contactSurname = ?, contactPhone = ?"
				+ "WHERE username = ? AND contactName = ? AND contactSurname = ? AND contactPhone = ?";
		
		try (PreparedStatement statement = connection.prepareStatement(querry); ) {
			statement.setString(1, newContact.getContactName());
			statement.setString(2, newContact.getContactSurname());
			statement.setString(3, newContact.getContactPhone());
			statement.setString(4, newContact.getUsername());
			statement.setString(5, oldContact.getContactName());
			statement.setString(6, oldContact.getContactSurname());
			statement.setString(7, oldContact.getContactPhone());
			statement.executeUpdate();
		}
	}

	@Override
	public void deleteContact(Contact contact) throws SQLException {
		String querry = "DELETE FROM contact WHERE username = ? AND contactName = ? AND contactSurname = ? "
						+ "AND contactPhone = ?";
		
		try (PreparedStatement statement = connection.prepareStatement(querry); ) {
			statement.setString(1, contact.getUsername());
			statement.setString(2, contact.getContactName());
			statement.setString(3, contact.getContactSurname());
			statement.setString(4, contact.getContactPhone());
			statement.executeUpdate();
		}
	}
}
