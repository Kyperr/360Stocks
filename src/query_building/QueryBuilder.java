package query_building;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import query_functions.QueryFunctionData;

public class QueryBuilder {

	private final static DefaultHttpClient httpClient = new DefaultHttpClient();

	private final String url;
	private final String apiKey;

	public QueryBuilder(String url, String apiKey) {
		this.url = url;
		this.apiKey = apiKey;
	}

	public String buildQuery(String symbol, QueryFunctionData qFunctionData) {
		StringBuilder sb = new StringBuilder();

		sb.append(this.url);
		sb.append("query");
		sb.append(qFunctionData.createFunctionStringFor(symbol));
		sb.append("&apikey=");
		sb.append(this.apiKey);

		return sb.toString();

	}

	public BufferedReader executeFunction(String symbol, QueryFunctionData qFunctionData)
			throws IllegalStateException, IOException {

		// Obtain String that represents the query.

		/*
		 * This line obtains the string representation of the query, it should looks
		 * something like:
		 * 
		 * "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=MSFT&interval=1min&apikey=demo"
		 * 
		 */
		String query = buildQuery(symbol, qFunctionData);

		// System.out.println(query);

		// Below is setting up for a connection, it is not connected yet.

		HttpGet getRequest = new HttpGet(query); // Query string is inserted here.
		getRequest.addHeader("accept", "application/json");

		// This line creates the connection.
		HttpResponse response = httpClient.execute(getRequest);

		// If something went wrong, this will give us more information about it.
		if (response.getStatusLine().getStatusCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
		}

		// This is a (probably) large object that contains the result of our query.
		BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));

		/*
		 * Usually the connection would be close here. However, we are using a
		 * BufferedReader which continues to read from the connection stream, we need to
		 * maintain the connection, therefore, we will call close at a later time.
		 */

		return br;
	}

}
