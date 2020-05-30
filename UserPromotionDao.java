package dao;

import java.util.*;

import vo.UserPromotionVO;
import data.Database;

public class UserPromotionDao {		// UserPromotionPromotion 테이블에 접근하기 위한 접근객체
	// 여기서부터
	private static UserPromotionDao instance;

	private UserPromotionDao() { }

	public static UserPromotionDao getInstance() {
		if (instance == null) {
			instance = new UserPromotionDao();
		}
		return instance;
	}
	//여기까지 싱글톤생성(하나의 객체를 생성하여 여러 클래스에서 사용하기 위한 목적으로 생성)
	
	Database db = Database.getInstance(); // Database 객체를 가져옴
	
	// 고객 프로모션 추가
	public void insertUserPromotion(UserPromotionVO userPromotion) {
		db.tb_userPromotion.add(userPromotion);
	}
	
	// 고객 프로모션 수정
	public void updateUserPromotion(UserPromotionVO userPromotion, int index) {
		db.tb_userPromotion.set(index, userPromotion);
	}

	// 고객 프로모션 삭제
	public void deleteUserPromotion(int index) {
		db.tb_userPromotion.remove(index);
	}
	
	// 전체 고객 프로모션 리스트
	public ArrayList<UserPromotionVO> selectAllUserPromotionist() {
		return db.tb_userPromotion;
	}
	
	// 데이터 검색
	public UserPromotionVO selectUserPromotion(HashMap<String, Object> param) {
		UserPromotionVO rtnUserPromotion = null;
		for (int i = 0; i < db.tb_userPromotion.size(); i ++) {
			UserPromotionVO userPromotion = db.tb_userPromotion.get(i); 			

			boolean flag = true;

			for(String key : param.keySet()) {
				Object value = param.get(key);

				if(key.equals("USERPROMOTIONNO")) {
					if(!(userPromotion.getPk_userPromotionNO() == (int) value)) {
						flag = false;
					}
				} else if(key.equals("STARTDATE")) {
					if(!userPromotion.getStartDate().equals(value)) {
						flag = false;
					}
				} else if(key.equals("ENDDATE")) {
					if(!userPromotion.getStartDate().equals(value)) {
						flag = false;
					}
				} else if(key.equals("PROMOTIONNO")) {
					if(!(userPromotion.getFk_promotionNo() == (int) value)) {
						flag = false;
					}
				} else if(key.equals("USERID")) {
					if(!userPromotion.getFk_userID().equals(value)) {
						flag = false;
					}
				} 
			}
			if(flag) { 
				rtnUserPromotion = userPromotion;
			}
		}	
		return rtnUserPromotion;
	}
	
	// 인덱스 검색
	public int userPromotionIndexSearch(HashMap<String, Object> param) {
		int index = -1;
		for (int i = 0; i < db.tb_userPromotion.size(); i ++) {
			UserPromotionVO userPromotion = db.tb_userPromotion.get(i); 			

			boolean flag = true;

			for(String key : param.keySet()) {
				Object value = param.get(key);

				if(key.equals("USERPROMOTIONNO")) {
					if(!(userPromotion.getPk_userPromotionNO() == (int) value)) {
						flag = false;
					}
				} else if(!key.equals("PROMOTIONNO")) {
					if(!(userPromotion.getFk_promotionNo() == (int) value)) {
						flag = false;
					}
				}  else if(!key.equals("ENDDATE")) {
					if(!userPromotion.getEndDate().equals(value)) {
						flag = false;
					}
				}  else if(!key.equals("USERID")) {
					if(!(userPromotion.getFk_userID().equals(value))) {
						flag = false;
					}
				}
			
				}
			if(flag) { 
				index = i;
			}
		}	
		return index;
	}
}