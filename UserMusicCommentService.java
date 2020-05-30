package service;

import java.util.*;

import vo.UserMusicCommentVO;
import dao.UserMusicCommentDao;

public class UserMusicCommentService {
	// 싱글톤화
	private static UserMusicCommentService instance;

	private UserMusicCommentService() {
	}

	public static UserMusicCommentService getInstance() {
		if (instance == null) {
			instance = new UserMusicCommentService();
		}
		return instance;
	}
	UserMusicCommentDao userMusicCommentDao = UserMusicCommentDao.getInstance();
	
	// 하나의 고객 음알 댓글 반환
	public UserMusicCommentVO selectUserMusicComment(HashMap<String, Object> param) {
		UserMusicCommentVO userMusicCommentVo = userMusicCommentDao.selectUserMusicComment(param);
		return userMusicCommentVo; 
	}
	
	// 전체 고객 음악 댓글 반환
	public ArrayList<UserMusicCommentVO> selectAllUserMusicComment() {
		ArrayList<UserMusicCommentVO> userMusicCommentList = userMusicCommentDao.selectAllUserCommentList();
		return userMusicCommentList;
	}

	// 음악 댓글 추가
	public void insertComment(int commentNo, String comment, Date day, int star,
			String pk_userID, int musicNo) {
		UserMusicCommentVO userComment = new UserMusicCommentVO();
		userComment.setPk_userCommentNo(commentNo);
		userComment.setCommentContent(comment);
		userComment.setCommentDate(day);
		userComment.setStar(star);
		userComment.setFk_userID(pk_userID);
		userComment.setFk_musicNo(musicNo);
		userMusicCommentDao.insertUserMusicComment(userComment);
	}

}
