package security;

public class Rsa {
	public static String letter = "abcdefghijklmnopqrstuvwxyz";

	// ------------------ Rsa -----------------------
	// Enc
	public static StringBuilder Rsa_enc(String plain, int p, int q) {
		StringBuilder cypher = new StringBuilder();
		int n = p * q;
		int z = (p - 1) * (q - 1);
		int E = 0;
		// find e
		for (int i = 0; i < 100; i++) {

			if (gcd(i, z) == 1 && i != 1) {
				E = i;
				break;
			}

		}

		for (int i = 0; i < plain.length(); i++) {
			int index = Math.floorMod((int) Math.pow(letter.indexOf(plain.charAt(i)), E), letter.length());
			cypher.append(letter.charAt(index));
		}

		return cypher;

	}

	// dec
	public static StringBuilder Rsa_dec(String cypher, int p, int q) {
		StringBuilder plain = new StringBuilder();
		int n = p * q;
		int z = (p - 1) * (q - 1);
		int E = 0;
		// find e
		for (int i = 0; i < 100; i++) {

			if (gcd(i, z) == 1 && i != 1) {
				E = i;
				break;
			}

		}

		int D = findInverse(E, z)[0];

		while (D < 0) {
			D += z;
		}

		for (int i = 0; i < cypher.length(); i++) {
			int index = Math.floorMod((int) Math.pow(letter.indexOf(cypher.charAt(i)), D), letter.length());
			plain.append(letter.charAt(index));
		}

		return plain;
	}

	// calc inverse
	public static int[] findInverse(int a, int b) {
		int x = 0, y = 1, lastx = 1, lasty = 0;
		while (b != 0) {
			int quotient = a / b;

			int temp = a;
			a = b;
			b = temp % b;

			temp = x;
			x = lastx - quotient * x;
			lastx = temp;

			temp = y;
			y = lasty - quotient * y;
			lasty = temp;
		}

		int[] coefficients = { lastx, lasty, a };
		return coefficients;
	}

	// calculate gcd
	public static int gcd(int a, int b) {
		if (a == 0 || b == 0)
			return a + b; // base case
		return gcd(b, a % b);
	}
}
