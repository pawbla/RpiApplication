package sysInfo;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ErrorMessage {
	
	/**
	 * Logger
	 */
	/*private final Logger logger = LogManager.getLogger(this.getClass().getName());

	private static final String SELECT_LOGS_QUERY = "SELECT * FROM logs ORDER BY id DESC";
	private static final int LIMIT_REMOVE_SQL = 100;
	private static final int THRESHOLD = 60;
	private static final String REMOVE_QUERY = "DELETE FROM logs WHERE id IN(SELECT id FROM logs ORDER BY id LIMIT "+ LIMIT_REMOVE_SQL + ")";
	private static final String SUM_ROWS_QUERY = "SELECT COUNT(*) FROM logs"; 
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	public ErrorMessage( ) {
		logger.debug("Create constructor");
	}
	
	public List<Map<String, Object>> getWarnigs() {
		return jdbcTemplate.queryForList(SELECT_LOGS_QUERY);
	}
	
	@Scheduled(cron = "0 0 1 * * *")
	public void removeLogs() {
		logger.debug("Checking logs amount for removing old traces from database.");
		int rows = jdbcTemplate.queryForObject(SUM_ROWS_QUERY, Integer.class);
		logger.debug("Calculated rows: " + rows);
		while (rows > THRESHOLD) {
			logger.debug("Remowe rows: " + rows);
			jdbcTemplate.update(REMOVE_QUERY);
			rows = jdbcTemplate.queryForObject(SUM_ROWS_QUERY, Integer.class);
		}
	}*/
}
