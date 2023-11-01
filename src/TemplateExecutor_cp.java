package ensyu2023;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import ensyu2023.model.StockPrice;

/* 抽出したDBをpricesListに格納しMainクラスに返す。
 * 各モードクラスの親クラス。
 * 空のcreateSQL()を保持する。
 */
abstract public class TemplateExecutor implements Executor {
	
	
	String dscrCd;
	String dscrNm;
	String marketNm;
	Double last;
	Connection conn;
	private Logger logger;
	
	public ArrayList<StockPrice> output(String[] args) throws SQLException,Exception {
		//JDBCに接続、抽出したDBをpricesListに格納しMainクラスに返すメソッド
				
		//DBに対応したかたちでStockPriceクラスのデータを管理するため、pricesListを生成
		ArrayList<StockPrice> pricesList = new ArrayList<StockPrice>();

		 ResourceBundle rb = ResourceBundle.getBundle("JDBC");
		 
	    
		//JDBC接続	
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException ex) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
		try (Connection conn = DriverManager.getConnection(rb.getString("JDBC_URL"),rb.getString("DB_USER"),rb.getString("DB_PASS"))) {
			//SELECT文
			
			String sql = createSQL(args);

			//SQL文を送信
			PreparedStatement pStmt = conn.prepareStatement(sql);
			//抽出したデータを取得
			ResultSet rs = pStmt.executeQuery();

			//StockPriceクラスにデータを1件ずつ格納
			while (rs.next()) {
				dscrCd = rs.getString("DSCR_CD");
				dscrNm = rs.getString("DSCR_NM");
				marketNm = rs.getString("MARKET_NM");
				last = rs.getDouble("LAST");
				StockPrice prices = new StockPrice(dscrCd, dscrNm, marketNm, last);
				pricesList.add(prices);
			}
			//logger.log(Level.INFO,"DBの抽出が完了しました");
		
				
		}		
		return pricesList;				
	}
	//各モードクラスでSQL文を作成するための箱
	abstract protected String createSQL(String[] args);
}


