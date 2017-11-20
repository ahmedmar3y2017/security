package security;

// ----------------- hillcypher 2*2 -----------------
// complete 2 * 2 
public class Hillcypher {
	public static String letter = "abcdefghijklmnopqrstuvwxyz";
	public static String letter_space = " abcdefghijklmnopqrstuvwxyz";

	// enc
	public static StringBuilder hillcypher_enc(String plain) {
		// delete all white spaces
		String plain_ = plain.replaceAll("\\s", "");
		// number of letters
		int n = letter.length();
		// final cypher
		StringBuilder cypher = new StringBuilder();

		// init matrix 2*2 as akey
		int[][] key = new int[2][2];
		key[0][0] = 9;
		key[0][1] = 4;

		key[1][0] = 5;
		key[1][1] = 7;
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
			System.out.println("error");
		}

		return null;
	}

	// mul 2 matrix (2*1)(2*2)
	private static int[] mul_matrix(int[] c_index, int[][] key) {
		// mul matrix 2*2
		int[] result = new int[2];
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
		if (a == 0 || b == 0)
			return a + b; // base case
		return gcd(b, a % b);
	}

	// dec
	public static StringBuilder hillcypher_edec(String cypher) {
		// delete all white spaces
		String cypher_ = cypher.replaceAll("\\s", "");
		// number of letters
		int n = letter.length();
		// final plain
		StringBuilder plain = new StringBuilder();
		// init matrix 2*2 as akey
		int[][] key = new int[2][2];
		key[0][0] = 9;
		key[0][1] = 4;

		key[1][0] = 5;
		key[1][1] = 7;
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

			int[][] key_inverse = matrix_inverse(key);
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
			System.out.println("Error ");

		}
		return null;
	}

	// find matrix inverse = ( multiplicative inverse * mathimatical inverse )
	public static int[][] matrix_inverse(int[][] key) {
		// 1 - find multiplicative inverse
		int multiplicative_inverse = findInverse(17, 26)[0];
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
