package banking2;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;

public abstract class Account2 implements Serializable{
	//멤버변수 선언
	String accNum;
	String name;
	int balance;

	public Account2(String accNum, String name, int balance) {
		//생성자
		this.accNum = accNum;
		this.name = name;
		this.balance = balance;
	}

	public void showAccInfo() {
		System.out.println("=========================================================");
		System.out.printf("계좌번호: %s\n 이름 : %s\n 잔고: %d\n 기본이자: %d\n", accNum, name, balance);
		
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int idx, int money) {
		if (idx == ICustomDefine2.DEPOSITS) {
			this.balance += money;
		} else if (idx == ICustomDefine2.WITHDRAWS) {
			if (this.balance < money) {
				this.balance = 0;
			} else {
				this.balance -= money;
			}
		}
	}

	@Override
	public int hashCode() {
		int returnHash = Objects.hash(this.accNum);
		return returnHash;
	}

	public boolean equals(Object obj) {

		Account2 acc = (Account2) obj;
		if ((this.accNum).equals(acc.accNum)) {
			return true;
		} else {
			return false;
		}
	}
	@Override
	public String toString() {
		return "계좌번호"+ accNum + "이름" + name + "잔고" + balance;
	}
}
