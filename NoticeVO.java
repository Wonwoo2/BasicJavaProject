package vo;

import java.util.Date;
//공지사항  테이블
//공지사항번호(pk)(int), 제목(String), 내용(String), 작성일(Date)

// Notice 테이블에 저장할 데이터들의 변수들을 정의
public class NoticeVO {
	private int pk_noticeNo;
	private String noticeTitle;
	private String noticeContent;
	private Date noticeDate;
	private String fk_userID;

	public int getPk_noticeNo() {
		return pk_noticeNo;
	}

	public void setPk_noticeNo(int pk_noticeNo) {
		this.pk_noticeNo = pk_noticeNo;
	}

	public String getNoticeTitle() {
		return noticeTitle;
	}

	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}

	public String getNoticeContent() {
		return noticeContent;
	}

	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}

	public Date getNoticeDate() {
		return noticeDate;
	}

	public void setNoticeDate(Date noticeDate) {
		this.noticeDate = noticeDate;
	}

	public String getFk_userID() {
		return fk_userID;
	}

	public void setFk_userID(String fk_userID) {
		this.fk_userID = fk_userID;
	}
}