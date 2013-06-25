package com.gandreani.lastpass;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;

import de.rtner.misc.BinTools;

public class Utils {

	public static String hexToUTF8(byte[] hex) {
		//TODO actually return a UTF-8 string
		return hexToUTF8(new String(hex));
	}

	public static String hexToUTF8(String hex) {
		//TODO actually return a UTF-8 string
		return new String(BinTools.hex2bin(hex));
	}
	
	public static String decodeAES(String cipherMode, byte[] iv, byte[] data, byte[] keyValue) {
		String decryptedValue = null;

		try {
			Key key = new SecretKeySpec(keyValue, "AES");
			Cipher c = null;

			if (cipherMode.equalsIgnoreCase("cbc")) {

				c = Cipher.getInstance("AES/CBC/PKCS5Padding");
				c.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(iv));

			} else {

				c = Cipher.getInstance("AES/ECB/PKCS5Padding");
				c.init(Cipher.DECRYPT_MODE, key);

			}

			byte[] decValue = c.doFinal(data);
			decryptedValue = new String(decValue);

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		}

		return decryptedValue;
	}
	
	public static byte[] decodeBase64String(String string) {
		try {
			return new BASE64Decoder().decodeBuffer(string);
		} catch (IOException e) {
			System.err
					.println("Exception trying to decode from base64 the following String: "
							+ string);
			e.printStackTrace();
		}
		return null;
	}

}
