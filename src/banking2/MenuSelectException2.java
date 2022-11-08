package banking2;
/*
개발자정의 예외클래
 */
public class MenuSelectException2 extends Exception{
	
	public MenuSelectException2() {
		super("1부터 5사이의 숫자를 입력하세요.");
	}
	
}
