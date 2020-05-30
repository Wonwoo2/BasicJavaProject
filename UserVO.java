package vo;

//고객 테이블
//아이디(pk)(String), 고객 비밀번호(String), 고객 이름(String), 고객 핸드폰번호(String)

// User 테이블에 저장할 데이터들의 변수들을 정의
public class UserVO {
	private String pk_userID;
	private String userPW;
	private String userName;
	private String userPhone;
	private int Fk_userPromotionNo;

	public int getFk_userPromotionNo() {
		return Fk_userPromotionNo;
	}

	public void setFk_userPromotionNo(int Fk_userPromotionNo) {
		this.Fk_userPromotionNo = Fk_userPromotionNo;
	}

	public String getPk_userID() {
		return pk_userID;
	}

	public void setPk_userID(String pk_userID) {
		this.pk_userID = pk_userID;
	}

	public String getUserPW() {
		return userPW;
	}

	public void setUserPW(String userPW) {
		this.userPW = userPW;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

}