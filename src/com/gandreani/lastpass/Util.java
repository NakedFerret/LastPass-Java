package com.gandreani.lastpass;

import de.rtner.misc.BinTools;

public class Util {

	public static String hexToUTF8(byte[] hex) {
		//TODO actually return a UTF-8 string
		return hexToUTF8(new String(hex));
	}

	public static String hexToUTF8(String hex) {
		//TODO actually return a UTF-8 string
		return new String(BinTools.hex2bin(hex));
	}
}
