package banking2;

public class AutoSaver extends Thread {

	AccountManager2 accMgr;
	
	
	public AutoSaver(AccountManager2 mgr) {
		accMgr = mgr;
	}

	@Override
	public void run() {
		
		while(true) {
			
			try {
				sleep(5000);
				accMgr.saveThread();
				System.out.println("AutoSaveAccount.txt가 생성되었습니다");
				System.out.println("자동저장기능이 5초마다 작동 중 입니다.");
			}
			catch (InterruptedException e) {
				System.out.println("자동저장종료");
				break;
			}
			
		}
	}

}
