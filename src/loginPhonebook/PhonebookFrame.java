package loginPhonebook;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;

public class PhonebookFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	User logedUser;

	public PhonebookFrame(User logedUser) {
		
		super("Phonebook");
		
		this.logedUser = logedUser;
		
		setResizable(false);
		getContentPane().setLayout(null);
		setBounds(200, 200, 800, 600);
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
		JList<String> jlContactList = new JList<String>();
		
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
	}
}
