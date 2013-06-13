package com.gandreani.lastpass;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;

import de.rtner.misc.BinTools;

public class EQDNChunk extends Chunk {

	//@formatter:off //Eclipse formatting
	public final List<ChunkItem> items = Arrays.asList(
			new ChunkItem("id", null), 
			new ChunkItem("domain", "hex")
			);
	//@formatter:on 

	public EQDNChunk(String type, int size, byte[] payload) {
		super(type, size, payload);
	}

	public void parseItems() {
		ByteBuffer buf = ByteBuffer.wrap(payload);
		for (ChunkItem i : items) {
			parseItem(i, buf);
		}
	}

	public static EQDNChunk makeFromChunk(Chunk c) {
		return new EQDNChunk(c.type, c.size, c.payload);
	}

	private void parseItem(ChunkItem i, ByteBuffer buf) {
		int size = buf.getInt();
		byte[] data = new byte[size];
		buf.get(data);

		if (i.encoding == null)
			i.data = new String(data);
		else if (i.encoding.equalsIgnoreCase("hex"))
			i.data = new String(hexToUTF8(data));

	}

	public static String hexToUTF8(byte[] hex) {
		return hexToUTF8(new String(hex));
	}

	public static String hexToUTF8(String hex) {
		return new String(BinTools.hex2bin(hex));
	}
}
