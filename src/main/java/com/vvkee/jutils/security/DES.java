package com.vvkee.jutils.security;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.apache.commons.codec.binary.Base64;

public class DES {

	private static SecretKeyFactory keyFactory;

	/**
	 * DES算法密钥
	 */
	private static final String _KEY = "88404904DEF3A96C03B6399E9216D406";

	static {
		try {
			keyFactory = SecretKeyFactory.getInstance("DES");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 数据加密，算法（DES）
	 * 
	 * @param data
	 *            要进行加密的数据
	 * @return 加密后的数据
	 */
	public static String encrypt(String s) {
		try {
			SecureRandom sr = new SecureRandom();
			DESKeySpec deskey = new DESKeySpec(_KEY.getBytes());
			SecretKey key = keyFactory.generateSecret(deskey);
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.ENCRYPT_MODE, key, sr);
			return new Base64().encodeAsString(cipher.doFinal(s.getBytes()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 数据解密，算法（DES）
	 * 
	 * @param cryptData
	 *            加密数据
	 * @return 解密后的数据
	 */
	public static String decrypt(String s) {
		try {
			// DES算法要求有一个可信任的随机数源
			SecureRandom sr = new SecureRandom();
			DESKeySpec deskey = new DESKeySpec(_KEY.getBytes());
			SecretKey key = keyFactory.generateSecret(deskey);
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.DECRYPT_MODE, key, sr);
			return new String(cipher.doFinal(new Base64().decode(s)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		System.out.println();
	}
}
