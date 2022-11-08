package banking2;

import java.util.Scanner;

public class NormalAccount2 extends Account2 {
	int inter;
	
	public NormalAccount2(String accountNum, String name, int balance, int inter) {
		super(accountNum, name, balance);
		this.inter = inter;
	}

	@Override
	public void showAccInfo() {
		super.showAccInfo();
		System.out.println("이자: "+inter+"%");
		System.out.println("==================================");
		
		
	}

	@Override
	public void setBalance(int temp, int money) {
		if(temp == ICustomDefine2.DEPOSITS ) {
			super.setBalance(ICustomDefine2.DEPOSITS,
					this.balance * inter/100 + money);
		}
		else if (temp == ICustomDefine2.WITHDRAWS) {
			super.setBalance(ICustomDefine2.WITHDRAWS, money);
		}
	}
	
	
}
