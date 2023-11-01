package ensyu2023.model;

public class StockPrice {
	private String dscrCd;
	private String dscrNm;
	private String marketNm;
	private double last;

	public StockPrice(String dscrCd, String dscrNm, String marketNm, double last) {
		this.dscrCd = dscrCd;
		this.dscrNm = dscrNm;
		this.marketNm = marketNm;
		this.last = last;
	}

	public String getDscrCd() {
		return dscrCd;
	}

	public String getDscrNm() {
		return dscrNm;
	}

	public String getMarketNm() {
		return marketNm;
	}

	public double getLast() {
		return last;
	}

}
