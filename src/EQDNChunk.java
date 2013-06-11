import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;

public class EQDNChunk extends Chunk {

	//@formatter:off //Eclipse formatting
	public final List<ChunkItem> items = Arrays.asList(
			new ChunkItem("id", null), 
			new ChunkItem("domain", "hex")
			);
	//@formatter:on 

	public EQDNChunk(String type, int size, byte[] payload) {
		super(type, size, payload);
		System.out.println("ENCU chunk ---");
		for (ChunkItem i : items) {
			parseItem(i);
		}
		System.out.println("-----");
	}

	public static EQDNChunk makeFromChunk(Chunk c) {
		return new EQDNChunk(c.type, c.size, c.payload);
	}

	public void parseItem(ChunkItem i) {
		System.out.print(i.name + " ");
		ByteBuffer buf = ByteBuffer.wrap(payload);
		int size = buf.getInt();
		byte[] data = new byte[size];
		System.out.print(" " + size);
		if (i.encoding == null)
			System.out.print(" " + new String(data));
		System.out.println("");
	}
}
