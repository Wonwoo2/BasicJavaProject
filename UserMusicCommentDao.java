package dao;

import java.util.ArrayList;
import java.util.HashMap;

import vo.*;
import data.Database;

public class UserMusicCommentDao { // UserComment 테이블에 접근하기 위한 접근 객체
	// 여기서부터
	private static UserMusicCommentDao instance;

	private UserMusicCommentDao() {
	}

	public static UserMusicCommentDao getInstance() {
		if (instance == null) {
			instance = new UserMusicCommentDao();
		}
		return instance;
	}

	// 여기까지 싱글톤생성(하나의 객체를 생성하여 여러 클래스에서 사용하기 위한 목적으로 생성)

	Database db = Database.getInstance(); // Database 객체를 가져옴

	// 데이터 삽입
	public void insertUserMusicComment(UserMusicCommentVO userComment) {
		db.tb_userMusicComment.add(userComment);
	}

	// 유저 댓글 업데이트
	public void updateUserMusicComment(UserMusicCommentVO userMusicComment,
			int index) {
		db.tb_userMusicComment.set(index, userMusicComment);
	}

	// 유저 댓글 삭제
	public void deletUserMusicComment(int index) {
		db.tb_userMusicComment.remove(index);
	}

	// 전체 고객 댓글 리스트
	public ArrayList<UserMusicCommentVO> selectAllUserCommentList() {
		return db.tb_userMusicComment;
	}

	// 데이터 검색
	public UserMusicCommentVO selectUserMusicComment(HashMap<String, Object> param) {
		UserMusicCommentVO rUserMusicComment = null;
		for (int i = 0; i < db.tb_userMusicComment.size(); i++) {
			UserMusicCommentVO UserComment = db.tb_userMusicComment.get(i);

			boolean flag = true;

			for (String key : param.keySet()) {
				Object value = param.get(key);

				if (key.equals("USERCOMMENTNO")) {
					if (!(UserComment.getPk_userCommentNo() == (int) value)) {
						flag = false;
					}
				} else if (key.equals("COMMENTCONTENT")) {
					if (!(UserComment.getCommentContent() == value)) {
						flag = false;
					}
				} else if (key.equals("COMMENTDATE")) {
					if (!(UserComment.getCommentDate() == value)) {
						flag = false;
					}
				} else if (key.equals("STAR")) {
					if (!(UserComment.getStar() == (double) value)) {
						flag = false;
					}
				} else if (key.equals("USERID")) {
					if (!(UserComment.getFk_userID() == value)) {
						flag = false;
					}
				} else if (key.equals("MUSICNO")) {
					if (!(UserComment.getFk_musicNo() == (int) value)) {
						flag = false;
					}
				}
			}
			if (flag) {
				rUserMusicComment = UserComment;
			}
		}
		return rUserMusicComment;
	}

	// 인덱스 검색
	public int userCommentIndexSearch(HashMap<String, Object> param) {
		int index = -1;
		for (int i = 0; i < db.tb_userMusicComment.size(); i++) {
			UserMusicCommentVO UserComment = db.tb_userMusicComment.get(i);

			boolean flag = true;

			for (String key : param.keySet()) {
				Object value = param.get(key);

				if (key.equals("USERCOMMENTNO")) {
					if (!(UserComment.getPk_userCommentNo() == (int) value)) {
						flag = false;
					}
				} else if (key.equals("COMMENTCONTENT")) {
					if (!(UserComment.getCommentContent() == value)) {
						flag = false;
					}
				} else if (key.equals("COMMENTDATE")) {
					if (!(UserComment.getCommentDate() == value)) {
						flag = false;
					}
				} else if (key.equals("STAR")) {
					if (!(UserComment.getStar() == (double) value)) {
						flag = false;
					}
				} else if (key.equals("USERID")) {
					if (!(UserComment.getFk_userID() == value)) {
						flag = false;
					}
				} else if (key.equals("MUSICNO")) {
					if (!(UserComment.getFk_musicNo() == (int) value)) {
						flag = false;
					}
				}
				
				}
			if (flag) {
				index = i;
			}
		}
		return index;
	}

	// 유저 댓글 고유 번호 검색
	public boolean userMusicCommentNo(HashMap<String, String> param) {
		ArrayList<UserMusicCommentVO> userMusicComment = db.tb_userMusicComment;
		for (int i = 0; i < userMusicComment.size(); i++) {
			UserMusicCommentVO musicComment = userMusicComment.get(i);
			for (String key : param.keySet()) {
				String value = param.get(key);
				if (key.equals("USERNO")) {
					if (musicComment.getFk_userID().equals(value)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	// 유저 댓글 노래 고유번호 검색
	public boolean userCommentMusicNo(HashMap<String, Integer> param) {
		ArrayList<UserMusicCommentVO> userComment = db.tb_userMusicComment;
		for (int i = 0; i < userComment.size(); i++) {
			UserMusicCommentVO comment = userComment.get(i);
			for (String key : param.keySet()) {
				int value = param.get(key);
				if (key.equals("MUSICNO")) {
					if (comment.getFk_musicNo() == value) {
						return true;
					}
				}
			}
		}
		return false;
	}
}