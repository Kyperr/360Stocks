package query_functions;

public enum Interval {
	OneMin("1min"), FiveMin("5min"), FifteenMin("15min"), ThirtyMin("30min"), SixtyMin("60min"), Daily("daily"), Weekly(
			"weekly"), Monthly("monthly");

	private final String literal;

	private Interval(String literal) {
		this.literal = literal;
	}

	public String getLiteral() {
		return this.literal;
	}

}
