package vo;

//음악 테이블
//음악 고유번호(pk), 음악장르(String), 가수이름(String), 음악 제목(String) , 작곡가(String), 작사가(String), 
//재생시간(String), 앨범명(String), 발매일자(String), 노래재생횟수(int)

// Music 테이블에 저장할 데이터들의 변수들을 정의
public class MusicVO {
	private int pk_musicNo;
	private String musicGenre; //음악장르
	private String singerName;
	private String musicTitle;
	private String songWriter;
	private String writer;
	private String musicPlayTime;
	private String albumName;
	private String releaseDate;
	private int streamingCount;

	public int getPk_musicNo() {
		return pk_musicNo;
	}

	public void setPk_musicNo(int pk_musicNo) {
		this.pk_musicNo = pk_musicNo;
	}
	
	public String getMusicGenre() {
		return musicGenre;
	}
	
	public void setgetMusicGenre(String musicGenre) {
		this.musicGenre = musicGenre;
	}

	public String getSingerName() {
		return singerName;
	}

	public void setSingerName(String singerName) {
		this.singerName = singerName;
	}

	public String getMusicTitle() {
		return musicTitle;
	}

	public void setMusicTitle(String musicTitle) {
		this.musicTitle = musicTitle;
	}

	public String getSongWriter() {
		return songWriter;
	}

	public void setSongWriter(String songWriter) {
		this.songWriter = songWriter;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	

	public String getMusicPlayTime() {
		return musicPlayTime;
	}

	public void setMusicPlayTime(String musicPlayTime) {
		this.musicPlayTime = musicPlayTime;
	}

	public void setMusicGenre(String musicGenre) {
		this.musicGenre = musicGenre;
	}

	public String getAlbumName() {
		return albumName;
	}

	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public int getStreamingCount() {
		return streamingCount;
	}

	public void setStreamingCount(int streamingCount) {
		this.streamingCount = streamingCount;
	}
}