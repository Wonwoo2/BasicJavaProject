package service;

public class AdminService {

	// 싱글톤화
	private static AdminService instance;

	private AdminService() { }

	public static AdminService getInstance() {
		if (instance == null) {
			instance = new AdminService();
		}
		return instance;
	}
}