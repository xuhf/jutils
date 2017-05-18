package com.vvkee.jutils.security;

import java.net.URLEncoder;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

import org.apache.commons.codec.binary.Base64;

public class Cryptogram {

	private Cipher encryptCipher;
	private Cipher decryptCipher;

	private String user;

	private String key;

	/**
	 * 构造方法
	 * 
	 * @param passKey
	 *            将用户的apikey作为密钥传入
	 * @throws Exception
	 */
	public Cryptogram(String user, String key) throws Exception {
		init(user, key);
		this.user = user;
		this.key = key;
	}

	/**
	 * 初始化
	 * 
	 * @param passKey
	 * @throws Exception
	 */
	private void init(String salt, String passKey) throws Exception {
		int iterationCount = 3;
		KeySpec keySpec = new PBEKeySpec(passKey.toCharArray(), salt.getBytes(), iterationCount);
		SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(keySpec);
		encryptCipher = Cipher.getInstance(key.getAlgorithm());
		decryptCipher = Cipher.getInstance(key.getAlgorithm());
		AlgorithmParameterSpec paramSpec = new PBEParameterSpec(salt.getBytes(), iterationCount);
		encryptCipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);
		decryptCipher.init(Cipher.DECRYPT_MODE, key, paramSpec);
	}

	/**
	 * 加密
	 * 
	 * @param str
	 *            要加密的字符串
	 * @return
	 * @throws Exception
	 */
	public String encrypt(String str) throws Exception {
		return Base64.encodeBase64String(encryptCipher.doFinal(str.getBytes("UTF-8")));
	}

	/**
	 * 解密
	 * 
	 * @param str
	 *            要解密的字符串
	 * @return
	 * @throws Exception
	 */
	public String decrypt(String str) throws Exception {
		return new String(decryptCipher.doFinal(Base64.decodeBase64(str)), "UTF-8");
	}

	// 加密字符串
	public String doEncrypterPhone(String phone) {
		String message = "";
		try {
			String accountAndPwd = "&user=" + URLEncoder.encode(this.user, "UTF-8") + "&key="
					+ URLEncoder.encode(this.key, "UTF-8");
			String str = "phone=" + URLEncoder.encode(phone, "UTF-8") + accountAndPwd;
			message = encrypt(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return message;
	}

	public static void main(String[] args) throws Exception {
		Cryptogram desEncrypter = new Cryptogram("CBLTZXKC", "05707440a4cd82d0f7a82048b73c4a08");
		String message = desEncrypter.doEncrypterPhone("13581691044");
		System.out.println(desEncrypter.encrypt(message));
		System.out.println(desEncrypter.decrypt(message));

	}

}
