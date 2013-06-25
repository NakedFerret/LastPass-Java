package com.gandreani.lastpass;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import de.rtner.misc.BinTools;
import de.rtner.security.auth.spi.PBKDF2Engine;
import de.rtner.security.auth.spi.PBKDF2Parameters;

public class Parser {

	public static byte[] getKey(String username, String password, int iterations) {

		PBKDF2Parameters p = new PBKDF2Parameters("HmacSHA256", null,
				username.getBytes(), iterations);
		PBKDF2Engine e = new PBKDF2Engine(p);
		p.setDerivedKey(e.deriveKey(password, 32));
		return p.getDerivedKey();
	}

	public static String getHash(String username, String password,
			int iterations) {
		byte[] key = getKey(username, password, iterations);

		PBKDF2Parameters p = new PBKDF2Parameters("HmacSHA256", "Latin1",
				password.getBytes(), 1);
		PBKDF2Engine e = new PBKDF2Engine(p);
		try {
			p.setDerivedKey(e.deriveKey(new String(key, "Latin1"), 32));
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

		return BinTools.bin2hex(p.getDerivedKey());
	}

	public static List<Account> parseAccounts(String blob, String username,
			String password, int iterations) {

		return parseAccounts(blob, getKey(username, password, iterations));
	}

	public static List<Account> parseAccounts(String blob, byte[] key) {
		// TODO: Check if blob is base64 string
		ByteBuffer buf = ByteBuffer.wrap(Utils.decodeBase64String(blob));
		ArrayList<Account> accounts = new ArrayList<Account>();

		while (buf.hasRemaining()) {
			Chunk c = readChunk(buf);
			if (!"ACCT".equals(c.type))
				continue;

			ACCTChunk ac = ACCTChunk.makeFromChunk(c);
			ac.parseItems(key);
			accounts.add(Account.getAccountFromChunk(ac));
		}

		return accounts;
	}

	public static String decryptPayload(byte[] payload, byte[] key) {
		int length = payload.length;
		int length16 = length % 16;
		int length64 = length % 64;

		if (length == 0) {

			return null;

		} else if (length16 == 0) {
			// ecb, plain
			return Utils.decodeAES("ecb", null, payload, key);

		} else if (length64 == 0 || length64 == 24 || length64 == 44) {

			// ecb, base64
			return Utils.decodeAES("ecb", null,
					Utils.decodeBase64String(new String(payload)), key);

		} else if (length16 == 1) {

			// ecb, plain
			ByteBuffer buf = ByteBuffer.wrap(payload);
			byte[] iv = new byte[16];
			byte[] data = new byte[payload.length - 17]; // all the bytes after
			buf.get(); // Throw away the first byte
			buf.get(iv);
			buf.get(data);
			return Utils.decodeAES("cbc", iv, data, key);

		} else if (length64 == 6 || length64 == 26 || length64 == 50) {

			// cbc, base64
			ByteBuffer buf = ByteBuffer.wrap(payload);
			byte[] iv = new byte[25];
			byte[] data = new byte[payload.length - 26]; // all the bytes after
			buf.get(); // Throw away the first byte
			buf.get(iv);
			buf.get(data);
			return Utils.decodeAES("cbc",
					Utils.decodeBase64String(new String(iv)),
					Utils.decodeBase64String(new String(data)), key);

		}
		return null;
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

}