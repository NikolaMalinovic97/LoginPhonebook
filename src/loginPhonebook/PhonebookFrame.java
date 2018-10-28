package loginPhonebook;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class PhonebookFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	User logedUser;

	public PhonebookFrame(User logedUser) {
		
		super("Phonebook");
		
		DefaultListModel<String> dlm = new DefaultListModel<String>();
		
		this.logedUser = logedUser;
		
		setResizable(false);
		getContentPane().setLayout(null);
		setBounds(200, 200, 800, 600);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Components
		JButton jbAddContact = new JButton ("Add Contact");
		JButton jbChangeContact = new JButton ("Change Contact");
		JButton jbDeleteContact = new JButton ("Delete Contact");
		JLabel jlSearch = new JLabel ("Search:");
		JLabel jlBy = new JLabel ("By:");
		JButton jbName = new JButton("NAME");
		JButton jbSurname = new JButton("SURNAME");
		JButton jbClear = new JButton("CLEAR");
		JTextField jtfSearch = new JTextField();
		JButton jbChangeAccountInformation = new JButton ("Change account informations");
		JButton jbLogOut = new JButton ("LOG OUT");
		JList<String> jlContactList = new JList<String>(dlm);
		
		//Components adding and positioning
		add(jbAddContact);
		add(jbChangeContact);
		add(jbDeleteContact);
		add(jlSearch);
		add(jlBy);
		add(jbName);
		add(jbSurname);
		add(jbClear);
		add(jtfSearch);
		add(jbChangeAccountInformation);
		add(jbLogOut);
		add(jlContactList);
		jbAddContact.setBounds(20, 20, 200, 20);
		jbChangeContact.setBounds(20, 45, 200, 20);
		jbDeleteContact.setBounds(20, 70, 200, 20);
		jlSearch.setBounds(255, 20, 60, 20);
		jlBy.setBounds(281, 45, 40, 20);
		jtfSearch.setBounds(305, 20, 200, 20);
		jbName.setBounds(305, 45, 95, 20);
		jbSurname.setBounds(410, 45, 95, 20);
		jbClear.setBounds(305, 70, 200, 20);
		jbLogOut.setBounds(560, 20, 210, 20);
		jbChangeAccountInformation.setBounds(560, 45, 210, 45);
		jlContactList.setBounds(20, 100, 760, 460);
		
		//Load all contacts
		ArrayList<Contact> allContacts = null;
		try {
			allContacts = Main.getDAO().getAllContactsForUser(logedUser);
			String str;
			for (Contact c: allContacts) {
				str = "";
				str += "NAME: " + c.getContactName();
				str += " | SURNAME: " + c.getContactSurname();
				str += " | PHONE: " + c.getContactPhone();
				dlm.addElement(str);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,
					"Oops! Somethin went wrong.",
				    "Warning",
				    JOptionPane.WARNING_MESSAGE);
		}
		
		//ActionListener for Add Contact button 
		jbAddContact.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AddContactFrame(logedUser);
			}
		});
		
		//ActionListener for Delete Contact button
		jbDeleteContact.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str = jlContactList.getSelectedValue();
				String username = logedUser.getUsername();
				String contactName = trimContactName(str);
				String contactSurname = trimContactSurname(str);
				String contactPhone = trimContactPhone(str);
				try {
					Main.getDAO().deleteContact(new Contact(username, contactName, contactSurname, contactPhone));
					JOptionPane.showMessageDialog(null,
						    "Contact has been deleted succesfuly. Click on CLEAR to see changes");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		//ActionListener for JButton NAME - gets all contacts which have the same name as input in jtfSearc
		jbName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = jtfSearch.getText();
				dlm.clear();
				ArrayList<Contact> contacts = null;
				String str;
				try {
					contacts = Main.getDAO().getContactsByName(logedUser, name);
					for (Contact c : contacts) {
						str = "";
						str += "NAME: " + c.getContactName();
						str += " | SURNAME: " + c.getContactSurname();
						str += " | PHONE: " + c.getContactPhone();
						dlm.addElement(str);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null,
							"Oops! Somethin went wrong.",
						    "Warning",
						    JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		
		//ActionListener for JButton SURNAME - gets all contacts which have the same surname as input in jtfSearc
		jbSurname.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String surname = jtfSearch.getText();
				dlm.clear();
				ArrayList<Contact> contacts = null;
				String str;
				try {
					contacts = Main.getDAO().getContactsBySurname(logedUser, surname);
					for (Contact c : contacts) {
						str = "";
						str += "NAME: " + c.getContactName();
						str += " | SURNAME: " + c.getContactSurname();
						str += " | PHONE: " + c.getContactPhone();
						dlm.addElement(str);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null,
							"Oops! Somethin went wrong.",
						    "Warning",
						    JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		
		//ActionListener for CLEAR
		jbClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dlm.clear();
				ArrayList<Contact> allContacts = null;
				try {
					allContacts = Main.getDAO().getAllContactsForUser(logedUser);
					String str;
					for (Contact c: allContacts) {
						str = "";
						str += "NAME: " + c.getContactName();
						str += " | SURNAME: " + c.getContactSurname();
						str += " | PHONE: " + c.getContactPhone();
						dlm.addElement(str);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null,
							"Oops! Somethin went wrong.",
						    "Warning",
						    JOptionPane.WARNING_MESSAGE);
				}
			}
		});
	}
	
	//Method to get contact name out of selected list item
	private String trimContactName(String str) {
		String resultString = "";
		
		for (int i = 6; i < str.length(); i++) {
			if(str.charAt(i) == ' ') {
				break;
			} else {
				resultString += str.charAt(i);
			}
		}
		
		return resultString;
	}
	
	//Method to get contact surname out of selected list item
	private String trimContactSurname(String str) {
		String resultString = "";
		int whitespaceCounter = 0;
		
		for (int i = 6; i < str.length(); i++) {
			if(str.charAt(i) == ' ') {
				whitespaceCounter++;
				if(whitespaceCounter == 4) {
					break;
				}
			} else if(whitespaceCounter == 3) {
				resultString += str.charAt(i);
			}
		}
		
		return resultString;
	}
	
	//Method to get contacts phone number out of selected list item
	private String trimContactPhone(String str) {
		String resultString = "";
		int whitespaceCounter = 0;
		
		for (int i = 6; i < str.length(); i++) {
			if(str.charAt(i) == ' ') {
				whitespaceCounter++;
				if(whitespaceCounter == 7) {
					break;
				}
			} else if(whitespaceCounter == 6) {
				resultString += str.charAt(i);
			}
		}
		
		return resultString;
	}
}
