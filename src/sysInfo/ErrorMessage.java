package sysInfo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

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
	
	public List<String> getWarnigs(int threshold) throws FileNotFoundException, IOException {
		List<String> errors;
		try(BufferedReader br = new BufferedReader(new FileReader(path))) {
		    errors = new ArrayList<String>();
		    br.lines().sorted(Collections.reverseOrder()).limit(threshold).forEach(err -> errors.add(err));
		}
		return errors;
	}
}
