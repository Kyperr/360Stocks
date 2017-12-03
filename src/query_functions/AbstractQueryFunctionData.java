package query_functions;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Abstract class level abstraction for the function data required by the API.
 * 
 * @author Daniel
 *
 */
public abstract class AbstractQueryFunctionData implements QueryFunctionData {

	/*
	 * These are the fields that all functions will have.
	 */

	/**
	 * Every QueryFunctionData is going to need a function name. It is really the
	 * only field that is common amongst all of the functions. It is protected so it
	 * can be used by it's children, but isn't public.
	 */
	protected final String functionName;

	protected AbstractQueryFunctionData(String functionName) {
		this.functionName = functionName;
	}

	@Override
	public String createFunctionStringFor(String stockSymbol) {
		StringBuilder sb = new StringBuilder();

		sb.append("?function=");
		sb.append(this.functionName);
		
		sb.append("&symbol=");
		sb.append(stockSymbol);

		// Add the params
		Map<String, String> paramSet = getParams();
		
		for (String param : paramSet.keySet()) {
			sb.append("&");
			sb.append(param);
			sb.append("=");
			sb.append(paramSet.get(param));
		}

		return sb.toString();
	}

	/**
	 * This should return a set of the parameters needed for this function.
	 * 
	 * @return Map<String, String>
	 */
	protected abstract Map<String, String> getParams();

}
