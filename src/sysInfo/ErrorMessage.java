package sysInfo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.appender.RollingFileAppender;
import org.springframework.stereotype.Component;

@Component
public class ErrorMessage {
	private final Logger logger = LogManager.getLogger(this.getClass().getName());
	
	private String path;
	
	public ErrorMessage () {
	    org.apache.logging.log4j.core.Logger loggerImpl = (org.apache.logging.log4j.core.Logger) logger;
	    Appender appender = loggerImpl.getAppenders().get("WarningRollingFile");
        path = ((RollingFileAppender) appender).getFileName();
	}
	
	public List<String> getWarnigs(int threshold) {
		List<String> errors = new ArrayList<String>();
		try(BufferedReader br = new BufferedReader(new FileReader(path))) {
		    br.lines().sorted(Collections.reverseOrder()).limit(threshold).forEach(err -> errors.add(err));
		} catch (IOException e) {
			String eMsg = "Exception has occured during opening warning log file: " + e;
			errors.add(eMsg);
			logger.warn(eMsg);
		}
		return errors;
	}
}
