package banking;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AccountManager {
	//해시셋
	HashSet<Account> accHash;

	public AccountManager() {
		accHash = new HashSet<>();
	}

	public void makeAccount(int choice) {
		String iAccNum, iName, iRating;
		int iBalance, inter;
		
		System.out.println("1.보통계좌");
		System.out.println("2.신용신뢰계좌");
		choice = scanInt("계좌종류");

		iAccNum = scanValue("계좌번호");
		iName = scanValue("고객이름");
		iBalance = scanInt("잔고");
		inter = scanInt("기본이자");

		Account acc = null;
		if (choice == 1) {
			//보통계좌일 경우 NormalAccount에 저장
			acc = new NormalAccount(iAccNum, iName, iBalance, inter);

		} else if (choice == 2) {
			iRating = scanValue("신용등급(A,B,C)");
			//신용신뢰계좌일 경우 HighCreditAccount에 저장
			acc = new HighCreditAccount(iAccNum, iName, iBalance, inter, iRating);
		}
		
		//중복제거
		boolean isDup = accHash.add(acc);
		if (isDup == false) {
			System.out.println("중복된 계좌가 발생하였습니다");
			String overlap = scanValue("[Y]덮어쓰기 / [N]취소");
			if (overlap.equalsIgnoreCase("Y")) {
				//덮어쓸 경우 기존정보를 지우고 새로운 정보를 입력한다.
				accHash.remove(acc);
				accHash.add(acc);
			} else if (overlap.equalsIgnoreCase("N")) {
				//그렇지 않을 경우 그대로 작업을 취소한다.
				System.out.println("작업을 취소합니다");
				return;
			}
		}
		//중복이 제거된 경우 작업이 완료되었다고 표시
		System.out.println("완료되었습니다.");
	}

	//전체계좌정보 출력 메소드
	public void showAccInfo() {
		System.out.println("***계좌정보출력***");
		for (Account acc : accHash) {
			acc.showAccInfo();
		}
		System.out.println("전체계좌정보 출력이 완료되었습니다.");
	}

	//입금요청 메소드
	public void depositMoney() {
		//작업이 완료되었는지 판단하기 위한 boolean형 변수 선언
		boolean isFind = false;
		String accSearch = scanValue("계좌번호");

		for (Account acc : accHash) {
			//입력받은 계좌번호와 기존에 존재하던 계좌번호를 비교
			if (accSearch.equals(acc.getAccNum())) {
				try {
					int addNum = scanInt("입금액");
					if (addNum < 0) {
						System.out.println("마이너스 금액은 입급할 수 없습니다.");
						return;
					}
					else if (addNum % 500 != 0) {
						System.out.println("500원 단위로 입금하세요.");
						return;
					}
					//계좌번호가 같고 위의 조건에 저촉되지 않는다면 입금처리
					else {
						acc.setBalance(ICustomDefine.DEPOSIT_CHECK, addNum);
						//boolean값이 변화되었다면 입금이 완료된 것
						isFind = true;
						System.out.println("입금이 완료되었습니다.");
					}
				} catch (InputMismatchException e) {
					System.out.println("숫자 이외는 입력할 수 없습니다.");
				}
			}
		}
		//boolean값이 그대로라면 입금처리가 되지 않은 것을 의미
		if (isFind == false)
			System.out.println("***일치하는 계좌번호가 없습니다.***");
	}

	//출금 요청 메소드
	public void withdrawMoney() {
		//입력받은 계좌번호와 기존에 존재하던 계좌번호를 비교
		boolean isFind = false;
		String accSearch = scanValue("계좌번호");
		
		for (Account acc : accHash) {
			//입력받은 계좌번호와 기존에 존재하던 계좌번호를 비교
			if (accSearch.equals(acc.getAccNum())) {
				int minusNum = scanInt("출금액");
				if (minusNum < 0) {
					System.out.println("마이너스 금액은 출금할 수 없습니다.");
					return;
				} 
				else if (minusNum % 1000 != 0) {
					System.out.println("출금은 1000원 단위로만 할 수 있습니다.");
				}
				else if (minusNum > acc.getBalance()) {
					System.out.println("잔고가 부족합니다. 전체잔고를 출력할까요?");
					String choice = scanValue("[Y]전체출금, [N]출금취소");
					//전체가 출금될 때도 정상적으로 출금이 완료된 것이므로 boolean값을 변화시킨다.
					if ("Y".equalsIgnoreCase(choice)) {
						System.out.println("전체출금");
						isFind = true;
					} else if ("N".equalsIgnoreCase(choice)) {
						System.out.println("출금요청 취소");
						return;
					}
				} 
				else {
					//출금액이 잔고와 같거나 적다면 그대로 출금한다.
					acc.setBalance(ICustomDefine.WITHDRAW_CHECK, minusNum);
					//boolean값이 변화되었다면 입금이 완료된 것
					isFind = true;
					System.out.println("출금이 완료되었습니다.");
				}
			}
		}
		//boolean값이 그대로라면 입금처리가 되지 않은 것을 의미
		if (isFind == false)
			System.out.println("***일치하는 계좌번호가 없습니다.***");
	}

	//직렬화 메소드
	public void saveAccountInfo() {
		ObjectOutputStream OutputSt = null;
		try {
			OutputSt = new ObjectOutputStream
					(new FileOutputStream("src/banking2/AccountInfo.obj"));
			for (Account acc : accHash) {
				OutputSt.writeObject(acc);
			}
			//파일이 없을 때
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//역직렬화 메소드
	public void readAccountInfo() {
		ObjectInputStream InputSt = null;
		try {
			InputSt = new ObjectInputStream
					(new FileInputStream("src/banking/AccountInfo.obj"));
			while (true) {
				accHash.add((Account)InputSt.readObject());
			}
		} catch (EOFException e) {
			System.out.println("파일불러오기 종료");
		} catch (FileNotFoundException e) {
			System.out.println("복원할 파일이 없습니다.");
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	//자동저장 메소드
	public void AutoSaver(AutoSaver accMgr) {

		int choice = scanInt("1.자동저장On, 2.자동저장Off");
		if (choice == 1) {
			//이미 실행중이다면
			if (accMgr.isAlive()) {
				System.out.println("이미 실행 중 입니다");
			} else {
				//자동저장 on
				accMgr.start();
			}

		} else if (choice == 2) {
			//자동저장 off
			accMgr.interrupt();
		}
	}

	public void saveThread() {
		try {
			PrintWriter out = 
					new PrintWriter
						(new FileWriter("src/banking/AutoSaveAccount.txt"));
			for (Account acc : accHash) {
				//자동저장이 되는 동안 계속 저장되고 있다는 메세지 출력
				out.print(acc.toString());
			}
			//끝난다면 자원반납
			out.close();
		} catch (Exception e) {
		}
	}

	/*
	사용자로부터 입력값을 받을 때 일일이 설정을 해주는 것보다는
	간단하다고 판단하여 scanValue와 scanInt를 메소드로 정의
	또한, 값 입력시 버퍼로 인해 생기는 문제점이 사라진다.
	 */
	String scanValue(String title) {
		Scanner scan = new Scanner(System.in);
		System.out.println(title + "을(를) 입력하세요: ");
		String str_scan = scan.nextLine();
		return str_scan;
	}

	//숫자를 문자열처럼 입력받아서 다시 숫자로 반환하는 scanInt메소드 생성
	int scanInt(String title) {
		Scanner scan = new Scanner(System.in);
		System.out.println(title + "을(를) 입력하세요: ");
		int int_scan = scan.nextInt();
		return int_scan;
	}
}