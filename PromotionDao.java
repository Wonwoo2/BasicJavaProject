package dao;

import java.util.ArrayList;
import java.util.HashMap;

import vo.PromotionVO;
import data.Database;

public class PromotionDao {		// Promotion 테이블에 접근하기 위한 접근 객체
	// 여기서부터
	private static PromotionDao instance;

	private PromotionDao() { }

	public static PromotionDao getInstance() {
		if (instance == null) {
			instance = new PromotionDao();
		}
		return instance;
	}
	//여기까지 싱글톤생성(하나의 객체를 생성하여 여러 클래스에서 사용하기 위한 목적으로 생성)
	
	Database db = Database.getInstance(); // Database 객체를 가져옴
	
	// 데이터 삽입
	public void insertPromotion(PromotionVO promotion) {
		db.tb_promotion.add(promotion);
	}

	// 전체 프로모션 리스트
	public ArrayList<PromotionVO> selectAllPromotionList() {
		return db.tb_promotion;
	}

	// 프로모션 업데이트
	public boolean updatePromotion(PromotionVO promotion, int index) {
		db.tb_promotion.set(index, promotion);
		return true;
	}
	
	// 프로모션 삭제
	public boolean deletePromotion(int index) {
		db.tb_promotion.remove(index);
		return true;
	}
	
	// 데이터 검색
	// PromotionVO 데이터 : promotionNo, promotionName, promotionPrice;
	public PromotionVO selectPromotion(HashMap<String, Object> param) {
		PromotionVO rPromotion = null;
		for (int i = 0; i < db.tb_promotion.size(); i++) {
			PromotionVO promotion = db.tb_promotion.get(i);

			boolean flag = true;

			for (String key : param.keySet()) {
				Object value = param.get(key);

				if (key.equals("PROMOTIONNO")) {
					if (!(promotion.getPk_promotionNo() == (int) value)) {
						flag = false;
					}
				} else if (key.equals("PROMOTIONNAME")) {
					if (!(promotion.getPromotionName().equals(value))) {
						flag = false;
					}
				} else if (key.equals("PROMOTIONPRICE")) {
					if (!(promotion.getPromotionPrice() == (int) value)) {
						flag = false;
					}
				} 
			}
			if (flag) {
				rPromotion = promotion;
			}
		}
		return rPromotion;
	}

	// 인덱스 검색
	public int promotionIndexSearch(HashMap<String, Object> param) {
		int index = -1;
		for (int i = 0; i < db.tb_promotion.size(); i++) {
			PromotionVO promotion = db.tb_promotion.get(i);

			boolean flag = true;

			for (String key : param.keySet()) {
				Object value = param.get(key);

				if (key.equals("PROMOTIONNO")) {
					if (!(promotion.getPk_promotionNo() == (int) value)) {
						flag = false;
					}
				} else if (key.equals("PROMOTIONNAME")) {
					if (!(promotion.getPromotionName().equals(value))) {
						flag = false;
					}
				} else if (key.equals("PROMOTIONPRICE")) {
					if (!(promotion.getPromotionPrice() == (int) value)) {
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

	// 프로모션 번호 검색
	public boolean promotionNo(HashMap<String, Integer> param) {
		ArrayList<PromotionVO> promotion = db.tb_promotion;
		for (int i = 0; i < promotion.size(); i++) {
			PromotionVO userP = promotion.get(i);
			for (String key : param.keySet()) {
				int value = param.get(key);
				if (key.equals("PROMOTIONNO")) {
					if (userP.getPk_promotionNo() == value) {
						return true;
					}
				}
			}
		}
		return false;
	}

}