package com.vvkee.jutils.enum0;

public enum ApplicationEnum {

	NETWORK("固网"), THIRD("三期"), URL("URL");

	private String display;

	ApplicationEnum(String display) {
		this.display = display;
	}

	public String getDisplay() {
		return this.display;
	}
}
