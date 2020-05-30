package dao;

import java.util.ArrayList;
import java.util.HashMap;

import vo.QuestionVO;
import data.Database;

public class QuestionDao { // Question 테이블에 접근하기 위한 접근 객체
	// 여기서부터
	private static QuestionDao instance;

	private QuestionDao() {
	}

	public static QuestionDao getInstance() {
		if (instance == null) {
			instance = new QuestionDao();
		}
		return instance;
	}

	// 여기까지 싱글톤생성(하나의 객체를 생성하여 여러 클래스에서 사용하기 위한 목적으로 생성)

	Database db = Database.getInstance(); // Database 객체를 가져옴

	// 데이터 삽입
	public void insertQuestion(QuestionVO Question) {
		db.tb_question.add(Question);
	}

	// Question 업데이트
	public void updateQuestion(QuestionVO Question, int index) {
		db.tb_question.set(index, Question);
	}

	// Question 삭제
	public void deleteQuestion(int index) {
		db.tb_question.remove(index);
	}

	// 전체 Question 리스트
	public ArrayList<QuestionVO> selectAllQuestionList() {
		return db.tb_question;
	}

	// 데이터 검색
	// QuestionVO 데이터 : questionNo, questionPerson, questionTitle,
	// questionContent, questionDate,
	// quetionUserNo, managerNo, answerPerson, answerContent, answerDate;
	public QuestionVO selectQuestion(HashMap<String, Object> param) {
		QuestionVO rQuestion = null;
		for (int i = 0; i < db.tb_question.size(); i++) {
			QuestionVO Question = db.tb_question.get(i);

			boolean flag = true;

			for (String key : param.keySet()) {
				Object value = param.get(key);

				if (key.equals("QUESTIONNO")) {
					if (!(Question.getPk_questionNo() == (int) value)) {
						flag = false;
					}
				} else if (key.equals("QUESTIONTITLE")) {
					if (!(Question.getQuestionTitle().equals(value))) {
						flag = false;
					}
				} else if (key.equals("QUESTIONCONTENT")) {
					if (!(Question.getQuestionContent().equals(value))) {
						flag = false;
					}
				} else if (key.equals("QUESTIONDATE")) {
					if (!(Question.getQuestionDate() == value)) {
						flag = false;
					}
				} else if (key.equals("USERID")) {
					if (!(Question.getFk_userID() == value)) {
						flag = false;
					}
				}
			}
			if (flag) {
				rQuestion = Question;
			}
		}
		return rQuestion;
	}

	// 인덱스 검색
	public int questionIndexSearch(HashMap<String, Object> param) {
		int index = -1;
		for (int i = 0; i < db.tb_question.size(); i++) {
			QuestionVO Question = db.tb_question.get(i);

			boolean flag = true;

			for (String key : param.keySet()) {
				Object value = param.get(key);

				if (key.equals("QUESTIONNO")) {
					if (!(Question.getPk_questionNo() == (int) value)) {
						flag = false;
					}
				} else if (key.equals("QUESTIONTITLE")) {
					if (!(Question.getQuestionTitle().equals(value))) {
						flag = false;
					}
				} else if (key.equals("QUESTIONCONTENT")) {
					if (!(Question.getQuestionContent().equals(value))) {
						flag = false;
					}
				} else if (key.equals("QUESTIONDATE")) {
					if (!(Question.getQuestionDate() == value)) {
						flag = false;
					}
				} else if (key.equals("USERID")) {
					if (!(Question.getFk_userID() == value)) {
						flag = false;
					}
				}
				if (flag) {
					index = i;
				}
			}
		}
		return index;
	}

	// Question테이블 질문 고유 번호 검색
	public boolean QuestionNo(HashMap<String, Integer> param) {
		ArrayList<QuestionVO> question = db.tb_question;
		for (int i = 0; i < question.size(); i++) {
			QuestionVO questionNo = question.get(i);
			for (String key : param.keySet()) {
				int value = param.get(key);
				if (key.equals("QUESTIONNO")) {
					if (questionNo.getPk_questionNo() == value) {
						return true;
					}
				}
			}
		}
		return false;
	}

	// Question 테이블 회원아이디 검색
	public boolean QuestionuserNo(HashMap<String, Integer> param) {
		ArrayList<QuestionVO> question = db.tb_question;
		for (int i = 0; i < question.size(); i++) {
			QuestionVO userID = question.get(i);
			for (String key : param.keySet()) {
				int value = param.get(key);
				if (key.equals("USERNO")) {
					if (userID.getFk_userID().equals(value)) {
						return true;
					}
				}
			}
		}
		return false;
	}
}
