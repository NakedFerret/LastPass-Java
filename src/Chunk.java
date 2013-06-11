public class Chunk {
	String type;
	int size;
	byte[] payload;
	
	public Chunk(){
		
	}

	public Chunk(String type, int size, byte[] payload) {
		super();
		this.type = type;
		this.size = size;
		this.payload = payload;
	}

}
