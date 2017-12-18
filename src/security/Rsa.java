package security;

import java.math.BigInteger;

public class Rsa {
	public static String letter = "abcdefghijklmnopqrstuvwxyz";

	// public static void main(String[] args) {
	// System.out.println(gcd(25, 26));
	// System.out.println(gcd1(25,26));
	//
	// }

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

		int D = findInverse(E, z);

		while (D < 0 || D == E) {
			D += z;
		}

		for (int i = 0; i < cypher.length(); i++) {
			int index = Math.floorMod((int) Math.pow(letter.indexOf(cypher.charAt(i)), D), letter.length());
			plain.append(letter.charAt(index));
		}

		return plain;
	}

	public static int findInverse(int num1, int num2) {
		BigInteger b1 = new BigInteger(String.valueOf(num1));
		BigInteger b2 = new BigInteger(String.valueOf(num2));

		return b1.modInverse(b2).intValue();

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

}
