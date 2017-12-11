package query_functions;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import query_functions.TimeSeriesIntradayData.IntradayInterval;

public class SimpleMovingAveragesData extends AbstractQueryFunctionData {

	public final static String FUNCTION_NAME = "SMA";

	private final Interval interval;
	private final Integer time_period;
	private final SeriesType series_type;

	/**
	 * Constructs a TimeSeriesIntradayData object, requiring the IntradayInterval,
	 * an optional field of the output size, and an optional field of the datatype
	 * the result should be returned in.
	 * 
	 * @param interval
	 * @param outputSize
	 * @param dataType
	 */
	public SimpleMovingAveragesData(Interval interval, Integer timePeriod, SeriesType seriesType) {
		super(FUNCTION_NAME);
		this.interval = interval;
		this.time_period = timePeriod;
		this.series_type = seriesType;
	}
	@Override
	protected Map<String, String> getParams() {
		Map<String, String> paramSet = new HashMap<>();
		paramSet.put("interval", interval.getLiteral());
		paramSet.put("time_period", time_period.toString());
		paramSet.put("series_type", series_type.getLiteral());
		
		return paramSet;
	}

}
