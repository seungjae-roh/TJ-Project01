package banking2;

import java.util.Scanner;

public class HighCreditAccount2 extends Account2 {
	int inter;
	String rating;

	public HighCreditAccount2(String accountNum, String name, int balance, int inter, String rating) {
		super(accountNum, name, balance);
		this.inter = inter;
		this.rating = rating;
	}

	@Override
	public void showAccInfo() {
		super.showAccInfo();
		System.out.println("이자:" + inter + "%");
		System.out.println("신용등급(A,B,C등급):" + rating);
		System.out.println("=========================================================");
	}
	@Override
	public void setBalance(int grade, int money) {
		if (grade == ICustomDefine2.DEPOSITS) {
			if ("A".equalsIgnoreCase(rating)) {
				super.setBalance(ICustomDefine2.DEPOSITS,
						(this.balance * inter / 100) + 
							(this.balance * ICustomDefine2.A / 100) + money);
				
			} else if ("B".equalsIgnoreCase(rating)) {
				super.setBalance(ICustomDefine2.DEPOSITS,
						(this.balance * inter / 100) +
							(this.balance * ICustomDefine2.B / 100) + money);
				
			} else if ("C".equalsIgnoreCase(rating)) {
				super.setBalance(ICustomDefine2.DEPOSITS,
						(this.balance * inter / 100) +
							(this.balance * ICustomDefine2.C / 100) + money);
				
			} else {
				super.setBalance(ICustomDefine2.DEPOSITS,
						this.balance * inter / 100 + money);
			}
		} else if (grade == ICustomDefine2.WITHDRAWS) {
			
			super.setBalance(ICustomDefine2.WITHDRAWS, money);
			
		}
	}

}
