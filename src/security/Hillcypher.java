package security;

import java.math.BigInteger;

// ----------------- hillcypher 2*2 -----------------
// complete 2 * 2 
public class Hillcypher {
	public static String letter = "abcdefghijklmnopqrstuvwxyz";
	public static String letter_space = " abcdefghijklmnopqrstuvwxyz";

	

	// enc
	public static StringBuilder hillcypher_enc(int index00, int index01, int index10, int index11, String plain) {
		// delete all white spaces
		String plain_ = plain.replaceAll("\\s", "");

		// number of letters
		int n = letter.length();
		// final cypher
		StringBuilder cypher = new StringBuilder();

		// init matrix 2*2 as akey
		int[][] key = new int[2][2];
		key[0][0] = letter.indexOf("a");
		key[0][1] = index01;

		key[1][0] = index10;
		key[1][1] = index11;
		// mul main row
		int mul1 = 1;
		// mul second row
		int mul2 = 1;

		// value matrix 2*2
		// find matrix value
		for (int i = 0; i < key.length; i++) {

			for (int j = 0; j < key.length; j++) {
				// main row
				if (i == j) {

					mul1 *= key[i][j];

				}
				// second row
				else {
					mul2 *= key[i][j];
				}

			}

		}
		// final matrix value (main row - second row)
		int matrix = mul1 - mul2;
		// if marix <0 add 26
		while (matrix < 0) {
			matrix += n;

		}
		int key_value = Math.floorMod(matrix, n);

		// check gcd between key and n
		// if =1
		if (gcd(key_value, n) == 1) {
			// complete the code
			// if plain length != even
			if (plain_.length() % 2 != 0) {
				plain_ += "a";
			}
			// divid to 2 chars
			for (int i = 0; i <= plain_.length(); i++) {
				// condition to divide letter to 2 chars
				if (i % 2 == 0 && i != 0) {
					// divid
					String s = plain_.substring(i - 2, i);
					// arrays of 2 chars
					char[] c = s.toCharArray();
					// get indexes from letter
					int[] c_index = new int[2];
					for (int j = 0; j < c.length; j++) {
						// array of c
						c_index[j] = Get_Index(c[j], letter);

					}
					// mul 2 index plain * key
					int[] mul_matrix = mul_matrix(c_index, key);
					int[] final_result_matrix = new int[2];
					// for each elemtnt mod by n =26
					// final step
					for (int j = 0; j < mul_matrix.length; j++) {
						int mul_m = mul_matrix[j];
						// System.out.println(Math.floorMod(mul_m, n));
						final_result_matrix[j] = Math.floorMod(mul_m, n);

					}
					// get char by index of character
					// -------------------- final step ---------------
					for (int j = 0; j < final_result_matrix.length; j++) {
						// append final chars to cypher string
						cypher.append(letter.charAt(final_result_matrix[j]));

					}

					// System.out.println(c_index[0]);
					// System.out.println();
				}
				// System.out.println(s);
			}
			return cypher;
			// System.out.println(plain);
		} else {
			cypher.append("");
			System.out.println("error");
		}

		return cypher;
	}

	// mul 2 matrix (2*1)(2*2)
	private static int[] mul_matrix(int[] c_index, int[][] key) {
		// mul matrix 2*2
		int[] cyl_result = new int[2];
		// System.out.println(key.length);
		for (int i = 0; i < key.length; i++) {
			int sum = 0;
			// int[] cy1 = null;
			for (int j = 0; j < key.length; j++) {
				// System.out.println(c_index[j] + " " + key[i][j]);
				sum += c_index[j] * key[i][j];
			}
			cyl_result[i] = sum;
			// result[j]=
			// System.out.println(sum);
			// cy1[i] = sum;

		}

		return cyl_result;
	}

	// get index from latter
	private static int Get_Index(char c, String letter2) {

		for (int i = 0; i < letter2.length(); i++) {

			if (letter2.charAt(i) == c) {

				return i;

			}

		}

		return 0;
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

	// dec
	public static StringBuilder hillcypher_edec(int index00, int index01, int index10, int index11, String cypher) {
		// delete all white spaces
		String cypher_ = cypher.replaceAll("\\s", "");
		// number of letters
		int n = letter.length();
		// final plain
		StringBuilder plain = new StringBuilder();
		// init matrix 2*2 as akey
		int[][] key = new int[2][2];
		key[0][0] = index00;
		key[0][1] = index01;

		key[1][0] = index10;
		key[1][1] = index11;
		// mul main row
		int mul1 = 1;
		// mul second row
		int mul2 = 1;

		// value matrix 2*2
		// find matrix value
		for (int i = 0; i < key.length; i++) {

			for (int j = 0; j < key.length; j++) {
				// main row
				if (i == j) {

					mul1 *= key[i][j];

				}
				// second row
				else {
					mul2 *= key[i][j];
				}

			}

		}
		// final matrix value (main row - second row)
		int matrix = mul1 - mul2;
		// if marix <0 add 26
		while (matrix < 0) {
			matrix += n;

		}
		int key_value = Math.floorMod(matrix, n);
		// check gcd between key and n
		// if =1
		if (gcd(key_value, n) == 1) {
			// complete the code
			// if plain length != even
			if (cypher_.length() % 2 != 0) {
				cypher_ += "a";
			}

			int[][] key_inverse = matrix_inverse(key, key_value);
			// divid to 2 chars
			for (int i = 0; i <= cypher_.length(); i++) {
				// condition to divide letter to 2 chars
				if (i % 2 == 0 && i != 0) {
					// divid
					String s = cypher_.substring(i - 2, i);
					// arrays of 2 chars
					char[] c = s.toCharArray();
					// get indexes from letter
					int[] c_index = new int[2];
					for (int j = 0; j < c.length; j++) {
						// array of c
						c_index[j] = Get_Index(c[j], letter);

					}

					// mul 2 index plain * key
					int[] mul_matrix = mul_matrix(c_index, key_inverse);
					int[] final_result_matrix = new int[2];
					// for each elemtnt mod by n =26
					// final step
					for (int j = 0; j < mul_matrix.length; j++) {
						int mul_m = mul_matrix[j];
						// mod 26
						final_result_matrix[j] = Math.floorMod(mul_m, n);

					}
					// get char by index of character
					// -------------------- final step ---------------
					for (int j = 0; j < final_result_matrix.length; j++) {
						// append final chars to cypher string
						plain.append(letter.charAt(final_result_matrix[j]));

					}

				}
			}
			return plain;
		} else {
			plain.append("");
			System.out.println("Error ");

		}
		return plain;
	}

	// find matrix inverse = ( multiplicative inverse * mathimatical inverse )
	public static int[][] matrix_inverse(int[][] key, int key_value) {
		// 1 - find multiplicative inverse
		int multiplicative_inverse = findInverse(key_value, letter.length());
		// if mutiplicative inserse < 0 add 26
		while (multiplicative_inverse < 0) {
			multiplicative_inverse += letter.length();

		}

		// 2 - find mathimatical inverse
		for (int i = 0; i < key.length; i++) {
			for (int j = 0; j < key.length; j++) {
				// move element of main row
				if (i == j) {
					if (i == 0) {
						// swap between 2 items
						int val = key[i][j];
						key[i][j] = key[i + 1][j + 1];
						key[i + 1][j + 1] = val;

					}

				}
				// second row
				else {
					// mul * -1
					key[i][j] *= -1;

				}

				// mul each element * ultiplicative inverse
				key[i][j] *= multiplicative_inverse;
				// if element < 0 add 26
				while (key[i][j] < 0) {
					key[i][j] += letter.length();
				}
				// make mod with 26
				key[i][j] = Math.floorMod(key[i][j], 26);

			}

		}
		// return final matrix
		return key;

	}

	public static int findInverse(int num1, int num2) {
		BigInteger b1 = new BigInteger(String.valueOf(num1));
		BigInteger b2 = new BigInteger(String.valueOf(num2));

		return b1.modInverse(b2).intValue();

	}

}
