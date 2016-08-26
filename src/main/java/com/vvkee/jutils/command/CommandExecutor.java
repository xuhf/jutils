package com.vvkee.jutils.command;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CommandExecutor {

	public ExecResult exec(AbstractCommand task) {
		ExecResult r = new ExecResult();
		try {
			String command = task.getCommand();
			System.out.println("Command is : " + command);
			Process p = Runtime.getRuntime().exec(command);
			final InputStream in = p.getErrorStream();
			final StringBuffer info = new StringBuffer();
			final String systemLineSeparator = System.getProperty("line.separator", "\n");
			Thread errorThread = new Thread() {
				public void run() {
					try {
						BufferedReader reader = new BufferedReader(new InputStreamReader(in, "GB18030"));
						String line = null;
						while ((line = reader.readLine()) != null) {
							info.append(line);
							info.append(systemLineSeparator);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			};
			errorThread.start();
			p.waitFor();
			int exitCode = p.exitValue();
			r.setRc(exitCode);
			if (task.isSuccess(info.toString(), exitCode)) {
				r.setSuccess(true);
				r.setErrorMessage("");
				r.setSysout(info.toString());
			} else {
				r.setSuccess(false);
				r.setErrorMessage(info.toString());
				r.setSysout("");
			}
			return r;
		} catch (Exception e) {
			r.setSuccess(false);
			r.setErrorMessage(e.getMessage());
		}
		return r;
	}
}
