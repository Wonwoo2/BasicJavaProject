package service;

import java.util.HashMap;
import java.util.regex.*;
import vo.UserVO;
import dao.UserDao;
import data.Session;

public class MainService {
	// 싱글톤화
	private static MainService instance;

	private MainService() {
	}

	public static MainService getInstance() {
		if (instance == null) {
			instance = new MainService();
		}
		return instance;

	}

	UserDao userDao = UserDao.getInstance();
	UserService userService = UserService.getInstance();
	MusicListService musicListService = MusicListService.getInstance();
	MusicService musicService = MusicService.getInstance();
	UserPromotionService userPromotionService = UserPromotionService.getInstance();

	// 아이디 찾기
	public UserVO idFind(String name, String phone) {
		HashMap<String, Object> param = new HashMap<>();

		param.put("USERNAME", name);
		param.put("USERPHONE", phone);

		UserVO userVo = idSelect(param);
		return userVo;
	}

	// 이름과 핸드폰정보를 통해 고객 정보를 받아온다
	public UserVO idSelect(HashMap<String, Object> param) {
		
		UserVO userVo = userDao.selectUser(param);
		return userVo;
	}

	// 비밀번호찾기
	public UserVO pwFind(String userID, String userPhone) {
		HashMap<String,Object> param = new HashMap<>();

		param.put("USERID", userID);
		param.put("USERPHONE", userPhone);
		UserVO userVo = pwSelect(param);

		return userVo;
	}

	// 아이디와 연락처를 통해 새로운 비밀번호를 받기위해 고객 정보를 받아온다
	public UserVO pwSelect(HashMap<String, Object> param) {
		UserVO userVo = userDao.selectUser(param);
		return userVo;
	}

	// 회원 가입
	public UserVO userJoin(String id, String pw, String name, String phone) {
		boolean is = userService.isUser(id);
		boolean idRegax = idCheck(id);
		boolean pwRegax = pwCheck(pw);

		UserVO userVo = new UserVO();
		if (!is && idRegax && pwRegax) {
			userVo.setPk_userID(id);
			userVo.setUserPW(pw);
			userVo.setUserName(name);
			userVo.setUserPhone(phone);

			userDao.insertUser(userVo);
			return userVo;
		} else {
			userVo = null;
			return userVo;
		}
	}
	
	public void userStartSet() {
//		Session.musicListSession = musicListService.selectMyMusicList(Session.LoginUser.getPk_userID());
//		Session.musicSession = musicService.myMusic();
//		Session.musicSession = musicService.sortList();
	}

	// 고객 비밀번호 수정
	public UserVO pwUpdate(String userID, String userPW) {
		HashMap<String, Object> param = new HashMap<>();

		param.put("USERID", userID);
		
		UserVO userVo = userDao.selectUser(param);
		int userIndex = userDao.userIndexSearch(param);

		userVo.setUserPW(userPW);
		userDao.updateUser(userVo, userIndex);

		return userVo;
	}

	// 아이디 정규표현식 체크
	public boolean idCheck(String id) {
		String regex = "^[a-zA-Z]{1}[a-zA-Z0-9_]{4,11}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(id);
		return matcher.matches();
	}

	// 비밀번호 정규표현식 체크
	public boolean pwCheck(String pw) {
		String regex = "^(?=.*\\d)(?=.*[~`!@#$%\\^&*()-])(?=.*[a-z])(?=.*[A-Z]).{9,12}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(pw);

		return matcher.matches();
	}
}