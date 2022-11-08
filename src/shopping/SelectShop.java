package shopping;

import java.sql.SQLException;

/*
Statement객체를 사용하여 작성한다.
클래스명 : SelectShop
검색할 상품명을 입력받은 후 like를 통해 해당조건에 맞는 레코드만 출력한다. 
출력항목 : 일련번호, 상품명, 가격, 등록일, 제품코드
단, 등록일은 0000-00-00 00:00 형태로 출력해야 한다.	상품가격은 세자리마다 컴마를 찍어준다.
Statement객체는 인파라미터를 통한 동적쿼리를 작성할 수 없으므로 순수 Java변수를 사용한다.
 */
public class SelectShop extends IConnectImpl{

	public SelectShop() {
		super("education", "1234");
	}
	
	@Override
	public void execute() {
		try {
			stmt = con.createStatement();
			String product = scanValue("상품명: ");
			String query = "SELECT g_idx, goods_name, "
					+ " to_char(goods_price, '9,999,000'), "
					+ " to_char(regidate, ('yyyy-mm-dd hh24:mi')), p_code"
					+ " FROM sh_goods WHERE goods_name LIKE '%"+ product+ "%'";
			rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				String idx = rs.getString(1);//g_idx컬럼
				String name = rs.getString(2);//goods_name컬럼
				String price = rs.getString(3);//goods_price컬럼
				String date = rs.getString(4).substring(0,16);//regidate컬럼
				String p_code = rs.getString(5);//g_idx컬럼
				
				System.out.printf("%s %s %s %s %s\n",
						idx, name, price, date, p_code);

			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(); //DB자원반납
		}
	}

	public static void main(String[] args) {

		SelectShop selShop = new SelectShop();
		selShop.execute();
		
	}
}
