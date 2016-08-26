package com.vvkee.jutils.command;

/**
 * 
 * @author xuhf
 *
 */
public abstract class AbstractCommand {

	protected static String DELIMETER = ";";

	protected String[] errorSysoutKeywords = { "Usage", "usage", "not found", "fail", "Fail", "error", "Error",
			"exception", "Exception", "not a valid" };

	public Boolean isSuccess(String stdout, int exitCode) {
		if (checkStdOut(stdout) && checkExitCode(exitCode))
			return true;
		else
			return false;
	}

	protected abstract Boolean checkStdOut(String stdout);

	protected abstract Boolean checkExitCode(int exitCode);

	public abstract String getCommand();

	public abstract String getInfo();

	protected String cat(String... args) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < args.length; i++) {
			sb.append(args[i]);
			sb.append(" ");
		}
		return sb.toString();
	}

}
