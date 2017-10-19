package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Main {

	/*
	 * I got this API key from the API we expect to use:
	 * 9I4PMNR4QJFBDKQX
	 */
	
	private final static String BASE_URL = "https://www.alphavantage.co/";
	
	public static void main(String[] args) {
		URL url;
		try {
			String test = "query?function=TIME_SERIES_INTRADAY&symbol=MSFT&interval=1min&apikey=demo"; 
					
			url = new URL(BASE_URL + test);
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("GET");
	        conn.setRequestProperty("Accept", "application/json");
	        BufferedReader in = new BufferedReader(new InputStreamReader(
                    conn.getInputStream()));
	        
	        String inputLine;
	        while ((inputLine = in.readLine()) != null) {
	            System.out.println(inputLine);
	        }	
	        in.close();
	        	
	        
	        
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
