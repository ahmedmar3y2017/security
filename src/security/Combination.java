package security;

// باقى انى اهندل ال z 
// لان ال z=q
public class Combination {
	public static String letter = "abcdefghijklmnopqrstuvwxyz";
	public static String letter_space = " abcdefghijklmnopqrstuvwxyz";

	// ---------- combination and Substitution --------
	// enc
	public static StringBuilder combination_Substitution_enc(String pplain) {
		char[] row = { 'a', 'b', 'c', 'd', 'e' };
		char[] col = { 'a', 'b', 'c', 'd', 'e' };
		// that return index from matrix
		StringBuilder final_result = new StringBuilder();

		// cypher text
		StringBuilder cypher = new StringBuilder();

		// create fixed matrix 5*5
		char ch[][] = new char[5][5];

		// delete all white spaces
		String plain = pplain.replaceAll("\\s", "");
		// count all char from a->z
		int count = 0;
		// insert all chars into matrix 5*5
		for (int i = 0; i < ch.length; i++) {
			for (int j = 0; j < ch.length; j++) {
				char c = letter.charAt(count);
				ch[i][j] = letter.charAt(count);
				count++;
			}

		}

		// get index of char from matriz 5*5
		for (int i = 0; i < plain.length(); i++) {
			// return result from matrix
			final_result.append(check_char(ch, plain.charAt(i)));

		}
		// get divid number
		int divid = final_result.length() / 2;
		// divid 1
		String res1 = final_result.substring(0, divid);
		// divid 2
		String res2 = final_result.substring(divid);
		// array char 1 ... div1
		char[] c1 = res1.toCharArray();
		// array char 2 ... div2
		char[] c2 = res2.toCharArray();
		int index1 = 0;
		int index2 = 0;
		for (int i = 0; i < c1.length; i++) {
			char cc1 = c1[i];
			char cc2 = c2[i];
			for (int j = 0; j < row.length; j++) {
				if (cc1 == row[j]) {
					index1 = j;
				}
				if (cc2 == col[j]) {

					index2 = j;

				}

			}
			// after finish char
			char rresult = get_char_from_index(ch, index1, index2);

			// append result char from matrix 5*5 to String cypher
			cypher.append(rresult);

		}
		// System.out.println("the cypher is : " + cypher);
		return cypher;

	}

	// get char from matrix 5*5 that contain all chars 26
	private static char get_char_from_index(char ch[][], int index1, int index2) {
		char cc = 0;
		for (int i = 0; i < ch.length; i++) {

			for (int j = 0; j < ch.length; j++) {

				if (i == index1 && j == index2) {
					cc = ch[i][j];

				}

			}
		}
		return cc;

	}

	//
	public static StringBuilder check_char(char ch[][], char c) {
		StringBuilder result = new StringBuilder();

		char[] row = { 'a', 'b', 'c', 'd', 'e' };
		char[] col = { 'a', 'b', 'c', 'd', 'e' };
		// search for characters in the matrix
		int count1 = 0;
		for (int i = 0; i < ch.length; i++) {

			for (int j = 0; j < ch.length; j++) {

				char cc = ch[i][j];

				if (cc == c) {

					result.append(row[i]);
					result.append(col[j]);

					break;
				}

			}
		}

		return result;

	}

	// dec
	public static StringBuilder combination_Substitution_dec(String ccypher) {
		char[] row = { 'a', 'b', 'c', 'd', 'e' };
		char[] col = { 'a', 'b', 'c', 'd', 'e' };
		StringBuilder text_cypher = new StringBuilder();
		StringBuilder final_cypher = new StringBuilder();

		// that return index from matrix
		StringBuilder final_result = new StringBuilder();
		// create fixed matrix 5*5
		char ch[][] = new char[5][5];

		// delete all white spaces
		String cypher = ccypher.replaceAll("\\s", "");
		// count all char from a->z
		int count = 0;
		// insert all chars into matrix 5*5
		for (int i = 0; i < ch.length; i++) {
			for (int j = 0; j < ch.length; j++) {
				char c = letter.charAt(count);
				ch[i][j] = letter.charAt(count);
				count++;
			}

		}
		// get index of char from matriz 5*5
		for (int i = 0; i < cypher.length(); i++) {
			// return result from matrix
			final_result.append(check_char(ch, cypher.charAt(i)));

		}
		// convert to array of char
		char arr[] = final_result.toString().toCharArray();
		// order cypher because he was not orderd
		for (int i = 0; i < arr.length; i += 2) {
			text_cypher.append(arr[i]);
			// System.out.print(arr[i]);

		}
		for (int i = 1; i < arr.length; i += 2) {
			// System.out.print(arr[i]);
			text_cypher.append(arr[i]);

		}
		int index1 = 0;
		int index2 = 0;
		// order 2 chars to get index from matrix
		for (int i = 0; i < text_cypher.length(); i += 2) {
			// divid text to 2 sequence chars to get index from matrix 5*5
			String s = text_cypher.substring(i, i + 2);
			// char 1
			char cc1 = s.charAt(0);
			// char 2
			char cc2 = s.charAt(1);
			// get index from row and column
			for (int j = 0; j < row.length; j++) {
				if (cc1 == row[j]) {
					// index 1 - row
					index1 = j;
				}
				if (cc2 == col[j]) {
					// index 2 - column
					index2 = j;

				}

			}
			// after finish char
			char rresult = get_char_from_index(ch, index1, index2);

			// append result char from matrix 5*5 to String cypher
			final_cypher.append(rresult);
		}

		return final_cypher;

	}
}
