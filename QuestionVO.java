package vo;

import java.util.Date;
//질문 테이블 QnA
//질문 고유 번호(pk)(int), 제목(String), 내용(String), 작성일(Date), 아이디(fk)(String)

public class QuestionVO {
	private int pk_questionNo;
	private String questionTitle;
	private String questionContent;
	private Date questionDate;
	private String fk_userID;

	public int getPk_questionNo() {
		return pk_questionNo;
	}

	public void setPk_questionNo(int pk_questionNo) {
		this.pk_questionNo = pk_questionNo;
	}

	public String getQuestionTitle() {
		return questionTitle;
	}

	public void setQuestionTitle(String questionTitle) {
		this.questionTitle = questionTitle;
	}

	public String getQuestionContent() {
		return questionContent;
	}

	public void setQuestionContent(String questionContent) {
		this.questionContent = questionContent;
	}

	public Date getQuestionDate() {
		return questionDate;
	}

	public void setQuestionDate(Date questionDate) {
		this.questionDate = questionDate;
	}

	public String getFk_userID() {
		return fk_userID;
	}

	public void setFk_userID(String fk_userID) {
		this.fk_userID = fk_userID;
	}

}
