package dao;

import java.util.ArrayList;
import java.util.HashMap;
import vo.UserVO;
import data.Database;

public class UserDao { // User 테이블에 접근하기 위한 접근객체
	// 여기서부터
	private static UserDao instance;

	private UserDao() {
	}

	public static UserDao getInstance() {
		if (instance == null) {
			instance = new UserDao();
		}
		return instance;
	}

	// 여기까지 싱글톤생성(하나의 객체를 생성하여 여러 클래스에서 사용하기 위한 목적으로 생성)

	Database db = Database.getInstance(); // Database 객체를 가져옴

	// 회원 데이터 삽입
	public void insertUser(UserVO user) {
		db.tb_user.add(user);
	}

	// 유저 업데이트
	public void updateUser(UserVO user, int index) {
		db.tb_user.set(index, user);
	}

	// 유저 삭제
	public void deleteUser(int index) {
		db.tb_user.remove(index);
	}

	// 전체 회원 조회
	public ArrayList<UserVO> selectAllUserList() {
		return db.tb_user;
	}

	// 데이터 검색
	public UserVO selectUser(HashMap<String, Object> param) {
		UserVO rtnUser = null;
		
		for (int i = 0; i < db.tb_user.size(); i++) {
			UserVO user = db.tb_user.get(i);
			boolean flag = true;
			for (String key : param.keySet()) {
				Object value = param.get(key);

				if (key.equals("USERID")) {
					if (!user.getPk_userID().equals(value)) {
						flag = false;
					}
				} else if (key.equals("USERPW")) {
					if (!user.getUserPW().equals(value)) {
						flag = false;
					}
				} else if (key.equals("USERNAME")) {
					if (!user.getUserName().equals(value)) {
						flag = false;
					}
				} else if (key.equals("USERPHONE")) {
					if (!user.getUserPhone().equals(value)) {
						flag = false;
					}
				} else if (key.equals("USERPROMOTIONNO")) {
					if (!(user.getFk_userPromotionNo() == (int) value)) {
						flag = false;
					}
				}
			}
			if (flag) {
				rtnUser = user;
			}	
		}

		return rtnUser;
	}

	// 인덱스 검색
	public int userIndexSearch(HashMap<String, Object> param) {
		int index = -1;
		for (int i = 0; i < db.tb_user.size(); i++) {
			UserVO user = db.tb_user.get(i);

			boolean flag = true;

			for (String key : param.keySet()) {
				Object value = param.get(key);

				if (key.equals("USERID")) {
					if (!user.getPk_userID().equals(value)) {
						flag = false;
					}
				} else if (key.equals("USERPW")) {
					if (!user.getUserPW().equals(value)) {
						flag = false;
					}
				} else if (key.equals("USERNAME")) {
					if (!user.getUserName().equals(value)) {
						flag = false;
					}
				} else if (key.equals("USERPHONE")) {
					if (!user.getUserPhone().equals(value)) {
						flag = false;
					}
				} else if (key.equals("USERPROMOTIONNO")) {
					if (!(user.getFk_userPromotionNo() == (int) value)) {
						flag = false;
					}
				}
				
				}
			if (flag) {
				index = i;
			}
		}
		return index;
	}

	// 회원번호 조회
	public boolean userNo(HashMap<String, String> param) {
		ArrayList<UserVO> userList = db.tb_user;
		for (int i = 0; i < userList.size(); i++) {
			UserVO user = userList.get(i);
			for (String key : param.keySet()) {
				String value = param.get(key);
				if (key.equals("USERID")) {
					if (user.getPk_userID().equals(value)) {
						return true;
					}
				}
			}
		}
		return false;
	}
}