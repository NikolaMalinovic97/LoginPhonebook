package loginPhonebook;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	public LoginFrame() {
		
		super("Login - Phonebook");
		setResizable(false);
		getContentPane().setLayout(null);
		setBounds(200, 200, 600, 360);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Components
		JLabel jlUsername = new JLabel("Username:");
		JLabel jlPassword = new JLabel("Password:");
		JTextField jtfUsername = new JTextField();
		JPasswordField jpfPassword = new JPasswordField();
		JButton jbRegister = new JButton("REGISTER");
		JButton jbLogin= new JButton("LOGIN");
		
		//Components adding and positioning
		add(jlUsername);
		add(jlPassword);
		add(jtfUsername);
		add(jpfPassword);
		add(jbLogin);
		add(jbRegister);
		jlUsername.setBounds(100, 120, 80, 50);
		jlPassword.setBounds(100, 170, 80, 50);
		jtfUsername.setBounds(210, 130, 300, 30);
		jpfPassword.setBounds(210, 180, 300, 30);
		jbRegister.setBounds(490, 30, 90, 20);
		jbLogin.setBounds(260, 260, 80, 30);
		
		//ActionListener for button REGISTER
		jbRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new RegisterFrame();
			}
		});
		
		//ActionListener for button LOGIN
		jbLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String inputUsername = jtfUsername.getText();
				String inputPassword = String.valueOf(jpfPassword.getPassword());
				
				User user = null;
				try {
					user = Main.getDAO().getUser(inputUsername);
					if(user == null) {
						JOptionPane.showMessageDialog(null,
								"This username doesn't exist! Check your input.",
							    "Warning",
							    JOptionPane.WARNING_MESSAGE);
					} else if(! inputPassword.equals(user.getPassword())) {
						JOptionPane.showMessageDialog(null,
								"Your password is incorrect.",
							    "Warning",
							    JOptionPane.WARNING_MESSAGE);
					} else {
						dispose();
						new PhonebookFrame(user);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null,
						    "Oops! Something went wrong.",
						    "Warning",
						    JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		
	}
}
