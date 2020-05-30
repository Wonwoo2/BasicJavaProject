package service;

import java.util.*;

import dao.QuestionDao;
import data.Session;
import vo.QuestionVO;

public class QuestionService {
	// 싱글톤화
	private static QuestionService instance;

	private QuestionService() {
	}

	public static QuestionService getInstance() {
		if (instance == null) {
			instance = new QuestionService();
		}
		return instance;
	}

	QuestionDao questionDao = QuestionDao.getInstance();

	// 전체 질문리스트 리턴
	public ArrayList<QuestionVO> questionSelectList() {
		ArrayList<QuestionVO> questionList = questionDao
				.selectAllQuestionList();
		return questionList;
	}

	// 질문 인덱스 리턴
	public int indexQuestion(HashMap<String, Object> param) {
		int questionIndex = questionDao.questionIndexSearch(param);
		return questionIndex;
	}

	// 삭제할 질문 인덱스 Dao로 전달
	public void questionDelete(int questionIndex) {
		questionDao.deleteQuestion(questionIndex);
	}

	// 하나의 질문정보를 리턴
	public QuestionVO selectQuestion(HashMap<String, Object> param) {
		QuestionVO questionVo = questionDao.selectQuestion(param);
		return questionVo;
	}

	// 가지고 있는 QnA 개수 반환
	public int haveQnANum() {
		int sum = 0;
		for (int i = 0; i < questionSelectList().size(); i++) {
			ArrayList<QuestionVO> quetionList = questionSelectList();
			QuestionVO question = quetionList.get(i);
			if (question.getFk_userID()
					.equals(Session.LoginUser.getPk_userID())) {
				sum++;
			}
		}
		return sum;
	}

	// 질문 추가
	public void InsertQuestion(int questionNum, String questionTitle,
			String contents, Date day, String userId) {

		QuestionVO que = new QuestionVO();
		que.setPk_questionNo(questionNum);
		que.setQuestionTitle(questionTitle);
		que.setQuestionContent(contents);
		que.setQuestionDate(day);
		que.setFk_userID(userId);

		questionDao.insertQuestion(que);

	}

}
