package com.vvkee.jutils.command;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

/**
 * 命令
 * 
 * @author xuhf
 *
 */
public class ExecCommand extends AbstractCommand {

	private String command;

	public ExecCommand(String... args) {
		List<String> commands = Lists.newArrayList();
		for (int i = 0; i < args.length; i++) {
			commands.add(args[i]);
		}
		command = Joiner.on(DELIMETER).join(commands);
	}

	@Override
	protected Boolean checkStdOut(String stdout) {
		if (StringUtils.isBlank(stdout)) {
			return true;
		}
		for (String errorKeyword : errorSysoutKeywords) {
			if (stdout.contains(errorKeyword)) {
				return false;
			}
		}
		return true;
	}

	@Override
	protected Boolean checkExitCode(int exitCode) {
		return exitCode == 0;
	}

	@Override
	public String getCommand() {
		return command;
	}

	@Override
	public String getInfo() {
		return "Exec Command : " + command;
	}

}
