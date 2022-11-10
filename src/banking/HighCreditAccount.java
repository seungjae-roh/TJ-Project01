package banking;


public class HighCreditAccount extends Account {

	private String iCredit;

	public HighCreditAccount
		(String accNum, String name, int balance, int rate, String iCredit) {
		super(accNum, name, balance, rate);
		this.iCredit = iCredit;
	}
	@Override
	public void showAccInfo() {
		super.showAccInfo();
		System.out.println("신용등급: "+ iCredit);
	}

	@Override
	public void setBalance(int grade, int money) {
		if (grade == ICustomDefine.DEPOSIT_CHECK) {
			if ("A".equalsIgnoreCase(iCredit)) {
				super.setBalance(ICustomDefine.DEPOSIT_CHECK,
						(this.balance * this.interest / 100) + 
							(this.balance * ICustomDefine.A / 100) + money);
				
			}
			else if ("B".equalsIgnoreCase(iCredit)) {
				super.setBalance(ICustomDefine.DEPOSIT_CHECK,
						(this.balance * this.interest / 100) +
							(this.balance * ICustomDefine.B / 100) + money);
				
			}
			else if ("C".equalsIgnoreCase(iCredit)) {
				super.setBalance(ICustomDefine.DEPOSIT_CHECK,
						(this.balance * this.interest / 100) +
							(this.balance * ICustomDefine.C / 100) + money);
				
			} 
			else {
				super.setBalance(ICustomDefine.DEPOSIT_CHECK,
						this.balance * this.interest / 100 + money);
			}
		} else if (grade == ICustomDefine.WITHDRAW_CHECK) {
			
			super.setBalance(ICustomDefine.WITHDRAW_CHECK, money);
			
		}
	}

}
