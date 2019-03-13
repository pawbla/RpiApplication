package hardware;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * Execute shell command class
 *
 */
@Component
public class ExecuteCommand {
	/**
	 * Logger
	 */
	private final Logger logger = LogManager.getLogger(this.getClass().getName());

	private Process process;
	private Runtime runtime;
	
	public ExecuteCommand() {
		runtime = Runtime.getRuntime();
	}
	
	public void runShellCommand(String cmd) {
		logger.debug("Execute shell command: " + cmd);
		int exitCode = -9;
		try {
			process = runtime.exec(cmd);
			exitCode = process.waitFor();
		} catch (IOException | InterruptedException e) {
			logger.warn("Exception has occured: " + e);
		}
		logger.debug("Exit code: " + exitCode);
	}
}
