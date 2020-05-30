package dao;

import java.util.*;
import vo.MusicVO;
import data.Database;

public class MusicDao { // Music 테이블에 접근하기 위한 접근 객체
	// 여기서부터
	private static MusicDao instance;

	private MusicDao() {
	}

	public static MusicDao getInstance() {
		if (instance == null) {
			instance = new MusicDao();
		}
		return instance;
	}

	// 여기까지 싱글톤생성(하나의 객체를 생성하여 여러 클래스에서 사용하기 위한 목적으로 생성)

	Database db = Database.getInstance(); // Database 객체를 가져옴

	// 노래 삽입
	public void insertMusic(MusicVO music) {
		db.tb_music.add(music);
	}

	// 노래 업데이트
	public void updateMusic(MusicVO music, int index) {
		db.tb_music.set(index, music);
	}

	// 노래 삭제
	public void deleteMusic(int index) {
		db.tb_music.remove(index);
	}

	// 전체 음악 정보
	public ArrayList<MusicVO> selectAllMusic() {
		return db.tb_music;
	}

	// 데이터 검색
	// (MusicVO 데이터 :musicNo, singerName, musicTitle, songWriter, writer,
	// musicPlayTime, albumName, releaseDate, streamingCount)
	public MusicVO selectMusic(HashMap<String, Object> param) {
		MusicVO rMusic = null;
		for (int i = 0; i < db.tb_music.size(); i++) {
			MusicVO music = db.tb_music.get(i);

			boolean flag = true;

			for (String key : param.keySet()) {
				Object value = param.get(key);

				if (key.equals("MUSICNO")) {
					if (!(music.getPk_musicNo() == (int) value)) {
						flag = false;
					}
				} else if (key.equals("SINGERNAME")) {
					if (!(music.getSingerName().equals(value))) {
						flag = false;
					}
				} else if (key.equals("MUSICTITLE")) {
					if (!(music.getMusicTitle().equals(value))) {
						flag = false;
					}
				} else if (key.equals("MUSICTITLE")) {
					if (!(music.getMusicGenre().equals(value))) {
						flag = false;
					}
				} else if (key.equals("SONGWRITER")) {
					if (!(music.getMusicTitle().equals(value))) {
						flag = false;
					}
				} else if (key.equals("WRITER")) {
					if (!(music.getWriter().equals(value))) {
						flag = false;
					}
				} else if (key.equals("MUSICPLAYTIME")) {
					if (!music.getMusicPlayTime().equals(value)) {
						flag = false;
					}
				} else if (key.equals("ALBUMNAME")) {
					if (!(music.getAlbumName() == value)) {
						flag = false;
					}
				} else if (key.equals("RELEASEDATE")) {
					if (!(music.getReleaseDate() == value)) {
						flag = false;
					}
				} else if (key.equals("STREAMINGCOUNT")) {
					if (!(music.getStreamingCount() == (int) value)) {
						flag = false;
					}
				}
			}
			if (flag) {
				rMusic = music;
			}
		}
		return rMusic;
	}

	// 인덱스 검색 (musicList 데이터 : musicListNo, userNo, listName)
	public int musicIndexSearch(HashMap<String, Object> param) {
		int index = -1;
		for (int i = 0; i < db.tb_music.size(); i++) {
			MusicVO music = db.tb_music.get(i);

			boolean flag = true;

			for (String key : param.keySet()) {
				Object value = param.get(key);

				if (key.equals("MUSICNO")) {
					if (!(music.getPk_musicNo() == (int) value)) {
						flag = false;
					}
				} else if (key.equals("SINGERNAME")) {
					if (!(music.getSingerName().equals(value))) {
						flag = false;
					}
				} else if (key.equals("MUSICTITLE")) {
					if (!(music.getMusicTitle().equals(value))) {
						flag = false;
					}
				} else if (key.equals("SONGWRITER")) {
					if (!(music.getMusicTitle().equals(value))) {
						flag = false;
					}
				} else if (key.equals("WRITER")) {
					if (!(music.getWriter().equals(value))) {
						flag = false;
					}
				} else if (key.equals("MUSICPLAYTIME")) {
					if (!music.getMusicPlayTime().equals(value)) {
						flag = false;
					}
				} else if (key.equals("ALBUMNAME")) {
					if (!(music.getAlbumName().equals(value))) {
						flag = false;
					}
				} else if (key.equals("RELEASEDATE")) {
					if (!(music.getReleaseDate().equals(value))) {
						flag = false;
					}
				} else if (key.equals("STREAMINGCOUNT")) {
					if (!(music.getStreamingCount() == (int) value)) {
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

	// musicNo 검색
	public boolean musicNo(HashMap<String, Integer> param) {
		ArrayList<MusicVO> musicNo = db.tb_music;
		for (int i = 0; i < musicNo.size(); i++) {
			MusicVO my = musicNo.get(i);
			for (String key : param.keySet()) {
				int value = param.get(key);
				if (key.equals("MUSICNO")) {
					if (my.getPk_musicNo() == value) {
						return true;
					}
				}
			}
		}
		return false;
	}

	// 노래 검색(제목, 가수이름 포함한 리스트 반환)
	public ArrayList<MusicVO> searchMusic(String search) {
		ArrayList<MusicVO> temp = new ArrayList<>();
		for (int i = 0; i < db.tb_music.size(); i++) {
			MusicVO music = db.tb_music.get(i);
			if (music.getMusicTitle().contains(search)
					|| music.getSingerName().contains(search)) {
				temp.add(music);
			}
		}
		return temp;
	}
}