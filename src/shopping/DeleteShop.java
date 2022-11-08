package shopping;

import java.sql.SQLException;

public class DeleteShop extends IConnectImpl{

	public DeleteShop() {
		super("education", "1234");
	}
	
	@Override
	public void execute() {

		try {
			stmt = con.createStatement();			
			String query = "DELETE FROM sh_goods WHERE g_idx = p_idx";
			/*
			insert, delete, update와 같이 행의 변화가 발생되는 쿼리문을 
			실행할때는 executeUpdate() 메서드를 사용한다. 해당 쿼리를
			실행한 후 영향을 받은 레코드의 갯수가 int타입으로 반환된다.		
			*/
			int affected = stmt.executeUpdate(query);			
			System.out.println(affected +"행이 삭제됨");
			close();
		}
		catch(SQLException e) {
			System.out.println("쿼리실행에 문제가 발생하였습니다.");
			e.printStackTrace();
		}
	} 
	

	public static void main(String[] args) {
		new DeleteShop().execute();
	}

}
