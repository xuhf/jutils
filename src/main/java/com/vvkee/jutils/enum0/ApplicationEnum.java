package com.vvkee.jutils.enum0;

public enum ApplicationEnum {

	GREEN("绿色"), RED("红色"), YELLOW("黄色");

	private String display;

	private ApplicationEnum(String display) {
		this.display = display;
	}

	public String getDisplay() {
		return this.display;
	}
}
