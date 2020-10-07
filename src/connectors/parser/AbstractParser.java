package connectors.parser;

import java.util.HashMap;
import java.util.Map;

import connectors.Values;

public abstract class AbstractParser implements ParserInterface {

	private Map<String, String> parsed;
	
	public AbstractParser() {
		parsed = new HashMap<String, String>();
	}
	
	protected void addParsed(Values key, String value) {
		parsed.put(key.getValue(), value);
	}
	
	@Override
	public Map<String, String> getParsed() {
		return parsed;
	}
}
