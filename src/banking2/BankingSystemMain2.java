package banking2;

import java.util.Scanner;

public class BankingSystemMain2 {
	
	public static void showMenu() {
		//기본메뉴 출력
		System.out.println("-----Menu------");
		System.out.println("1.계좌개설");
		System.out.println("2.입금");
		System.out.println("3.출금");
		System.out.println("4.전체계좌정보출력");
		System.out.println("5.저장옵션");
		System.out.println("6.프로그램종료");
		
	}
	
	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		
		MenuSelectException2 ex = new MenuSelectException2();
		AccountManager2 accMgr = new AccountManager2();
		AutoSaver sa = new AutoSaver(accMgr);
		sa.setDaemon(true);
		
		//역직렬화
		accMgr.readAccountInfo();
		while (true) {
			showMenu();
			try {
				int choice = scan.nextInt();
				switch (choice) {
				case ICustomDefine2.MAKE:
					accMgr.makeAccount(choice);
					break;
				case ICustomDefine2.DEPOSIT:
					accMgr.depositMoney();
					break;
				case ICustomDefine2.WITHDRAW:
					accMgr.withdrawMoney();
					break;
				case ICustomDefine2.INQUIRE:
					accMgr.showAccInfo();
					break;
				case ICustomDefine2.AUTO_SAVE:
					accMgr.AutoSaver(sa);
					break;
				case ICustomDefine2.EXIT:
					System.out.println("프로그램종료");
					accMgr.saveAccountInfo();
					System.exit(0);
				default:
					throw ex;
				}
			} catch (MenuSelectException2 e) {
				System.out.println(e.getMessage());
			} catch (Exception e) {
				System.out.println();
				scan.nextLine();
			}
		}
	}
}
