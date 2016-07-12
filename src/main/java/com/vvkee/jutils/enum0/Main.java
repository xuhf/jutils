package com.vvkee.jutils.enum0;

public class Main {

	public static void main(String[] args) {
		for (ApplicationEnum app : ApplicationEnum.values()) {
			System.out.println(app.name() + "_" + app.getDisplay());
		}
	}
}
