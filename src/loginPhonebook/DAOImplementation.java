package loginPhonebook;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DAOImplementation implements UserDAOInterface {

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
			}
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
}
