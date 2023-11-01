package ensyu2023;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import ensyu2023.model.StockPrice;

//全ての処理をまとめているクラス
public class Main {

	/*引数１・引数２の入力値チェックをし、
	 * 条件に合わせてメソッドを呼び、値を返してもらうメソッド
	 * */

	/*引数1には出力モードを選択する1～3が入り、
	 * 引数2には、コードの番号が入る
	 */
	//親クラスの変数宣言
	static TemplateExecutor mode;
	private static Logger logger;

	public static void main(String[] args) {

		ArrayList<StockPrice> pricesList = null;
		int selectMode = 0;
		int selectCode = 0;
		int digitsCheck;
		String[] selectCode2 = null;
		String sql;
		// Loggerクラスのインスタンスを生成
		Logger logger = Logger.getLogger("ensyu2023");

		try {
			FileHandler fHandler = new FileHandler("ensyu2023.log", true);
			fHandler.setFormatter(new SimpleFormatter());
			logger.addHandler(fHandler);
			// ログレベルの設定
            logger.setLevel(Level.ALL);
		
			// 例外をスロー
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			//引数1に文字列が含まれている場合の例外処理			
			selectMode = Integer.parseInt(args[0]);
		} catch (NumberFormatException e) {
			logger.log(Level.SEVERE, "文字列は入力できません。プログラムを終了します。", e);
			System.exit(1);

		}

		if (selectMode == 1) {
			//引数1が1の場合、全出力モードメソッドが起動

			//全出力モード小クラスのインスタンス生成
			mode = new FullOutputMode();

		} else if (selectMode == 2) {
			//引数1が2の場合、銘柄指定モードが起動

			try {
				//引数2に文字列が含まれている場合の例外処理
				selectCode = Integer.parseInt(args[1]);
			} catch (NumberFormatException e) {
				logger.log(Level.SEVERE, "文字列は入力できません。プログラムを終了します。", e);
				System.exit(1);
			}

			//引数2の桁数をチェックするため
			digitsCheck = String.valueOf(selectCode).length();

			//createSQLの引数に入れるため、String型配列に変換			
			selectCode2 = new String[1];
			selectCode2[0] = String.valueOf(selectCode);

			if (digitsCheck != 4) {
				//4桁以外の数字が入力された場合の処理
				logger.log(Level.SEVERE, "4桁のコードを入力してください。プログラムを終了します。");
				System.exit(1);

			} else {
				//適切に銘柄コードが入力された場合、データを抽出する
				//logger.log(Level.INFO, "指定のデータを抽出します。");
			}
			//銘柄指定モード小クラスのインスタンス生成
			mode = new BrandAssignmentMode();

		} else if (selectMode == 3) {
			//引数1が3の場合、市場指定モード起動。

			try {
				//引数2に文字列が含まれている場合の例外処理
				selectCode = Integer.parseInt(args[1]);
			} catch (NumberFormatException e) {
				logger.log(Level.SEVERE, "文字列は入力できません。プログラムを終了します。", e);
				System.exit(1);
			}

			//createSQLの引数に入れるため、String型配列に変換
			selectCode2 = new String[1];
			selectCode2[0] = String.valueOf(selectCode);

			if (selectCode == 10 || selectCode == 11 ||
					selectCode == 12 || selectCode == 13 || selectCode == 30) {
				//適切な入力がされた場合データを抽出する
				//logger.log(Level.INFO, "指定のデータを抽出します。");

			} else {
				//入力値が10,11,12,13,30に当てはまらない場合の処理
				logger.log(Level.SEVERE, "入力された市場コードは存在しません。プログラムを終了します。");
				System.exit(1);
			}
			////市場指定モード小クラスのインスタンス生成
			mode = new MarketAssignmentMode();

		} else {
			//引数1が1～3に当てはまらない場合の処理
			logger.log(Level.SEVERE, "入力値が間違っています。プログラムを終了します。");
			System.exit(1);
		}

		try {

			/*コード番号を引数に設定し、
			 * 指定したモードクラスのcreateSQL()で返されたsqlをoutput()で実行。
			 *抽出したデータをpricesListとして返す*/
			pricesList = mode.output(selectCode2);

		} catch (SQLException e) {
			logger.log(Level.SEVERE, "SQL接続時に例外が発生しました。プログラムを終了します。", e);
			System.exit(1);
		} catch (Exception e) {
			e.printStackTrace();
			logger.log(Level.SEVERE, "例外が発生しました。プログラムを終了します。", e);
			System.exit(1);
		}

		OutputCsv outputCsv = new OutputCsv();
		try {
			/* pricesListに格納されたデータをcsvに出力するため、
			   exportCsvメソッドに引数で渡す */
			outputCsv.exportCsv(pricesList);

			// ファイル出力に失敗したときの処理   
		} catch (IOException e) {
			logger.log(Level.SEVERE, "出力に失敗しました。プログラムを終了します。", e);
			System.exit(1);
		}
		logger.log(Level.INFO, "正常に処理を終了しました。");
	}
}
