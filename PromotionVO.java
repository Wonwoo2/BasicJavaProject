package vo;
//프로모션 테이블
//프로모션 번호(pk)(int), 프로모션명(String), 가격(int)

// Promotion 테이블에 저장할 데이터들의 변수들을 정의
public class PromotionVO {
	private int pk_promotionNo;
	private String promotionName;
	private int promotionPrice;

	public int getPk_promotionNo() {
		return pk_promotionNo;
	}

	public void setPk_promotionNo(int pk_promotionNo) {
		this.pk_promotionNo = pk_promotionNo;
	}

	public String getPromotionName() {
		return promotionName;
	}

	public void setPromotionName(String promotionName) {
		this.promotionName = promotionName;
	}

	public int getPromotionPrice() {
		return promotionPrice;
	}

	public void setPromotionPrice(int promotionPrice) {
		this.promotionPrice = promotionPrice;
	}
}