package hardware;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@Component
public class HandleScreenSwitching extends Thread {
	/**
	 * Logger
	 */
	private final Logger logger = LogManager.getLogger(this.getClass().getName());
	
	/**
	 * Constants
	 */
	private static final String ENABLE_DPMS_CMD = "xset +dpms";
	private static final String DISABLE_SCREENSAVER_CMD = "xset s off";
	private static final String ENABLE_DISPLAY_CMD = "xset dpms force on";
	private static final String DISABLE_DISPLAY_CMD = "xset dpms force off";
	private static final int TIMEOUT = 10000;
	
	@Autowired
	private ExecuteCommand exe;
	
	@PostConstruct
	public void init() {
		logger.debug("Initialize DPMS");
		exe.runShellCommand(ENABLE_DPMS_CMD);
		exe.runShellCommand(DISABLE_SCREENSAVER_CMD);
	}
	
	public void run() {
		logger.debug("Enable display");
		exe.runShellCommand(ENABLE_DISPLAY_CMD);
		try {
			Thread.sleep(TIMEOUT);
		} catch (InterruptedException e) {
			logger.warn("Exception has occured: " + e);
		}
		logger.debug("Disable display");
		exe.runShellCommand(DISABLE_DISPLAY_CMD);
	}
}
