package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.cert.Certificate;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

public class Main {

	private static String[] STOCK_SYMBOLS = {"AAPL", "AMZN", "TSLA"};

	public static void main(String[] args) {
		Stock stock;
		
		try {
			for (String symbol : STOCK_SYMBOLS) {

				stock = YahooFinance.get(symbol);
				stock.print();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
