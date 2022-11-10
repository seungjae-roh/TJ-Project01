package banking;

/*
자동저장을 위한 클래스. Thread클래스를 상속한다.
 */
public class AutoSaver extends Thread {

	AccountManager accMgr;
	
	
	public AutoSaver(AccountManager accMgr) {
		this.accMgr = accMgr;
	}
	
	
	@Override
	public void run() {
		//interupt가 되기 전까지 무한반복
		while(true) {
			
			System.out.println("AutoSaveAccount.txt가 생성되었습니다");
			try {
				//5초마다 자동저장을 한다.
				sleep(5000);
				accMgr.saveThread();
				System.out.println("자동저장기능이 5초마다 작동 중 입니다.");
			}
			catch (InterruptedException e) {
				System.out.println("자동저장종료");
				break;
			}
			
		}
	}

}
