package banking;

import java.util.Scanner;

public class BankingSystemMain {
	
	public static void showMenu() {
		//기본메뉴 출력
		System.out.println("-----Menu------");
		System.out.println("1.계좌개설");
		System.out.println("2.입금");
		System.out.println("3.출금");
		System.out.println("4.전체계좌정보출력");
		System.out.println("5.저장옵션");
		System.out.println("6.프로그램종료");
		System.out.print("메뉴 선택: ");
		
	}
	
	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		
		MenuSelectException ex = new MenuSelectException();
		AccountManager accMgr = new AccountManager();
		AutoSaver au_save = new AutoSaver(accMgr);
		au_save.setDaemon(true);
		
		//역직렬화(저장된 정보 복원)
		accMgr.readAccountInfo();
		//프로그램을 종료할 때까지 실행
		while (true) {
			showMenu();
			try {
				//입력받는 숫자에 따라 메뉴실행(인터페이스형 상수)
				int choice = scan.nextInt();
				switch (choice) {
				case ICustomDefine.MAKE:
					accMgr.makeAccount(choice);
					break;
				case ICustomDefine.DEPOSIT:
					accMgr.depositMoney();
					break;
				case ICustomDefine.WITHDRAW:
					accMgr.withdrawMoney();
					break;
				case ICustomDefine.INQUIRE:
					accMgr.showAccInfo();
					break;
				case ICustomDefine.AUTO_SAVE:
					accMgr.AutoSaver(au_save);
					break;
				case ICustomDefine.EXIT:
					System.out.println("프로그램종료");
					//종료 전에 직렬화 수행
					accMgr.saveAccountInfo();
					System.exit(0);
				default:
					throw ex;
				}
				//입력값이 잘못되었을 경우(1 ~ 5 사이의 값이 아닐 때)
			} catch (MenuSelectException e) {
				System.out.println(e.getMessage());
				//알 수 없는 오류 발생
			} catch (Exception e) {
				System.out.println("잘못 입력하셨습니다.");
				scan.nextLine();
			}
		}
	}
}
