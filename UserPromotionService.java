package service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import vo.UserPromotionVO;
import dao.UserPromotionDao;

public class UserPromotionService {
	// 싱글톤화
	private static UserPromotionService instance;

	private UserPromotionService() {
	}

	public static UserPromotionService getInstance() {
		if (instance == null) {
			instance = new UserPromotionService();
		}
		return instance;
	}

	UserPromotionDao userPromotionDao = UserPromotionDao.getInstance();

	// 프로모션 추가
	public void userPromotionData(int userPromotionNo, Date start, Date end,
			int pk_promotionNo, String pk_userID) {
		UserPromotionVO userPromotionVo = new UserPromotionVO();
		
		userPromotionVo.setPk_userPromotionNO(userPromotionNo);
		userPromotionVo.setStartDate(start);
		userPromotionVo.setEndDate(end);
		userPromotionVo.setFk_promotionNo(pk_promotionNo);
		userPromotionVo.setFk_userID(pk_userID);

		userPromotionDao.insertUserPromotion(userPromotionVo);
	}

	// 하나의 유저프로모션 반환
	public UserPromotionVO selectUserPromotion(int userPromotionNo) {
		HashMap<String, Object> param = new HashMap<>();
		param.put("USERPROMOTIONNO", userPromotionNo);

		UserPromotionVO userPromotionVo = userPromotionDao
				.selectUserPromotion(param);

		return userPromotionVo;
	}

	// 전체 고객 프로모션 리스트 반환
	public ArrayList<UserPromotionVO> selectAllUserPromotionList() {
		ArrayList<UserPromotionVO> userPromotionList = userPromotionDao.selectAllUserPromotionist();
		return userPromotionList;
	}

	// 보유한 프로모션이 있는지 검사
	public boolean userHavePromotion(int userPromotionNo) {
		HashMap<String, Object> param = new HashMap<>();

		param.put("USERPROMOTIONNO", userPromotionNo);

		UserPromotionVO userPromotionVo = userPromotionDao
				.selectUserPromotion(param);
		if (userPromotionVo == null) {
			return false;
		} else {
			return true;
		}
	}

}