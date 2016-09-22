package com.vvkee.jutils.command;

public class CommandTest {

	public static void main(String[] args) {
		AbstractCommand command = new ExecCommand("java -jar c://EncrptRepo_new.jar");
		CommandExecutor executor = new CommandExecutor();
		ExecResult r = executor.exec(command);
		System.out.println(r);
	}
}
