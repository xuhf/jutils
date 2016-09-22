package com.vvkee.jutils.ssh.test;

import net.neoremind.sshxcute.core.ConnBean;
import net.neoremind.sshxcute.core.Result;
import net.neoremind.sshxcute.core.SSHExec;
import net.neoremind.sshxcute.exception.TaskExecFailException;
import net.neoremind.sshxcute.task.CustomTask;
import net.neoremind.sshxcute.task.impl.ExecCommand;

public class Demo {

	public static void main(String[] args) throws TaskExecFailException {
		ConnBean cb = new ConnBean("127.0.0.1", "root", "123");
		SSHExec ssh = SSHExec.getInstance(cb);
		ssh.connect();
		CustomTask sampleTask = new ExecCommand("java -version");
		Result result = ssh.exec(sampleTask);
		System.out.println(result.isSuccess);
		System.out.println(result.error_msg);
		System.out.println(result.sysout);
		System.out.println(result.sysout);
		ssh.disconnect();
	}
}
