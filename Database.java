package data;

import java.util.ArrayList;
import java.util.Date;

import vo.*;

public class Database {

	private static Database instance;

	private Database() {
	}

	public static Database getInstance() {
		if (instance == null) {
			instance = new Database();
		}
		return instance;
	}

	public ArrayList<UserVO> tb_user = new ArrayList<>(); // 5명
	public ArrayList<MusicVO> tb_music = new ArrayList<>(); // 5곡
	public ArrayList<MusicListVO> tb_musicList = new ArrayList<>(); // 5개
	public ArrayList<PromotionVO> tb_promotion = new ArrayList<>(); // 3개
	public ArrayList<UserPromotionVO> tb_userPromotion = new ArrayList<>(); // 회원당
																			// 1개씩
	public ArrayList<UserMusicCommentVO> tb_userMusicComment = new ArrayList<>(); // 한곡당
																					// 하나씩
	public ArrayList<NoticeVO> tb_notice = new ArrayList<>(); // 3개
	public ArrayList<QuestionVO> tb_question = new ArrayList<>(); // 3개
	public ArrayList<AnswerVO> tb_answer = new ArrayList<>(); // 회원당 1개

	// 유져 ,관리자
	{
		UserVO admin = new UserVO();
		admin.setPk_userID("admin");
		admin.setUserPW("admin");
		admin.setUserName("관리자");
		admin.setUserPhone("112");
		admin.setFk_userPromotionNo(0);
		tb_user.add(admin);

		UserVO user1 = new UserVO();
		user1.setPk_userID("id1");
		user1.setUserPW("id1");
		user1.setUserName("원종찬");
		user1.setUserPhone("5214");
		user1.setFk_userPromotionNo(0);
		tb_user.add(user1);

		UserVO user2 = new UserVO();
		user2.setPk_userID("id2");
		user2.setUserPW("id2");
		user2.setUserName("이원우");
		user2.setUserPhone("2359");
		user2.setFk_userPromotionNo(2);
		tb_user.add(user2);

		UserVO user3 = new UserVO();
		user3.setPk_userID("id3");
		user3.setUserPW("id3");
		user3.setUserName("유효상");
		user3.setUserPhone("7476");
		user3.setFk_userPromotionNo(3);
		tb_user.add(user3);

		UserVO user4 = new UserVO();
		user4.setPk_userID("id4");
		user4.setUserPW("id4");
		user4.setUserName("채홍규");
		user4.setUserPhone("5255");
		user4.setFk_userPromotionNo(1);
		tb_user.add(user4);

		UserVO id5 = new UserVO();
		id5.setPk_userID("id5");
		id5.setUserPW("id5");
		id5.setUserName("이지윤");
		id5.setUserPhone("0198");
		tb_user.add(id5);
	}

	// 뮤직
	{
			MusicVO music1 = new MusicVO();
			music1.setPk_musicNo(1);
			music1.setMusicTitle("에잇(Prod.&Feat. SUGA of BTS)");
			music1.setSingerName("아이유");
			music1.setAlbumName("에잇");
			music1.setReleaseDate("2020.05.06");
			music1.setgetMusicGenre("록/메탈");
			music1.setSongWriter("SUGA, 아이유, EL CAPITXN");
			music1.setWriter("SUGA, 아이유");
			music1.setMusicPlayTime("2:48");
			music1.setStreamingCount(5);
			tb_music.add(music1);
			
			MusicVO music2 = new MusicVO();
			music2.setPk_musicNo(2);
			music2.setMusicTitle("나비와 고양이");
			music2.setSingerName("볼빨간사춘기");
			music2.setAlbumName("사춘기집2 꽃 본 나비");
			music2.setReleaseDate("2020.05.07");
			music2.setgetMusicGenre("인디음악, 포크/블루스");
			music2.setSongWriter("안지영, 바닐라맨");
			music2.setWriter("안지영");
			music2.setMusicPlayTime("3:09");
			music2.setStreamingCount(2);
			tb_music.add(music2);
			
			MusicVO music3 = new MusicVO();
			music3.setPk_musicNo(3);
			music3.setMusicTitle("사랑하게 될 줄 알았어");
			music3.setSingerName("전미도");
			music3.setSongWriter("이상규");
			music3.setAlbumName("슬기로운 의사생활 OST Part 11");
			music3.setReleaseDate("2020.05.22");
			music3.setgetMusicGenre("발라드, 국내드라마");
			music3.setWriter("이상규");
			music3.setMusicPlayTime("4:22");
			music3.setStreamingCount(7);
			tb_music.add(music3);
			
			MusicVO music4 = new MusicVO();
			music4.setPk_musicNo(4);
			music4.setMusicTitle("아로하");
			music4.setSingerName("조정석");
			music4.setAlbumName("슬기로운 의사생활 OST Part 3");
			music4.setReleaseDate("2020.03.27");
			music4.setgetMusicGenre("발라드, 국내드라마");
			music4.setSongWriter("위종수");
			music4.setWriter("김태훈");
			music4.setMusicPlayTime("4:06");
			music4.setStreamingCount(4);
			tb_music.add(music4);
			
			MusicVO music5 = new MusicVO();
			music5.setPk_musicNo(5);
			music5.setMusicTitle("Candy");
			music5.setSingerName("백현");
			music5.setAlbumName("Delight - The 2nd Mini Album");
			music5.setReleaseDate("2020.05.25");
			music5.setgetMusicGenre("R&B/Soul");
			music5.setSongWriter("Mike Daley, Mitchell Owens, DEEZ, Adran Mckinnon");
			music5.setWriter("kenzie");
			music5.setMusicPlayTime("3:48");
			music5.setStreamingCount(8);
			tb_music.add(music5);
			
			MusicVO music6 = new MusicVO();
			music6.setPk_musicNo(6);
			music6.setMusicTitle("살짝 설렜어");
			music6.setSingerName("오마이걸");
			music6.setAlbumName("NONSTOP");
			music6.setReleaseDate("2020.04.27");
			music6.setgetMusicGenre("댄스");
			music6.setSongWriter("Steven Lee, Andreas Johansson, Laurell, Sebastian Thott");
			music6.setWriter("서지음, 미미");
			music6.setMusicPlayTime("3:23");
			music6.setStreamingCount(11);
			tb_music.add(music6);
			
			MusicVO music7 = new MusicVO();
			music7.setPk_musicNo(7);
			music7.setMusicTitle("ON");
			music7. setSingerName("방탄소년단");
			music7.setAlbumName("MAP OF THE SOUL : 7");
			music7.setReleaseDate("2020.02.21");
			music7.setgetMusicGenre("랩/힙합");
			music7.setSongWriter("Pdogg, RM, August Rigo, Melanie Joy Fontana, Michel Lindgren Schulz, SUGA, j-hope, Antonina Armato, Krysta Youngs, Julia Ross");
			music7.setWriter("Pdogg, RM, August Rigo, Melanie Joy Fontana, Michel Lindgren Schulz, SUGA, j-hope, Antonina Armato, Krysta Youngs, Julia Ross");
			music7.setMusicPlayTime("4:07");
			music7.setStreamingCount(21);
			tb_music.add(music7);
			
			MusicVO music8 = new MusicVO();
			music8.setPk_musicNo(8);
			music8.setMusicTitle("좋은 사람 있으면 소개시켜줘");
			music8.setSingerName("조이");
			music8.setAlbumName("슬기로운 의사생활 OST Part 2");
			music8.setReleaseDate("2020.03.20");
			music8.setgetMusicGenre("발라드, 국내드라마");
			music8.setSongWriter("정재형");
			music8.setWriter("김희탐");
			music8.setMusicPlayTime("3:04");
			music8.setStreamingCount(30);
			tb_music.add(music8);
			
			MusicVO music9 = new MusicVO();
			music9.setPk_musicNo(9);
			music9.setMusicTitle("시작");
			music9.setSingerName("가호");
			music9.setAlbumName("이태원 클라쓰 OST Part 2");
			music9.setReleaseDate("2020.02.01");
			music9.setgetMusicGenre("록/메탈, 국내드라마");
			music9.setSongWriter("박성일");
			music9.setWriter("서동성");
			music9.setMusicPlayTime("3:23");
			music9.setStreamingCount(1);
			tb_music.add(music9);
			
			MusicVO music10 = new MusicVO();
			music10.setPk_musicNo(10);
			music10.setMusicTitle("처음처럼");
			music10.setSingerName("엠씨더맥스");
			music10.setAlbumName("CEREMONIA");
			music10.setReleaseDate("2020.03.25");
			music10.setgetMusicGenre("발라드");
			music10.setSongWriter("김창락, 김수빈, 조세희");
			music10.setWriter("이수");
			music10.setMusicPlayTime("3:53");
			music10.setStreamingCount(6);
			tb_music.add(music10);
			
			MusicVO music11 = new MusicVO();
			music11.setPk_musicNo(11);
			music11.setMusicTitle("덤더럼");
			music11.setSingerName("Apink");
			music11.setAlbumName("LOOK");
			music11.setReleaseDate("2020.04.13");
			music11.setgetMusicGenre("댄스");
			music11.setSongWriter("블랙아이드 필승, 전군");
			music11.setWriter("블랙아이드 필승, 전군");
			music11.setMusicPlayTime("3:29");
			music11.setStreamingCount(3);
			tb_music.add(music11);
			
			MusicVO music12 = new MusicVO();
			music12.setPk_musicNo(12);
			music12.setMusicTitle("작은 것들을 위한 시");
			music12.setSingerName("방탄소년단");
			music12.setAlbumName("MAP OF THE SOUL : PERSONA");
			music12.setReleaseDate("2019.04.12");
			music12.setgetMusicGenre("댄스");
			music12.setSongWriter("Pdogg, RM, Melanie Fontana, Michel Lindgren Schulz, 방시혁, SUGA, Emil Weisband, j-hope, Halsey");
			music12.setWriter("Pdogg, RM, Melanie Fontana, Michel Lindgren Schulz, 방시혁, SUGA, Emil Weisband, j-hope, Halsey");
			music12.setMusicPlayTime("3:50");
			music12.setStreamingCount(9);
			tb_music.add(music12);
			
			MusicVO music13 = new MusicVO();
			music13.setPk_musicNo(13);
			music13.setMusicTitle("너를 사랑하고 있어");
			music13.setSingerName("백현");
			music13.setAlbumName("낭만닥터 김사부2 OST Part 1");
			music13.setReleaseDate("2020.01.07");
			music13.setgetMusicGenre("발라드, 국내드라마");
			music13.setSongWriter("최인환, 이승주");
			music13.setWriter("지훈, 전창엽");
			music13.setMusicPlayTime("3:15");
			music13.setStreamingCount(10);
			tb_music.add(music13);
			
			MusicVO music14 = new MusicVO();
			music14.setPk_musicNo(14);
			music14.setMusicTitle("Bungee");
			music14.setSingerName("백현");
			music14.setAlbumName("Delight - The 2nd Mini Album");
			music14.setReleaseDate("2020.05.25");
			music14.setgetMusicGenre("R&B/Soul");
			music14.setSongWriter("이아일, Royal Dive, Brian Cho, jane");
			music14.setWriter("jane");
			music14.setMusicPlayTime("3:27");
			music14.setStreamingCount(17);
			tb_music.add(music14);
			
			MusicVO music15 = new MusicVO();
			music15.setPk_musicNo(15);
			music15.setMusicTitle("화려하지 않은 고백");
			music15.setSingerName("규현");
			music15.setAlbumName("슬기로운 의사생활 OST Part 4");
			music15.setReleaseDate("2020.04.03");
			music15.setgetMusicGenre("발라드, 국내드라마");
			music15.setSongWriter("오태호");
			music15.setWriter("오태호");
			music15.setMusicPlayTime("3:49");
			music15.setStreamingCount(13);
			tb_music.add(music15);
			
			MusicVO music16 = new MusicVO();
			music16.setPk_musicNo(16);
			music16.setMusicTitle("흔들리는 꽃들 속에서 네 샴푸향이 느껴진거야");
			music16.setSingerName("장범준");
			music16.setAlbumName("멜로가 체질 OST Part3");
			music16.setReleaseDate("2019.08.23");
			music16.setgetMusicGenre("록/메탈, 국내드라마");
			music16.setSongWriter("장범준");
			music16.setWriter("장범준");
			music16.setMusicPlayTime("2:49");
			music16.setStreamingCount(14);
			tb_music.add(music16);
			
			MusicVO music17 = new MusicVO();
			music17.setPk_musicNo(17);
			music17.setMusicTitle("Love Again");
			music17.setSingerName("백현");
			music17.setAlbumName("Delight - The 2nd Mini Album");
			music17.setReleaseDate("2020.05.25");
			music17.setgetMusicGenre("R&B/Soul");
			music17.setSongWriter("Colde, Stally");
			music17.setWriter("Colde");
			music17.setMusicPlayTime("3:26");
			music17.setStreamingCount(8);
			tb_music.add(music17);
			
			MusicVO music18 = new MusicVO();
			music18.setPk_musicNo(18);
			music18.setMusicTitle("Dolphin");
			music18.setSingerName("오마이걸");
			music18.setAlbumName("NONSTOP");
			music18.setReleaseDate("2020.04.27");
			music18.setgetMusicGenre("댄스");
			music18.setSongWriter("");
			music18.setWriter("");
			music18.setMusicPlayTime("2:57");
			music18.setStreamingCount(22);
			tb_music.add(music18);
			
			MusicVO music19 = new MusicVO();
			music19.setPk_musicNo(19);
			music19.setMusicTitle("Blueming");
			music19.setSingerName("아이유");
			music19.setAlbumName("Love poem");
			music19.setReleaseDate("2019.11.18");
			music19.setgetMusicGenre("록/메탈");
			music19.setSongWriter("이종훈, 이채규, 아이유");
			music19.setWriter("아이유");
			music19.setMusicPlayTime("3:38");
			music19.setStreamingCount(17);
			tb_music.add(music19);
			
			MusicVO music20 = new MusicVO();
			music20.setPk_musicNo(20);
			music20.setMusicTitle("아무노래");
			music20.setSingerName("지코");
			music20.setAlbumName("아무노래");
			music20.setReleaseDate("2020.01.13");
			music20.setgetMusicGenre("랩/힙합");
			music20.setSongWriter("지코, Poptime");
			music20.setWriter("지코, Poptime");
			music20.setMusicPlayTime("3:48");
			music20.setStreamingCount(15);
			tb_music.add(music20);
			
			MusicVO music21 = new MusicVO();
			music21.setPk_musicNo(21);
			music21.setMusicTitle("그대 고운 내사랑");
			music21.setSingerName("어반자카파");
			music21.setAlbumName("슬기로운 의사생활  OST Part 5");
			music21.setReleaseDate("2020.04.10");
			music21.setgetMusicGenre("발라드, 국내드라마");
			music21.setSongWriter("윤민석");
			music21.setWriter("윤민석");
			music21.setMusicPlayTime("3:52");
			music21.setStreamingCount(12);
			tb_music.add(music21);
			
			MusicVO music22 = new MusicVO();
			music22.setPk_musicNo(22);
			music22.setMusicTitle("METEOR");
			music22.setSingerName("창모");
			music22.setAlbumName("Boyhood");
			music22.setReleaseDate("2019.11.29");
			music22.setgetMusicGenre("랩/힙합");
			music22.setSongWriter("창모");
			music22.setWriter("창모");
			music22.setMusicPlayTime("3:18");
			music22.setStreamingCount(16);
			tb_music.add(music22);
			
			MusicVO music23 = new MusicVO();
			music23.setPk_musicNo(23);
			music23.setMusicTitle("Underwater");
			music23.setSingerName("백현");
			music23.setAlbumName("Delight - The 2nd Mini Album");
			music23.setReleaseDate("2020.05.25");
			music23.setgetMusicGenre("R&B/Soul");
			music23.setSongWriter("앤드류 최, 김연서, 밍지션");
			music23.setWriter("이이진, 조미양");
			music23.setMusicPlayTime("3:20");
			music1.setStreamingCount(18);
			tb_music.add(music23);
			
			MusicVO music24 = new MusicVO();
			music24.setPk_musicNo(24);
			music24.setMusicTitle("Poppin`");
			music24.setSingerName("백현");
			music24.setAlbumName("Delight - The 2nd Mini Album");
			music24.setReleaseDate("2020.05.25");
			music24.setgetMusicGenre("R&B/Soul");
			music24.setSongWriter("Jonathan Yip, Ray Romulus, Jeremy Reevesm Ray Charles McMullough 2, August rigo");
			music24.setWriter("kenzie");
			music24.setMusicPlayTime("3:29");
			music1.setStreamingCount(19);
			tb_music.add(music24);
			
			MusicVO music25 = new MusicVO();
			music25.setPk_musicNo(25);
			music25.setMusicTitle("너에게 가는 이 길 위에서");
			music25.setSingerName("백현");
			music25.setAlbumName("하이에나 OST Part 2");
			music25.setReleaseDate("2020.02.29");
			music25.setgetMusicGenre("발라드, 국내드라마");
			music25.setSongWriter("홍곰, 주진희");
			music25.setWriter("캥거루, 코뿔소");
			music25.setMusicPlayTime("4:00");
			music25.setStreamingCount(20);
			tb_music.add(music25);
			
			MusicVO music26 = new MusicVO();
			music26.setPk_musicNo(26);
			music26.setMusicTitle("00:00");
			music26.setSingerName("방탄소년단");
			music26.setAlbumName("MAP OF THE SOUL : 7");
			music26.setReleaseDate("2020.02.21");
			music26.setgetMusicGenre("랩/힙합");
			music26.setSongWriter("2020.05.25");
			music26.setWriter("R&B/Soul");
			music26.setMusicPlayTime("4:11");
			music26.setStreamingCount(23);
			tb_music.add(music26);
			
			MusicVO music27 = new MusicVO();
			music27.setPk_musicNo(27);
			music27.setMusicTitle("R U Ridin?");
			music27.setSingerName("백현");
			music27.setAlbumName("Delight - The 2nd Mini Album");
			music27.setReleaseDate("2020.05.25");
			music27.setgetMusicGenre("R&B/Soul");
			music27.setSongWriter("kenzie, Mike Daley, Mitchell Owens, Wilbart Vedo McCoy3");
			music27.setWriter("kenzie");
			music27.setMusicPlayTime("2:55");
			music27.setStreamingCount(29);
			tb_music.add(music27);
			
			MusicVO music28 = new MusicVO();
			music28.setPk_musicNo(28);
			music28.setMusicTitle("Happy");
			music28.setSingerName("태연");
			music28.setAlbumName("Happy");
			music28.setReleaseDate("2020.05.04");
			music28.setgetMusicGenre("R&B/Soul");
			music28.setSongWriter("Chris Wahle, Chelcee Grimes, Samuel Gerongco, Robert Gerongco");
			music28.setWriter("이스란");
			music28.setMusicPlayTime("3:42");
			music1.setStreamingCount(24);
			tb_music.add(music28);
			
			MusicVO music29 = new MusicVO();
			music29.setPk_musicNo(29);
			music29.setMusicTitle("마음을 드려요");
			music29.setSingerName("아이유");
			music29.setAlbumName("사랑의 불시착 OST Part 11");
			music29.setReleaseDate("2020.02.15");
			music29.setgetMusicGenre("발라드, 국내드라마");
			music29.setSongWriter("남혜승, 박진호");
			music29.setWriter("남혜승, 박진호");
			music29.setMusicPlayTime("4:41");
			music29.setStreamingCount(25);
			tb_music.add(music29);
			
			MusicVO music30 = new MusicVO();
			music30.setPk_musicNo(30);
			music30.setMusicTitle("Black Swan");
			music30.setSingerName("방탄소년단");
			music30.setAlbumName("MAP OF THE SOUL : 7");
			music30.setReleaseDate("2020.01.17");
			music30.setgetMusicGenre("랩/힙합");
			music30.setSongWriter("Pdogg, RM, August Rigo, Vince Nantes, Clyde Kelly");
			music30.setWriter("Pdogg, RM, August Rigo, Vince Nantes, Clyde Kelly");
			music30.setMusicPlayTime("3:19");
			music30.setStreamingCount(26);
			tb_music.add(music30);
	}
	
	// 뮤직리스트
	{
		// 1번
		MusicListVO musicL1 = new MusicListVO();
		musicL1.setFk_musicNo(1);
		musicL1.setFk_userID("id1");
		tb_musicList.add(musicL1);

		MusicListVO musicL2 = new MusicListVO();
		musicL2.setFk_musicNo(2);
		musicL2.setFk_userID("id1");
		tb_musicList.add(musicL2);

		MusicListVO musicL3 = new MusicListVO();
		musicL3.setFk_musicNo(3);
		musicL3.setFk_userID("id1");
		tb_musicList.add(musicL3);

		MusicListVO musicL4 = new MusicListVO();
		musicL4.setFk_musicNo(4);
		musicL4.setFk_userID("id1");
		tb_musicList.add(musicL4);

		MusicListVO musicL5 = new MusicListVO();
		musicL1.setFk_musicNo(5);
		musicL1.setFk_userID("id1");
		tb_musicList.add(musicL5);

		// 2번유져
		MusicListVO musicL6 = new MusicListVO();
		musicL6.setFk_musicNo(1);
		musicL6.setFk_userID("id2");
		tb_musicList.add(musicL6);

		MusicListVO musicL7 = new MusicListVO();
		musicL7.setFk_musicNo(2);
		musicL7.setFk_userID("id2");
		tb_musicList.add(musicL7);

		MusicListVO musicL8 = new MusicListVO();
		musicL8.setFk_musicNo(3);
		musicL8.setFk_userID("id2");
		tb_musicList.add(musicL8);

		MusicListVO musicL9 = new MusicListVO();
		musicL9.setFk_musicNo(4);
		musicL9.setFk_userID("id2");
		tb_musicList.add(musicL9);

		MusicListVO musicL10 = new MusicListVO();
		musicL10.setFk_musicNo(5);
		musicL10.setFk_userID("id2");
		tb_musicList.add(musicL10);

		// 3번유져
		MusicListVO musicL11 = new MusicListVO();
		musicL11.setFk_musicNo(1);
		musicL11.setFk_userID("id3");
		tb_musicList.add(musicL11);

		MusicListVO musicL12 = new MusicListVO();
		musicL12.setFk_musicNo(2);
		musicL12.setFk_userID("id3");
		tb_musicList.add(musicL12);

		MusicListVO musicL13 = new MusicListVO();
		musicL13.setFk_musicNo(3);
		musicL13.setFk_userID("id3");
		tb_musicList.add(musicL13);

		MusicListVO musicL14 = new MusicListVO();
		musicL14.setFk_musicNo(4);
		musicL14.setFk_userID("id3");
		tb_musicList.add(musicL14);

		MusicListVO musicL15 = new MusicListVO();
		musicL15.setFk_musicNo(5);
		musicL15.setFk_userID("id3");
		tb_musicList.add(musicL15);

		// 4번유져
		MusicListVO musicL16 = new MusicListVO();
		musicL16.setFk_musicNo(1);
		musicL16.setFk_userID("id4");
		tb_musicList.add(musicL16);

		MusicListVO musicL17 = new MusicListVO();
		musicL17.setFk_musicNo(2);
		musicL17.setFk_userID("id4");
		tb_musicList.add(musicL17);

		MusicListVO musicL18 = new MusicListVO();
		musicL18.setFk_musicNo(3);
		musicL18.setFk_userID("id4");
		tb_musicList.add(musicL18);

		MusicListVO musicL19 = new MusicListVO();
		musicL19.setFk_musicNo(4);
		musicL19.setFk_userID("id4");
		tb_musicList.add(musicL19);

		MusicListVO musicL20 = new MusicListVO();
		musicL20.setFk_musicNo(5);
		musicL20.setFk_userID("id4");
		tb_musicList.add(musicL20);

		// 5번유져
		MusicListVO musicL21 = new MusicListVO();
		musicL21.setFk_musicNo(1);
		musicL21.setFk_userID("id5");
		tb_musicList.add(musicL21);

		MusicListVO musicL22 = new MusicListVO();
		musicL22.setFk_musicNo(2);
		musicL22.setFk_userID("id5");
		tb_musicList.add(musicL22);

		MusicListVO musicL23 = new MusicListVO();
		musicL23.setFk_musicNo(3);
		musicL23.setFk_userID("id5");
		tb_musicList.add(musicL23);

		MusicListVO musicL24 = new MusicListVO();
		musicL24.setFk_musicNo(4);
		musicL24.setFk_userID("id5");
		tb_musicList.add(musicL24);

		MusicListVO musicL25 = new MusicListVO();
		musicL25.setFk_musicNo(5);
		musicL25.setFk_userID("id5");
		tb_musicList.add(musicL25);
	}
	
	// 프로모션
	{
		PromotionVO promotion = new PromotionVO();
		promotion.setPk_promotionNo(1);
		promotion.setPromotionName("스트리밍 플러스");
		promotion.setPromotionPrice(5400);
		tb_promotion.add(promotion);

		PromotionVO promotion1 = new PromotionVO();
		promotion1.setPk_promotionNo(2);
		promotion1.setPromotionName("스트리밍 클럽");
		promotion1.setPromotionPrice(3900);
		tb_promotion.add(promotion1);

		PromotionVO promotion2 = new PromotionVO();
		promotion2.setPk_promotionNo(3);
		promotion2.setPromotionName("MP3 30 플러스");
		promotion2.setPromotionPrice(10900);
		tb_promotion.add(promotion2);
	}

	// 유저 프로모션
	{
		UserPromotionVO userpromotion = new UserPromotionVO();
		userpromotion.setPk_userPromotionNO(1);
		userpromotion.setStartDate(new Date());
		userpromotion.setEndDate(promotionDate.promotionDate());
		userpromotion.setFk_promotionNo(1);
		userpromotion.setFk_userID("id1");
		tb_userPromotion.add(userpromotion);

		UserPromotionVO userpromotion1 = new UserPromotionVO();
		userpromotion1.setPk_userPromotionNO(2);
		userpromotion1.setStartDate(new Date());
		userpromotion1.setEndDate(DateData.promotionDate());
		userpromotion1.setFk_userID("id2");
		userpromotion1.setFk_promotionNo(2);
		tb_userPromotion.add(userpromotion1);
		
		UserPromotionVO userpromotion2 = new UserPromotionVO();
		userpromotion2.setPk_userPromotionNO(3);
		userpromotion2.setStartDate(new Date());
		userpromotion2.setEndDate(DateData.promotionDate());
		userpromotion2.setFk_userID("id3");
		userpromotion2.setFk_promotionNo(3);
		tb_userPromotion.add(userpromotion2);


		UserPromotionVO userpromotion3 = new UserPromotionVO();
		userpromotion3.setPk_userPromotionNO(4);
		userpromotion3.setStartDate(new Date());
		userpromotion3.setEndDate(DateData.promotionDate());
		userpromotion3.setFk_userID("id4");
		userpromotion3.setFk_promotionNo(1);
		tb_userPromotion.add(userpromotion3);
	}

	// 질문
	{
		QuestionVO question1 = new QuestionVO();
		question1.setPk_questionNo(1);
		question1.setQuestionTitle("질문있어요");
		question1.setQuestionContent("질문없어요");
		question1.setQuestionDate(new Date());
		question1.setFk_userID("id1");
		tb_question.add(question1);

		QuestionVO question2 = new QuestionVO();
		question2.setPk_questionNo(2);
		question2.setQuestionTitle("이용권 구매 오류");
		question2.setQuestionContent("해결부탁");
		question2.setQuestionDate(new Date());
		question2.setFk_userID("id2");
		tb_question.add(question2);

		QuestionVO question3 = new QuestionVO();
		question3.setPk_questionNo(3);
		question3.setQuestionTitle("재생오류");
		question3.setQuestionContent("노래듣고싶어요");
		question3.setQuestionDate(new Date());
		question3.setFk_userID("id3");
		tb_question.add(question3);
	}

	// 답변
	{
		AnswerVO answer1 = new AnswerVO();
		answer1.setPk_answerNo(1);
		answer1.setAnswerContent("질문이 없어서 다행이네요");
		answer1.setAnswerDate(new Date());
		answer1.setFk_questionNo(1);
		tb_answer.add(answer1);

		AnswerVO answer2 = new AnswerVO();
		answer2.setPk_answerNo(2);
		answer2.setAnswerContent("해결완료");
		answer2.setAnswerDate(new Date());
		answer2.setFk_questionNo(2);
		tb_answer.add(answer2);

		AnswerVO answer3 = new AnswerVO();
		answer3.setPk_answerNo(3);
		answer3.setAnswerContent("볼륨을 키워주세요");
		answer3.setAnswerDate(new Date());
		answer3.setFk_questionNo(3);
		tb_answer.add(answer3);
	}

	// 곡5개 :곡 당 유저 댓글 1개
	{	
		//1번 유저
		UserMusicCommentVO usermusiccomment1 = new UserMusicCommentVO();
		usermusiccomment1.setPk_userCommentNo(1);
		usermusiccomment1.setCommentContent("댓글 테스트");
		usermusiccomment1.setCommentDate(new Date());
		usermusiccomment1.setStar(1.1);
		usermusiccomment1.setFk_musicNo(1);
		usermusiccomment1.setFk_userID("id1");
		tb_userMusicComment.add(usermusiccomment1);

		UserMusicCommentVO usermusiccomment2 = new UserMusicCommentVO();
		usermusiccomment2.setPk_userCommentNo(2);
		usermusiccomment2.setCommentContent("채홍규_댓글 테스트2");
		usermusiccomment2.setCommentDate(new Date());
		usermusiccomment2.setStar(2.2);
		usermusiccomment2.setFk_musicNo(2);
		usermusiccomment2.setFk_userID("id1");
		tb_userMusicComment.add(usermusiccomment2);

		UserMusicCommentVO usermusiccomment3 = new UserMusicCommentVO();
		usermusiccomment3.setPk_userCommentNo(3);
		usermusiccomment3.setCommentContent("채홍규_댓글 테스트3");
		usermusiccomment3.setCommentDate(new Date());
		usermusiccomment3.setStar(3.3);
		usermusiccomment3.setFk_musicNo(3);
		usermusiccomment3.setFk_userID("id1");
		tb_userMusicComment.add(usermusiccomment3);

		UserMusicCommentVO usermusiccomment4 = new UserMusicCommentVO();
		usermusiccomment4.setPk_userCommentNo(4);
		usermusiccomment4.setCommentContent("채홍규_댓글 테스트4");
		usermusiccomment4.setCommentDate(new Date());
		usermusiccomment4.setStar(4.4);
		usermusiccomment4.setFk_musicNo(4);
		usermusiccomment4.setFk_userID("id1");
		tb_userMusicComment.add(usermusiccomment4);

		UserMusicCommentVO usermusiccomment5 = new UserMusicCommentVO();
		usermusiccomment5.setPk_userCommentNo(5);
		usermusiccomment5.setCommentContent("채홍규_댓글 테스트5");
		usermusiccomment5.setCommentDate(new Date());
		usermusiccomment5.setStar(5.5);
		usermusiccomment5.setFk_musicNo(5);
		usermusiccomment5.setFk_userID("id1");
		tb_userMusicComment.add(usermusiccomment5);

		//2번 유저
		UserMusicCommentVO usermusiccomment6 = new UserMusicCommentVO();
		usermusiccomment6.setPk_userCommentNo(6);
		usermusiccomment6.setCommentContent("이원우_댓글 테스트");
		usermusiccomment6.setCommentDate(new Date());
		usermusiccomment6.setStar(1.1);
		usermusiccomment6.setFk_musicNo(1);
		usermusiccomment6.setFk_userID("id2");
		tb_userMusicComment.add(usermusiccomment6);

		UserMusicCommentVO usermusiccomment7 = new UserMusicCommentVO();
		usermusiccomment7.setPk_userCommentNo(7);
		usermusiccomment7.setCommentContent("이원우_댓글 테스트2");
		usermusiccomment7.setCommentDate(new Date());
		usermusiccomment7.setStar(2.2);
		usermusiccomment7.setFk_musicNo(2);
		usermusiccomment7.setFk_userID("id2");
		tb_userMusicComment.add(usermusiccomment7);

		UserMusicCommentVO usermusiccomment8 = new UserMusicCommentVO();
		usermusiccomment8.setPk_userCommentNo(8);
		usermusiccomment8.setCommentContent("이원우_댓글 테스트3");
		usermusiccomment8.setCommentDate(new Date());
		usermusiccomment8.setStar(3.3);
		usermusiccomment8.setFk_musicNo(3);
		usermusiccomment8.setFk_userID("id2");
		tb_userMusicComment.add(usermusiccomment8);

		UserMusicCommentVO usermusiccomment9 = new UserMusicCommentVO();
		usermusiccomment9.setPk_userCommentNo(9);
		usermusiccomment9.setCommentContent("이원우_댓글 테스트4");
		usermusiccomment9.setCommentDate(new Date());
		usermusiccomment9.setStar(4.4);
		usermusiccomment9.setFk_musicNo(4);
		usermusiccomment9.setFk_userID("id2");
		tb_userMusicComment.add(usermusiccomment9);

		UserMusicCommentVO usermusiccomment10 = new UserMusicCommentVO();
		usermusiccomment10.setPk_userCommentNo(10);
		usermusiccomment10.setCommentContent("이원우_댓글 테스트5");
		usermusiccomment10.setCommentDate(new Date());
		usermusiccomment10.setStar(5.5);
		usermusiccomment10.setFk_musicNo(5);
		usermusiccomment10.setFk_userID("id2");
		tb_userMusicComment.add(usermusiccomment10);

		//3번 유저
		UserMusicCommentVO usermusiccomment11 = new UserMusicCommentVO();
		usermusiccomment11.setPk_userCommentNo(11);
		usermusiccomment11.setCommentContent("유효상_댓글 테스트");
		usermusiccomment11.setCommentDate(new Date());
		usermusiccomment11.setStar(1.1);
		usermusiccomment11.setFk_musicNo(1);
		usermusiccomment11.setFk_userID("id3");
		tb_userMusicComment.add(usermusiccomment11);

		UserMusicCommentVO usermusiccomment12 = new UserMusicCommentVO();
		usermusiccomment12.setPk_userCommentNo(12);
		usermusiccomment12.setCommentContent("유효상_댓글 테스트2");
		usermusiccomment12.setCommentDate(new Date());
		usermusiccomment12.setStar(2.2);
		usermusiccomment12.setFk_musicNo(2);
		usermusiccomment12.setFk_userID("id3");
		tb_userMusicComment.add(usermusiccomment12);

		UserMusicCommentVO usermusiccomment13 = new UserMusicCommentVO();
		usermusiccomment13.setPk_userCommentNo(13);
		usermusiccomment13.setCommentContent("유효상_댓글 테스트3");
		usermusiccomment13.setCommentDate(new Date());
		usermusiccomment13.setStar(3.3);
		usermusiccomment13.setFk_musicNo(3);
		usermusiccomment13.setFk_userID("id3");
		tb_userMusicComment.add(usermusiccomment13);

		UserMusicCommentVO usermusiccomment14 = new UserMusicCommentVO();
		usermusiccomment14.setPk_userCommentNo(14);
		usermusiccomment14.setCommentContent("유효상_댓글 테스트4");
		usermusiccomment14.setCommentDate(new Date());
		usermusiccomment14.setStar(4.4);
		usermusiccomment14.setFk_musicNo(4);
		usermusiccomment14.setFk_userID("id3");
		tb_userMusicComment.add(usermusiccomment14);

		UserMusicCommentVO usermusiccomment15 = new UserMusicCommentVO();
		usermusiccomment15.setPk_userCommentNo(15);
		usermusiccomment15.setCommentContent("유효상_댓글 테스트5");
		usermusiccomment15.setCommentDate(new Date());
		usermusiccomment15.setStar(5.5);
		usermusiccomment15.setFk_musicNo(5);
		usermusiccomment15.setFk_userID("id3");
		tb_userMusicComment.add(usermusiccomment15);

		//4번 유저
		UserMusicCommentVO usermusiccomment16 = new UserMusicCommentVO();
		usermusiccomment16.setPk_userCommentNo(16);
		usermusiccomment16.setCommentContent("원종찬_댓글 테스트");
		usermusiccomment16.setCommentDate(new Date());
		usermusiccomment16.setStar(1.1);
		usermusiccomment16.setFk_musicNo(1);
		usermusiccomment16.setFk_userID("id4");
		tb_userMusicComment.add(usermusiccomment16);

		UserMusicCommentVO usermusiccomment17 = new UserMusicCommentVO();
		usermusiccomment17.setPk_userCommentNo(17);
		usermusiccomment17.setCommentContent("원종찬_댓글 테스트2");
		usermusiccomment17.setCommentDate(new Date());
		usermusiccomment17.setStar(2.2);
		usermusiccomment17.setFk_musicNo(2);
		usermusiccomment17.setFk_userID("id4");
		tb_userMusicComment.add(usermusiccomment17);

		UserMusicCommentVO usermusiccomment18 = new UserMusicCommentVO();
		usermusiccomment18.setPk_userCommentNo(18);
		usermusiccomment18.setCommentContent("원종찬_댓글 테스트3");
		usermusiccomment18.setCommentDate(new Date());
		usermusiccomment18.setStar(3.3);
		usermusiccomment18.setFk_musicNo(3);
		usermusiccomment18.setFk_userID("id4");
		tb_userMusicComment.add(usermusiccomment18);

		UserMusicCommentVO usermusiccomment19 = new UserMusicCommentVO();
		usermusiccomment19.setPk_userCommentNo(19);
		usermusiccomment19.setCommentContent("원종찬_댓글 테스트4");
		usermusiccomment19.setCommentDate(new Date());
		usermusiccomment19.setStar(4.4);
		usermusiccomment19.setFk_musicNo(4);
		usermusiccomment19.setFk_userID("id4");
		tb_userMusicComment.add(usermusiccomment19);

		UserMusicCommentVO usermusiccomment20 = new UserMusicCommentVO();
		usermusiccomment20.setPk_userCommentNo(20);
		usermusiccomment20.setCommentContent("원종찬_댓글 테스트5");
		usermusiccomment20.setCommentDate(new Date());
		usermusiccomment20.setStar(5.5);
		usermusiccomment20.setFk_musicNo(5);
		usermusiccomment20.setFk_userID("id4");
		tb_userMusicComment.add(usermusiccomment20);

		//5번 유저
		UserMusicCommentVO usermusiccomment21 = new UserMusicCommentVO();
		usermusiccomment21.setPk_userCommentNo(21);
		usermusiccomment21.setCommentContent("이지윤_댓글 테스트");
		usermusiccomment21.setCommentDate(new Date());
		usermusiccomment21.setStar(1.1);
		usermusiccomment21.setFk_musicNo(1);
		usermusiccomment21.setFk_userID("id5");
		tb_userMusicComment.add(usermusiccomment21);

		UserMusicCommentVO usermusiccomment22 = new UserMusicCommentVO();
		usermusiccomment22.setPk_userCommentNo(22);
		usermusiccomment22.setCommentContent("이지윤_댓글 테스트2");
		usermusiccomment22.setCommentDate(new Date());
		usermusiccomment22.setStar(2.2);
		usermusiccomment22.setFk_musicNo(2);
		usermusiccomment22.setFk_userID("id5");
		tb_userMusicComment.add(usermusiccomment22);

		UserMusicCommentVO usermusiccomment23 = new UserMusicCommentVO();
		usermusiccomment23.setPk_userCommentNo(23);
		usermusiccomment23.setCommentContent("이지윤_댓글 테스트3");
		usermusiccomment23.setCommentDate(new Date());
		usermusiccomment23.setStar(3.3);
		usermusiccomment23.setFk_musicNo(3);
		usermusiccomment23.setFk_userID("id5");
		tb_userMusicComment.add(usermusiccomment23);

		UserMusicCommentVO usermusiccomment24 = new UserMusicCommentVO();
		usermusiccomment24.setPk_userCommentNo(24);
		usermusiccomment24.setCommentContent("이지윤_댓글 테스트4");
		usermusiccomment24.setCommentDate(new Date());
		usermusiccomment24.setStar(4.4);
		usermusiccomment24.setFk_musicNo(4);
		usermusiccomment24.setFk_userID("id5");
		tb_userMusicComment.add(usermusiccomment24);

		UserMusicCommentVO usermusiccomment25 = new UserMusicCommentVO();
		usermusiccomment25.setPk_userCommentNo(25);
		usermusiccomment25.setCommentContent("이지윤_댓글 테스트5");
		usermusiccomment25.setCommentDate(new Date());
		usermusiccomment25.setStar(5.5);
		usermusiccomment25.setFk_musicNo(5);
		usermusiccomment25.setFk_userID("id5");
		tb_userMusicComment.add(usermusiccomment25);
	}
	
	// 공지사항
	{
		NoticeVO notice1 = new NoticeVO();
		notice1.setPk_noticeNo(1);
		notice1.setNoticeTitle("할인이벤트");
		notice1.setNoticeContent("10%할인");
		notice1.setNoticeDate(new Date());
		tb_notice.add(notice1);
		
		NoticeVO notice2 = new NoticeVO();
		notice2.setPk_noticeNo(2);
		notice2.setNoticeTitle("임시점검");
		notice2.setNoticeContent("오후에 임시점검");
		notice2.setNoticeDate(new Date());
		tb_notice.add(notice2);
		
		NoticeVO notice3 = new NoticeVO();
		notice3.setPk_noticeNo(3);
		notice3.setNoticeTitle("춥다");
		notice3.setNoticeContent("덥다");
		notice3.setNoticeDate(new Date());
		tb_notice.add(notice3);
	}
}