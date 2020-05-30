package controller;

import java.util.*;

import service.*;
import vo.*;
import data.*;

public class UserController {
	private static UserController instance;

	private UserController() {
	}

	public static UserController getInstance() {
		if (instance == null) {
			instance = new UserController();
		}
		return instance;
	}

	String name = "admin";
	String bank = "하나은행";
	String account = "64891031258207";
	int userPromotionNo = 10;
	int musicListNo = 10;
	int musicComment = 10;
	int volume = 0;
	int questioncount = 10;
	int selectMusic = 0;
	final int MIN_VOLUME = 0;
	final int MAX_VOLUME = 20;
	int musicNum = 0;
	String state = "▶";

	String nowPlaySong = "";

	UserService userService = UserService.getInstance();
	UserPromotionService userPromotionService = UserPromotionService
			.getInstance();
	AdminService adminService = AdminService.getInstance();
	MusicService musicService = MusicService.getInstance();
	MusicListService musicListService = MusicListService.getInstance();
	NoticeService noticeService = NoticeService.getInstance();
	PromotionService promotionService = PromotionService.getInstance();
	QuestionService questionService = QuestionService.getInstance();
	AnswerService answerService = AnswerService.getInstance();
	UserMusicCommentService userMusicCommentService = UserMusicCommentService
			.getInstance();

	public void userStart() {
		String menu = "0";
		nowPlaySet();

		do {
			System.out
					.println("\t\t _______________________________________________________________________________");
			System.out.println("\t\t|\t\t\t\t\t\t\t\t\t\t|");
			System.out.println("\t\t|\t\t\t\t\tMELON\t\t\t\t\t|");
			System.out.println("\t\t|\t\t\t\t\t\t\t\t\t\t|");
			showNotice();
			System.out
					.println("\t\t|_______________________________________________________________________________|");
			System.out.println("\t\t|\t\t\t\t\t\t\t\t\t\t|");
			System.out.println("\t\t|\t\t\t\t\tTOP 5\t\t\t\t\t|");
			System.out
					.println("\t\t|_______________________________________________________________________________|");
			showRealfiveMuiscchat();
			System.out.println("\t\t|\t\t\t\t\t\t\t\t\t\t|");
			System.out.print("\t\t|\t");
			System.out.println("\t\t\t\t" + nowPlaySong);
			System.out
					.println("\t\t|_______________________________________________________________________________|");
			System.out.println("\t\t|\t\t\t\t\t\t\t\t\t\t|");
			System.out.println("\t\t|\t\t\t\t|<\t" + state + "\t>|\t\t\t\t|");
			System.out.println("\t\t|\t\t\t\t\t\t\t\t\t\t|");
			System.out
					.println("\t\t|===================================== 메뉴  =====================================|");
			System.out
					.println("\t\t|     1.실시간순위\t  2.재생목록\t3.검색\t\t4.프로모션\t\t5.내정보\t\t|\n\t\t|     6.공지사항\t  7.QnA게시판\t8.볼륨증가\t\t9.볼륨다운\t\t0.로그아웃\t\t|");
			System.out
					.println("\t\t|===============================================================================|");
			System.out.print("메뉴에 해당하는 번호 입력 > ");
			menu = ScanUtil.nextLine();

			switch (menu) {
			case "▶": // 재생
				this.state = "||";
				play(menu); // 카운터 증가
				break;
			case "||": // 정지
				this.state = "▶";
				break;
			case "|<":// 이전곡
				previousSong();
				break;
			case ">|":// 다음곡
				nextSong();
				break;
			case "1": // 실시간 순위
				realTimeRankMusicchat();
				break;
			case "2": // 재생목록
				playList();
				break;
			case "3": // 검색
				search();
				break;
			case "4": // 프로모션
				promotion();
				break;
			case "5": // 내 정보
				mypage();
				break;
			case "6": // 공지사항
				notice();
				break;
			case "7": // QnA게시판
				qnaBoard();
				break;
			case "8": // 볼륨증가
				int vol = 8;
				showVolume(vol);
				break;
			case "9": // 볼륨다운
				vol = 9;
				showVolume(9);
				break;
			case "0": // 로그아웃
				userService.logout();
				break;
			}
		} while (!menu.equals("0"));
	}

	public void nowPlaySet() {
		ArrayList<MusicListVO> musicList = musicListService
				.selectAllMusicList();
		ArrayList<MusicVO> music = musicService.selectMusicList();

		int nowPlayCount = 0;
		int index = 0;
		boolean flag = false;
		MusicVO rMusicVo = null;
		for (int i = 0; i < musicList.size(); i++) {
			MusicListVO musicListVo = musicList.get(i);
			if (flag) {
				break;
			}
			for (int j = 0; j < music.size(); j++) {
				MusicVO musicVo = music.get(j);
				if (Session.LoginUser.getPk_userID().equals(
						musicListVo.getFk_userID())
						&& musicVo.getPk_musicNo() == musicListVo
								.getFk_musicNo()) {

					if (nowPlayCount == musicNum) {
						rMusicVo = musicVo;
						index = j;
						this.nowPlaySong = musicVo.getSingerName() + " - "
								+ musicVo.getMusicTitle();
						flag = true;
						break;
					}
					nowPlayCount++;
				}
			}
		}
		index = musicService.indexMusic(rMusicVo.getPk_musicNo());
		rMusicVo.setStreamingCount(rMusicVo.getStreamingCount() + 1);
		musicService.updateMusic(rMusicVo, index);
	}

	// 볼륨 보여주기(0527수정)
	public void showVolume(int menu) {
		ArrayList<String> volumeDraw = new ArrayList<>();
		System.out.print("볼륨 : ");
		if (menu == 0) {
			volumeDraw = volumeDraw();
			for (int i = 0; i < volumeDraw.size(); i++) {
				System.out.print(volumeDraw.get(i) + " ");
			}
		} else if (menu == 8) {
			volume += 1;

			if (volume >= MAX_VOLUME) {
				volume = MAX_VOLUME;
			}
			volumeDraw = volumeDraw();

			for (int i = 0; i < volumeDraw.size(); i++) {
				System.out.print(volumeDraw.get(i) + " ");
			}
		} else if (menu == 9) {
			volume -= 1;

			if (volume <= MIN_VOLUME) {
				volume = MIN_VOLUME;
			}

			volumeDraw = volumeDraw();
			for (int i = 0; i < volumeDraw.size(); i++) {
				System.out.print(volumeDraw.get(i) + " ");
			}
		}
	}

	// 볼륨 그리기(0527 수정)
	public ArrayList<String> volumeDraw() {
		ArrayList<String> volumeDraw = new ArrayList<>();

		for (int i = 0; i < MAX_VOLUME; i++) {
			if (volume > i) {
				volumeDraw.add("■");
			} else {
				volumeDraw.add("□");
			}
		}
		return volumeDraw;
	}

	// 공지사항 보여주기
	public void showNotice() {

		ArrayList<NoticeVO> noticeList = noticeService.noticeSelectList();
		System.out.printf("\t\t|\t\t\t\t%-30s\t\t\t\t|\n",
				noticeList.get(noticeList.size() - 1).getNoticeTitle());
	}

	// 실시간 순위
	public void realTimeRankMusicchat() {
		String menu = "0";
		do {
			System.out
					.println("\t\t _______________________________________________________________________________");
			System.out.println("\t\t|\t\t\t\t\t\t\t\t\t\t|");
			System.out.println("\t\t|\t\t\t\t     MELON\t\t\t\t\t|");
			System.out.println("\t\t|\t\t\t\t\t\t\t\t\t\t|");
			System.out.println("\t\t|\t\t\t\t   -실시간 순위-   \t\t\t\t|");
			System.out.println("\t\t|\t\t\t\t     Top 20\t\t\t\t\t|");
			System.out
					.println("\t\t|_______________________________________________________________________________|");
			// 실시간 순위 차트 출력
			showRealTimeRankMuiscchat();
			System.out.println("\t\t|\t\t\t\t\t\t\t\t\t\t|");
			System.out.print("\t\t|");
			System.out
					.print("_______________________________________________________________________________");
			System.out.println("|\t\t\t\t\t\t\t\t\t\t|");
			System.out.println("\t\t\t\t" + nowPlaySong);
			System.out.println("\t\t|\t\t\t\t|<\t" + state + "\t>|\t\t\t\t|");
			System.out.println("\t\t|\t\t\t\t\t\t\t\t\t\t|");
			System.out
					.println("\t\t|===================================== 메뉴  =====================================|");
			System.out.println("\t\t|\t\t\t1. 노래담기 \t\t 0. 뒤로가기 \t\t\t|");
			System.out
					.println("\t\t|===============================================================================|");

			System.out.print("메뉴에 해당하는 번호 입력 > ");
			menu = ScanUtil.nextLine();

			switch (menu) {
			case "▶": // 재생
				this.state = "||";
				play(menu); // 카운터 증가
				break;
			case "||": // 정지
				this.state = "▶";
				break;
			case "|<":// 이전곡
				if (state.equals("▶")) {
					this.state = "||";
				}
				previousSong();
				break;
			case ">|":// 다음곡
				if (state.equals("▶")) {
					this.state = "||";
				}
				nextSong();
				break;
			case "1": // 노래 담기
				musicInsert();
				break;
			case "0": // 뒤로가기
				break;
			}
		} while (!menu.equals("0"));
	}

	// 음악 -> 음악리스트에 담기
	public void musicInsert() {
		System.out
				.println("\t\t _______________________________________________________________________________");
		System.out.println("\t\t|\t\t\t\t\t\t\t\t\t\t|");
		System.out.println("\t\t|\t\t\t\t     MELON\t\t\t\t\t|");
		System.out.println("\t\t|\t\t\t\t\t\t\t\t\t\t|");
		System.out.println("\t\t|\t\t\t\t   -노래 담기-   \t\t\t\t\t|");
		System.out
				.println("\t\t|_______________________________________________________________________________|");
		showRealTimeRankMuiscchat();

		System.out.print("재생목록에 담을 음악 번호 입력 > ");
		int musicNo = ScanUtil.nextInt();

		if (musicNo == 0) {
			System.out.println("잘못된 음악 번호입니다.");
			return;
		}

		ArrayList<MusicVO> musicList = musicService.sortList();
		MusicVO rMusicVo = null;
		for (int i = 0; i < musicList.size(); i++) {
			MusicVO musicVo = musicList.get(i);
			if (musicNo == i + 1) {
				rMusicVo = musicVo;
				break;
			}
		}
		

		int index = musicService.indexMusic(rMusicVo.getPk_musicNo());

		musicListService.insertMusicList(rMusicVo.getPk_musicNo(),
				Session.LoginUser.getPk_userID());
		System.out.println("\t\t\t\t\t재생목록에 음악을 추가했습니다.");

		rMusicVo.setStreamingCount(rMusicVo.getStreamingCount() + 1);
		musicService.updateMusic(rMusicVo, index);
		
		ArrayList<MusicListVO> musicL = musicListService
				.selectAllMusicList();
		ArrayList<MusicVO> music = musicService.selectMusicList();

		int count = 0;

		for (int i = 0; i < musicL.size(); i++) {
			MusicListVO musicListVo = musicL.get(i);
			if (Session.LoginUser.getPk_userID().equals(
					musicListVo.getFk_userID())) {
				count++;
			}
		}
		this.musicNum = count - 1;
		
		boolean flag = false;

		for (int i = 0; i < musicL.size(); i++) {
			MusicListVO musicListVo = musicL.get(i);
			if (flag) {
				break;
			}
			for (int j = 0; j < music.size(); j++) {
				MusicVO musicVo = music.get(j);
				if (Session.LoginUser.getPk_userID().equals(
						musicListVo.getFk_userID())
						&& rMusicVo.getPk_musicNo() == musicVo.getPk_musicNo()) {
					System.out.println(rMusicVo.getPk_musicNo() + rMusicVo.getMusicTitle());
						if(state.equals("▶")) {
							state = "||";
						}
						this.nowPlaySong = rMusicVo.getSingerName() + " - "
								+ rMusicVo.getMusicTitle();
						flag = true;
						break;
					
				}
			}
		}

		System.out.println("\t\t\t\t\t음악을 재생합니다.");
		System.out.println(rMusicVo.getSingerName() + " - "
				+ rMusicVo.getMusicTitle());
	}

	// 실시간20순위차트
	public void showRealTimeRankMuiscchat() {
		ArrayList<MusicVO> musicList = musicService.sortList();
		System.out.println("\t\t|\t\t\t\t\t\t\t\t\t\t|");
		System.out.println("\t\t|\t순위\t\t음악제목\t\t\t\t가수\t\t\t|");
		System.out
				.println("\t\t|_______________________________________________________________________________|");
		for (int i = 0; i < 20; i++) {
			MusicVO musicVo = musicList.get(i);
			int num = i + 1;
			System.out.printf("\t\t|\t%2d\t%20s\t\t%15s\t\t\t|%n", num,
					musicVo.getMusicTitle(), musicVo.getSingerName());
		}
		System.out
				.println("\t\t|_______________________________________________________________________________|");
	}

	public void showRealfiveMuiscchat() {
		ArrayList<MusicVO> musicList = musicService.sortList();
		System.out.println("\t\t|\t\t\t\t\t\t\t\t\t\t|");
		System.out.println("\t\t|\t순위\t\t음악제목\t\t\t\t가수\t\t\t|");
		System.out
				.println("\t\t|_______________________________________________________________________________|");
		for (int i = 0; i < 5; i++) {
			MusicVO musicVo = musicList.get(i);
			int num = i + 1;
			System.out.printf("\t\t|\t%2d\t%20s\t\t%15s\t\t\t|%n", num,
					musicVo.getMusicTitle(), musicVo.getSingerName());
		}
		System.out
				.println("\t\t|_______________________________________________________________________________|");
	}

	// qna 게시판 글쓰기
	public void qnaBoard() {
		System.out
				.println("\t\t _______________________________________________________________________________");
		System.out.println("\t\t|\t\t\t\t\t\t\t\t\t\t|");
		System.out.println("\t\t|\t\t\t\t     MELON\t\t\t\t\t|");
		System.out.println("\t\t|\t\t\t\t\t\t\t\t\t\t|");
		System.out.println("\t\t|\t\t\t\t   -QnA 달기-   \t\t\t\t\t|");
		System.out
				.println("\t\t|_______________________________________________________________________________|");
		System.out.print("\t\t\t\t\t제목을 입력해주세요>");
		String title = ScanUtil.nextLine();
		System.out.print("\t\t\t\t\t내용을 입력해주세요>");
		String contents = ScanUtil.nextLine();
		Date day = new Date();

		questionService.InsertQuestion(questioncount++, title, contents, day,
				Session.LoginUser.getPk_userID());

		System.out.println("\t\t\t\tQnA가 추가되었니다. 답변은 2~3일 소요됩니다.");
	}

	// 공지사항
	public void notice() {
		int menu;

		do {
			System.out
					.println("\t\t _______________________________________________________________________________");
			System.out.println("\t\t|\t\t\t\t\t\t\t\t\t\t|");
			System.out.println("\t\t|\t\t\t\t     MELON\t\t\t\t\t|");
			System.out.println("\t\t|\t\t\t\t\t\t\t\t\t\t|");
			System.out.println("\t\t|\t\t\t\t   -공지 사항-   \t\t\t\t\t|");
			System.out
					.println("\t\t|_______________________________________________________________________________|");
			System.out
					.println("\t\t|===============================================================================|");
			System.out.println("\t\t|\t번호\t\t제목\t\t\t작성일\t\t\t\t|");
			System.out
					.println("\t\t|===============================================================================|");
			noticeList();
			System.out
					.println("\t\t|===================================== 메뉴  =====================================|");
			System.out.println("\t\t|\t\t\t1. 공지보기 \t\t 0. 뒤로가기 \t\t\t|");
			System.out
					.println("\t\t|===============================================================================|");
			System.out.print("번호를 입력하세요>");
			menu = ScanUtil.nextInt();

			switch (menu) {
			case 1:
				noticeselect();
				break;
			case 0:
				break;
			}
		} while (menu != 0);
	}

	// 공지사항 보기
	private void noticeselect() {
		ArrayList<NoticeVO> noticeList = noticeService.noticeSelectList();
		System.out.print("열람하실 번호를 입력해주세요>");
		int noticeNo = ScanUtil.nextInt();
		for (int i = 0; i < noticeList.size(); i++) {
			NoticeVO notice = noticeList.get(i);
			if (notice.getPk_noticeNo() == noticeNo) {
				System.out
						.println("\t\t _______________________________________________________________________________");
				System.out.println("\t\t|\t\t\t\t\t\t\t\t\t\t|");
				System.out.println("\t\t|\t\t\t\t     MELON\t\t\t\t\t|");
				System.out.println("\t\t|\t\t\t\t\t\t\t\t\t\t|");
				System.out.println("\t\t|\t\t\t\t   -공지 사항-   \t\t\t\t\t|");
				System.out
						.println("\t\t|_______________________________________________________________________________|");
				System.out
						.println("\t\t|===============================================================================|");
				System.out.println("\t\t\t번호 : " + notice.getPk_noticeNo()
						+ "\t작성일 : "
						+ DateFormat.dateFormat(notice.getNoticeDate()));
				System.out.println("\t\t\t제목 : " + notice.getNoticeTitle());
				System.out.println("\t\t\t내용 : " + notice.getNoticeContent());
				System.out
						.println("\t\t|===============================================================================|");
				System.out.println();
			}
		}
		System.out.println("\t\t1. 돌아가기");
		System.out.print("번호를 입력해주세요.>");
		int prev = ScanUtil.nextInt();
		switch (prev) {
		case 1:
			break;
		}

	}

	// 공지사항 리스트
	public void noticeList() {
		ArrayList<NoticeVO> noticeList = noticeService.noticeSelectList();

		for (int i = 0; i < noticeList.size(); i++) {
			NoticeVO nn = noticeList.get(i);
			System.out.println("\t\t\t" + nn.getPk_noticeNo() + "\t\t"
					+ nn.getNoticeTitle() + "\t\t\t" + nn.getNoticeDate());
		}

	}

	// 내정보 확인
	public void mypage() {
		int menu;
		int qnaSum = questionService.haveQnANum();

		do {
			if (Session.LoginUser == null) {
				break;
			}

			showMyPage(qnaSum);
			System.out
			.println("\t\t|\t\t1. 변경하기\t2. QnA확인하기\t3. 회원탈퇴 \t4. 뒤로가기\t|");
			System.out.println("\t\t|_______________________________________________________________________________|");

			System.out.print("해당번호를 입력하시오.>");
			menu = ScanUtil.nextInt();

			switch (menu) {
			case 1: // 변경하기
				mypageUpdate();
				break;
			case 2: // QnA확인하기
				qnaConfirm();
				break;
			case 3: // 회원 탈퇴
				userWithdraw();
				break;
			case 4: // 뒤로가기
				break;
			}
		} while (menu != 4);
	}

	public void showMyPage(int qnaSum) {
		UserPromotionVO userPromotionVo = userPromotionService
				.selectUserPromotion(Session.LoginUser.getFk_userPromotionNo());
		PromotionVO promotionVo = new PromotionVO();
		if (userPromotionVo != null) {
			promotionVo = promotionService.selectOnePromotion(userPromotionVo
					.getFk_promotionNo());
		} else {
			promotionVo.setPromotionName("존재하지 않습니다.");
		}
		System.out
				.println("\t\t _______________________________________________________________________________");
		System.out.println("\t\t|\t\t\t\t\t\t\t\t\t\t|");
		System.out.println("\t\t|\t\t\t\t     MELON\t\t\t\t\t|");
		System.out.println("\t\t|\t\t\t\t\t\t\t\t\t\t|");
		System.out.println("\t\t|\t\t\t\t   -회원 정보-   \t\t\t\t\t|");
		System.out
				.println("\t\t|_______________________________________________________________________________|");
		System.out
				.println("\t\t|===============================================================================|");
		System.out.println("\t\t|\t\t\t\t아    이    디 : "
				+ Session.LoginUser.getPk_userID() + "\t\t\t\t\t|");
		System.out.println("\t\t|\t\t\t\t비 밀   번 호 : **********\t\t\t\t|");
		System.out.println("\t\t|\t\t\t\t이           름 : "
				+ Session.LoginUser.getUserName() + "\t\t\t\t\t|");
		System.out.println("\t\t|\t\t\t\t핸드폰  번호 : "
				+ Session.LoginUser.getUserPhone() + "\t\t\t\t\t|");
		if (promotionVo == null) {
			System.out.println("\t\t|\t\t\t\t프 로   모 션 : "
					+ "존재하지않습니다.\t\t\t\t|");
			System.out.println("\t\t|\t\t\t\tQ  n  A  : " + qnaSum + ("개 ")
					+ "\t\t\t\t\t|");
			System.out
					.println("\t\t|===============================================================================|");
			System.out.println("\t\t|\t\t\t\t\t\t\t\t\t\t|");
			System.out
					.println("\t\t|\t\t1. 변경하기\t2. QnA확인하기\t3. 회원탈퇴 \t4. 뒤로가기\t|");
			System.out.println("\t\t|\t\t\t\t\t\t\t\t\t\t|");
		} else {
			System.out.println("\t\t|\t\t\t\t프 로   모 션 : "
					+ promotionVo.getPromotionName() + "\t\t\t\t|");
			System.out.println("\t\t|\t\t\t\tQ  n  A  : " + qnaSum + ("개 ")
					+ "\t\t\t\t\t|");
			System.out
					.println("\t\t|===============================================================================|");
			System.out.println("\t\t|\t\t\t\t\t\t\t\t\t\t|");
		}
	}

	// 정보 변경
	public void mypageUpdate() {
		int menu;
		int updateIndex = userService.indexUser(Session.LoginUser
				.getPk_userID());
		UserVO userVo = userService.selectOneUser(Session.LoginUser
				.getPk_userID());

		do {
			System.out
					.println("\t\t _______________________________ 회원정보 변경하기 ___________________________________");
			System.out.println("\t\t|\t\t\t\t\t\t\t\t\t\t|");
			System.out
					.println("\t\t|\t\t1. 비밀번호\t2. 이름\t3. 핸드폰번호\t0. 수정완료\t\t|");
			System.out.println("\t\t|\t\t\t\t\t\t\t\t\t\t|");
			System.out
					.println("\t\t|_______________________________________________________________________________|");

			System.out.print("수정할 번호 입력 > ");
			menu = ScanUtil.nextInt();
			if (menu == 0) {
				break;
			}
			System.out.println("\t\t|\t\t\t\t\t\t\t\t\t\t|");
			System.out.print("\t\t\t\t● 현재 비밀번호를 입력하시오> ");
			String pw = ScanUtil.nextLine();
			System.out.println("\t\t|\t\t\t\t\t\t\t\t\t\t|");
			if (Session.LoginUser.getUserPW().equals(pw)) {
				userVo = userSetMenu(userVo, menu);

				System.out.println("\t\t|\t\t● 변경이 완료 되었습니다 ●\t\t\t\t\t\t|");
				System.out.println("\t\t|\t\t\t\t\t\t\t\t\t\t|");
				Session.LoginUser = userVo;

			} else {
				System.out.println("\t\t|\t\t\t● 비밀번호를 잘 못 입력하셨습니다 ●\t\t\t|");
				System.out.println("\t\t|\t\t\t\t\t\t\t\t\t\t|");
				break;

			}
		} while (menu != 0);
	}

	// 고객정보 수정하여 수정한 정보 리턴
	public UserVO userSetMenu(UserVO userVo, int menu) {
		if (menu == 1) {

			System.out.print("\t\t\t\t● 변경하려는 비밀번호를  입력하시오 > ");
			String pw = ScanUtil.nextLine();
			System.out.println("\t\t|\t\t\t\t\t\t\t\t\t\t|");
			userVo.setUserPW(pw);
		} else if (menu == 2) {

			System.out.print("\t\t\t\t● 변경하려는 이름을  입력하시오 > ");
			String name = ScanUtil.nextLine();
			System.out.println("\t\t|\t\t\t\t\t\t\t\t\t\t|");
			userVo.setUserName(name);
		} else if (menu == 3) {

			System.out.print("\t\t\t\t● 변경하려는 전화번호를  입력하시오 > ");
			String phone = ScanUtil.nextLine();
			System.out.println("\t\t|\t\t\t\t\t\t\t\t\t\t|");
			userVo.setUserPhone(phone);
		}
		return userVo;
	}

	// qna 확인
	public void qnaConfirm() {
		int menu;
		do {
			System.out
					.println("\t\t _______________________________________________________________________________");
			System.out.println("\t\t|\t\t\t\t\t\t\t\t\t\t|");
			System.out.println("\t\t|\t\t\t\t     MELON\t\t\t\t\t|");
			System.out.println("\t\t|\t\t\t\t\t\t\t\t\t\t|");
			System.out.println("\t\t|\t\t\t\t   -QnA 확인-   \t\t\t\t\t|");
			System.out
					.println("\t\t|_______________________________________________________________________________|");
			qnaList();
			System.out.print("열람번호를 입력하시오. (뒤로가기 0)>");
			menu = ScanUtil.nextInt();
			if (menu != 0) {
				qnaConfirm2(menu);
			}
		} while (menu != 0);
	}

	// qna확인하기2
	public void qnaConfirm2(int menu) {
		ArrayList<QuestionVO> questionList = questionService
				.questionSelectList();
		ArrayList<AnswerVO> answerList = answerService.answerSelectList();

		int quetionNumber = 0;
		Date questionDay = null;
		String quetionTitle = " ";
		String quetionContent = " ";
		int answerNumber = 0;
		Date answerDay = null;
		String answerContent = " ";
		int fk_quetionNumber = 0;

		for (int i = 0; i < questionList.size(); i++) {
			QuestionVO question = questionList.get(i);

			if (question.getPk_questionNo() == menu
					&& question.getFk_userID().equals(
							Session.LoginUser.getPk_userID())) {

				quetionNumber = question.getPk_questionNo();
				questionDay = question.getQuestionDate();
				quetionTitle = question.getQuestionTitle();
				quetionContent = question.getQuestionContent();

			}

			for (int j = 0; j < answerList.size(); j++) {
				AnswerVO answer = answerList.get(j);
				if (question.getPk_questionNo() == answer.getFk_questionNo()
						&& answer.getFk_questionNo() == menu) {
					fk_quetionNumber = answer.getFk_questionNo();
					answerNumber = answer.getPk_answerNo();
					answerDay = answer.getAnswerDate();
					answerContent = answer.getAnswerContent();

				}
			}
		}
		System.out
				.println("\t\t _______________________________________________________________________________");
		System.out.println("\t\t|\t\t\t\t\t\t\t\t\t\t|");
		System.out.println("\t\t|\t\t\t\t     MELON\t\t\t\t\t|");
		System.out.println("\t\t|\t\t\t\t\t\t\t\t\t\t|");
		System.out.println("\t\t|\t\t\t\t   -QnA 확인-   \t\t\t\t\t|");
		System.out
				.println("\t\t|_______________________________________________________________________________|");
		System.out
				.println("\t\t|==================================== Q n A ====================================|");
		System.out
				.println("\t\t|_______________________________________________________________________________|");
		System.out.println("\t\t|\t번호 : " + quetionNumber + "\t\t\t\t작성일 : "
				+ DateFormat.dateFormat(questionDay) + "\t\t\t|");
		System.out.println("\t\t|\t제목 : " + quetionTitle + "\t\t\t\t\t\t\t\t|");
		System.out.println("\t\t|\t내용 : " + quetionContent + "\t\t\t\t\t\t\t|");
		System.out
				.println("\t\t|_______________________________________________________________________________|");
		if (quetionNumber == fk_quetionNumber) {
			System.out
					.println("\t\t|=================================== ANS WER ===================================|");
			System.out
					.println("\t\t|_______________________________________________________________________________|");
			System.out.println("\t\t|\t번호 : " + answerNumber + "\t\t\t\t작성일 : "
					+ DateFormat.dateFormat(answerDay) + "\t\t\t|");
			System.out.println("\t\t|\t내용 : " + answerContent
					+ "\t\t\t\t\t\t\t|");
			System.out
					.println("\t\t|_______________________________________________________________________________|");
		}
		System.out.print("뒤로가기 0>");
		int back = ScanUtil.nextInt();
	}

	// 회원탈퇴
	public void userWithdraw() {
		int userIndex = userService.indexUser(Session.LoginUser.getPk_userID());

		System.out
				.println("\t\t _______________________________________________________________________________");
		System.out.println("\t\t|\t\t\t\t\t\t\t\t\t\t|");
		System.out.println("\t\t|\t\t\t\t     MELON\t\t\t\t\t|");
		System.out.println("\t\t|\t\t\t\t\t\t\t\t\t\t|");
		System.out.println("\t\t|\t\t\t\t   -회원 탈퇴-   \t\t\t\t\t|");
		System.out
				.println("\t\t|_______________________________________________________________________________|");
		System.out.print("\t\t\t\t비밀번호를 입력하세요>");
		String fisrtPW = ScanUtil.nextLine();

		if (Session.LoginUser.getUserPW().equals(fisrtPW)) {
			System.out.print("\t\t\t\t비밀번호를 한번더 입력하세요>");
			String secondPW = ScanUtil.nextLine();

			if (Session.LoginUser.getUserPW().equals(secondPW)) {
				userService.deleteUser(userIndex);
				Session.LoginUser.setPk_userID(null);
				System.out.println("\t\t\t\t\t회원이 탈퇴 되었습니다.");
			} else {
				System.out.println("\t\t\t\t\t비밀번호를 확인하십시오.");
			}
		} else {
			System.out.println("\t\t\t\t\t비밀번호를 확인하십시오.");
		}
	}

	// qna리스트
	public void qnaList() {
		ArrayList<QuestionVO> questionList = questionService
				.questionSelectList();
		ArrayList<AnswerVO> answerList = answerService.answerSelectList();
		String condition = " ";

		System.out.println("\t\t|\t번호\t\t제목\t\t\t상태\t\t\t\t|");
		System.out
				.println("\t\t|_______________________________________________________________________________|");

		for (int i = 0; i < questionList.size(); i++) {
			QuestionVO question = questionList.get(i);
			for (int j = 0; j < answerList.size(); j++) {
				AnswerVO answerVo = answerList.get(j);
				if (question.getPk_questionNo() == answerVo.getFk_questionNo()) {
					condition = "답변완료";
				} else {
					condition = "답변전";
				}

			}
			if (question.getFk_userID()
					.equals(Session.LoginUser.getPk_userID())) {
				System.out.println("\t\t|\t" + question.getPk_questionNo()
						+ "\t\t" + question.getQuestionTitle() + "\t\t\t"
						+ condition + "\t\t\t\t|");
			}
		}
		System.out
				.println("\t\t|_______________________________________________________________________________|");
		System.out.println();
	}

	// 프로모션
	public void promotion() {
		int menu;
		do {
			System.out
					.println("\t\t|===================================== 메뉴  =====================================|");
			System.out.println("\t\t|\t\t\t\t\t\t\t\t\t\t|");
			System.out
					.println("\t\t|\t\t1. 구매하기\t2. 확인하기(내 프로모션 정보)\t3. 뒤로가기\t\t|");
			System.out.println("\t\t|\t\t\t\t\t\t\t\t\t\t|");
			System.out
					.println("\t\t|===============================================================================|");
			System.out.print("메뉴에 해당하는 번호 입력 > ");
			menu = ScanUtil.nextInt();
			switch (menu) {
			case 1: // 구매하기
				buyPromotion();
				break;
			case 2: // 확인하기
				myPromotion();
				break;
			case 3: // 뒤로가기
				break;
			}
		} while (menu != 3);

	}

	// 프로모션 구매하기
	public void buyPromotion() {
		promotionList();
		System.out.println("\t\t|\t\t\t\t\t\t\t\t\t\t|");
		System.out.print("\t\t\t\t\t● 구매하고자 하는 프로모션을 골라주세요 > ");
		int promotionNo = ScanUtil.nextInt();
		System.out.println("\t\t|\t\t\t\t\t\t\t\t\t\t|");

		boolean isPromotion = userPromotionService
				.userHavePromotion(Session.LoginUser.getFk_userPromotionNo());
		PromotionVO promotionVo = promotionService
				.selectOnePromotion(promotionNo);

		if (!isPromotion) {
			showPromotion(promotionVo); // 하나의 프로모션 정보
			buyMethod(promotionVo); // 결제 수단 메소드
		} else {
			System.out.println("프로모션을 보유하고 있습니다.");
			return;
		}

	}

	// 프로모션 리스트
	public void promotionList() {
		ArrayList<PromotionVO> promotionList = promotionService
				.selectAllPromotionList();
		System.out
				.println("\t\t|===============================================================================|");
		System.out.println("\t\t|\t\t\t\t\t\t\t\t\t\t|");
		System.out.println("\t\t|\t\t\t● 프 로 모 션\t리 스 트 ●\t\t\t\t\t|");
		System.out.println("\t\t|\t\t\t\t\t\t\t\t\t\t|");
		System.out
				.println("\t\t|===============================================================================|");
		System.out.println("\t\t|\t\t\t\t\t\t\t\t\t\t|");
		System.out.println("\t\t|\t\t번호\t\t  이름\t\t\t가격\t\t\t|");
		System.out.println("\t\t|\t\t\t\t\t\t\t\t\t\t|");
		System.out
				.println("\t\t|_______________________________________________________________________________|");
		System.out.println("\t\t|\t\t\t\t\t\t\t\t\t\t|");
		for (int i = 0; i < promotionList.size(); i++) {
			PromotionVO promotion = promotionList.get(i);
			System.out.println("\t\t|\t\t" + promotion.getPk_promotionNo()
					+ "\t\t" + promotion.getPromotionName() + "\t\t"
					+ promotion.getPromotionPrice() + "\t\t\t|");
		}
		System.out.println("\t\t|\t\t\t\t\t\t\t\t\t\t|");
		System.out
				.println("\t\t|===============================================================================|");
	}

	// 하나의 프로모션 정보 출력
	public void showPromotion(PromotionVO promotionVo) {
		System.out
				.println("\t\t|===============================================================================|");
		System.out.println("\t\t|\t\t\t\t\t\t\t\t\t\t|");
		System.out.println("\t\t|\t\t\t\t프로모션 번호 : "
				+ promotionVo.getPk_promotionNo() + "\t\t\t\t\t|");
		System.out.println("\t\t|\t\t\t\t프로모션 이름 : "
				+ promotionVo.getPromotionName() + "\t\t\t\t|");
		System.out.println("\t\t|\t\t\t\t프로모션 가격 : "
				+ promotionVo.getPromotionPrice() + "\t\t\t\t\t|");
		System.out.println("\t\t|\t\t\t\t\t\t\t\t\t\t|");
		System.out
				.println("\t\t|===============================================================================|");
	}

	// 구매 방법
	public void buyMethod(PromotionVO promotionVo) {
		System.out
				.println("\t\t|_______________________________________________________________________________|");
		System.out.println("\t\t|\t\t\t\t\t\t\t\t\t\t|");
		System.out.println("\t\t|\t\t1.무통장입금\t2.휴대폰\t\t3.카드\t\t4.뒤로가기\t\t|");
		System.out.println("\t\t|\t\t\t\t\t\t\t\t\t\t|");
		System.out
				.println("\t\t|_______________________________________________________________________________|");
		System.out.println("\t\t|\t\t\t\t\t\t\t\t\t\t|");
		System.out.print("\t\t\t\t\t● 결제 방법을 선택해주세요 > ");
		int menu = ScanUtil.nextInt();
		System.out.println("\t\t|\t\t\t\t\t\t\t\t\t\t|");
		switch (menu) {
		case 1:
			deposit(promotionVo);
			break;
		case 2:
			phone(promotionVo);
			break;
		case 3:
			card(promotionVo);
			break;
		case 4:
			break;
		}
	}

	// 무통장 입금
	public void deposit(PromotionVO promotionVo) {
		Date day = new Date();
		String name = "admin";
		String bank = "하나은행";
		String account = "64891031258207";

		boolean isUser = userService.isUser(Session.LoginUser.getPk_userID());

		if (isUser) {

			System.out
					.println("\t\t|================================== 무통장 입금  ===================================|");
			System.out.println("\t\t|\t\t\t\t\t\t\t\t\t\t|");
			System.out
					.println("\t\t|\t\t\t\t예  금  주 : " + name + "\t\t\t\t\t|");
			System.out
					.println("\t\t|\t\t\t\t은  행  명 : " + bank + "\t\t\t\t\t|");
			System.out.println("\t\t|\t\t\t\t계좌 번호 : " + account + "\t\t\t\t|");
			System.out.println("\t\t|\t\t\t\t입  금  액 : "
					+ promotionVo.getPromotionPrice() + "\t\t\t\t\t|");
			System.out.print("\t\t\t\t\t\t입금하시겠습니까 ? ");
			String yesOrNo = ScanUtil.nextLine().toUpperCase();
			System.out.println("\t\t|\t\t\t\t\t\t\t\t\t\t|");
			System.out
					.println("\t\t|===============================================================================|");

			if (yesOrNo.equals("YES")) {
				System.out.println("\t\t|\t\t\t\t\t\t\t\t\t\t|");
				System.out.println("\t\t|\t\t\t\t● 구매완료 되었습니다 ●\t\t\t\t|");
				System.out.println("\t\t|\t\t\t\t감사합니다. 좋은하루 되세욧!\t\t\t\t|");
				System.out.println("\t\t|\t\t\t\t\t\t\t\t\t\t|");
				userPromotionService.userPromotionData(userPromotionNo++, day,
						DateData.promotionDate(),
						promotionVo.getPk_promotionNo(),
						Session.LoginUser.getPk_userID());
			} else {
				System.out.println("\t\t|\t\t\t되돌아갑니다.\t\t\t");
				return;
			}
		}
	}

	// 휴대폰 결제
	public void phone(PromotionVO promotionVo) {
		Date day = new Date();
		System.out.println("\t\t|\t\t\t\t\t\t\t\t\t\t|");
		System.out.print("\t\t\t\t● 연락처를 입력하세요 > ");
		String phone = ScanUtil.nextLine();
		System.out.print("\t\t\t\t● 이름을 입력하세요 > ");
		String name = ScanUtil.nextLine();
		System.out.println("\t\t|\t\t\t\t\t\t\t\t\t\t|");
		boolean isUser = userService.isUser(Session.LoginUser.getPk_userID());

		boolean equalsUser = userService.userEquals(phone, name);

		if (isUser && equalsUser) {
			System.out.print("\t\t\t\t● 결제하시겠습니까? ");
			String yesOrNo = ScanUtil.nextLine().toUpperCase();

			if (yesOrNo.equals("YES")) {
				System.out.println("\t\t|\t\t\t\t● 구매완료 되었습니다 ●\t\t\t\t|");
				System.out.println("\t\t|\t\t\t\t감사합니다. 좋은하루 되세욧!\t\t\t\t|");
				userPromotionService.userPromotionData(userPromotionNo++, day,
						DateData.promotionDate(),
						promotionVo.getPk_promotionNo(),
						Session.LoginUser.getPk_userID());
			} else {
				System.out.println("되돌아갑니다.");
				return;
			}
		}

	}

	// 카드
	public void card(PromotionVO promotionVo) {
		Date day = new Date();
		System.out.println("\t\t|\t\t\t\t\t\t\t\t\t\t|");
		System.out.print("\t\t\t\t  ● 카드번호를 입력하세요(16자리) >");
		String cardNo = ScanUtil.nextLine();
		System.out.print("\t\t\t\t  ● 연락처를 입력하세요 > ");
		String phone = ScanUtil.nextLine();
		System.out.print("\t\t\t\t  ● 이름을 입력하세요 > ");
		String name = ScanUtil.nextLine();
		System.out.println("\t\t|\t\t\t\t\t\t\t\t\t\t|");
		System.out
				.println("\t\t|_______________________________________________________________________________|");
		System.out.println("\t\t|\t\t\t\t\t\t\t\t\t\t|");
		boolean isUser = userService.isUser(Session.LoginUser.getPk_userID());

		boolean equalsUser = userService.userEquals(phone, name);

		if (isUser && equalsUser && (cardNo.length() == 16)) {

			System.out.print("\t\t\t\t  ● 결제하시겠습니까? ");
			String yesOrNo = ScanUtil.nextLine().toUpperCase();

			System.out.println("\t\t|\t\t\t\t\t\t\t\t\t\t|");
			System.out
					.println("\t\t|_______________________________________________________________________________|");

			if (yesOrNo.equals("YES")) {
				System.out.println("\t\t|\t\t\t\t\t\t\t\t\t\t|");
				System.out.println("\t\t|\t\t\t\t● 구매완료 되었습니다 ●\t\t\t\t|");
				System.out.println("\t\t|\t\t\t\t감사합니다. 좋은하루 되세욧!\t\t\t\t|");
				System.out.println("\t\t|\t\t\t\t\t\t\t\t\t\t|");
				userPromotionService.userPromotionData(userPromotionNo++, day,
						DateData.promotionDate(),
						promotionVo.getPk_promotionNo(),
						Session.LoginUser.getPk_userID());
			} else {
				System.out.println("되돌아갑니다.");
				return;
			}
		}
	}

	// 내 프로모션 정보
	public void myPromotion() {
		boolean havePromotion = userPromotionService
				.userHavePromotion(Session.LoginUser.getFk_userPromotionNo());
		UserPromotionVO userPromotionVo = userPromotionService
				.selectUserPromotion(Session.LoginUser.getFk_userPromotionNo());
		
		if(userPromotionVo.getFk_promotionNo() == 0 || havePromotion == false) {
			System.out.println("보유하신 프로모션이 없습니다.");
			return;
		}
		PromotionVO promotionVo = promotionService
				.selectOnePromotion(userPromotionVo.getFk_promotionNo());
		System.out.println(promotionVo.getPromotionName());


		System.out
				.println("=========================== 보유한 프로모션 ===========================");
		System.out.println("프로모션번호\t프로모션명\t\t프로모션가격");
		System.out.println(promotionVo.getPk_promotionNo() + "\t\t"
				+ promotionVo.getPromotionName() + "\t"
				+ promotionVo.getPromotionPrice());
		System.out
				.println("==================================================================");
	}

	// 검색
	public void search() {
		String menu = "0";

		System.out.print("검색을 해주세요 (뒤로가기0)>");
		String search = ScanUtil.nextLine();

		do {
			if (search.equals("0")) {
				break;
			}
			System.out
					.println("\t\t _______________________________________________________________________________");
			System.out.println("\t\t|\t\t\t\t\t\t\t\t\t\t|");
			System.out.println("\t\t|\t\t\t\t\tMELON\t\t\t\t\t|");
			System.out.println("\t\t|\t\t\t\t\t\t\t\t\t\t|");
			System.out.println("\t\t|\t\t\t\t   -검색 결과-   \t\t\t\t\t|");
			System.out
					.println("\t\t|===============================================================================|");
			System.out.println("\t\t|\t번호\t제목\t\t가수명\t\t앨범명\t\t\t\t|");
			System.out
					.println("\t\t|===============================================================================|");

			showSearchMusic(search);

			System.out
					.println("\t\t|===================================== 메뉴  =====================================|");
			System.out
					.println("\t\t|\t\t1. 정보보기 \t2. 노래담기     \t3. 댓글달기\t 0. 뒤로가기\t| ");
			System.out
					.println("\t\t|===============================================================================|");
			System.out.print("메뉴에 해당하는 번호 입력 > ");
			menu = ScanUtil.nextLine();

			switch (menu) {
			case "1": // 정보 보기
				musicInfo(search);
				break;
			case "2": // 노래담기
				musicInsertList(search);
				break;
			case "3": // 댓글달기
				musicComment(search);
				break;
			case "0": // 뒤로가기
				break;
			}

		} while (!menu.equals("0"));

	}

	// 뮤직 댓글달기
	public void musicComment(String search) {
		System.out
				.println("\t\t _______________________________________________________________________________");
		System.out.println("\t\t|\t\t\t\t\t\t\t\t\t\t|");
		System.out.println("\t\t|\t\t\t\t\tMELON\t\t\t\t\t|");
		System.out.println("\t\t|\t\t\t\t\t\t\t\t\t\t|");
		System.out.println("\t\t|\t\t\t\t      -댓글 달기-      \t\t\t\t|");
		System.out
				.println("\t\t|_______________________________________________________________________________|");
		showSearchMusic(search);
		System.out
				.println("\t\t|_______________________________________________________________________________|");
		int musicNo = 0;

		System.out.print("댓글 달고 싶은 음악 번호 입력 > ");
		musicNo = ScanUtil.nextInt();
		boolean flag = false;

		ArrayList<MusicVO> musicList = musicService.selectMusicList();

		for (int i = 0; i < musicList.size(); i++) {
			MusicVO musicVo = musicList.get(i);
			if (musicNo == musicVo.getPk_musicNo()) {
				flag = true;
				break;
			}
		}

		if (flag) {
			inputComment(musicNo);
			System.out.println("\t\t\t\t\t댓글을 달았습니다.");
		}
	}

	public void showSearchMusic(String search) {
		ArrayList<MusicVO> musicList = musicService.selectMusicList();

		boolean isSearchMusic = false;

		for (int i = 0; i < musicList.size(); i++) {
			MusicVO music = musicList.get(i);
			if (music.getSingerName().contains(search)
					|| music.getMusicTitle().contains(search)) {
				isSearchMusic = true;
				break;
			}
		}

		if (!isSearchMusic) {
			System.out.println("\t\t\t\t일치하는 음악이 없습니다.");
			return;
		} else {
			for (int i = 0; i < musicList.size(); i++) {
				MusicVO music = musicList.get(i);
				if (music.getSingerName().contains(search)
						|| music.getMusicTitle().contains(search)) {
					System.out.println("\t\t|\t" + music.getPk_musicNo() + "\t"
							+ music.getMusicTitle() + "\t\t"
							+ music.getSingerName() + "\t\t" + " "
							+ music.getAlbumName() + "\t\t|");
				}
			}
		}

	}

	public void inputComment(int musicNo) {
		Date day = new Date();
		System.out
				.println("\t\t _______________________________________________________________________________");
		System.out.println("\t\t|\t\t\t\t\t\t\t\t\t\t|");
		System.out.println("\t\t|\t\t\t\t\tMELON\t\t\t\t\t|");
		System.out.println("\t\t|\t\t\t\t\t\t\t\t\t\t|");
		System.out.println("\t\t|\t\t\t\t      -댓글 달기-      \t\t\t\t|");
		System.out
				.println("\t\t|_______________________________________________________________________________|");
		System.out.print("\t\t\t\t\t댓글 입력 : ");
		String comment = ScanUtil.nextLine();
		System.out.print("\t\t\t\t\t별점 입력(1~5) : ");
		int star = ScanUtil.nextInt();

		userMusicCommentService.insertComment(musicComment++, comment, day,
				star, Session.LoginUser.getPk_userID(), musicNo);
	}

	// 재생목록에 음악 추가
	public void musicInsertList(String search) {
		System.out
				.println("\t\t _______________________________________________________________________________");
		System.out.println("\t\t|\t\t\t\t\t\t\t\t\t\t|");
		System.out.println("\t\t|\t\t\t\t\tMELON\t\t\t\t\t|");
		System.out.println("\t\t|\t\t\t\t      -노래 담기-      \t\t\t\t|");
		System.out
				.println("\t\t|===============================================================================|");
		System.out.println("\t\t|\t번호\t제목\t\t가수명\t\t앨범명\t\t\t\t|");
		System.out
				.println("\t\t|===============================================================================|");

		showSearchMusic(search);
		System.out
				.println("\t\t|_______________________________________________________________________________|");

		System.out.print("노래 번호를 입력해주세요 > (0. 뒤로가기)");
		int musicNumber = ScanUtil.nextInt();

		if (musicNumber == 0) {
			return;
		}

		MusicVO musicVo = musicService.selectMusic(musicNumber);

		ArrayList<MusicListVO> myMusicList = musicListService
				.selectMyMusicList(Session.LoginUser.getPk_userID());

		boolean isMusic = true;

		for (int i = 0; i < myMusicList.size(); i++) {
			MusicListVO musicListVo = myMusicList.get(i);
			if (musicListVo.getFk_musicNo() == musicVo.getPk_musicNo()) {
				isMusic = false;
				break;
			}
		}

		if (isMusic) {
			System.out.println("재생목록에 음악을 추가하였습니다.");
			this.state = "||";
			this.nowPlaySong = musicVo.getMusicTitle() + " - "
					+ musicVo.getSingerName();
			musicListService.insertMusicList(musicVo.getPk_musicNo(),
					Session.LoginUser.getPk_userID());
		} else {
			System.out.println("재생목록에 음악을 추가할 수 없습니다.");
		}
	}

	// 음악정보
	private void musicInfo(String search) {
		System.out
				.println("\t\t _______________________________________________________________________________");
		System.out.println("\t\t|\t\t\t\t\t\t\t\t\t\t|");
		System.out.println("\t\t|\t\t\t\t\tMELON\t\t\t\t\t|");
		System.out.println("\t\t|\t\t\t\t\t\t\t\t\t\t|");
		System.out
				.println("\t\t|===============================================================================|");
		System.out.println("\t\t|\t번호\t제목\t\t가수명\t\t앨범명\t\t\t\t|");
		System.out
				.println("\t\t|===============================================================================|");

		showSearchMusic(search);
		System.out
				.println("\t\t|_______________________________________________________________________________|");
		System.out.print("노래 번호를 입력해주세요 >");
		int musicNumber = ScanUtil.nextInt();

		MusicVO musicVo = musicService.selectMusic(musicNumber);

		System.out
				.println("\t\t _______________________________________________________________________________");
		System.out.println("\t\t|\t\t\t\t\t\t\t\t\t\t|");
		System.out.println("\t\t|\t\t\t\t\tMELON\t\t\t\t\t|");
		System.out.println("\t\t|\t\t\t\t\t\t\t\t\t\t|");
		System.out
				.println("\t\t|===============================================================================|");
		System.out.println("\t\t|\t번호\t제목\t\t가수명\t\t앨범명\t\t\t\t|");
		System.out
				.println("\t\t|===============================================================================|");

		System.out.println("\t\t|\t" + musicVo.getPk_musicNo() + "\t"
				+ musicVo.getMusicTitle() + "\t\t" + musicVo.getSingerName()
				+ "\t\t" + " " + musicVo.getAlbumName() + "\t\t|");
		System.out
				.println("\t\t|===============================================================================|");
		ArrayList<UserMusicCommentVO> musicComment = userMusicCommentService
				.selectAllUserMusicComment();

		String starAvg = starAvg(musicComment, musicVo.getPk_musicNo());
		if (musicComment == null) {
			return;
		}
		System.out.println("\t\t|\t댓글번호\t댓글내용\t\t\t작성자\t\t  작성일\t\t\t|");
		System.out
				.println("\t\t|===============================================================================|");
		for (int i = 0; i < musicComment.size(); i++) {
			UserMusicCommentVO userMusicCommentVo = musicComment.get(i);
			if (userMusicCommentVo.getFk_musicNo() == musicVo.getPk_musicNo()) {
				System.out.println("\t\t|\t"
						+ userMusicCommentVo.getPk_userCommentNo()
						+ "\t"
						+ userMusicCommentVo.getCommentContent()
						+ "\t\t"
						+ userMusicCommentVo.getFk_userID()
						+ "\t\t"
						+ " "
						+ DateFormat.dateFormat(userMusicCommentVo
								.getCommentDate()) + "\t\t|");
			}
		}
		System.out.println("\t\t|\t별점 : " + starAvg + "\t\t\t\t\t\t\t\t|");
		System.out
				.println("\t\t|===============================================================================|");
		System.out.print("뒤로가기 0>");
		int back = ScanUtil.nextInt();

	}

	// 별점계산
	private String starAvg(ArrayList<UserMusicCommentVO> musicComment,
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

	// 재생목록
	public void playList() {
		String menu = "";
		do {
			System.out
					.println("\t\t _______________________________________________________________________________");
			System.out.println("\t\t|\t\t\t\t\t\t\t\t\t\t|");
			System.out.println("\t\t|\t\t\t\t\tMELON\t\t\t\t\t|");
			System.out.println("\t\t|\t\t\t\t\t\t\t\t\t\t|");
			System.out.println("\t\t|\t\t\t\t      -재생 목록-      \t\t\t\t|");
			System.out
					.println("\t\t|_______________________________________________________________________________|");

			showPlayList();

			System.out
					.println("\t\t|_______________________________________________________________________________|");
			System.out.println("\t\t|\t\t\t\t\t\t\t\t\t\t|");
			System.out.println("\t\t\t\t\t" + nowPlaySong);
			System.out.println("\t\t|\t\t\t\t|<\t" + state + "\t>|\t\t\t\t|");
			System.out.println("\t\t|\t\t\t\t\t\t\t\t\t\t|");
			System.out
					.println("\t\t|===================================== 메뉴  =====================================|");
			System.out
					.println("\t\t|\t\t1. 삭제 \t\t    2. 곡선택 \t\t0. 뒤로가기 \t\t|");
			System.out
					.println("\t\t|===============================================================================|");
			System.out.print("메뉴 입력 > ");
			menu = ScanUtil.nextLine();

			switch (menu) {
			case "▶": // 재생
				play(menu);
				break;
			case "||": // 정지
				this.state = "▶";
				break;
			case "|<":// 이전곡
				previousSong();
				break;
			case ">|":// 다음곡
				nextSong();
				break;
			case "1": // 삭제
				listMusicDelete();
				break;
			case "2": // 음악 선택
				choiceMusic();
				break;
			case "0": // 뒤로가기
				break;
			}
		} while (!menu.equals("0"));

	}

	// 플레이리스트 보여주는 메소드
	public void showPlayList() {
		System.out.println("\t\t|\t\t\t\t\t\t\t\t\t\t|");
		System.out.println("\t\t|\t음악번호\t\t\t음악제목\t\t\t\t가수\t\t\t|");
		System.out
				.println("\t\t|_______________________________________________________________________________|");

		ArrayList<MusicVO> musicList = musicService.selectMusicList();
		ArrayList<MusicListVO> myMusicList = musicListService
				.selectAllMusicList();

		for (int i = 0; i < musicList.size(); i++) {
			MusicVO musicVo = musicList.get(i);
			for (int j = 0; j < myMusicList.size(); j++) {
				MusicListVO musicListVo = myMusicList.get(j);
				if (musicVo.getPk_musicNo() == musicListVo.getFk_musicNo()
						&& Session.LoginUser.getPk_userID().equals(
								musicListVo.getFk_userID())) {
					System.out.printf("\t\t|\t%2d\t%20s\t\t%15s\t\t\t|%n",
							musicVo.getPk_musicNo(), musicVo.getMusicTitle(),
							musicVo.getSingerName());
				}
			}
		}
	}

	// 음악 선택
	public void choiceMusic() {
		int musicNo;
		do {
			System.out
					.println("\t\t _______________________________________________________________________________");
			System.out.println("\t\t|\t\t\t\t\t\t\t\t\t\t|");
			System.out.println("\t\t|\t\t\t\t\tMELON\t\t\t\t\t|");
			System.out.println("\t\t|\t\t\t\t\t\t\t\t\t\t|");
			System.out.println("\t\t|\t\t\t\t      -곡 선택-      \t\t\t\t|");
			System.out
					.println("\t\t|_______________________________________________________________________________|");
			showPlayList();
			System.out
					.println("\t\t|_______________________________________________________________________________|");

			ArrayList<MusicVO> musicList = musicService.selectMusicList();
			System.out.print("재생할 음악 번호 입력 > (0.뒤로가기)");
			musicNo = ScanUtil.nextInt();

			if (musicNo == 0) {
				System.out.println("되돌아갑니다.");
				break;
			}

			MusicVO rMusicVo = null;
			int musicIndex = 0;
			for (int i = 0; i < musicList.size(); i++) {
				MusicVO musicVo = musicList.get(i);
				if (musicNo == musicVo.getPk_musicNo()) {
					rMusicVo = musicVo;
					musicIndex = i;
					break;
				}
			}

			if (rMusicVo == null) {
				System.out.println("재생할 수 없습니다.");
				break;
			} else {

				System.out.println("\t\t\t\t\t음악을 재생합니다.");
				System.out.println("\t\t\t\t\t" + rMusicVo.getSingerName()
						+ " - " + rMusicVo.getMusicTitle());
				rMusicVo.setStreamingCount(rMusicVo.getStreamingCount() + 1);
				musicService.updateMusic(rMusicVo, musicIndex);
				this.state = "||";
				this.nowPlaySong = rMusicVo.getSingerName() + " - "
						+ rMusicVo.getMusicTitle();

			}
		} while (musicNo != 0);

	}

	public void play(String menu) {
		if (state.equals("▶")) {
			this.state = "||";
		}
		nowPlaySet();
	}

	// 다음곡
	public void nextSong() {
		if (state.equals("▶")) {
			this.state = "||";
		}
		ArrayList<MusicListVO> musicList = musicListService
				.selectAllMusicList();
		ArrayList<MusicVO> music = musicService.selectMusicList();

		int count = 0;
		int index = 0;
		MusicVO rMusicVo = null;

		for (int i = 0; i < musicList.size(); i++) {
			MusicListVO musicListVo = musicList.get(i);
			if (Session.LoginUser.getPk_userID().equals(
					musicListVo.getFk_userID())) {
				count++;
			}
		}

		if (this.musicNum >= count - 1) {
			this.musicNum = 0;
		} else {
			this.musicNum++;
		}

		int nowPlayCount = 0;
		boolean flag = false;

		for (int i = 0; i < musicList.size(); i++) {
			MusicListVO musicListVo = musicList.get(i);
			if (flag) {
				break;
			}
			for (int j = 0; j < music.size(); j++) {
				MusicVO musicVo = music.get(j);
				if (Session.LoginUser.getPk_userID().equals(
						musicListVo.getFk_userID())
						&& musicVo.getPk_musicNo() == musicListVo
								.getFk_musicNo()) {

					if (nowPlayCount == musicNum) {
						index = i;
						rMusicVo = musicVo;
						this.nowPlaySong = musicVo.getSingerName() + " - "
								+ musicVo.getMusicTitle();
						flag = true;
						break;
					}
					nowPlayCount++;
				}
			}
		}
		index = musicService.indexMusic(rMusicVo.getPk_musicNo());
		rMusicVo.setStreamingCount(rMusicVo.getStreamingCount() + 1);
		musicService.updateMusic(rMusicVo, index);
	}

	// 이전곡
	public void previousSong() {
		if (state.equals("▶")) {
			this.state = "||";
		}
		ArrayList<MusicListVO> musicList = musicListService
				.selectAllMusicList();
		ArrayList<MusicVO> music = musicService.selectMusicList();

		int count = 0;
		int index = 0;
		MusicVO rMusicVo = null;

		for (int i = 0; i < musicList.size(); i++) {
			MusicListVO musicListVo = musicList.get(i);
			if (Session.LoginUser.getPk_userID().equals(
					musicListVo.getFk_userID())) {
				count++;
			}
		}

		if (this.musicNum <= 0) {
			this.musicNum = count - 1;
		} else {
			this.musicNum--;
		}

		int nowPlayCount = 0;
		boolean flag = false;

		for (int i = 0; i < musicList.size(); i++) {
			MusicListVO musicListVo = musicList.get(i);
			if (flag) {
				break;
			}
			for (int j = 0; j < music.size(); j++) {
				MusicVO musicVo = music.get(j);
				if (Session.LoginUser.getPk_userID().equals(
						musicListVo.getFk_userID())
						&& musicVo.getPk_musicNo() == musicListVo
								.getFk_musicNo()) {

					if (nowPlayCount == musicNum) {
						rMusicVo = musicVo;
						this.nowPlaySong = musicVo.getSingerName() + " - "
								+ musicVo.getMusicTitle();
						flag = true;
						break;
					}
					nowPlayCount++;
				}
			}
		}

		index = musicService.indexMusic(rMusicVo.getPk_musicNo());
		rMusicVo.setStreamingCount(rMusicVo.getStreamingCount() + 1);
		musicService.updateMusic(rMusicVo, index);
	}

	// 재생목록에서 음악 삭제
	public void listMusicDelete() {
		System.out
				.println("\t\t _______________________________________________________________________________");
		System.out.println("\t\t|\t\t\t\t\t\t\t\t\t\t|");
		System.out.println("\t\t|\t\t\t\t\tMELON\t\t\t\t\t|");
		System.out.println("\t\t|\t\t\t\t\t\t\t\t\t\t|");
		System.out.println("\t\t|\t\t\t\t      -노래 삭제-      \t\t\t\t|");
		System.out
				.println("\t\t|_______________________________________________________________________________|");
		searchList();
		System.out
				.println("\t\t|_______________________________________________________________________________|");
		System.out.print("삭제할노래 번호를 입력해주세요 >");
		int musicNumber = ScanUtil.nextInt();

		int deleteIndex = musicListService.musicListIndexSearch(musicNumber);
		if (deleteIndex == -1) {
			System.out.println("\t\t\t\t\t삭제할 수 없습니다.");
		} else {
			musicListService.deleteMusicList(deleteIndex);
			System.out.println("\t\t\t\t\t삭제했습니다.");
		}
	}

	// 플레이리스트 찾아가는 메소드
	public void searchList() {
		ArrayList<MusicListVO> musicList = musicListService
				.selectAllMusicList();
		System.out.println("\t\t|\t\t\t\t\t\t\t\t\t\t|");
		System.out.println("\t\t|\t번호\t\t음악제목\t\t\t\t가수\t\t\t|");
		System.out
				.println("\t\t|_______________________________________________________________________________|");
		for (int i = 0; i < musicList.size(); i++) {
			MusicListVO musicListVo = musicList.get(i);
			if (Session.LoginUser.getPk_userID().equals(
					musicListVo.getFk_userID())) {
				showMusic(musicListVo.getFk_musicNo());
			}
		}
	}

	// 플레이 리스트 보여주는 메소드
	private void showMusic(int fk_musicNo) {
		ArrayList<MusicVO> music = musicService.selectMusicList();

		for (int i = 0; i < music.size(); i++) {
			MusicVO musicVo = music.get(i);
			if (fk_musicNo == musicVo.getPk_musicNo()) {
				System.out.printf("\t\t|\t%2d\t%20s\t\t%15s\t\t\t|%n",
						musicVo.getPk_musicNo(), musicVo.getMusicTitle(),
						musicVo.getSingerName());
			}
		}
	}
}
