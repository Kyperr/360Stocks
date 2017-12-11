package query_functions;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import query_functions.TimeSeriesIntradayData.IntradayInterval;

public class RelativeStrengthIndex extends AbstractQueryFunctionData {

	public final static String FUNCTION_NAME = "RSI";

	private final Interval interval;
	private final Integer time_period;
	private final SeriesType series_type;

	public RelativeStrengthIndex(Interval interval, Integer timePeriod, SeriesType seriesType) {
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
	@Override
	public String getJSONObjectHeader() {
		return "Technical Analysis: RSI";
	}

}
