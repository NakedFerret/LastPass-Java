package com.gandreani.lastpass;

import java.nio.ByteBuffer;

public class ChunkItem {

	public enum Encoding {
		HEX, AES, PLAIN
	};

	public String name;
	public Encoding encoding;
	public String data;

	public ChunkItem(String name, Encoding encoding) {
		super();
		this.name = name;
		this.encoding = encoding;
	}

	public void parseData(ByteBuffer buf) {
		int size = buf.getInt();
		byte[] dataInBinary = new byte[size];
		buf.get(dataInBinary);

		switch (encoding) {
		
		case AES:
			// TODO decode aes chunkitem data
			break;
		case HEX:
			data = Utils.hexToUTF8(dataInBinary);
			break;

		default:
			data = new String(dataInBinary);
			break;
		}

	}
}
