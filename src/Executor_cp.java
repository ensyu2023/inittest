package ensyu2023;

import java.sql.SQLException;
import java.util.ArrayList;

import ensyu2023.model.StockPrice;

interface  Executor {
	
	 ArrayList<StockPrice> output(String[] args) throws SQLException,Exception;

}

