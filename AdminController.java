package controller;

import java.util.*;

import service.*;
import vo.*;
import data.*;

public class AdminController {

	private static AdminController instance;

	private AdminController() {
	}

	public static AdminController getInstance() {
		if (instance == null) {
			instance = new AdminController();
		}
		return instance;
	}

	int noticeNo = 10;
	int answerNo = 10;

	UserService userService = UserService.getInstance();
	AdminService adminService = AdminService.getInstance();
	MusicService musicService = MusicService.getInstance();
	NoticeService noticeService = NoticeService.getInstance();
	PromotionService promotionService = PromotionService.getInstance();
	QuestionService questionService = QuestionService.getInstance();
	AnswerService answerService = AnswerService.getInstance();

	public void adminStart() {
		int menu;

		do {
			System.out.println("==========메뉴==========");
			System.out.println("1. 음악 관리");
			System.out.println("2. 프로모션 관리");
			System.out.println("3. 고객 관리");
			System.out.println("4. 공지사항 관리");
			System.out.println("5. QnA게시판 관리");
			System.out.println("0. 로그아웃");
			System.out.println("=======================");
			System.out.print("메뉴에 해당하는 번호 입력>");

			menu = ScanUtil.nextInt();

			switch (menu) {
			case 1: // 1.노래 관리
				MusicManagement();
				break;
			case 2: // 2.프로모션 관리
				PromotionManagement();
				break;
			case 3: // 3.고객 관리
				UserManagement();
				break;
			case 4: // 4.공지사항 관리
				NoticeManagement();
				break;
			case 5: // 5. QnA관리
				QnAManagement();
				break;
			case 0: // 0. 로그아웃
				userService.logout();
				break;
			}
		} while (menu != 0);

	}

	public void MusicManagement() {
		int menu;
		do {
			System.out.println("==========메뉴==========");
			System.out.println("1. 음악 추가");
			System.out.println("2. 음악 삭제");
			System.out.println("3. 음악 정보변경");
			System.out.println("4. 음악 리스트 보기");
			System.out.println("0. 뒤로가기");
			System.out.println("=======================");
			System.out.print("메뉴에 해당하는 번호 입력 > ");

			menu = ScanUtil.nextInt();

			switch (menu) {
			case 1: // 1.음악 추가
				musicInsert();
				break;
			case 2: // 2.음악 삭제
				musicDelete();
				break;
			case 3: // 3.음악 수정
				musicUpdate();
				break;
			case 4: // 4.음악 리스트
				musicList();
				break;
			case 0: // 뒤로가기
				break;

			}
		} while (menu != 0);
	}

	// 음악 추가
	public void musicInsert() {
		System.out.print("음악 번호 : ");
		int musicNo = ScanUtil.nextInt();
		System.out.print("음악 제목 : ");
		String title = ScanUtil.nextLine();
		System.out.print("가수 이름 : ");
		String name = ScanUtil.nextLine();
		System.out.print("음악 종류 : ");
		String genre = ScanUtil.nextLine();
		System.out.print("작곡가 : ");
		String songWriter = ScanUtil.nextLine();
		System.out.print("작사가 : ");
		String writer = ScanUtil.nextLine();
		System.out.print("재생시간 : ");
		String time = ScanUtil.nextLine();
		System.out.print("앨범명 : ");
		String albumName = ScanUtil.nextLine();
		System.out.print("발매일자 : ");
		String releaseDate = ScanUtil.nextLine();
		System.out.println("재생횟수 : ");
		int streaming = ScanUtil.nextInt();

		MusicVO musicVo = musicService.selectMusic(musicNo);
		if(musicVo != null) {
			System.out.println("음악을 추가하였습니다.");
			musicService.insertMusic(musicNo, title, name, genre,
					songWriter, writer, time, albumName, releaseDate, streaming);
		} else {
			System.out.println("이미 존재하는 음악입니다.");
			System.out.println("추가할 수 없습니다.");
		}
	}

	// 음악 수정
	public void musicUpdate() {
		int menu;
		System.out.println("========전체 음악리스트========");
		musicList();
		System.out.println("==========================");

		System.out.print("수정할 음악 번호 입력 > ");
		int updateMusicNo = ScanUtil.nextInt();

		MusicVO musicVo = musicService.selectMusic(updateMusicNo);
		int updateIndex = musicService.indexMusic(updateMusicNo);
		showMusic(musicVo);
		System.out.println("무엇을 수정할까요?");
		do {
			System.out
					.println("1.장르 2.가수이름 3.음악제목 4.작곡가 5.작사가 6.재생시간 7.앨범명 8.발매일 9.재생횟수 0.수정완료");
			System.out.println("수정할 번호 입력 > ");
			menu = ScanUtil.nextInt();
			musicVo = musicSetMenu(musicVo, menu);
		} while (menu != 0);

		if (musicVo == null) {
			System.out.println("잘못된 음악번호를 입력하여 음악 정보 수정을 실패하였습니다");
		} else {
			musicService.updateMusic(musicVo, updateIndex);
			System.out.println("음악 정보를 수정하였습니다.");
		}
	}

	// 음악정보 수정하여 수정한 음악 리턴
	public MusicVO musicSetMenu(MusicVO musicVo, int menu) {
		if (menu == 1) {
			System.out.print("장르 입력 > ");
			String genre = ScanUtil.nextLine();
			musicVo.setgetMusicGenre(genre);
		} else if (menu == 2) {
			System.out.print("가수 이름 입력 > ");
			String singer = ScanUtil.nextLine();
			musicVo.setSingerName(singer);
		} else if (menu == 3) {
			System.out.print("음악 제목 입력 > ");
			String title = ScanUtil.nextLine();
			musicVo.setMusicTitle(title);
		} else if (menu == 4) {
			System.out.print("작곡가 입력 > ");
			String songWriter = ScanUtil.nextLine();
			musicVo.setSongWriter(songWriter);
		} else if (menu == 5) {
			System.out.print("작사가 입력 > ");
			String writer = ScanUtil.nextLine();
			musicVo.setWriter(writer);
		} else if (menu == 6) {
			System.out.print("재생시간 입력 > ");
			String playTime = ScanUtil.nextLine();
			musicVo.setMusicPlayTime(playTime);
		} else if (menu == 7) {
			System.out.print("앨범명 입력 > ");
			String album = ScanUtil.nextLine();
			musicVo.setAlbumName(album);
		} else if (menu == 8) {
			System.out.print("발매일자입력 > ");
			String releaseDate = ScanUtil.nextLine();
			musicVo.setReleaseDate(releaseDate);
		} else if (menu == 9) {
			System.out.print("재생횟수 입력 > ");
			int streaming = ScanUtil.nextInt();
			musicVo.setStreamingCount(streaming);
		}
		return musicVo;
	}

	// 음악 삭제
	public void musicDelete() {
		musicList();

		System.out.print("삭제할 음악 번호 입력 > ");
		int deleteMusicNo = ScanUtil.nextInt();

		int deleteIndex = musicService.indexMusic(deleteMusicNo);

		if (deleteIndex == -1) {
			System.out.println("삭제할 음악의 번호를 잘못 입력했습니다.");
			System.out.println("다시 한번 확인해주세요.");
		} else {
			musicService.deleteMusic(deleteIndex);
			System.out.println("음악을 삭제하였습니다");
		}
	}

	// 하나의 음악정보를 보여주는 메소드
	public void showMusic(MusicVO musicVo) {
		System.out.println("======================");
		System.out.println("가수 이름 : " + musicVo.getSingerName());
		System.out.println("음악 장르 : " + musicVo.getMusicGenre());
		System.out.println("음악 제목 : " + musicVo.getMusicTitle());
		System.out.println("작  곡  가 : " + musicVo.getSongWriter());
		System.out.println("작  사  가 : " + musicVo.getWriter());
		System.out.println("재생 시간 : " + musicVo.getMusicPlayTime());
		System.out.println("앨  범  명 : " + musicVo.getAlbumName());
		System.out.println("발매 일자 : " + musicVo.getReleaseDate());
		System.out.println("재생 횟수 : " + musicVo.getStreamingCount());
		System.out.println("======================");
	}

	// 전체 음악리스트를 보여주는 메소드
	public void musicList() {
		ArrayList<MusicVO> musicList = musicService.selectMusicList();
		System.out
				.println("===================================================");
		System.out.println("음악번호\t음악제목\t\t\t가수이름\t\t앨범명");
		System.out
				.println("===================================================");
		for (int i = 0; i < musicList.size(); i++) {
			MusicVO music = musicList.get(i);

			System.out.println(i + 1 + "\t" + music.getMusicTitle() + "\t\t\t"
					+ music.getSingerName() + "\t\t" + music.getAlbumName());
		}
		System.out
				.println("===================================================");
	}

	// 프로모션 관리
	public void PromotionManagement() {
		int menu;
		do {
			System.out.println("==========메뉴==========");
			System.out.println("1. 프로모션 추가");
			System.out.println("2. 프로모션 삭제");
			System.out.println("3. 프로모션 리스트 보기");
			System.out.println("0. 뒤로가기");
			System.out.println("=======================");
			System.out.print("메뉴에 해당하는 번호 입력>");

			menu = ScanUtil.nextInt();

			switch (menu) {
			case 1: // 1.프로모션 추가
				promotionInsert();
				break;
			case 2: // 2.프로모션 삭제
				promotionDelete();
				break;
			case 3: // 3. 프로모션 리스트 보기
				promotionList();
				break;
			case 0:// 뒤로가기
				break;
			}
		} while (menu != 0);
	}

	public void promotionList() {
		ArrayList<PromotionVO> promotionList = promotionService
				.selectAllPromotionList();

		System.out.println("~~~~~~~~~프 로 모 션 리 스 트 ~~~~~~~~~~");
		System.out.println("================================");
		System.out.println("번호\t이름\t\t가격");
		System.out.println("================================");
		for (int i = 0; i < promotionList.size(); i++) {
			PromotionVO promotion = promotionList.get(i);
			System.out.println(promotion.getPk_promotionNo() + "\t"
					+ promotion.getPromotionName() + "\t"
					+ promotion.getPromotionPrice());
		}
		System.out.println("================================");
	}

	// 프로모션 추가
	public void promotionInsert() {

		System.out.print("프로모션 번호를 입력하세요.> ");
		int promotionNo = ScanUtil.nextInt();
		System.out.print("프로모션 이름을 입력하시오.> ");
		String name = ScanUtil.nextLine();
		System.out.print("프로모션 가격을 입력하시오.>");
		int price = ScanUtil.nextInt();

		boolean is = promotionService.isPromotion(promotionNo);

		if (is) {
			System.out.println("중복된 프로모션입니다.");
		} else {
			promotionService.promotionInsert(promotionNo, name, price);
			System.out.println("프로모션을 추가하였습니다.");
		}
	}

	// 프로모션 삭제
	public void promotionDelete() 
	{
		promotionList();

		System.out.print("삭제할 프로모션의 번호를 입력하시오 > ");
		int promotionNo = ScanUtil.nextInt();
		int proIndex = promotionService.promotionIndex(promotionNo);

		if (proIndex == -1) {
			System.out.println("삭제할 프로모션의 번호를 잘못 입력했습니다.");
			System.out.println("다시 확인해주세요.");
		} else {
			promotionService.deletePromotion(proIndex);
			System.out.println("프로모션을 삭제하였습니다.");
		}
	}

	// 고객관리
	public void UserManagement() {
		int menu;
		do {
			System.out.println("==========메뉴==========");
			System.out.println("1. 고객 계정 추가");
			System.out.println("2. 고객 계정 삭제");
			System.out.println("3. 고객 정보 수정");
			System.out.println("4. 고객 리스트");
			System.out.println("0. 뒤로가기");
			System.out.println("=======================");
			System.out.print("메뉴에 해당하는 번호 입력>");

			menu = ScanUtil.nextInt();

			switch (menu) {
			case 1: // 1.고객 계정 추가
				userInsert();
				break;
			case 2: // 2.고객 계정삭제
				userDelete();
				break;
			case 3: // 3.고객 정보수정
				userUpdate();
				break;
			case 4: // 3.고객 리스트
				userList();
				break;
			case 0: // 뒤로가기
				break;
			}
		} while (menu != 0);
	}

	// 고객 추가
	public void userInsert() {
		System.out.print("회원의 아이디를 입력하시오.> ");
		String id = ScanUtil.nextLine();
		System.out.print("회원의 비밀번호를 입력하시오.>");
		String password = ScanUtil.nextLine();
		System.out.print("회원의 이름을 입력하시오.>");
		String name = ScanUtil.nextLine();
		System.out.print("핸드폰 번호를 입력하시오.>");
		String phone = ScanUtil.nextLine();

		userService.insertUser(id, password, name, phone);
	}

	// 고객 정보 수정
	public void userUpdate() {
		int menu;
		userList();
		System.out.print("수정할 고객의 아이디를 입력하시오. > ");
		String userID = ScanUtil.nextLine();

		UserVO userVo = userService.selectOneUser(userID);
		int updateIndex = userService.indexUser(userID);

		showUser(userVo);
		System.out.println("무엇을 수정할까요?");
		do {
			System.out.println("1.아이디 2.비밀번호 3.이름 4.연락처 0.수정완료");
			System.out.println("수정할 번호 입력 > ");
			menu = ScanUtil.nextInt();
			userVo = userSetMenu(userVo, menu);
		} while (menu != 0);

		if (userVo == null) {
			System.out.println("잘못된 아이디를 입력했습니다.");
		} else {
			userService.updateUser(userVo, updateIndex);
			System.out.println("고객 정보를 수정하였습니다.");
		}

	}

	// 수정할 한명의 유저 정보
	public void showUser(UserVO userVo) {
		System.out.println("=========");
		System.out.println("아이디 : " + userVo.getPk_userID());
		System.out.println("비밀번호 : " + userVo.getUserPW());
		System.out.println("이름 : " + userVo.getUserName());
		System.out.println("연락처 : " + userVo.getUserPhone());
		System.out.println("=========");
	}

	// 수정할 고객 정보 리턴
	public UserVO userSetMenu(UserVO userVo, int menu) {
		if (menu == 1) {
			System.out.print("아이디 입력 > ");
			String userID = ScanUtil.nextLine();
			userVo.setPk_userID(userID);
		} else if (menu == 2) {
			System.out.print("비밀번호 입력 > ");
			String userPW = ScanUtil.nextLine();
			userVo.setUserPW(userPW);
		} else if (menu == 3) {
			System.out.print("이름 입력 > ");
			String userName = ScanUtil.nextLine();
			userVo.setUserName(userName);
		} else if (menu == 4) {
			System.out.print("연락처 입력 > ");
			String userPhone = ScanUtil.nextLine();
			userVo.setUserPhone(userPhone);
		}
		return userVo;
	}

	// 고객 삭제
	public void userDelete() {
		userList();
		System.out.print("삭제할 고객의 아이디를 입력하시오. > ");
		String userID = ScanUtil.nextLine();

		int userIndex = userService.indexUser(userID);

		if (userIndex == -1 || userID.equals("admin")) {
			System.out.println("잘못된 아이디입니다. 삭제할 수 없습니다.");
		} else {
			userService.deleteUser(userIndex);
			System.out.println("고객정보를 수정했습니다.");
		}
	}

	// 고객 전체 리스트
	public void userList() {
		ArrayList<UserVO> userList = userService.selectUserList();
		System.out.println("~~~~~~~~~~고 객 리 스 트 ~~~~~~~~~~~");
		System.out.println("-------------------------------");
		System.out.println("아이디\t이름");
		System.out.println("-------------------------------");
		for (int i = 0; i < userList.size(); i++) {
			UserVO user = userList.get(i);
			System.out.println(user.getPk_userID() + "\t" + user.getUserName());
		}
		System.out.println("-------------------------------");
	}

	// 공지사항 관리
	public void NoticeManagement() {
		int menu;

		do {
			System.out.println("==========메뉴==========");
			System.out.println("1. 공지사항 추가");
			System.out.println("2. 공지사항 삭제");
			System.out.println("3. 공지사항 수정");
			System.out.println("4. 공지사항 리스트");
			System.out.println("0. 뒤로가기");
			System.out.println("=======================");
			System.out.print("메뉴에 해당하는 번호 입력>");

			menu = ScanUtil.nextInt();

			switch (menu) {
			case 1: // 1.공지사항 추가
				noticeInsert();
				break;
			case 2: // 2.공지사항 삭제
				noticeDelete();
				break;
			case 3: // 3.공지사항 수정
				noticeUpdate();
				break;
			case 4: // 4.공지사항 리스트
				noticeList();
				break;
			case 0: // 뒤로가기
				break;
			}
		} while (menu != 0);
	}

	// 공지사항 추가
	public void noticeInsert() {
		System.out.print("공지사항 제목 입력 > ");
		String noticeTitle = ScanUtil.nextLine();
		System.out.print("공지사항 내용 입력 > ");
		String noticeContent = ScanUtil.nextLine();
		Date noticeDate = new Date();

		HashMap<String, Object> param = new HashMap<>();

		param.put("NOTICENO", noticeNo);

		NoticeVO noticeVo = noticeService.noticeSelect(param);

		if (noticeVo == null) {
			noticeService.noticeInsert(noticeNo++, noticeTitle, noticeContent,
					noticeDate);
			System.out.println("공지사항을 추가하였습니다.");
		} else {
			System.out.println("공지사항을 추가할 수 없습니다.");
		}
	}

	// 공지사항 수정
	public void noticeUpdate() {
		int menu;
		noticeList();
		System.out.println("수정할 공지사항 번호 입력 > ");
		int noticeNumber = ScanUtil.nextInt();

		HashMap<String, Object> param = new HashMap<>();
		param.put("NOTICENO", noticeNumber);

		NoticeVO noticeVo = noticeService.noticeSelect(param);
		int noticeIndex = noticeService.indexNotice(param);

		if (noticeIndex != -1 && noticeVo != null) {
			System.out.println("무엇을 수정할까요?");
			System.out.println("1.제목 2.내용 ");
			menu = ScanUtil.nextInt();

			noticeVo = noticeSetMenu(noticeVo, menu);

			noticeService.noticeUpdate(noticeVo, noticeIndex);
			System.out.println("공지사항을 수정했습니다.");
		} else {
			System.out.println("공지사항을 수정할 수 없습니다.");
		}
	}

	// 공지사항 삭제
	public void noticeDelete() {
		noticeList();
		System.out.println("삭제할 공지사항 번호 입력 > ");
		int noticeNumber = ScanUtil.nextInt();

		HashMap<String, Object> param = new HashMap<>();
		param.put("NOTICENO", noticeNumber);

		int noticeIndex = noticeService.indexNotice(param);

		if (noticeIndex == -1) {
			System.out.println("공지사항을 삭제할 수 없습니다");
		} else {
			noticeService.noticeDelete(noticeIndex);
			System.out.println("공지사항을 삭제헀습니다.");
		}
	}

	// 공지사항 설정
	public NoticeVO noticeSetMenu(NoticeVO noticeVo, int menu) {
		Date day = new Date();
		if (menu == 1) {
			System.out.print("공지사항 제목 입력 > ");
			String noticeTitle = ScanUtil.nextLine();
			noticeVo.setNoticeTitle(noticeTitle);
		} else if (menu == 2) {
			System.out.print("공지사항 내용 입력 > ");
			String noticeContent = ScanUtil.nextLine();
			noticeVo.setNoticeContent(noticeContent);
		}
		noticeVo.setNoticeDate(day);
		return noticeVo;
	}

	public void noticeList() {
		ArrayList<NoticeVO> noticeList = noticeService.noticeSelectList();
		System.out.println("==========공지사항 리스트==========");
		System.out.println("공지사항번호\t제목\t내용\t작성일");
		System.out.println("==============================");

		for (int i = 0; i < noticeList.size(); i++) {
			NoticeVO noticeVo = noticeList.get(i);
			System.out.println(noticeVo.getPk_noticeNo() + "\t"
					+ noticeVo.getNoticeTitle() + "\t"
					+ noticeVo.getNoticeContent() + "\t"
					+ DateData.dateFormat(noticeVo.getNoticeDate()));
		}
		System.out.println("==============================");
	}

	public void QnAManagement() {
		int menu;

		do {
			System.out.println("==========메뉴==========");
			System.out.println("1. 질문리스트");
			System.out.println("2. 질문삭제");
			System.out.println("3. 답글달기");
			System.out.println("4. 답글삭제");
			System.out.println("0. 뒤로가기");
			System.out.println("=======================");
			System.out.print("메뉴에 해당하는 번호 입력>");

			menu = ScanUtil.nextInt();

			switch (menu) {
			case 1: // 1.질문 리스트
				questionList();
				break;
			case 2: // 2.질문삭제
				questionDelete();
				break;
			case 3: // 3.답글달기
				answerInsert();
			case 4: // 4. 답글삭제
				answerDelete();
			case 0: // 뒤로가기
				break;
			}
		} while (menu != 0);
	}

	// 전체 질문리스트
	public void questionList() {
		ArrayList<QuestionVO> questionList = questionService
				.questionSelectList();
		System.out.println("============질문 리스트============");
		System.out.println("질문번호\t제목\t내용\t작성자\t작성일");
		System.out.println("===============================");

		for (int i = 0; i < questionList.size(); i++) {
			QuestionVO quetionVo = questionList.get(i);
			System.out.println(quetionVo.getPk_questionNo() + "\t"
					+ quetionVo.getQuestionTitle() + "\t"
					+ quetionVo.getQuestionContent() + "\t"
					+ quetionVo.getFk_userID() + "\t"
					+ DateData.dateFormat(quetionVo.getQuestionDate()));
		}
		System.out.println("===============================");
	}

	// 질문삭제
	public void questionDelete() {
		questionList();
		System.out.println("삭제할 질문 번호 입력 > ");
		int questionNumber = ScanUtil.nextInt();

		HashMap<String, Object> param = new HashMap<>();
		param.put("QUESTIONENO", questionNumber);

		AnswerVO answerVo = answerService.selectAnswer(param);
		int questionIndex = questionService.indexQuestion(param);

		if (questionIndex == -1 || answerVo.getFk_questionNo() != 0) {
			System.out.println("질문을 삭제할 수 없습니다");
		} else {
			questionService.questionDelete(questionIndex);
			System.out.println("질문을 삭제헀습니다.");
		}
	}

	// 답변 달기
	public void answerInsert() {
		Date day = new Date();
		questionList();
		System.out.println("답변을 남길 질문 번호를 입력 > ");
		int questionNumber = ScanUtil.nextInt();

		HashMap<String, Object> param = new HashMap<>();
		param.put("QUESTIONNO", questionNumber);
		QuestionVO questionVo = questionService.selectQuestion(param);

		System.out.println("답변을 입력하세요 > ");
		String answerContent = ScanUtil.nextLine();

		answerService.insertAnswer(answerNo, answerContent, day,
				questionVo.getPk_questionNo());
	}

	public void answerDelete() {
		answerList();
		System.out.println("삭제할 답변의 번호를 입력 > ");
		int answerNumber = ScanUtil.nextInt();

		int answerIndex = answerService.answerIndexSearch(answerNumber);
		if (answerIndex == -1) {
			System.out.println("답글을 삭제할 수 없습니다.");
		} else {
			answerService.deleteAnswer(answerIndex);
			System.out.println("답글을 삭제했습니다");
		}

	}

	public void answerList() {
		ArrayList<AnswerVO> answerList = answerService.answerSelectList();
		System.out.println("============답변 리스트============");
		System.out.println("답변번호\t내용\t작성일");
		System.out.println("===============================");

		for (int i = 0; i < answerList.size(); i++) {
			AnswerVO asnwerVo = answerList.get(i);
			System.out.println(asnwerVo.getPk_answerNo() + "\t"
					+ asnwerVo.getAnswerContent() + "\t"
					+ DateData.dateFormat(asnwerVo.getAnswerDate()));
		}
		System.out.println("===============================");
	}
}