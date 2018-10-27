package loginPhonebook;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ContactDAOInterface {

	//Method to get all contacts for a specific user
	public ArrayList<Contact> getAllContactsForUser(User user) throws SQLException;
	
	//Method to get contacts by name 
	public ArrayList<Contact> getContactsByName(User user, String name) throws SQLException;
	
	//Method to get contacts by surname
	public ArrayList<Contact> getContactsBySurname(User user, String surname) throws SQLException;
	
	//Method to add a contact
	public void addContact(Contact contact) throws SQLException;
		
	//Method to update a specific contact
	public void updateContact(Contact oldContact, Contact newContact) throws SQLException;
	
	//Method to delete a contact
	public void deleteContact(Contact contact) throws SQLException;
}
