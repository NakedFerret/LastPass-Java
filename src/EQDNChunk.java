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
	
	public void parseItems(){
		System.out.println(type + " chunk --- payload size: " + payload.length);
		ByteBuffer buf = ByteBuffer.wrap(payload);
		for (ChunkItem i : items) {
			parseItem(i, buf);
		}
		System.out.println("-----");
	}

	public static EQDNChunk makeFromChunk(Chunk c) {
		return new EQDNChunk(c.type, c.size, c.payload);
	}

	private void parseItem(ChunkItem i, ByteBuffer buf) {
		System.out.println("**\nchunk item: " + i.name);
		int size = buf.getInt();
		System.out.println("size: " + size);
		
		byte[] data = new byte[size];
		buf.get(data);
		
		System.out.print("data: ");
		if (i.encoding == null)
			System.out.println(new String(data));
		else if(i.encoding.equalsIgnoreCase("hex")) {
			System.out.println(hexToUTF8(data));
		}
		
		System.out.println("**");
	}
	
	public static String hexToUTF8(byte[] hex){
		return hexToUTF8(new String(hex));
	}
	
	public static String hexToUTF8(String hex){
		return new String(BinTools.hex2bin(hex));
	}
}
