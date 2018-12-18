package sysInfo;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class ErrorMessage {
	private final Logger logger = LogManager.getLogger(this.getClass().getName());
	
	private String path;
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	public ErrorMessage () {
		//List<Map<String, Object>> rows = jdbcTemplate.queryForList("SELECT * FROM logs");
	    /*org.apache.logging.log4j.core.Logger loggerImpl = (org.apache.logging.log4j.core.Logger) logger;
	    Appender appender = loggerImpl.getAppenders().get("WarningRollingFile");
        path = ((RollingFileAppender) appender).getFileName();*/
	}
	
	public List<String> getWarnigs(int threshold) {
		System.out.println("=== GET ==");
		List<Map<String, Object>> rows = jdbcTemplate.queryForList("SELECT * FROM logs");
		System.out.println("=== GET SOZE ==" + rows.size());
		for (Map<String, Object> row : rows) {
			System.out.println("===" + row.get("date") + row.get("message"));
		}
		/*List<String> errors = new ArrayList<String>();
		try(BufferedReader br = new BufferedReader(new FileReader(path))) {
		    br.lines().sorted(Collections.reverseOrder()).limit(threshold).forEach(err -> errors.add(err));
		} catch (IOException e) {
			String eMsg = "Exception has occured during opening warning log file: " + e;
			errors.add(eMsg);
			logger.warn(eMsg);
		}
		return errors;*/
		return null;
	}
}
