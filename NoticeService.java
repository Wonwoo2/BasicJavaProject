package service;

import java.util.*;

import vo.NoticeVO;
import dao.NoticeDao;

public class NoticeService {
	// 싱글톤화
	private static NoticeService instance;

	private NoticeService() {
	}

	public static NoticeService getInstance() {
		if (instance == null) {
			instance = new NoticeService();
		}
		return instance;
	}

	NoticeDao noticeDao = NoticeDao.getInstance(); // 공지사항 Dao 객체 생성

	// 하나의 공지사항 정보 리턴
	public NoticeVO noticeSelect(HashMap<String, Object> param) {
		NoticeVO noticeVo = noticeDao.selectNoticeList(param);
		return noticeVo;
	}
	
	// 전체 공지사항 리스트 리턴
	public ArrayList<NoticeVO> noticeSelectList() {
		ArrayList<NoticeVO> noticeList = noticeDao.selectAllNoticeList();
		return noticeList;
	}

	// 공지사항 추가
	public void noticeInsert(int noticeNo, String noticeTitle,
			String noticeContent, Date noticeDate) {
		NoticeVO noticeVo = new NoticeVO();
		noticeVo.setPk_noticeNo(noticeNo);
		noticeVo.setNoticeTitle(noticeTitle);
		noticeVo.setNoticeContent(noticeContent);
		noticeVo.setNoticeDate(noticeDate);

		noticeDao.insertNotice(noticeVo);
	}

	// dao로부터 삭제할 공지사항의 인덱스를 가져옴
	public int indexNotice(HashMap<String, Object> param) {
		int noticeIndex = noticeDao.noticeIndexSearch(param);
		return noticeIndex;
	}

	// 삭제할 공지사항의 인덱스를 Dao로 넘겨줌
	public void noticeDelete(int noticeIndex) {
		noticeDao.deleteNotice(noticeIndex);
	}

	public void noticeUpdate(NoticeVO noticeVo, int noticeIndex) {
		noticeDao.updateNotice(noticeVo, noticeIndex);
		
	}

}
