package banking;

import java.util.Scanner;

/*
컨트롤 클래스로 프로그램의 전반적인 기능을 구현한다. 
 */
public class AccountManager {

	private Account[] accountInfo;
	/*
	친구정보를 저장한 객체를 생성한 후 배열에 저장할때 인덱스로 사용할
	멤버변수로 생성자에서 0으로 초기화된다. 
	 */
	private int numOfAccounts;
	
	public AccountManager(int num) {
		super();
		accountInfo = new Account[num];
 		//배열의 인덱스는 0부터 시작이므로 0으로 초기화한다.
		numOfAccounts = 0;
	}
	
	public void makeAccount() {
		Scanner scan = new Scanner(System.in);
		String iAccount,iName, iCredit;
		int iBalance,iRate, iSelect;
		System.out.println("-----Menu-----");
		System.out.println("1. 보통계좌");
		System.out.println("2. 신용신뢰계좌");
		System.out.print("선택: ");
		iSelect = Integer.parseInt(scan.nextLine());
		
		if (iSelect == 1) {
			System.out.println("***신규계좌개설***");
			System.out.print("계좌번호: ");iAccount = scan.nextLine();
			System.out.print("고객이름: ");iName = scan.nextLine();
			System.out.print("잔액: ");iBalance = scan.nextInt();
			System.out.print("기본이자%(정수형태로입력): ");iRate = scan.nextInt();
			accountInfo[numOfAccounts++] =
					new NormalAccount(iAccount, iName, iBalance, iRate);
		
			System.out.println("계좌개설이 완료되었습니다.");

		}
		else if (iSelect == 2) {
			System.out.println("***신규계좌개설***");
			System.out.print("계좌번호: ");iAccount = scan.nextLine();
			System.out.print("고객이름: ");iName = scan.nextLine();
			System.out.print("잔액: ");iBalance = scan.nextInt();
			System.out.print("기본이자%(정수형태로입력): ");iRate = scan.nextInt();
			System.out.print("신용등급(A,B,C등급):" );iCredit = scan.nextLine(); 
			accountInfo[numOfAccounts++] = 
					new HighCreditAccount(iAccount, iName, iBalance, iRate, iCredit);
			System.out.println("계좌개설이 완료되었습니다.");

		}
	}
	public void depositMoney() {
		
	}
	public void withdrawMoney() {
		
	}
	public void showAccInfo() {
		
		for (int i = 0; i < numOfAccounts; i++) {
			accountInfo[i].showAccInfo();
		}
		System.out.println("전체계좌정보 출력이 완료되었습니다.");
	}
}
