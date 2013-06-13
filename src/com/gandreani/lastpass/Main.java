package com.gandreani.lastpass;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;

public class Main {

	private static byte[] keyValue;

	public static void main(String[] args) throws Exception {
		String word = "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AAACMzIAAAAkNjI2MTZlNjE2ZTYxNzI2NTcwNzU2MjZjNjk2MzJlNjM2ZjZkRVFETgAAABgAAAACMzIAAAAONjc2MTcwMmU2MzZmNmRFUUROAAAAIAAAAAIzMgAAABY2ZjZjNjQ2ZTYxNzY3OTJlNjM2ZjZkRVFETgAAACQAAAACMzIAAAAaNzA2OTcwNjU3MjZjNjk2ZDY1MmU2MzZmNmRFUUROAAAAGgAAAAI0MgAAABA2MjY5NmU2NzJlNjM2ZjZkRVFETgAAACAAAAACNDIAAAAWNjg2Zjc0NmQ2MTY5NmMyZTYzNmY2ZEVRRE4AAAAaAAAAAjQyAAAAEDZjNjk3NjY1MmU2MzZmNmRFUUROAAAAJAAAAAI0MgAAABo2ZDY5NjM3MjZmNzM2ZjY2NzQyZTYzNmY2ZEVRRE4AAAAYAAAAAjQyAAAADjZkNzM2ZTJlNjM2ZjZkRVFETgAAACIAAAACNDIAAAAYNzA2MTczNzM3MDZmNzI3NDJlNmU2NTc0RVFETgAAABwAAAACNTIAAAASNzU2MTMyNjc2ZjJlNjM2ZjZkRVFETgAAABgAAAACNTIAAAAONzU2MTZjMmU2MzZmNmRFUUROAAAAHgAAAAI1MgAAABQ3NTZlNjk3NDY1NjQyZTYzNmY2ZEVRRE4AAAAiAAAAAjgyAAAAGDZmNzY2NTcyNzQ3NTcyNjUyZTYzNmY2ZEVRRE4AAAAcAAAAAjgyAAAAEjc5NjE2ODZmNmYyZTYzNmY2ZEVRRE4AAAAkAAAAAjkyAAAAGjdhNmY2ZTY1NjE2YzYxNzI2ZDJlNjM2ZjZkRVFETgAAACIAAAACOTIAAAAYN2E2ZjZlNjU2YzYxNjI3MzJlNjM2ZjZkRVFETgAAABsAAAADODQyAAAAEDYxNzY2ZjZlMmU2MzZmNmRFUUROAAAAIwAAAAM4NDIAAAAYNzk2Zjc1NzI2MTc2NmY2ZTJlNjM2ZjZkRVFETgAAACwAAAAEMTQ3NAAAACAzMTM4MzAzMDYzNmY2ZTc0NjE2Mzc0NzMyZTYzNmY2ZEVRRE4AAAAqAAAABDE0NzQAAAAeMzgzMDMwNjM2ZjZlNzQ2MTYzNzQ3MzJlNjM2ZjZkVVJVTAAAACgAAAAaNjc2ZjZmNjc2YzY1MmU2MzZmNmQyZjYxMmYAAAABMAAAAAEwVVJVTAAAACQAAAAWNmM2ZjY3NmQ2NTY5NmUyZTYzNmY2ZAAAAAExAAAAATBFTkRNAAAAAk9L";
		keyValue = decodeBase64String("OfOUvVnQzB4v49sNh4+PdwIFb9Fr5+jVfWRTf+E2Ghg=");

		ByteBuffer buf = ByteBuffer.wrap(decodeBase64String(word));

		ArrayList<Chunk> chunks = new ArrayList<Chunk>();

		while (buf.hasRemaining()) {
			chunks.add(readChunk(buf));
		}

		for (Chunk c : chunks) {
			parseChunk(c);
		}

	}

	private static void parseChunk(Chunk c) {
		if (c.type.equals("LPAV")) {
			System.out.println("LPAV " + new String(c.payload));
		} else if (c.type.equals("NMAC")) {
			System.out.println("NMAC " + new String(c.payload));
		} else if (c.type.equals("ENCU")) {
			System.out.println("ENCU " + decryptPayload(c.payload));
		} else if (c.type.equals("EQDN")) {
			EQDNChunk chunk = EQDNChunk.makeFromChunk(c);
			chunk.parseItems();
			
		}

	}

	private static String decryptPayload(byte[] payload) {
		int length = payload.length;
		int length16 = length % 16;
		int length64 = length % 64;

		if (length == 0) {

			return null;

		} else if (length16 == 0) {

			return decodeAES("ecb", null, payload); // ecb, plain

		} else if (length64 == 0 || length64 == 24 || length64 == 44) {

			return decodeAES("ecb", null,
					decodeBase64String(new String(payload)));

		} else if (length16 == 1) {

			ByteBuffer buf = ByteBuffer.wrap(payload);
			byte[] iv = new byte[17];
			byte[] data = new byte[payload.length - 18]; // all the bytes after
			buf.get(); // Throw away the first byte
			buf.get(iv);
			buf.get(data);
			return decodeAES("cbc", decodeBase64String(new String(iv)),
					decodeBase64String(new String(data)));

		} else if (length64 == 6 || length64 == 26 || length64 == 50) {
			// cbc base64
			ByteBuffer buf = ByteBuffer.wrap(payload);
			byte[] iv = new byte[25];
			byte[] data = new byte[payload.length - 26]; // all the bytes after
			buf.get(); // Throw away the first byte
			buf.get(iv);
			buf.get(data);
			return decodeAES("cbc", decodeBase64String(new String(iv)),
					decodeBase64String(new String(data)));

		}
		return null;
	}

	private static String decodeAES(String cipherMode, byte[] iv, byte[] data) {
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

	private static Chunk readChunk(ByteBuffer buf) {
		Chunk c = new Chunk();
		byte[] type = new byte[4];
		int size = 0;
		byte[] payload; // dynamic size

		buf.get(type);
		size = buf.getInt();
		// the size of the buffer is contained in the 4 bytes
		// that proceed the type
		payload = new byte[size];
		buf.get(payload);

		c.type = new String(type);
		c.size = type.length;
		c.payload = payload;

		return c;
	}

	private static byte[] decodeBase64String(String string) {
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