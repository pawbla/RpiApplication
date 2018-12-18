package sysInfo;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class ErrorMessage {

	private static final String SELECT_LOGS_QUERY = "SELECT * FROM logs";
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	public List<Map<String, Object>> getWarnigs() {
		return jdbcTemplate.queryForList(SELECT_LOGS_QUERY);
	}
}
