package shopping;

import java.util.Scanner;

/*
PreparedStatement객체를 사용하여 작성한다.
클래스명 : InsertShop
상품명, 상품가격, 상품코드를 scanValue() 메소드로 입력받아 사용한다. 
입력이 완료되면 입력된 행의 갯수를 반환하여 출력한다. 
 */
public class InsertShop extends IConnectImpl{
	
	//생성자 : 부모클래스의 생성자를 호출하여 연결한다.
	public InsertShop() {
		super("education", "1234");
	}
	
	@Override
	public void execute() {
		try {
			//1.쿼리문 준비 : 값의 세팅이 필요한 부분을 ?(인파라미터)로 기술한다.
			String query =
					"INSERT INTO sh_goods VALUES"
					+ "(seq_total_idx.nextval, ?, ?, sysdate, ?)";
			
			//2.prepared객체생성 : 객체 생성시 준비한 쿼리문을 인수로 전달한다.
			psmt = con.prepareStatement(query);
			
			//3.사용자로부터 insert할 내용을 입력받는다. 
			Scanner scan = new Scanner(System.in);
			
			String p_name = scanValue("상품명: ");
			String p_price = scanValue("상품가격: ");
			String p_code = scanValue("상품코드: ");			
			
			/*
			4.인파라미터 설정 : ?의 순서대로 설정하고, 인덱스는 1부터 
				시작한다. 
				자료형이 
					숫자면 setInt()
					문자면 setString()
					날짜면 setDate()
				입력값이 문자 혹은 날짜면 자동으로 싱글쿼테이션이 추가된다.
			 */
			psmt.setString(1, p_name);
			psmt.setString(2, p_price);
			psmt.setString(3, p_code);
			
			//5.쿼리실행 및 결과값 반환
			int affected = psmt.executeUpdate();
			System.out.println(affected +"행이 입력되었습니다.");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			close();
		}
	}
	

	public static void main(String[] args) {

		new InsertShop().execute();
	}

}
