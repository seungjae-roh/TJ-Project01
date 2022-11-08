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

}
