package hardware;

import java.io.IOException;

/**
 * Execute shell command class
 *
 */
public class ExecuteCommand {

	private Process process;
	private Runtime runtime;
	
	public ExecuteCommand() {
		System.out.println("RUNTIME");
		runtime = Runtime.getRuntime();
	}
	
	public void runShellCommand(String cmd) {
		int exitCode = -9;
		try {
			process = runtime.exec(cmd);
			exitCode = process.waitFor();
		} catch (IOException | InterruptedException e) {
			System.out.println("Ex " + e);
		}
		if (exitCode < 0) {
			System.out.println("ExitCode " + exitCode);
		}
		System.out.println("ExitCodeOK " + exitCode);
	}
}
