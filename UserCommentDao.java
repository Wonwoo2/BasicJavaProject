package dao;

import java.util.ArrayList;

import vo.UserMusicCommentVO;
import data.Database;

public class UserCommentDao {
	private static UserCommentDao instance;

	private UserCommentDao() { }

	public static UserCommentDao getInstance() {
		if (instance == null) {
			instance = new UserCommentDao();
		}
		return instance;
	}
	
	Database database = Database.getInstance();
	
	public ArrayList<UserMusicCommentVO> selectUserCommentList() {
		return database.tb_userMusicComment;
	}
}
