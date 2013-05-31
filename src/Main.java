import java.util.Arrays;


public class Main {

	public static void main(String[] args) {
		String username = "postlass@gmail.com";
		String password = "pl1234567890";

		String s = new String("hello");
		
		// Convert from byte array to string and back
		byte[] a = s.getBytes();
		String c = new String(a);
		byte[] b = c.getBytes();
		System.out.println(Arrays.equals(a, b));
		
		Lastpass.getHash(username, password, 5);

	}

}