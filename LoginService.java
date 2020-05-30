package service;

import java.util.HashMap;

import dao.*;
import vo.*;

public class LoginService {
	// 싱글톤화
	private static LoginService instance;

	private LoginService() {}
	
	public static LoginService getInstance() {
		if (instance == null) {
			instance = new LoginService();
		}
		return instance;
	}
	
	UserDao userDao = UserDao.getInstance();

	// 로그인 유저 정보 반환
	public UserVO LoginData(String userID, String userPW) {
		HashMap<String, Object> param = new HashMap<>();
		param.put("USERID", userID);
		param.put("USERPW", userPW);
		
		UserVO userVo = userDao.selectUser(param);
		return userVo;
	}

	
}