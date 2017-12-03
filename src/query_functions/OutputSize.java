package query_functions;

/**
 * This enum is a standalone enum because it will be used in multiple functions,
 * so it made sense to have it in a single location and only done once. Here is
 * a description of this field as stated by the api:
 * 
 * "By default, outputsize=compact. Strings compact and full are accepted with
 * the following specifications: compact returns only the latest 100 data points
 * in the intraday time series; full returns the full-length intraday time
 * series. The "compact" option is recommended if you would like to reduce the
 * data size of each API call."
 * 
 * @author Daniel
 *
 */
public enum OutputSize {
	COMPACT("compact"), FULL("full");
	
	private final String literal;

	private OutputSize(String literal) {
		this.literal = literal;
	}

	public String getLiteral() {
		return this.literal;
	}
}
