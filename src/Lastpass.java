import java.io.UnsupportedEncodingException;

import de.rtner.misc.BinTools;
import de.rtner.security.auth.spi.PBKDF2Engine;
import de.rtner.security.auth.spi.PBKDF2Parameters;

public class Lastpass {

	public static byte[] getKey(String username, String password, int iterations) {

		PBKDF2Parameters p = new PBKDF2Parameters("HmacSHA256", null,
				username.getBytes(), iterations);
		PBKDF2Engine e = new PBKDF2Engine(p);
		p.setDerivedKey(e.deriveKey(password, 32));
		return p.getDerivedKey();
	}

	public static String getHash(String username, String password,
			int iterations) {
		byte[] key = getKey(username, password, iterations);
		
		PBKDF2Parameters p = new PBKDF2Parameters("HmacSHA256", "Latin1",
				password.getBytes(), 1);
		PBKDF2Engine e = new PBKDF2Engine(p);
		try {
			p.setDerivedKey(e.deriveKey(new String(key, "Latin1"), 32));
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

		return BinTools.bin2hex(p.getDerivedKey());
	}

}