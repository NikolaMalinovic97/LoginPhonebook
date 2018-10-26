package loginPhonebook;

public class Main {

	private static DAOImplementation DAO = new DAOImplementation();
	

	public static void main(String[] args) {
		
		new LoginFrame();
	}

	public static DAOImplementation getDAO() {
		return DAO;
	}
}
