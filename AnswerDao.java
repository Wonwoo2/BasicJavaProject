package dao;

import java.util.*;

import data.Database;
import vo.AnswerVO;

public class AnswerDao {
	// 여기서부터
	private static AnswerDao instance;

	private AnswerDao() {
	}

	public static AnswerDao getInstance() {
		if (instance == null) {
			instance = new AnswerDao();
		}
		return instance;
	}

	Database database = Database.getInstance();

	// 답변 추가
	public void insertAnswer(AnswerVO answerVo) {
		database.tb_answer.add(answerVo);
	}
	
	// 답변 삭제
	public void deleteAnswer(int answerIndex) {
		database.tb_answer.remove(answerIndex);
		
	}
	
	// 전체 답변리스트 반환
	public ArrayList<AnswerVO> selectAllList() {
		return database.tb_answer;
	}

	// 답변 데이터 검색 
	public AnswerVO selectAnswer(HashMap<String, Object> param) {
		AnswerVO rAnswerVo = null;
		for (int i = 0; i < database.tb_answer.size(); i++) {
			AnswerVO answerVo = database.tb_answer.get(i);

			boolean flag = true;

			for (String key : param.keySet()) {
				Object value = param.get(key);

				if (key.equals("ANSWERNO")) {
					if (!(answerVo.getPk_answerNo() == (int) value)) {
						flag = false;
					}
				} else if (key.equals("ANSWERCONTENT")) {
					if (!(answerVo.getAnswerContent().equals(value))) {
						flag = false;
					}
				} else if (key.equals("ANSWERDATE")) {
					if (!(answerVo.getAnswerContent().equals(value))) {
						flag = false;
					}
				} else if (key.equals("QUESTIONNO")) {
					if (!(answerVo.getFk_questionNo() == (int) value)) {
						flag = false;
					}
				}
			}
			if (flag) {
				rAnswerVo = answerVo;
			}
		}
		return rAnswerVo;
	}

	// 답변 인덱스 검색
	public int answerIndexSearch(HashMap<String, Object> param) {
		int index = -1;
		for (int i = 0; i < database.tb_answer.size(); i++) {
			AnswerVO answerVo = database.tb_answer.get(i);

			boolean flag = true;

			for (String key : param.keySet()) {
				Object value = param.get(key);

				if (key.equals("ANSWERNO")) {
					if (!(answerVo.getPk_answerNo() == (int) value)) {
						flag = false;
					}
				} else if (key.equals("ANSWERCONTENT")) {
					if (!(answerVo.getAnswerContent().equals(value))) {
						flag = false;
					}
				} else if (key.equals("ANSWERDATE")) {
					if (!(answerVo.getAnswerContent().equals(value))) {
						flag = false;
					}
				} else if (key.equals("QUESTIONNO")) {
					if (!(answerVo.getFk_questionNo() == (int) value)) {
						flag = false;
					}
				}
				
			}if (flag)
				index = i;
		}
		return index;
	}

	
}