package banking;

import java.util.Scanner;

/*
main 메서드를 포함한 클래스. 프로그램은 여기서 실행한다.
 */
public class BankingSystemMain {

	public static void showMenu() {
		System.out.println("-----Menu-----");
		System.out.println("1.계좌개설");
		System.out.println("2.입   금");
		System.out.println("3.출   금");
		System.out.println("4.계좌정보출력");
		System.out.println("5.프로그램종료");
		System.out.print("메뉴선택>>>");
	}
	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		/*
		기능을 담당하는 핸들러(메니져) 클래스의 객체를 생성
		 */
		AccountManager accmanager = new AccountManager(50);		
		
		while(true) {
			//메뉴를 출력한다. 
			showMenu();
			
			//사용자는 수행할 기능의 메뉴를 선택한다. 
			int choice = scan.nextInt();
			
			//선택한 번호에 따라 switch문으로 각 메서드를 호출한다.
			switch(choice) {
			case ICustomDefine.MAKE:
				accmanager.makeAccount();
				break;
			case ICustomDefine.DEPOSIT:
				accmanager.depositMoney();
				break;
			case ICustomDefine.WITHDRAW:
				accmanager.withdrawMoney();
				break;
			case ICustomDefine.INQUIRE:
				accmanager.showAccInfo();
				break;
			case ICustomDefine.EXIT:
				System.out.println("프로그램종료");
				return;
			}
		}
	}
}