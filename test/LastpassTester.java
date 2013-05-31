import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

import org.junit.Test;

import de.rtner.misc.BinTools;

public class LastpassTester {

	@Test
	public void testKeyGenerator(){
		HashMap<Integer, String> map = new HashMap<Integer, String>();

		map.put(5,
				"a44f60a1acd2091aa75b0722c5633834726358cbc1e53d7974c85eeae835a598");
		map.put(10,
				"9fd4b44b225dacc7a07811ed931531f0bcdcef023a68697ecb7fee7469957b2f");
		map.put(50,
				"1b023cfe4372d4d8c87dedd9d150197c5efcf77f1456e0a2eb100bdcc241bb1d");
		map.put(100,
				"a6219d4942de1cc5a2052dd024d338e8ce4f218c105c0e9c352d74a4b0775dff");
		map.put(500,
				"39f394bd59d0cc1e2fe3db0d878f8f7702056fd16be7e8d57d64537fe1361a18");
		map.put(1000,
				"cfb09dc252246eed17bdc07ba102299e5ab03467a676b1930660ca9cbf6d68f8");

		for (Integer i : map.keySet()) {
			byte[] generatedKey = Lastpass.getKey("postlass@gmail.com",
					"pl1234567890", i);
			byte[] testKey = BinTools.hex2bin(map.get(i));
			assertArrayEquals(null, testKey, generatedKey);
		}
	}
	
	@Test
	public void testHashGenerator() throws NoSuchAlgorithmException{
		HashMap<Integer, String> map = new HashMap<Integer, String>();

		map.put(5,
				"a95849e029a7791cfc4503eed9ec96ab8675c4a7c4e82b00553ddd179b3d8445");
		map.put(10,
				"0da0b44f5e6b7306f14e92de6d629446370d05afeb1dc07cfcbe25f169170c16");
		map.put(50,
				"1d5bc0d636da4ad469cefe56c42c2ff71589facb9c83f08fcf7711a7891cc159");
		map.put(100,
				"82fc12024acb618878ba231a9948c49c6f46e30b5a09c11d87f6d3338babacb5");
		map.put(500,
				"3139861ae962801b59fc41ff7eeb11f84ca56d810ab490f0d8c89d9d9ab07aa6");
		map.put(1000,
				"03161354566c396fcd624a424164160e890e96b4b5fa6d942fc6377ab613513b");

		for (Integer i : map.keySet()) {
			String generatedHash = Lastpass.getHash("postlass@gmail.com",
					"pl1234567890", i);
			// I don't know how to assert that two strings are equal while ignoring the case
			// using the Junit functions
			assertEquals(null, map.get(i).equalsIgnoreCase(generatedHash), true);
		}
	}

}
