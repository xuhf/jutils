package com.vvkee.jutils.quartz;

public class Task {

	private String display; // task的中文名称

	private String name; // task的英文名称

	private String group; // task的组

	private String clazz; // task的全路径

	private String method; // 执行的方法名

	private String crontab; // crontab表达式

	private boolean isContainerInit; // 是否随容器启动

	private boolean isConcurrent; // 是否允许并行

	public String getDisplay() {
		return display;
	}

	public void setDisplay(String display) {
		this.display = display;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getClazz() {
		return clazz;
	}

	public void setClazz(String clazz) {
		this.clazz = clazz;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getCrontab() {
		return crontab;
	}

	public void setCrontab(String crontab) {
		this.crontab = crontab;
	}

	public boolean isContainerInit() {
		return isContainerInit;
	}

	public void setContainerInit(boolean isContainerInit) {
		this.isContainerInit = isContainerInit;
	}

	public boolean isConcurrent() {
		return isConcurrent;
	}

	public void setConcurrent(boolean isConcurrent) {
		this.isConcurrent = isConcurrent;
	}

	@Override
	public String toString() {
		return "Task [display=" + display + ", name=" + name + ", group=" + group + ", clazz=" + clazz + ", method="
				+ method + ", crontab=" + crontab + ", isContainerInit=" + isContainerInit + ", isConcurrent="
				+ isConcurrent + "]";
	}
}
