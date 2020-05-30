package vo;

import java.util.Date;
//답변 테이블 
//답변 댓글 번호(pk)(int), 답변 내용(String), 답변 작성일(Date), 질문 고유 번호(fk)(int)

public class AnswerVO {
	private int pk_answerNo;
	private String answerContent;
	private Date answerDate;
	private int fk_questionNo;

	public int getPk_answerNo() {
		return pk_answerNo;
	}

	public void setPk_answerNo(int pk_answerNo) {
		this.pk_answerNo = pk_answerNo;
	}

	public String getAnswerContent() {
		return answerContent;
	}

	public void setAnswerContent(String answerContent) {
		this.answerContent = answerContent;
	}

	public Date getAnswerDate() {
		return answerDate;
	}

	public void setAnswerDate(Date answerDate) {
		this.answerDate = answerDate;
	}

	public int getFk_questionNo() {
		return fk_questionNo;
	}

	public void setFk_questionNo(int fk_questionNo) {
		this.fk_questionNo = fk_questionNo;
	}
}