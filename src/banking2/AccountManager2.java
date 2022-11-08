package banking2;

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

public class AccountManager2 {
	//해시
	HashSet<Account2> accountHashSet;

	public AccountManager2() {
		accountHashSet = new HashSet<>();
	}

	public void makeAccount(int choice) {
		String iAccountNum, iName, iRating;

		int iBalance;
		System.out.println("1.보통계좌");
		System.out.println("2.신용신뢰계좌");
		choice = scanInt("계좌종류");

		iAccountNum = scanValue("계좌번호: ");
		iName = scanValue("고객이름: ");
		iBalance = scanInt("잔고: ");
		int inter = scanInt("기본이자: ");

		Account2 acc = null;
		if (choice == 1) {
			acc = new NormalAccount2(iAccountNum, iName, iBalance, inter);

		} else if (choice == 2) {
			iRating = scanValue("신용등급: ");
			acc = new HighCreditAccount2(iAccountNum, iName, iBalance, inter, iRating);
		}
		boolean isDup = accountHashSet.add(acc);
		if (isDup == false) {
			System.out.println("중복된 계좌가 발생하였습니다");
			String overlap = scanValue("[Y]덮어쓰기 /[N]취소");
			if (overlap.equalsIgnoreCase("Y")) {
				accountHashSet.remove(acc);
				accountHashSet.add(acc);
			} else if (overlap.equalsIgnoreCase("N")) {
				System.out.println("취소합니다");
				return;
			}
		}
		System.out.println("완료되었습니다.");
	}

	public void showAccInfo() {
		System.out.println("***계좌정보출력***");
		for (Account2 acc2 : accountHashSet) {
			acc2.showAccInfo();
		}
		System.out.println("전체계좌정보 출력이 완료되었습니다.");
	}

	public void depositMoney() {
		boolean isFind = false;
		String searchaccountnum = scanValue("계좌번호와 입금할 금액을 입력하세요.");
		System.out.println("계좌번호:");

		for (Account2 acc2 : accountHashSet) {
			if (searchaccountnum.equals(acc2.accNum)) {
				try {
					int addnum = scanInt(" 입금액");
					if (addnum < 0) {
						System.out.println("마이너스 금액은 입급할 수 없습니다.");
						return;
					} else if (addnum % 500 != 0) {
						System.out.println("500원 단위로 입금하세요.");
						return;
					} else {
						acc2.setBalance(ICustomDefine2.DEPOSITS, addnum);
						System.out.println(" 입금이 완료되었습니다.");
						isFind = true;
					}
				} catch (InputMismatchException e) {
					System.out.println("문자를 입력할 수 없습니다.");
				}
			}
		}
		if (isFind == false)
			System.out.println("***계좌번호가 틀렸습니다.***");
	}

	public void withdrawMoney() {
		boolean isFind = false;
		String searchaccountnum = scanValue("계좌번호");
		for (Account2 acc2 : accountHashSet) {
			if (searchaccountnum.equals(acc2.accNum)) {
				int addnum = scanInt(" 출금할 금액");
				if (addnum < 0) {
					System.out.println("마이너스 금액은 출금할 수 없습니다.");
					return;
				} else if (addnum > acc2.balance) {
					System.out.println("잔고가 부족합니다. 전체잔고를 출력할까요?");
					String addnym = scanValue("Y: 전체처리, N출금요청");
					if ("Y".equalsIgnoreCase(addnym)) {
						System.out.println("전체처리");
						isFind = true;
					} else if ("N".equalsIgnoreCase(addnym)) {
						System.out.println("출금요청 취소");
						return;
					}
				} else {
					System.out.println(" 출금이 완료되었습니다.");
					isFind = true;
				}
				acc2.setBalance(ICustomDefine2.WITHDRAWS, addnum);
			}
		}
		if (isFind == false)
			System.out.println("***계좌번호가 틀렸습니다.***");
	}

	public void saveAccountInfo() {
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream
					(new FileOutputStream("src/banking2/AccountInfo.obj"));
			for (Account2 ac2 : accountHashSet) {
				oos.writeObject(ac2);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void readAccountInfo() {
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream
					(new FileInputStream("src/banking2/AccountInfo.obj"));
			while (true) {
				accountHashSet.add((Account2) ois.readObject());
			}
		} catch (EOFException e) {
			System.out.println("파일불러오기 종료");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void AutoSaver(AutoSaver mgr) {

		int choice = scanInt("1.자동저장On, 2.자동저장Off");
		if (choice == 1) {
			if (mgr.isAlive()) {
				System.out.println("이미 실행 중 입니다");
			} else {
				mgr.start();
			}

		} else if (choice == 2) {
			mgr.interrupt();
		}
	}

	public void saveThread() {
		try {
			PrintWriter out = 
					new PrintWriter
						(new FileWriter("src/banking2/AutoSaveAccount.txt"));
			for (Account2 ac2 : accountHashSet) {
				out.print(ac2.toString());
			}
			out.close();
		} catch (Exception e) {
		}
	}

	String scanValue(String title) {
		Scanner scanner = new Scanner(System.in);
		System.out.println(title + "을/를 입력하세요: ");
		String str = scanner.nextLine();
		return str;
	}

	int scanInt(String title) {
		Scanner scanner = new Scanner(System.in);
		System.out.println(title + "을/를 입력하세요: ");
		int intNum = scanner.nextInt();
		return intNum;
	}
}