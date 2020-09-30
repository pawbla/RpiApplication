package connectors.parser;

import java.util.Map;

import org.json.JSONException;

import connectors.models.Response;

public interface ParserInterface {
	void parse(Response response) throws JSONException;
	Map<String, String> getParsed();
}
