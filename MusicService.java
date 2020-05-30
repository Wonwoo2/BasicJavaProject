package service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import vo.MusicListVO;
import vo.MusicVO;
import vo.UserMusicCommentVO;
import dao.MusicDao;
import data.Session;

public class MusicService {
	// 싱글톤화
	private static MusicService instance;

	private MusicService() {
	}

	public static MusicService getInstance() {
		if (instance == null) {
			instance = new MusicService();
		}
		return instance;
	}

	MusicDao musicDao = MusicDao.getInstance(); // musicDao 싱글톤 객체 생성
	MusicListService musicListService = MusicListService.getInstance();

	// 음악 추가
	public void insertMusic(int musicNo, String title, String name,
			String genre, String songWriter, String writer, String time,
			String albumName, String releaseDate, int streaming) {
		MusicVO music = new MusicVO();

		music.setPk_musicNo(musicNo);
		music.setMusicTitle(title);
		music.setSingerName(name);
		music.setgetMusicGenre(genre);
		music.setSongWriter(songWriter);
		music.setWriter(writer);
		music.setMusicPlayTime(time);
		music.setAlbumName(albumName);
		music.setReleaseDate(releaseDate);
		music.setStreamingCount(streaming);

		musicDao.insertMusic(music);

	}

	// 음악 수정
	public void updateMusic(MusicVO musicVo, int updateIndex) {
		musicDao.updateMusic(musicVo, updateIndex);
	}

	// 음악 삭제
	public void deleteMusic(int deleteIndex) {
		musicDao.deleteMusic(deleteIndex);
	}

	// 전체 음악리스트 리턴
	public ArrayList<MusicVO> selectMusicList() {
		ArrayList<MusicVO> musicList = musicDao.selectAllMusic();
		return musicList;
	}

	// 하나의 음악 정보 리턴
	public MusicVO selectMusic(int musicNo) {
		HashMap<String, Object> param = new HashMap<>();

		param.put("MUSICNO", musicNo);
		MusicVO music = musicDao.selectMusic(param);

		return music;
	}

	// 음악 인덱스 리턴
	public int indexMusic(int musicNo) {
		HashMap<String, Object> param = new HashMap<>();

		param.put("MUSICNO", musicNo);
		int deleteIndex = musicDao.musicIndexSearch(param);

		return deleteIndex;
	}

	// 음악리스트를 오름차순 정렬하여 반환
	public ArrayList<MusicVO> sortList() {
		ArrayList<MusicVO> musicList = selectMusicList();

		MusicComparator descending = new MusicComparator();
		Collections.sort(musicList, descending);

		return musicList;
	}

	// 별점계산
	public String starAvg(ArrayList<UserMusicCommentVO> musicComment,
			int musicNo) {
		double starSum = 0;
		double count = 0;
		for (int i = 0; i < musicComment.size(); i++) {
			UserMusicCommentVO userMusicCommentVo = musicComment.get(i);
			if (userMusicCommentVo.getFk_musicNo() == musicNo) {
				starSum += userMusicCommentVo.getStar();
				count++;
			}
		}
		double avg = starSum / count;
		if (avg == 5) {
			return "★ ★ ★ ★ ★";
		} else if (avg >= 4.5) {
			return "★ ★ ★ ★ ☆";
		} else if (avg >= 4) {
			return "★ ★ ★ ★ ";
		} else if (avg >= 3.5) {
			return "★ ★ ★ ☆";
		} else if (avg >= 3) {
			return "★ ★ ★ ";
		} else if (avg >= 2.5) {
			return "★ ★ ☆";
		} else if (avg >= 2) {
			return "★ ★ ";
		} else if (avg >= 1.5) {
			return "★ ☆";
		} else if (avg >= 1) {
			return "★";
		} else if (avg >= 0.5) {
			return "☆";
		}
		return " ";
	}
}

// ArrayList<객체타입> 오름차순 정렬
class MusicComparator implements Comparator<MusicVO> {

	@Override
	public int compare(MusicVO a, MusicVO b) {
		if (a.getStreamingCount() > b.getStreamingCount()) {
			return -1;
		} else if (a.getStreamingCount() < b.getStreamingCount()) {
			return 1;
		} else {
			return 0;
		}
	}
}
