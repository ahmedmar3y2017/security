package security;

import java.math.BigInteger;

public class Affine {
	public static String letter = "abcdefghijklmnopqrstuvwxyz";
	public static String letter_space = " abcdefghijklmnopqrstuvwxyz";
	// ------------------------- Affine method -------------------------------------

	// enc
	public static StringBuilder affine_enc(int m, String pplain, int k, int n) {

		// delete all white spaces
		String plain = pplain.replaceAll("\\s", "");
		// result
		StringBuilder result = new StringBuilder();
		// check m must < n
		if (m < n) {
			if (gcd(m, n) == 1) {
				// complete method
				for (int i = 0; i < plain.length(); i++) {
					// ecuation of affne (m*p+k) mod n
					int re = Math.floorMod((m * (letter.indexOf(plain.charAt(i))) + k), n);
					// System.out.print(re+ " , ");
					result.append(letter.charAt(re));

				}

			} else {
				// not complete enc
				System.out.println("Gcd != 1");

			}

		} else {
			System.out.println("m not less than n ");
		}
		return result;

	}

	// dec
	public static StringBuilder affine_dec(int m, String ccypher, int k, int n) {
		// create 3 BigInteger objects
		BigInteger bi1;
		BigInteger bi2;
		BigInteger m_inverse;
		// delete all white spaces
		String cypher = ccypher.replaceAll("\\s", "");
		// result
		StringBuilder result = new StringBuilder();
		// check m must < n
		if (m < n) {
			if (gcd(m, n) == 1) {
				// Two numbers are relatively prime if they have no
				// common factors, other than 1.
				bi1 = new BigInteger(String.valueOf(m));
				bi2 = new BigInteger(String.valueOf(n));
				m_inverse = bi1.modInverse(bi2);
				System.out.println(m_inverse.intValue());
				// complete method
				for (int i = 0; i < cypher.length(); i++) {
					// ecuation of affne (m*p+k) mod n
					int re = Math.floorMod((m_inverse.intValue() * (letter.indexOf(cypher.charAt(i)) - k)), n);
					// System.out.print(re+ " , ");
					result.append(letter.charAt(re));

				}

			} else {
				// not complete enc
				System.out.println("Gcd != 1");

			}

		} else {
			System.out.println("m not less than n ");
		}
		return result;

	}

	// calculate gcd
	public static int gcd(int a, int b) {
		if (a == 0 || b == 0)
			return a + b; // base case
		return gcd(b, a % b);
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
}
