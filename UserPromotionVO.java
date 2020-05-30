package vo;

import java.util.Date;
//고객 프로모션 테이블
//고객프로모션번호(pk)(int), 구매일자(Date), 시작일자(Date), 종료일자(Date), 프로모션 번호(fk)(int), 아이디(fk)(String)

//UserPromotion 테이블에 저장할 데이터들의 변수들을 정의
public class UserPromotionVO {
	private int pk_userPromotionNO;
	private Date startDate;
	private Date endDate;
	private int fk_promotionNo;
	private String fk_userID;

	public int getPk_userPromotionNO() {
		return pk_userPromotionNO;
	}

	public void setPk_userPromotionNO(int pk_userPromotionNO) {
		this.pk_userPromotionNO = pk_userPromotionNO;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getFk_promotionNo() {
		return fk_promotionNo;
	}

	public void setFk_promotionNo(int fk_promotionNo) {
		this.fk_promotionNo = fk_promotionNo;
	}

	public String getFk_userID() {
		return fk_userID;
	}

	public void setFk_userID(String fk_userID) {
		this.fk_userID = fk_userID;
	}

}