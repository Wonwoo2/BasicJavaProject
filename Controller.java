package controller;

import service.*;
import vo.*;
import data.*;

public class Controller {
	public static void main(String[] args) {
		/*
		 * 조 소개 > 주제 소개 > 주제 선정 배경 > 프로그램 구조> 시연 발표자 1명, ppt 및 시연 도우미 1명
		 * 
		 * Controller : 메뉴 선택 Service : 메뉴 기능 수행 Dao : 데이터 베이스 접속 VO : 데이터 담는
		 * 클래스
		 * 
		 * 회원가입 로그인 회원목록
		 */
		new Controller().start();
	}

	MainService mainService = MainService.getInstance();
	LoginService loginService = LoginService.getInstance();
	AdminController adminController = AdminController.getInstance();
	UserController userController = UserController.getInstance();
	MusicListService musicListService = MusicListService.getInstance();

	private void start() {

		int menu;

		do {
			System.out.println("\t\t _______________________________________________________________________________");
			System.out.println("\t\t|\t\t\t\t\t\t\t\t\t\t|");
			System.out.println("\t\t|\t\t\t\t       MELON      \t\t\t\t|");
			System.out.println("\t\t|\t\t\t\t\t\t\t\t\t\t|");
			System.out.println("\t\t|\t\t\t\t1. 회원가입\t\t\t\t\t|");
			System.out.println("\t\t|\t\t\t\t2. 로그인   \t\t\t\t\t|");
			System.out.println("\t\t|\t\t\t\t3. 아이디/비밀번호 찾기\t\t\t\t|");
			System.out.println("\t\t|\t\t\t\t0. 프로그램 종료   \t\t\t\t\t|");
			System.out.println("\t\t|\t\t\t\t\t\t\t\t\t\t|");
			System.out.println("\t\t|\t\t\t\t\t\t\t\t\t\t|");
			System.out.println("\t\t|_______________________________________________________________________________|");
			System.out.print("메뉴에 해당하는 번호 입력 > ");

			menu = ScanUtil.nextInt();

			switch (menu) {
			case 1: // 회원가입
				join();
				break;
			case 2: // 로그인
				login();
				break;
			case 3: // 아이디 / 비밀번호 찾기
				find();
				break;
			case 0: // 프로그램 종료
				System.out.println("\t\t\t\t\t\t프로그램 종료");
				break;

			}

		} while (menu != 0);

	}

	// 회원가입
	public void join() {
		System.out.println("\t\t _______________________________________________________________________________");
		System.out.println("\t\t|\t\t\t\t\t\t\t\t\t\t|");
		System.out.println("\t\t|\t\t\t\t       MELON      \t\t\t\t|");
		System.out.println("\t\t|\t\t\t\t    - 회원가입 -    \t\t\t\t|");
		System.out.print("\t\t\t\t\t\t아이디 : ");
		String id = ScanUtil.nextLine();
		System.out.print("\t\t\t\t\t\t비밀번호 : ");
		String password = ScanUtil.nextLine();
		System.out.print("\t\t\t\t\t\t이름 : ");
		String name = ScanUtil.nextLine();
		System.out.print("\t\t\t\t\t\t연락처 :");
		String phone = ScanUtil.nextLine();
		System.out.println("\t\t|\t\t\t\t\t\t\t\t\t\t|");
		System.out.println("\t\t|\t\t\t\t\t\t\t\t\t\t|");
		System.out.println("\t\t|_______________________________________________________________________________|");
		System.out.println("\n\n\n\n\n\n\n");
		
		UserVO userVo = mainService.userJoin(id, password, name, phone);
		if(userVo != null) {
			System.out.println("\t\t\t\t\t\t회원가입을 성공했습니다.");
		} else {
			System.out.println("\t\t\t\t\t\t회원가입을 실패했습니다.");
		}
	}

	// 로그인
	public void login() {
		System.out.println("\t\t _______________________________________________________________________________");
		System.out.println("\t\t|\t\t\t\t\t\t\t\t\t\t|");
		System.out.println("\t\t|\t\t\t\t       MELON      \t\t\t\t|");
		System.out.println("\t\t|\t\t\t\t     - 로그인 -     \t\t\t\t|");
		System.out.print("\t\t\t\t\t\t아이디 : ");
		String id = ScanUtil.nextLine();
		System.out.println("\t\t|_______________________________________________________________________________|");
		System.out.println("\t\t|\t\t\t\t\t\t\t\t\t\t|");
		System.out.print("\t\t\t\t\t\t비밀번호 : ");
		String pw = ScanUtil.nextLine();
		System.out.println("\t\t|\t\t\t\t\t\t\t\t\t\t|");
		System.out.println("\t\t|_______________________________________________________________________________|");
		System.out.println("\n\n\n\n\n");
		UserVO user = loginService.LoginData(id, pw);
		
		if (user == null) {
			System.out.println("\t\t\t\t\t\t로그인을 실패하였습니다.");
			System.out.println("\t\t\t\t\t\t아이디 혹은 비밀번호를 다시 한번 확인해주세요.");
			return;
		} else if (user.getPk_userID().equals("admin")) {
			Session.LoginUser = user;
			System.out.println("\t\t\t\t\t\t로그인을 성공하였습니다.");
			adminController.adminStart();
			
		} else {
			Session.LoginUser = user;
			System.out.println("\t\t\t\t\t\t로그인을 성공하였습니다.");
			userController.userStart();
			
		}
	}
	
	public void find() {
		int menu;

		do {
			System.out.println("\t\t _______________________________________________________________________________");
			System.out.println("\t\t|\t\t\t\t\t\t\t\t\t\t|");
			System.out.println("\t\t|\t\t\t\t\tMELON\t\t\t\t\t|");
			System.out.println("\t\t|\t\t\t\t\t\t\t\t\t\t|");
			System.out.println("\t\t|\t\t\t\t1. 아이디 찾기\t\t\t\t\t|");
			System.out.println("\t\t|\t\t\t\t2. 비밀번호 찾기\t\t\t\t\t|");
			System.out.println("\t\t|\t\t\t\t0. 뒤로가기\t\t\t\t\t|");
			System.out.println("\t\t|\t\t\t\t\t\t\t\t\t\t|");
			System.out.println("\t\t|\t\t\t\t\t\t\t\t\t\t|");
			System.out.println("\t\t|_______________________________________________________________________________|");
			
			System.out.print("메뉴에 해당하는 번호 입력 > ");

			menu = ScanUtil.nextInt();
			switch (menu) {
			case 1: // 아이디 찾기
				idFind();
				break;
			case 2: // 비밀번호 찾기
				pwFind();
				break;
			case 0:
				break;
			}

		} while (menu != 0);
	}

	// 아이디찾기
	public void idFind() {
		System.out.println("\t\t _______________________________________________________________________________");
		System.out.println("\t\t|\t\t\t\t\t\t\t\t\t\t|");
		System.out.println("\t\t|\t\t\t\t\tMELON\t\t\t\t\t|");
		System.out.println("\t\t|\t\t\t\t    - 아이디 찾기 -     \t\t\t\t|");
		System.out.print("\t\t\t\t\t\t이름 : ");
		String name = ScanUtil.nextLine();
		System.out.print("\t\t\t\t\t\t연락처 : ");
		String phone = ScanUtil.nextLine();
		System.out.println("\t\t|\t\t\t\t\t\t\t\t\t\t|");
		System.out.println("\t\t|\t\t\t\t\t\t\t\t\t\t|");
		System.out.println("\t\t|_______________________________________________________________________________|");
		
		
		UserVO userVo = mainService.idFind(name, phone);
		
		if(userVo == null) {
			System.out.println("\t\t\t\t\t\t가입된 아이디가 없습니다.");
		} else {
			System.out.println("\t\t\t\t\t\t아이디는 " + userVo.getPk_userID() + " 입니다.");
		}
	}

	// 비밀번호 찾기
	public void pwFind() {
		System.out.println("\t\t _______________________________________________________________________________");
		System.out.println("\t\t|\t\t\t\t\t\t\t\t\t\t|");
		System.out.println("\t\t|\t\t\t\t\tMELON\t\t\t\t\t|");
		System.out.println("\t\t|\t\t\t\t    - 비밀번호 찾기 -    \t\t\t\t|");
		System.out.print("\t\t\t\t\t\t아이디 :  ");
		String id = ScanUtil.nextLine();
		System.out.print("\t\t\t\t\t\t연락처  :  ");
		String phone = ScanUtil.nextLine();
		System.out.println("\t\t|\t\t\t\t\t\t\t\t\t\t|");
		System.out.println("\t\t|\t\t\t\t\t\t\t\t\t\t|");
		System.out.println("\t\t|_______________________________________________________________________________|");
		
		UserVO userVo = mainService.pwFind(id, phone);
		
		if(userVo == null) {
			System.out.println("\t\t\t\t\t\t아이디 혹은 연락처가 잘못됐습니다.");
		} else {
			int password = (int)(Math.random() * 100000);
			UserVO user = mainService.pwUpdate(id, password + "");
			System.out.println("\t\t\t\t\t\t임시비밀번호는 " + user.getUserPW() + " 입니다.");
		}
	}
}