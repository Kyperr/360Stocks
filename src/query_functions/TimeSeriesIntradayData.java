package query_functions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Map.Entry;

/*
 * This is the first fully implemented QueryFunctionData object. It is fully
 * formed and serves as an example on how to correctly create more function. For
 * other functions, please see the documentation for the API we are using. The
 * api documentation will provide the necessary parameters for the appropriate
 * function. Documentation url:
 * 
 * https://www.alphavantage.co/documentation/
 * 
 * You might wonder why there appear to be 2 fields missing (symbol and apikey). 
 * The apikey is added in after the functiondata is applied to the query. This is
 * because the apikey has nothing to do with the function, so it did not seem
 * appropriate to include it in each function(redundant). The reason the symbol is
 * not given until it is time to create the string representation of the function
 * is because that will give us a certain level of reusability. In otherwords, we
 * can create one instance of this object set for 5min intervals and use that for
 * every stock. It saves on memory usage.
 * 
 * Additional note: There is not immediate reason that this object is immutable.
 * If you wanted to, you could totally add getters/setters for the additional fields.
 * 
 * @author Daniel
 *
 */

/**
 * From the API: "This API [function] returns intraday time series (timestamp,
 * open, high, low, close, volume) of the equity specified, updated realtime."
 * 
 * This class
 * 
 * @author Daniel
 *
 */
public class TimeSeriesIntradayData extends AbstractQueryFunctionData {

	private final static String FUNCTION_NAME = "TIME_SERIES_INTRADAY";

	private final IntradayInterval interval;

	private final Optional<OutputSize> outputSize;
	private final Optional<DataType> dataType;

	/**
	 * Constructs a TimeSeriesIntradayData object, requiring the IntradayInterval,
	 * an optional field of the output size, and an optional field of the datatype
	 * the result should be returned in.
	 * 
	 * @param interval
	 * @param outputSize
	 * @param dataType
	 */
	public TimeSeriesIntradayData(IntradayInterval interval, Optional<OutputSize> outputSize,
			Optional<DataType> dataType) {
		super(FUNCTION_NAME);
		this.interval = interval;
		this.outputSize = outputSize;
		this.dataType = dataType;
	}

	/**
	 * Overridden constructor for a simpler invocation.
	 * 
	 * @param interval
	 */
	public TimeSeriesIntradayData(IntradayInterval interval) {
		this(interval, Optional.empty(), Optional.empty());
	}

	protected Map<String, String> getParams() {
		Map<String, String> paramSet = new HashMap<>();
		paramSet.put("interval", interval.getLiteral());
		
		// If it is present, add it.
		if (outputSize.isPresent()) {
			paramSet.put("outputsize", outputSize.get().getLiteral());
		}
		
		// If it is present, add it.
		if (dataType.isPresent()) {
			paramSet.put("datatype", dataType.get().getLiteral());
		}
		
		return paramSet;
	}

	// Begin IntradayInterval

	/*
	 * This is an enum because the API only supports a few distince values. It might
	 * seem reasonable to make it a standalone enum like the OutputSize enum. The
	 * problem is that the other functions that have an interval param have a
	 * different set of valid intervals (ie: week, month) that wouldn't be valid for
	 * this function.
	 * 
	 *
	 */
	public enum IntradayInterval {
		OneMin("1min"), FiveMin("5min"), FifteenMin("15min"), ThirtyMin("30min"), SixtyMin("60min");

		private final String literal;

		private IntradayInterval(String literal) {
			this.literal = literal;
		}

		public String getLiteral() {
			return this.literal;
		}

	}

	@Override
	public String getJSONObjectHeader() {
		return "Time Series (" + interval.getLiteral() + ")";
	}

}
