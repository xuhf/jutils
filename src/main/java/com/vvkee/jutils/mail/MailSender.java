package com.vvkee.jutils.mail;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 使用Spring发送邮件
 * 
 * @author xuhf
 * 
 * @URL http://spiritfrog.iteye.com/blog/708799
 */
public final class MailSender {

	private String charset = "UTF-8";

	/** 收件人 **/
	private final List<String> toList = new ArrayList<String>();

	/** 抄送 **/
	private final List<String> ccList = new ArrayList<String>();

	/** 抄送 **/
	private final List<String> bccList = new ArrayList<String>();

	/** 附件 **/
	private final List<File> attchmentList = new ArrayList<File>();

	/** 邮件服务器 **/
	private String host;

	/** 邮件端口号 **/
	private int port;

	/** 邮件用户名 **/
	private String username;

	/** 邮件密码 **/
	private String password;

	/** 邮件From **/
	private String from;

}
