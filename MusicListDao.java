package dao;

import java.util.ArrayList;
import java.util.HashMap;

import vo.MusicListVO;
import data.Database;

public class MusicListDao {		// MusicList 테이블에 접근하기 위한 접근 객체
	// 여기서부터
	private static MusicListDao instance;

	private MusicListDao() { }

	public static MusicListDao getInstance() {
		if (instance == null) {
			instance = new MusicListDao();
		}
		return instance;
	}
	//여기까지 싱글톤생성(하나의 객체를 생성하여 여러 클래스에서 사용하기 위한 목적으로 생성)
	
	Database db = Database.getInstance(); // Database 객체를 가져옴
	
	// 데이터 삽입
	public boolean insertMusicList(MusicListVO musicList) {
		db.tb_musicList.add(musicList);
		return true;
	}
	
	// 전체 리스트
	public ArrayList<MusicListVO> selectAllMusicList() {
		return db.tb_musicList;
	}
	
	// 업데이트
	public boolean updateMusicList(MusicListVO musicList, int index) {
		db.tb_musicList.set(index, musicList);
		return true;
	}
	
	// 삭제
	public boolean deleteMusicList(int index) {
		db.tb_musicList.remove(index);
		return true;
	}
	
	// 데이터 검색 (musicList 데이터 : musicListNo, userNo, listName)
	public MusicListVO selectMusicList(HashMap<String, Object> param) {
		MusicListVO rMusicList = null;
		for (int i = 0; i < db.tb_musicList.size(); i++) {
			MusicListVO musicList = db.tb_musicList.get(i);

			boolean flag = true;

			for (String key : param.keySet()) {
				Object value = param.get(key);

				if (key.equals("USERID")) {
					if (!musicList.getFk_userID().equals(value)) {
						flag = false;
					}
				} else if (key.equals("MUSICNO")) {
					if (!(musicList.getFk_musicNo() == (int) value)) {
						flag = false;
					}
				} 
			}
			if (flag) {
				rMusicList = musicList;
			}
		}
		return rMusicList;
	}

	// 인덱스 검색 (musicList 데이터 : musicListNo, userNo, listName)
	public int musicListIndexSearch(HashMap<String, Object> param) {
		int index = -1;
		for (int i = 0; i < db.tb_musicList.size(); i++) {
			MusicListVO musicList = db.tb_musicList.get(i);

			boolean flag = true;

			for (String key : param.keySet()) {
				Object value = param.get(key);

				if (key.equals("USERID")) {
					if (!(musicList.getFk_userID().equals(value))) {
						flag = false;
					}
				} else if (key.equals("MUSICNO")) {
					if (!(musicList.getFk_musicNo() == (int) value)) {
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


}