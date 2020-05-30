package service;

import java.util.*;

import vo.MusicListVO;
import dao.MusicListDao;
import data.Session;

public class MusicListService {
	private static MusicListService instance;

	private MusicListService() {
	}

	public static MusicListService getInstance() {
		if (instance == null) {
			instance = new MusicListService();
		}
		return instance;

	}

	MusicListDao musicListDao = MusicListDao.getInstance();

	// 전체 뮤직리스트 반환
	public ArrayList<MusicListVO> selectAllMusicList() {
		ArrayList<MusicListVO> musicList = musicListDao.selectAllMusicList();
		return musicList;
	}

	// 하나의 뮤직리스트 반환
	public MusicListVO selectMusicList(String userID) {
		HashMap<String, Object> param = new HashMap<>();
		param.put("USERID", userID);
		MusicListVO musicListVo = musicListDao.selectMusicList(param);
		return musicListVo;
	}

	// 뮤직리스트에 음악 추가
	public void insertMusicList(int musicNo, String userID) {
		MusicListVO musicListVo = new MusicListVO();
		musicListVo.setFk_userID(userID);
		musicListVo.setFk_musicNo(musicNo);

		musicListDao.insertMusicList(musicListVo);
	}

	// 뮤직리스트에서 음악 삭제
	public void deleteMusicList(int deleteIndex) {
		musicListDao.deleteMusicList(deleteIndex);
	}

	// 고객이 갖고 있는 재생목록 반환
	public ArrayList<MusicListVO> selectMyMusicList(String pk_userID) {

		ArrayList<MusicListVO> musicList = musicListDao.selectAllMusicList();
		ArrayList<MusicListVO> myMusic = new ArrayList<>();

		for (int i = 0; i < musicList.size(); i++) {
			MusicListVO musicListVo = musicList.get(i);
			if (pk_userID.equals(musicListVo.getFk_userID())) {
				myMusic.add(musicListVo);
			}
		}
		return myMusic;
	}

	// 뮤직리스트에서 음악의 인덱스를 찾아 반환
	public int musicListIndexSearch(int musicNumber) {
		ArrayList<MusicListVO> musicList = musicListDao.selectAllMusicList();
		int deleteIndex = 0;
		for (int i = 0; i < musicList.size(); i++) {
			MusicListVO musicListVo = musicList.get(i);
			if (Session.LoginUser.getPk_userID().equals(
					musicListVo.getFk_userID())
					&& musicNumber == musicListVo.getFk_musicNo()) {
				deleteIndex = i;
				break;
			}
		}
		return deleteIndex;
	}

}