package com.vvkee.jutils.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;

import org.apache.commons.codec.binary.Hex;

/**
 * 文件操作工具类
 * 
 * @author xuhf
 *
 */
public class FileUtil {

	/**
	 * 获取文件MD5值<br/>
	 * 
	 * 缺点是文件越大，MD5获取消耗时间越长
	 * 
	 * @param file
	 * @return
	 */
	public static String getMD5(File file) {
		FileInputStream in = null;
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			in = new FileInputStream(file);
			byte[] buffer = new byte[8192];
			int length;
			while ((length = in.read(buffer)) != -1) {
				md5.update(buffer, 0, length);
			}
			return new String(Hex.encodeHex(md5.digest()));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}
