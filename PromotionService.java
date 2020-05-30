package service;

import java.util.*;

import dao.PromotionDao;
import vo.*;

public class PromotionService {

	// 싱글톤화
	private static PromotionService instance;

	private PromotionService() {
	}

	public static PromotionService getInstance() {
		if (instance == null) {
			instance = new PromotionService();
		}
		return instance;
	}

	PromotionDao promotionDao = PromotionDao.getInstance();

	UserPromotionService userPromotionService = UserPromotionService
			.getInstance();

	// 프로모션 추가
	public void promotionInsert(int promotionNo, String name, int price) {
		PromotionVO promotion = new PromotionVO();

		promotion.setPk_promotionNo(promotionNo);
		promotion.setPromotionName(name);
		promotion.setPromotionPrice(price);

		promotionDao.insertPromotion(promotion);
	}

	// 프로모션 삭제
	public void deletePromotion(int promotionIndex) {
		promotionDao.deletePromotion(promotionIndex);
	}

	// 하나의 프로모션 반환
	public PromotionVO selectOnePromotion(int promotionNo) {
		HashMap<String, Object> param = new HashMap<>();
		param.put("PROMOTIONNO", promotionNo);
		
		PromotionVO promotion = promotionDao.selectPromotion(param);
		return promotion;
	}

	// 전체 프로모션 목록을 리턴
	public ArrayList<PromotionVO> selectAllPromotionList() {
		ArrayList<PromotionVO> promotionList = promotionDao
				.selectAllPromotionList();
		return promotionList;
	}

	// 프로모션 인덱스 반환
	public int promotionIndex(int promotionNo) {
		HashMap<String, Object> param = new HashMap<>();
		param.put("PROMOTIONNO", promotionNo);
		int promotionIndex = promotionDao.promotionIndexSearch(param);
		return promotionIndex;
	}

	// 프로모션 중복 여부
	public boolean isPromotion(int promotionNo) {
		PromotionVO promotion = selectOnePromotion(promotionNo);
		if (promotion == null) {
			return false;
		} else {
			return true;
		}
	}
}