package com.gandreani.lastpass;

import java.nio.ByteBuffer;

public class ChunkItem {
	
	public enum Encoding {
		HEX, AES
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

	}
}
