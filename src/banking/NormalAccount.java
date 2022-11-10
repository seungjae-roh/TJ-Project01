package banking;

/*
Account의 자식클래스로 보통예금계좌를 의미한다.
생성자를 통해서 이율정보(이자비율의정보)를 초기화 할수있도록 정의한다
 */
public class NormalAccount extends Account {

	public NormalAccount(String iAccount, String iName, int iBalance, int iRate) {
		super(iAccount, iName, iBalance, iRate);
	}
	@Override
		public void showAccInfo() {
			super.showAccInfo();
		}
	@Override
	public void setBalance(int temp, int money) {
		if(temp == ICustomDefine.DEPOSIT_CHECK) {
			super.setBalance(ICustomDefine.DEPOSIT_CHECK,
					this.balance * this.interest / 100 + money);
		}
		else if (temp == ICustomDefine.WITHDRAW_CHECK) { 
			super.setBalance(ICustomDefine.WITHDRAW_CHECK, money);
		}
	}
	

}
