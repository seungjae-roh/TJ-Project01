package banking;

public interface ICustomDefine {

	//case절 인터페이스형 상수
	int MAKE = 1, DEPOSIT = 2, WITHDRAW = 3, INQUIRE = 4, AUTO_SAVE = 5, EXIT = 6;
	//신용등급에 따른 이자율 계산을 위한 상수 설정
	int A = 7, B = 4, C = 2;
	//입금과 출력을 위한 체크용 상수
	int DEPOSIT_CHECK= 1, WITHDRAW_CHECK= 2;
}
