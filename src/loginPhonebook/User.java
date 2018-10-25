package loginPhonebook;

public class User {

	private String username;
	private String password;
	private String name;
	private String surname;
	private String dob;
	private String email;
	private String phone;
	
	public User(String username, String password, String name, String surname, String dob, String email, String phone) {
		this.username = username;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.dob = dob;
		this.email = email;
		this.phone = phone;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public String getDob() {
		return dob;
	}

	public String getEmail() {
		return email;
	}

	public String getPhone() {
		return phone;
	}
}
