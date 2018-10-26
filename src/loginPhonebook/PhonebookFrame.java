package loginPhonebook;

import javax.swing.JFrame;

public class PhonebookFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	public PhonebookFrame() {
		
		super("Phonebook");
		setResizable(false);
		getContentPane().setLayout(null);
		setBounds(200, 200, 800, 600);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
