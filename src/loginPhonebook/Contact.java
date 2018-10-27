package loginPhonebook;

public class Contact {

	private String username;
	private String contactName;
	private String contactSurname;
	private String contactPhone;
	
	public Contact(String username, String contactName, String contactSurname, String contactPhone) {
		this.username = username;
		this.contactName = contactName;
		this.contactSurname = contactSurname;
		this.contactPhone = contactPhone;
	}

	public String getUsername() {
		return username;
	}

	public String getContactName() {
		return contactName;
	}

	public String getContactSurname() {
		return contactSurname;
	}

	public String getContactPhone() {
		return contactPhone;
	}
}
