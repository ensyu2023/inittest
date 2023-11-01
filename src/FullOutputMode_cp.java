package ensyu2023;

//全出力モードクラスのSQL文を保持するクラス
public class FullOutputMode extends TemplateExecutor {

	public String createSQL(String[] select) {
		//全銘柄の銘柄コード、銘柄名、市場、終値を抽出するSQL文を作成する
		
		String sql = "SELECT DSCR_CD ,DSCR_NM ,MARKET_NM ,LAST \n"
				+ "FROM STOCK_PRICE_TBL\n"
				+ "INNER JOIN MARKET ON STOCK_PRICE_TBL.MARKET_CD = MARKET.MARKET_CD";

		return sql;

	}

}

