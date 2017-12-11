package query_functions;

/**
 * This is the interface level abstraction for the function data required by the
 * API.
 * 
 * @author Daniel
 *
 */
public interface QueryFunctionData {

	/**
	 * This will return the "meat" of the query functions, the format is as follows:
	 * "?function=TIME_SERIES_INTRADAY&[param1]=[value1]&[param2]=[value2]"
	 * 
	 * Make sure that your string starts with a "?" or it won't work.
	 * Make sure that your string seperates each parameter with a "&".
	 * 
	 * @param stockSymbol
	 * @return String
	 */
	String createFunctionStringFor(String stockSymbol);
	
	String getJSONObjectHeader();

}
