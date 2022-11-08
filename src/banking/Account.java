package banking;
/*
계좌정보를 표현한 클래스로 NormalAccount, HighCreditAccount의 부모클래스가 된다. 
 */
public abstract class Account {

	private String accNum;
	private String name;
	private int balance;
	private int rate;
	
	public Account(String accNum, String name, int balance, int rate) {
		super();
		this.accNum = accNum;
		this.name = name;
		this.balance = balance;
		this.rate = rate;
	}

	public String getAccNum() {
		return accNum;
	}

	public void setAccNum(String accNum) {
		this.accNum = accNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}
	
	public void showAccInfo() {
		System.out.println("***계좌정보출력***");
		System.out.println("-----------------------");
		System.out.println("계좌번호: "+ accNum);
		System.out.println("이름: "+ name);
		System.out.println("잔액: "+ balance);
		System.out.println("기본이자%(정수형태로입력): "+ rate);
	}
	
	
}
