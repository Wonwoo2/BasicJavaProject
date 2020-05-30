package dao;

import java.util.ArrayList;
import java.util.HashMap;

import vo.NoticeVO;
import data.Database;

public class NoticeDao { // Notice 테이블에 접근하기 위한 접근 객체
	// 여기서부터
	private static NoticeDao instance;

	private NoticeDao() {
	}

	public static NoticeDao getInstance() {
		if (instance == null) {
			instance = new NoticeDao();
		}
		return instance;
	}

	// 여기까지 싱글톤생성(하나의 객체를 생성하여 여러 클래스에서 사용하기 위한 목적으로 생성)

	Database db = Database.getInstance(); // Database 객체를 가져옴

	// 데이터 삽입
	public void insertNotice(NoticeVO notice) {
		db.tb_notice.add(notice);
	}

	// 공지사항 수정
	public void updateNotice(NoticeVO notice, int index) {
		db.tb_notice.set(index, notice);
	}

	// 공지사항 삭제
	public void deleteNotice(int index) {
		db.tb_notice.remove(index);
	}

	// 전체 공지사항 리스트
	public ArrayList<NoticeVO> selectAllNoticeList() {
		return db.tb_notice;
	}

	// 데이터 검색
	// NoticeVO 데이터 : noticeNo, noticePerson, noticeTitle, noticeContent,
	// noticeDate
	public NoticeVO selectNoticeList(HashMap<String, Object> param) {
		NoticeVO rNotice = null;
		for (int i = 0; i < db.tb_notice.size(); i++) {
			NoticeVO notice = db.tb_notice.get(i);

			boolean flag = true;

			for (String key : param.keySet()) {
				Object value = param.get(key);

				if (key.equals("NOTICENO")) {
					if (!(notice.getPk_noticeNo() == (int) value)) {
						flag = false;
					}
				} else if (key.equals("NOTICETITLE")) {
					if (!(notice.getNoticeTitle().equals(value))) {
						flag = false;
					}
				} else if (key.equals("NOTICECONTENT")) {
					if (!(notice.getNoticeContent().equals(value))) {
						flag = false;
					}
				}
			} 
			if (flag) {
				rNotice = notice;
			}
		}
		return rNotice;
	}

	// 인덱스 검색
	public int noticeIndexSearch(HashMap<String, Object> param) {

		int index = -1;
		for (int i = 0; i < db.tb_notice.size(); i++) {
			NoticeVO notice = db.tb_notice.get(i);

			boolean flag = true;

			for (String key : param.keySet()) {
				Object value = param.get(key);

				if (key.equals("NOTICENO")) {
					if (!(notice.getPk_noticeNo() == (int) value)) {
						flag = false;
					}
				} else if (key.equals("NOTICETITLE")) {
					if (!(notice.getNoticeTitle().equals(value))) {
						flag = false;
					}
				} else if (key.equals("NOTICECONTENT")) {
					if (!(notice.getNoticeContent().equals(value))) {
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

	// 공지사항번호 검색
	public boolean noticeNo(HashMap<String, Integer> param) {
		ArrayList<NoticeVO> notice = db.tb_notice;
		for (int i = 0; i < notice.size(); i++) {
			NoticeVO n = notice.get(i);
			for (String key : param.keySet()) {
				int value = param.get(key);
				if (key.equals("NICENO")) {
					if (n.getPk_noticeNo() == value) {
						return true;
					}
				}
			}
		}
		return false;
	}
}