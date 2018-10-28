package loginPhonebook;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ChangeAccountInfo extends JFrame {

	private static final long serialVersionUID = 1L;

	public ChangeAccountInfo(User oldUser) {
		
		super("Change Acc Info - Phonebook");
		setResizable(false);
		getContentPane().setLayout(null);
		setBounds(1, 1, 360, 340);
		setLocationRelativeTo(null);
		setVisible(true);
		
		//Components
		JLabel jlName = new JLabel("Name:");
		JLabel jlSurname = new JLabel("Surname:");
		JLabel jlDob = new JLabel("Dob:");
		JLabel jlEmail = new JLabel("Email:");
		JLabel jlPhone = new JLabel("Phone:");
		JTextField jtfName = new JTextField();
		JTextField jtfSurname = new JTextField();
		JTextField jtfEmail = new JTextField();
		JTextField jtfPhone = new JTextField();
		JComboBox<Integer> jcbYear = new JComboBox<Integer>();
		JComboBox<Integer> jcbMonth = new JComboBox<Integer>();
		JComboBox<Integer> jcbDay = new JComboBox<Integer>();
		JButton jbSave = new JButton("SAVE");
		
		//Components adding and positioning
		add(jlName);
		add(jlSurname);
		add(jlDob);
		add(jlEmail);
		add(jlPhone);
		add(jtfName);
		add(jtfSurname);
		add(jtfEmail);
		add(jtfPhone);
		add(jcbYear);
		add(jcbMonth);
		add(jcbDay);
		add(jbSave);
		jlName.setBounds(30, 30, 80, 30);
		jlSurname.setBounds(30, 70, 80, 30);
		jlDob.setBounds(30, 110, 80, 30);
		jlEmail.setBounds(30, 150, 80, 30);
		jlPhone.setBounds(30, 190, 80, 30);
		jtfName.setBounds(110, 30, 200, 25);
		jtfSurname.setBounds(110, 70, 200, 25);
		jtfEmail.setBounds(110, 150, 200, 25);
		jtfPhone.setBounds(110, 190, 200, 25);
		jcbYear.setBounds(110, 110, 70, 25);
		jcbMonth.setBounds(193, 110, 50, 25);
		jcbDay.setBounds(259, 110, 50, 25);
		jbSave.setBounds(130, 250, 100, 40);
		jtfName.setText(oldUser.getName());
		jtfSurname.setText(oldUser.getSurname());
		jtfEmail.setText(oldUser.getEmail());
		jtfPhone.setText(oldUser.getPhone());
		
		//Seting up combo boxes
		addYears(jcbYear);
		addMonths(jcbMonth);
		
		//Adding items into jcbDay
		jcbMonth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jcbDay.removeAllItems();
				addDays(jcbYear, jcbMonth, jcbDay);
			}
		});
		jcbYear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jcbDay.removeAllItems();
				addDays(jcbYear, jcbMonth, jcbDay);
			}
		});
		
		
		//ActionListener for SAVE button
		jbSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = oldUser.getUsername();
				String password = oldUser.getPassword();
				String newUserName = jtfName.getText();
				if(newUserName.equals("")) {
					newUserName = null;
				}
				String newUserSurname = jtfSurname.getText();
				if(newUserSurname.equals("")) {
					newUserSurname = null;
				}
				String newUserDob = null;
				if(jcbDay.getSelectedItem() != null) {
					newUserDob = "" + jcbYear.getSelectedItem() + "-"
							+ jcbMonth.getSelectedItem() + "-"
							+ jcbDay.getSelectedItem();
				}
				String newUserEmail = jtfEmail.getText();
				if(newUserEmail.equals("")) {
					newUserEmail = null;
				}
				String newUserPhone = jtfPhone.getText();
				if(newUserPhone.equals("")) {
					newUserPhone = null;
				}
				if(! isNameCorrect(newUserName)) {
					displayWarning("Name is incorrect!");
				} else if(! isNameCorrect(newUserSurname)) {
					displayWarning("Surname is incorrect!");
				} else if(! isEmailCorrect(newUserEmail)) {
					displayWarning("Email is incorrect!");
				} else if(! isPhoneCorrect(newUserPhone)) {
					displayWarning("Phone number is incorrect!\nIt can contain only out of '+', '-', 0-9.");
				} else {
					User newUser = new User(username, password, newUserName, newUserSurname, newUserDob, newUserEmail, newUserPhone);
					try {
						Main.getDAO().updateUser(oldUser, newUser);
						JOptionPane.showMessageDialog(null,
							    "Changes have been saved.");
						dispose();
					} catch (SQLException e1) {
						e1.printStackTrace();
						displayWarning("Ooops! Something went wrong.");
					}
				}
			}
		});
	}
	
	//Method for adding years into jcbYear ComboBox
	private void addYears(JComboBox<Integer> jcbYear) {
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		for (int i = currentYear; i >= 1900; i--) {
			jcbYear.addItem(i);
		}
	}
	
	//Method for adding months into jcbMonth ComboBox
	private void addMonths(JComboBox<Integer> jcbMonth) {
		for (int i = 1; i < 13; i++) {
			jcbMonth.addItem(i);
		}
	}
	
	//Method for adding days into jcbDay ComboBox
	private void addDays(JComboBox<Integer> jcbYear, JComboBox<Integer> jcbMonth, JComboBox<Integer> jcbDay) {
		int year = (int) jcbYear.getSelectedItem();
		int month = (int) jcbMonth.getSelectedItem();
		int maxDays = 0;
		
		switch (month) {
		case 1: case 3: case 5:
        case 7: case 8: case 10:
        case 12:
            maxDays = 31;
            break;
        case 4: case 6:
        case 9: case 11:
            maxDays = 30;
            break;
        case 2:
            if (((year % 4 == 0) && 
                 !(year % 100 == 0))
                 || (year % 400 == 0))
                maxDays = 29;
            else
                maxDays = 28;
            break;
		}
		
		for (int i = maxDays; i > 0; i--) {
			jcbDay.addItem(i);
		}
	}
	
	//Method for displaying warning with specific message
	private void displayWarning(String message) {
		JOptionPane.showMessageDialog(null,
			    message,
			    "Warning",
			    JOptionPane.WARNING_MESSAGE);
	}
	
	//This method checks if name or surname are entered correct
	private boolean isNameCorrect(String name) {
		if(name == null) {
			return true;
		}
		if(name.length() > 40) {
			return false;
		}
		return true;	
	}
	
	//This method checks if email is entered correct
	private boolean isEmailCorrect(String email) {
		if(email == null) {
			return true;
		}
		if(email.length() > 254) {
			return false;
		}
		for (int i = 0; i < email.length(); i++) {
			if(email.charAt(i) == '@') {
				return true;
			}
		}
		return false;
	}
	
	//This method checks if phone number is entered correct
	private boolean isPhoneCorrect(String phone) {
		if(phone == null) {
			return true;
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
