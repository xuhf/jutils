package com.vvkee.jutils.security;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;

public class RSA {

	public static void main(String[] args) throws Exception {
		String plainText = "18686837152";

		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("rsa");
		keyPairGenerator.initialize(1024);
		KeyPair keyPair = keyPairGenerator.generateKeyPair();

		PublicKey publicKey = keyPair.getPublic();
		PrivateKey privateKey = keyPair.getPrivate();

		Cipher cipher = Cipher.getInstance("rsa");
		SecureRandom random = new SecureRandom();

		cipher.init(Cipher.ENCRYPT_MODE, privateKey, random);
		byte[] cipherData = cipher.doFinal(plainText.getBytes());
		System.out.println("cipherText : " + new Base64().encode(cipherData));

		cipher.init(Cipher.DECRYPT_MODE, publicKey, random);
		byte[] plainData = cipher.doFinal(cipherData);
		System.out.println("plainText : " + new String(plainData));

		Signature signature = Signature.getInstance("MD5withRSA");
		signature.initSign(privateKey);
		signature.update(cipherData);
		byte[] signData = signature.sign();
		System.out.println("signature : " + new Base64().encode(signData));

		signature.initVerify(publicKey);
		signature.update(cipherData);
		boolean status = signature.verify(signData);
		System.out.println("status : " + status);
	}
	
	public String encrypt(String s) {
		return null;
	}

}
