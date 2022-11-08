package shopping;

import java.sql.SQLException;

public class UpdateShop extends IConnectImpl {

	public UpdateShop() {
		super("education", "1234");
	}
	@Override
	public void execute() {
		try {
			//쿼리실행을 위한 Statement객체생성 및 쿼리문 작성
			stmt = con.createStatement();
			String sql = "UPDATE g_idx "
					+ " SET "
					+ " 	a_name = goods_name,"
					+ "		a_price = goods_price,"
					+ "		a_code = p_code "
					+ " WHERE goods_name = a_name ";
			System.out.println("sql="+ sql);			
			
			//쿼리문 실행
			int affected = stmt.executeUpdate(sql);
			System.out.println(affected +"행이 업데이트 됨");
		}
		catch(SQLException e) {
			System.out.println("쿼리오류");
			e.printStackTrace();
		}
		catch(Exception e) {
			System.out.println("알수없는 예외발생");
			e.printStackTrace();
		}
		finally {
			close();
		}
	}
	
	public static void main(String[] args) {

		new UpdateShop().execute();
	}

}
