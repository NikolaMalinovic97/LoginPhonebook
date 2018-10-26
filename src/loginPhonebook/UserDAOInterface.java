package loginPhonebook;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UserDAOInterface {

	//Method to get all users
	public ArrayList<User> getUsers() throws SQLException;
		
	//Method to get a specific user
	public User getUser(String username) throws SQLException;
	
	//Method to update a specific user
	public void updateUser(User oldUser, User newUser) throws SQLException;
	
	//Method top add a user
	public void addUser(User user) throws SQLException;
}
