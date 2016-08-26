package com.vvkee.jutils.command;

/**
 * 执行结果
 * 
 * @author xuhf
 *
 */
public class ExecResult {

	private int rc = -1;

	private String sysout;

	private String errorMessage;

	private boolean isSuccess = false;

	public int getRc() {
		return rc;
	}

	public void setRc(int rc) {
		this.rc = rc;
	}

	public String getSysout() {
		return sysout;
	}

	public void setSysout(String sysout) {
		this.sysout = sysout;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	@Override
	public String toString() {
		return "ExecResult [rc=" + rc + ", sysout=" + sysout + ", errorMessage=" + errorMessage + ", isSuccess="
				+ isSuccess + "]";
	}
}
