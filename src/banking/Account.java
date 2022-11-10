package banking;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;

public abstract class Account implements Serializable{
	//멤버변수 선언
	String accNum;
	String name;
	int balance;
	int interest;

	public Account(String accNum, String name, int balance, int interest) {
		//생성자
		this.accNum = accNum;
		this.name = name;
		this.balance = balance;
		this.interest = interest;
	}

	
	public String getAccNum() {
		return accNum;
	}
	public int getBalance() {
		return balance;
	}
	public int getInterest() {
		return interest;
	}
	//정보 출력
	public void showAccInfo() {
		System.out.println("=========================================================");
		System.out.printf("계좌번호: %s\n이름 : %s\n잔고: %d\n기본이자: %d%%\n",
				accNum, name, balance, interest);
		
	}
	//잔고 설정 메소드
	public void setBalance(int idx, int money) {
		if (idx == ICustomDefine.DEPOSIT_CHECK) {
			this.balance += money;
		} 
		else if (idx == ICustomDefine.WITHDRAW_CHECK) {
			if (this.balance < money) {
				this.balance = 0;
			} else {
				this.balance -= money;
			}
		}
	}

	@Override
	public int hashCode() {
		int reHash = Objects.hash(accNum);
		return reHash;
	}

	public boolean equals(Object obj) {

		Account acc = (Account) obj;
		if ((accNum).equals(acc.accNum)) {
			return true;
		} else {
			return false;
		}
	}
	@Override
	public String toString() {
		return "계좌번호" + accNum + "이름" + name + "잔고" + balance + "기본이자" + interest;
	}
}
