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
				result.append("1");

			}

		} else {
			System.out.println("m not less than n ");
			result.append("2");

		}
		return result;

	}

	// dec
	public static StringBuilder affine_dec(int m, String ccypher, int k, int n) {
		// create 3 BigInteger objects
		BigInteger bi1;
		BigInteger bi2;
		int m_inverse;
		// delete all white spaces
		String cypher = ccypher.replaceAll("\\s", "");
		// result
		StringBuilder result = new StringBuilder();
		// check m must < n
		if (m < n) {
			if (gcd(m, n) == 1) {
				// Two numbers are relatively prime if they have no
				// common factors, other than 1.

				m_inverse = findInverse(m, n);
				System.out.println(m_inverse);
				// complete method
				for (int i = 0; i < cypher.length(); i++) {
					// ecuation of affne (m*p+k) mod n
					int re = Math.floorMod((m_inverse * (letter.indexOf(cypher.charAt(i)) - k)), n);
					// System.out.print(re+ " , ");
					result.append(letter.charAt(re));

				}

			} else {
				// not complete enc
				System.out.println("Gcd != 1");
				result.append("1");

			}

		} else {
			System.out.println("m not less than n ");
			result.append("2");
		}
		return result;

	}

	// calculate gcd
	public static int gcd(int a, int b) {
		int gcd = 0;
		if (a == 0 || b == 0) {
			gcd = 0; // base case
		} else {

			// a>b B>a a==b
			if (a > b) {
				for (int i = b; i > 0; i--) {

					if (a % i == 0 && b % i == 0) {
						gcd = i;
						break;

					}

				}

			} else if (a < b) {

				for (int i = a; i > 0; i--) {

					if (a % i == 0 && b % i == 0) {
						gcd = i;
						break;

					}

				}
			}
			// a==b
			else {
				gcd = a;

			}

		}

		return gcd;
	}

	public static int findInverse(int num1, int num2) {
		BigInteger b1 = new BigInteger(String.valueOf(num1));
		BigInteger b2 = new BigInteger(String.valueOf(num2));

		return b1.modInverse(b2).intValue();

	}

}
