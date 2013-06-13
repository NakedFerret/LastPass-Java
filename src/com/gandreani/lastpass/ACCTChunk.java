package com.gandreani.lastpass;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;

public class ACCTChunk extends Chunk {

	//@formatter:off //Eclipse formatting
	public final List<ChunkItem> items = Arrays.asList(
			new ChunkItem("id", ChunkItem.Encoding.PLAIN),
			new ChunkItem("name", ChunkItem.Encoding.AES),
			new ChunkItem("group", ChunkItem.Encoding.AES),
			new ChunkItem("url", ChunkItem.Encoding.HEX),
			new ChunkItem("extra", ChunkItem.Encoding.PLAIN),
			new ChunkItem("favorite", ChunkItem.Encoding.PLAIN),
			new ChunkItem("shared_from_id", ChunkItem.Encoding.PLAIN),
			new ChunkItem("username",ChunkItem.Encoding.AES),
			new ChunkItem("password", ChunkItem.Encoding.AES),
			new ChunkItem("password_protected",ChunkItem.Encoding.PLAIN),
			new ChunkItem("generated_password", ChunkItem.Encoding.PLAIN),
			new ChunkItem("sn", ChunkItem.Encoding.PLAIN),
			new ChunkItem("last_touched", ChunkItem.Encoding.PLAIN), 
			new ChunkItem("auto_login",	ChunkItem.Encoding.PLAIN), 
			new ChunkItem("never_autofill", ChunkItem.Encoding.PLAIN),
			new ChunkItem("realm_data", ChunkItem.Encoding.PLAIN), 
			new ChunkItem("fiid", ChunkItem.Encoding.PLAIN),
			new ChunkItem("custom_js", ChunkItem.Encoding.PLAIN), 
			new ChunkItem("submit_id", ChunkItem.Encoding.PLAIN),
			new ChunkItem("captcha_id", ChunkItem.Encoding.PLAIN), 
			new ChunkItem("urid", ChunkItem.Encoding.PLAIN),
			new ChunkItem("basic_authorization", ChunkItem.Encoding.PLAIN), 
			new ChunkItem("method",	ChunkItem.Encoding.PLAIN), 
			new ChunkItem("action", ChunkItem.Encoding.HEX), 
			new ChunkItem("group_id", ChunkItem.Encoding.PLAIN), 
			new ChunkItem("deleted", ChunkItem.Encoding.PLAIN),
			new ChunkItem("attach_key", ChunkItem.Encoding.PLAIN), 
			new ChunkItem("attach_present",	ChunkItem.Encoding.PLAIN), 
			new ChunkItem("individual_share", ChunkItem.Encoding.PLAIN),
			new ChunkItem("unknown1", ChunkItem.Encoding.PLAIN)
			);
	//@formatter:on

	public ACCTChunk(String type, int size, byte[] payload) {
		super(type, size, payload);
	}

	public static ACCTChunk makeFromChunk(Chunk c) {
		return new ACCTChunk(c.type, c.size, c.payload);
	}
	
	public void parseItems() {
		ByteBuffer buf = ByteBuffer.wrap(payload);
		for (ChunkItem i : items) {
			i.parseData(buf);
		}
	}

}
