package com.gandreani.lastpass;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;

public class EQDNChunk extends Chunk {

	//@formatter:off //Eclipse formatting
	public final List<ChunkItem> items = Arrays.asList(
			new ChunkItem("id", ChunkItem.Encoding.PLAIN), 
			new ChunkItem("domain", ChunkItem.Encoding.HEX)
			);
	//@formatter:on 

	public EQDNChunk(String type, int size, byte[] payload) {
		super(type, size, payload);
	}

	public static EQDNChunk makeFromChunk(Chunk c) {
		return new EQDNChunk(c.type, c.size, c.payload);
	}

	public void parseItems() {
		ByteBuffer buf = ByteBuffer.wrap(payload);
		for (ChunkItem i : items) {
			i.parseData(buf);
		}
	}

}
