package loginPhonebook;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ChangeContactFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	public ChangeContactFrame(Contact oldContact) {
		
		super("Change Contact - Phonebook");
		setResizable(false);
		getContentPane().setLayout(null);
		setBounds(1, 1, 360, 300);
		setLocationRelativeTo(null);
		setVisible(true);
		
		//Components
		JLabel jlName = new JLabel("Name:");
		JLabel jlSurname = new JLabel("Surname:");
		JLabel jlPhone = new JLabel("Phone:");
		JTextField jtfName = new JTextField();
		JTextField jtfSurname = new JTextField();
		JTextField jtfPhone = new JTextField();
		JButton jbChange = new JButton("CHANGE");
		
		//Components adding and positioning
		add(jlName);
		add(jlSurname);
		add(jlPhone);
		add(jtfName);
		add(jtfSurname);
		add(jtfPhone);
		add(jbChange);
		jlName.setBounds(30, 50, 80, 30);
		jlSurname.setBounds(30, 90, 80, 30);
		jlPhone.setBounds(30, 130, 80, 30);
		jtfName.setBounds(110, 50, 200, 25);
		jtfSurname.setBounds(110, 90, 200, 25);
		jtfPhone.setBounds(110, 130, 200, 25);
		jbChange.setBounds(130, 190, 100, 40);
		jtfName.setText(oldContact.getContactName());
		jtfSurname.setText(oldContact.getContactSurname());
		jtfPhone.setText(oldContact.getContactPhone());
		
		//ActionListener for button CHANGE
		jbChange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = oldContact.getUsername();
				String newContactName = jtfName.getText();
				String newContactSurname = jtfSurname.getText();
				String newContactPhone = jtfPhone.getText();
				if(! isNameCorrect(newContactName)) {
					displayWarning("Name is incorrect!\nName must have between 1 and 40 characters.");
				} else if(! isNameCorrect(newContactSurname)) {
					displayWarning("Surame is incorrect!\nSurname must have between 1 and 40 characters.");
				} else if(! isPhoneCorrect(newContactPhone)) {
					displayWarning("Phone number is incorrect!\nIt can contain only out of '+', '-', 0-9.");
				} else {
					try {
						Contact newContact = new Contact(username, newContactName, newContactSurname, newContactPhone);
						Main.getDAO().updateContact(oldContact, newContact);
						JOptionPane.showMessageDialog(null,
							    "Contact has been changed succesfuly. Click on CLEAR to see changes");
						dispose();
					} catch (SQLException e1) {
						e1.printStackTrace();
						displayWarning("Ooops! Something went wrong.");
					}
				}
			}
		});
	}
	
	//Method for displaying warning with specific message
	private void displayWarning(String message) {
		JOptionPane.showMessageDialog(null,
			    message,
			    "Warning",
			    JOptionPane.WARNING_MESSAGE);
	}
	
	//This method checks if name or surname is entered correct
	private boolean isNameCorrect(String name) {
		if(name == null) {
			return false;
		}
		if(name.length() > 40) {
			return false;
		}
		return true;	
	}
	
	//This method checks if phone number is entered correct
	private boolean isPhoneCorrect(String phone) {
		if(phone == null) {
			return false;
		}
		boolean correct = true;
		char c;
		for (int i = 0; i < phone.length(); i++) {
			c = phone.charAt(i);
			if(! (c == '+' || c == '-' || c== '0' || c== '1' || c== '2' || c== '3' || c== '4'
									|| c== '5' || c== '6' || c== '7' || c== '8' || c== '9'))  {
				correct = false;
				break;
			}
		}
		return correct;
	}
}
