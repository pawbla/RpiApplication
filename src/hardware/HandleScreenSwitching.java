package hardware;

import org.springframework.stereotype.Component;

@Component
public class HandleScreenSwitching extends Thread {
	
	/**
	 * Constants
	 */
	private static final String ENABLE_DPMS_CMD = "xset +dpms";
	private static final String DISABLE_SCREENSAVER_CMD = "xset dpms force on";
	private static final String ENABLE_DISPLAY_CMD = "xset dpms force on";
	private static final String DISABLE_DISPLAY_CMD = "xset dpms force off";
	
	private ExecuteCommand exe;
	
	public HandleScreenSwitching() {

		exe = new ExecuteCommand();
		exe.runShellCommand("cmd.exe /c dir");
	}
	
	public void run() {
		exe.runShellCommand("cmd.exe /c dir");
	}
}
