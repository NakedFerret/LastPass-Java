import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;

public class ACCTChunk extends Chunk {

	//@formatter:off //Eclipse formatting
	public final List<ChunkItem> items = Arrays.asList(
			new ChunkItem("id", null),
			new ChunkItem("name", "aes256"),
			new ChunkItem("group", "aes256"),
			new ChunkItem("url", "hex"),
			new ChunkItem("extra", null),
			new ChunkItem("favorite", null),
			new ChunkItem("shared_from_id", null),
			new ChunkItem("username","aes256"),
			new ChunkItem("password", "aes256"),
			new ChunkItem("password_protected",null),
			new ChunkItem("generated_password", null),
			new ChunkItem("sn", null),
			new ChunkItem("last_touched", null), 
			new ChunkItem("auto_login",	null), 
			new ChunkItem("never_autofill", null),
			new ChunkItem("realm_data", null), 
			new ChunkItem("fiid", null),
			new ChunkItem("custom_js", null), 
			new ChunkItem("submit_id", null),
			new ChunkItem("captcha_id", null), 
			new ChunkItem("urid", null),
			new ChunkItem("basic_authorization", null), 
			new ChunkItem("method",	null), 
			new ChunkItem("action", "hex"), 
			new ChunkItem("group_id", null), 
			new ChunkItem("deleted", null),
			new ChunkItem("attach_key", null), 
			new ChunkItem("attach_present",	null), 
			new ChunkItem("individual_share", null),
			new ChunkItem("unknown1", null)
			);
	//@formatter:on

	public ACCTChunk(String type, int size, byte[] payload) {
		super(type, size, payload);
		System.out.println("ACCT chunk ---");
		for (ChunkItem i : items) {
			parseItem(i);
		}
		System.out.println("-----");
	}

	public static ACCTChunk makeFromChunk(Chunk c) {
		return new ACCTChunk(c.type, c.size, c.payload);
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
