package security;

public class Ceaser {
	public static String letter = "abcdefghijklmnopqrstuvwxyz";
	public static String letter_space = " abcdefghijklmnopqrstuvwxyz";

	// ----------------------- ceaser ------------------------------------
	// enc
	public static StringBuilder ceaser_enc(String plain, int key) {
		// cypher text
		StringBuilder cypher = new StringBuilder();

		for (int i = 0; i < plain.length(); i++) {

			int method = Math.floorMod((letter_space.indexOf(plain.charAt(i)) + key), letter_space.length());
			// System.out.print(method + " , ");
			cypher.append(letter_space.charAt(method));
		}
		// System.out.println(cypher);

		return cypher;
	}

	// dec
	public static StringBuilder ceaser_dec(String cypher, int key) {
		// cypher text
		int n = letter_space.length();
		StringBuilder plain = new StringBuilder();

		for (int i = 0; i < cypher.length(); i++) {
			int c = letter_space.indexOf(cypher.charAt(i));
			// if c < 0 add 27
			while (c < 0) {
				c += n;

			}
			int method = Math.floorMod((c - key), n);

			// System.out.print(method + " , ");
			plain.append(letter_space.charAt(method));
		}
		// System.out.println(plain);
		return plain;
	}
}
