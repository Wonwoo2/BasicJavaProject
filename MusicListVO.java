package vo;
//음악리스트 테이블
//음악 리스트 번호(pk)(int), 아이디(fk)(String), 음악 고유 번호(fk)(int)

// MusicList 테이블에 저장할 데이터들의 변수들을 정의
public class MusicListVO {
	private String fk_userID;
	private int fk_musicNo;

	public String getFk_userID() {
		return fk_userID;
	}

	public void setFk_userID(String fk_userID) {
		this.fk_userID = fk_userID;
	}

	public int getFk_musicNo() {
		return fk_musicNo;
	}

	public void setFk_musicNo(int fk_musicNo) {
		this.fk_musicNo = fk_musicNo;
	}
}