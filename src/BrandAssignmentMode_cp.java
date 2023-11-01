package ensyu2023;


//銘柄指定モードのSQL文を保持するクラス
public class BrandAssignmentMode extends TemplateExecutor {


	String selectBrand2;
	String sql;


	public String createSQL(String[] selectBrand2) {
		//指定された銘柄の銘柄コード、銘柄名、市場、終値を抽出するSQL文を作成する
		
		sql = "SELECT DSCR_CD ,DSCR_NM ,MARKET_NM ,LAST \n"
				+ "FROM STOCK_PRICE_TBL\n"
				+ "INNER JOIN MARKET ON STOCK_PRICE_TBL.MARKET_CD = MARKET.MARKET_CD\n"
				+ "WHERE DSCR_CD= '"+selectBrand2[0]+"'";


		return sql;

	}
}



