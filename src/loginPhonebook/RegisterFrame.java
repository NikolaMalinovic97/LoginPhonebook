package loginPhonebook;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
		jlUsername.setBounds(60, 20, 110, 30);
		jlPassword.setBounds(60, 60, 110, 30);
		jlRepeatPassword.setBounds(60, 100, 110, 30);
		jlName.setBounds(60, 140, 110, 30);
		jlSurname.setBounds(60, 180, 110, 30);
		jlDob.setBounds(60, 220, 110, 30);
		jlEmail.setBounds(60, 260, 110, 30);
		jlPhone.setBounds(60, 300, 110, 30);
		jtfUsername.setBounds(180, 20, 260, 25);
		jpfPassword.setBounds(180, 60, 260, 25);
		jpfRepeatPassword.setBounds(180, 100, 260, 25);
		jtfName.setBounds(180, 140, 260, 25);
		jtfSurname.setBounds(180, 180, 260, 25);
		jcbYear.setBounds(180, 220, 100, 25);
		jcbMonth.setBounds(300, 220, 60, 25);
		jcbDay.setBounds(380, 220, 60, 25);
		jtfEmail.setBounds(180, 260, 260, 25);
		jtfPhone.setBounds(180, 300, 260, 25);
		jbDone.setBounds(210, 350, 80, 30);
		jbBack.setBounds(400, 350, 80, 30);
		
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
		
	}

	private void addYears(JComboBox<Integer> jcbYear) {
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		for (int i = currentYear; i >= 1900; i--) {
			jcbYear.addItem(i);
		}
	}
	
	private void addMonths(JComboBox<Integer> jcbMonth) {
		for (int i = 1; i < 13; i++) {
			jcbMonth.addItem(i);
		}
	}
	
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
}
