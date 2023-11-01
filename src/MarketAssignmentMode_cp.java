package ensyu2023;


//市場指定モードのSQL文を保持するクラス
public class MarketAssignmentMode extends TemplateExecutor {


	String selectMarket;
	String sql;


	public String createSQL(String[] selectMarket) {
		//指定された市場に上場する銘柄の銘柄コード、銘柄名、市場、終値を抽出を作成する


		sql = "SELECT DSCR_CD ,DSCR_NM ,MARKET_NM ,LAST \n"
				+ "FROM STOCK_PRICE_TBL\n"
				+ "INNER JOIN MARKET ON STOCK_PRICE_TBL.MARKET_CD = MARKET.MARKET_CD\n"
				+ "WHERE STOCK_PRICE_TBL.MARKET_CD = '"+selectMarket[0]+"'";


		return sql;
	}
}




