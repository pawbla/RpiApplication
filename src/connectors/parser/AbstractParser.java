package connectors.parser;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractParser implements ParserInterface {

	private Map<String, String> parsed;
	
	public AbstractParser() {
		parsed = new HashMap<String, String>();
	}
	
	protected void addParsed(String key, String value) {
		parsed.put(key, value);
	}
	
	@Override
	public Map<String, String> getParsed() {
		return parsed;
	}
}
