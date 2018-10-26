package loginPhonebook;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class RegisterFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	public RegisterFrame() {
		
		super("Register - Phonebook");
		setResizable(false);
		getContentPane().setLayout(null);
		setBounds(200, 200, 500, 420);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Components
		JLabel jlUsername = new JLabel("Username:");
		JTextField jtfUsername = new JTextField();
		JLabel jlRequired1 = new JLabel("(required)");
		JLabel jlPassword = new JLabel("Password:");
		JPasswordField jpfPassword = new JPasswordField();
		JLabel jlRequired2 = new JLabel("(required)");
		JLabel jlRepeatPassword = new JLabel("Repeat password:");
		JPasswordField jpfRepeatPassword = new JPasswordField();
		JLabel jlRequired3 = new JLabel("(required)");
		JLabel jlName = new JLabel("Username:");
		JTextField jtfName = new JTextField();
		JLabel jlSurname = new JLabel("Username:");
		JTextField jtfSurname = new JTextField();
		JLabel jlDob = new JLabel("Date of birth:");
		JTextField jtfDob = new JTextField();
		JComboBox<Integer> jcbYear = new JComboBox<Integer>();
		JComboBox<Integer> jcbMonth = new JComboBox<Integer>();
		JComboBox<Integer> jcbDay = new JComboBox<Integer>();
		JLabel jlEmail = new JLabel("E-mail:");
		JTextField jtfEmail = new JTextField();
		JLabel jlPhone = new JLabel("Phone:");
		JTextField jtfPhone = new JTextField();
		JButton jbDone = new JButton("DONE");
		JButton jbBack = new JButton("BACK");
		
		//Components adding and positioning
		add(jlUsername);
		add(jtfUsername);
		add(jlRequired1);
		add(jlPassword);
		add(jpfPassword);
		add(jlRequired2);
		add(jlRepeatPassword);
		add(jpfRepeatPassword);
		add(jlRequired3);
		add(jlName);
		add(jtfName);
		add(jlSurname);
		add(jtfSurname);
		add(jlDob);
		add(jtfDob);
		add(jlEmail);
		add(jtfEmail);
		add(jlPhone);
		add(jtfPhone);
		add(jbDone);
		add(jbBack);
		add(jcbYear);
		add(jcbMonth);
		add(jcbDay);
		jlUsername.setBounds(30, 20, 110, 30);
		jlPassword.setBounds(30, 60, 110, 30);
		jlRepeatPassword.setBounds(30, 100, 110, 30);
		jlName.setBounds(30, 140, 110, 30);
		jlSurname.setBounds(30, 180, 110, 30);
		jlDob.setBounds(30, 220, 110, 30);
		jlEmail.setBounds(30, 260, 110, 30);
		jlPhone.setBounds(30, 300, 110, 30);
		jtfUsername.setBounds(150, 20, 260, 25);
		jpfPassword.setBounds(150, 60, 260, 25);
		jpfRepeatPassword.setBounds(150, 100, 260, 25);
		jtfName.setBounds(150, 140, 260, 25);
		jtfSurname.setBounds(150, 180, 260, 25);
		jcbYear.setBounds(150, 220, 100, 25);
		jcbMonth.setBounds(270, 220, 60, 25);
		jcbDay.setBounds(350, 220, 60, 25);
		jtfEmail.setBounds(150, 260, 260, 25);
		jtfPhone.setBounds(150, 300, 260, 25);
		jbDone.setBounds(210, 350, 80, 30);
		jbBack.setBounds(400, 350, 80, 30);
		jlRequired1.setBounds(420, 20, 60, 20);
		jlRequired2.setBounds(420, 60, 60, 20);
		jlRequired3.setBounds(420, 100, 60, 20);
		
		//Adding items into jcbYear
		addYears(jcbYear);
		
		//Adding items into jcbMonth
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
		
		//ActionListener for JButton jbBack
		jbBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new LoginFrame();	
			}
		});
		
		//ActionListener for jbDone - cheks if all fields are entered correct
		//and adds new user to database if everything is correct (registration)
		jbDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String password = String.valueOf(jpfPassword.getPassword());
				String repeatPassword = String.valueOf(jpfRepeatPassword.getPassword());
				String username = jtfUsername.getText();
				String name = jtfName.getText();
				if(name.length() == 0) {
					name = null;
				}
				String surname = jtfSurname.getText();
				if(surname.length() == 0) {
					surname = null;
				}
				String dob = null;
				if(jcbDay.getSelectedItem() != null) {
					dob = "" + jcbYear.getSelectedItem() + "-"
							+ jcbMonth.getSelectedItem() + "-"
							+ jcbDay.getSelectedItem();
				}
				String email = jtfEmail.getText();
				if(email.length() == 0) {
					email = null;
				}
				String phone = jtfPhone.getText();
				if(phone.length() == 0) {
					phone = null;
				}
				
				//Creating User out of entered text fields
				User user = new User(username, password, name, surname, dob, email, phone);
				if(! isUsernameCorrect(username)) {
					displayWarning("Username is incorrect!\nIt must have atleast 6 characters and less than 21.");
				} else if(! isUsernameUnique(username)) {
					displayWarning("Username already taken!");
				} else if(! isPasswordCorrect(password, repeatPassword)) {
					displayWarning("Password is incorrect or you didn't repeat the same password!");
				} else if(! isNameCorrect(name)) {
					displayWarning("Name is incorrect!");
				} else if(! isNameCorrect(surname)) {
					displayWarning("Surname is incorrect!");
				} else if(! isEmailCorrect(email)) {
					displayWarning("Email is incorrect!");
				} else if(! isPhoneCorrect(phone)) {
					displayWarning("Phone number is incorrect!\nIt can contain only out of '+', '-', 0-9.");
				} else {
					try {
						Main.getDAO().addUser(user);
						JOptionPane.showMessageDialog(null,
							    "Registration was succesful.");
						jtfUsername.setText("");
						jpfPassword.setText("");
						jpfRepeatPassword.setText("");
						jtfName.setText("");
						jtfSurname.setText("");
						jtfEmail.setText("");
						jtfPhone.setText("");
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
	
	//This method checks if username is entered correct
	private boolean isUsernameCorrect(String username) {
		if(username.length() < 6 || username.length() > 20) {
			return false;
		}
		return true;
	}
	
	//This method checks if username has not been taken already,
	//in other words, it checks if database contains no such username as one which is entered
	private boolean isUsernameUnique(String username) {
		
		ArrayList<User> users = null;
		
		try {
			users = Main.getDAO().getUsers();
		} catch (SQLException e) {
			e.printStackTrace();
			displayWarning("Oops! Something went wrong.");
		}
		
		for (User user : users) {
			if(username.equals(user.getUsername())) {
				return false;
			}
		}
		
		return true;
	}
	
	//This method checks if password is entered correct
	private boolean isPasswordCorrect(String p1, String p2) {
		if(! p1.equals(p2)) {
			return false;
		}
		if(p1.length() < 6 || p1.length() > 20) {
			return false;
		}
		return true;
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
