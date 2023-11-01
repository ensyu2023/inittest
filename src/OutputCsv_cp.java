package ensyu2023;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import ensyu2023.model.StockPrice;

// output()で抽出したDBをCSVファイルに出力するクラス
public class OutputCsv {

	String today;
	//private Logger logger;

	public void exportCsv(ArrayList<StockPrice> PricesList) throws IOException {
		//CSVファイルを作成し、作成した日時とヘッダー部、DBを書き込むメソッド

		//ファイル名に日時を入れるため、現在時刻をyyyy年MM月dd日HH時mm分ss秒の形で取得する
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH時mm分ss秒");
		String formattedDate = sdf.format(date);
		String fileName = "株式時価情報_" + formattedDate + ".csv";

		// 出力ファイルの作成
		FileWriter fw = new FileWriter(fileName, false);
		// PrintWriterクラスのオブジェクトを生成
		PrintWriter pw = new PrintWriter(new BufferedWriter(fw));

		// ヘッダーの指定
		pw.print("DSCR_CD");
		pw.print(",");
		pw.print("DSCR_NM");
		pw.print(",");
		pw.print("MARKET_NM");
		pw.print(",");
		pw.print("LAST");
		pw.println();

		//引数で受け取ったpricesListの中身を書き込む
		for (int i = 0; i < PricesList.size(); i++) {
			pw.print(PricesList.get(i).getDscrCd());
			pw.print(",");
			pw.print(PricesList.get(i).getDscrNm());
			pw.print(",");
			pw.print(PricesList.get(i).getMarketNm());
			pw.print(",");
			//Lastがnullの場合は「-」を出力
			if (PricesList.get(i).getLast() == 0) {
				pw.print("-");
			} else {
				pw.print(PricesList.get(i).getLast());
			}
			pw.println();
		}
		// ファイルを閉じる
		pw.close();
		// 出力確認用のメッセージ
		//logger.log(Level.INFO,"csvファイルに出力完了しました");

	}
}
