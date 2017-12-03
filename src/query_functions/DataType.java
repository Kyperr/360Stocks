package query_functions;

/**
 * This enum serves to fulfill the optional field supported by the api. Here is
 * the explanation per the api documentation:
 * 
 * "By default, datatype=json. Strings json and csv are accepted with the
 * following specifications: json returns the daily time series in JSON format;
 * csv returns the time series as a CSV (comma separated value) file."
 * 
 * @author Daniel
 *
 */
public enum DataType {
	JSON("json"), CSV("csv");
	
	private final String literal;

	private DataType(String literal) {
		this.literal = literal;
	}

	public String getLiteral() {
		return this.literal;
	}
}
