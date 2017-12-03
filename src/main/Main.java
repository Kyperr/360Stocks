package main;

import java.io.BufferedReader;
import java.io.IOException;

import query_building.QueryBuilder;
import query_functions.QueryFunctionData;
import query_functions.TimeSeriesIntradayData;
import query_functions.TimeSeriesIntradayData.IntradayInterval;

public class Main {

	// https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=MSFT&interval=1min&apikey=demo

	public final static String API_URL = "https://www.alphavantage.co/";
	public final static String API_KEY = "9Z4MWR6UJT9JYE4W"; // This is our API key.

	public static void main(String[] args) {

		// The object we created to build the query string for us.
		QueryBuilder qb = new QueryBuilder(API_URL, API_KEY);

		// The data for the specific function we wish to call, and its args.
		QueryFunctionData intraDay = new TimeSeriesIntradayData(IntradayInterval.OneMin);

		// Execute query and receive a BufferedReader object that contains the result.
		BufferedReader br;
		try {
			br = qb.executeFunction("MSFT", intraDay);

			// Print out the stuff in our buffered reader.
			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}
			
			// Because we are using a buffered reader, we can close the stream here.
			br.close();
			
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	

}
