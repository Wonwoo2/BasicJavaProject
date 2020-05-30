package service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import dao.AnswerDao;
import vo.AnswerVO;

public class AnswerService {
	// 싱글톤화
	private static AnswerService instance;

	private AnswerService() {
	}

	public static AnswerService getInstance() {
		if (instance == null) {
			instance = new AnswerService();
		}
		return instance;
	}

	AnswerDao answerDao = AnswerDao.getInstance();

	// 답변의 전체 리스트 리턴
	public ArrayList<AnswerVO> answerSelectList() {
		ArrayList<AnswerVO> answerList = answerDao.selectAllList();
		return answerList;
	}

	public AnswerVO selectAnswer(HashMap<String, Object> param) {
		AnswerVO answerVo = answerDao.selectAnswer(param);
		return answerVo;
	}

	public void insertAnswer(int answerNo, String answerContent, Date day, int questionNo) {
		AnswerVO answerVo = new AnswerVO();
		answerVo.setPk_answerNo(answerNo);
		answerVo.setAnswerContent(answerContent);
		answerVo.setAnswerDate(day);
		answerVo.setFk_questionNo(questionNo);
		
		answerDao.insertAnswer(answerVo);
	}

	// 답변 인덱스 검색
	public int answerIndexSearch(int answerNumber) {
		HashMap<String, Object> param = new HashMap<>();
		param.put("ANSWERNO", answerNumber);
		
		int answerIndex = answerDao.answerIndexSearch(param);
		return answerIndex;
	}

	public void deleteAnswer(int answerIndex) {
		answerDao.deleteAnswer(answerIndex);
	}
}
