package query_functions;

public enum SeriesType {
	CLOSE("close"), OPEN("open"), HIGH("high"), LOW("low");

	private final String literal;

	private SeriesType(String literal) {
		this.literal = literal;
	}

	public String getLiteral() {
		return this.literal;
	}

}
