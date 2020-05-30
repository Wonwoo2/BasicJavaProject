package vo;

import java.util.Date;
//고객 음악 댓글 테이블
//고객 음악 댓글 번호(pk)(int), 내용(String), 작성일(Date), 별점(double), 음악 고유번호(fk)(int), 아이디(fk)(String)

//UserComment 테이블에 저장할 데이터들의 변수들을 정의
public class UserMusicCommentVO {
	private int pk_userCommentNo;
	private String commentContent;
	private Date commentDate;
	private double star;
	private String fk_userID;
	private int fk_musicNo;

	public int getPk_userCommentNo() {
		return pk_userCommentNo;
	}

	public void setPk_userCommentNo(int pk_userCommentNo) {
		this.pk_userCommentNo = pk_userCommentNo;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public Date getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}
	
	public double getStar() {
		return star;
	}

	public void setStar(double star) {
		this.star = star;
	}

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