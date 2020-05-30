package service;

import java.util.*;
import vo.UserVO;
import dao.UserDao;
import data.*;

public class UserService {

	// 싱글톤화
	private static UserService instance;

	public UserService() {
	}

	public static UserService getInstance() {
		if (instance == null) {
			instance = new UserService();
		}
		return instance;

	}

	UserDao userDao = UserDao.getInstance();

	// 고객 추가
	public void insertUser(String id, String password, String name, String phone) {
		UserVO user = new UserVO();

		user.setPk_userID(id);
		user.setUserPW(password);
		user.setUserName(name);
		user.setUserPhone(phone);

		userDao.insertUser(user);
	}

	// 고객 수정
	public void updateUser(UserVO userVo, int updateIndex) {
		userDao.updateUser(userVo, updateIndex);
	}

	// 고객 삭제
	public void deleteUser(int userIndex) {
		userDao.deleteUser(userIndex);
	}

	// 고객 리스트 반환
	public ArrayList<UserVO> selectUserList() {
		ArrayList<UserVO> userList = userDao.selectAllUserList();
		return userList;
	}

	// 하나의 고객 정보 반환
	public UserVO selectOneUser(String userID) {
		HashMap<String, Object> param = new HashMap<>();
		param.put("USERID", userID);

		UserVO user = userDao.selectUser(param);
		return user;
	}

	// 고객 인덱스 리턴
	public int indexUser(String userID) {
		HashMap<String, Object> param = new HashMap<>();
		param.put("USERID", userID);

		int userIndex = userDao.userIndexSearch(param);
		return userIndex;
	}
	
	public boolean isUser(String userID) {
		HashMap<String, Object> param = new HashMap<>();
		param.put("USERID", userID);
		
		UserVO userVo = userDao.selectUser(param);
		
		if(userVo != null) {
			return true;
		} else {
			return false;
		}
	}

	// 로그아웃
	public void logout() {
		Session.LoginUser = null;
		System.out.println("로그아웃 되었습니다.");
	}

	// 동일한 고객이 있으면 false 반환
	public boolean userEquals(String phone, String name) {
		HashMap<String, Object> param = new HashMap<>();
		
		param.put("USERPHONE", phone);
		param.put("USERNAME", name);
		
		UserVO userVo = userDao.selectUser(param);
		
		if(userVo != null) {
			return true;
		} else {
			return false;
		}
	}
}