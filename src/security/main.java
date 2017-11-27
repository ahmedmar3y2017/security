package security;

import java.util.ArrayList;

public class main {
	public static String letter = "abcdefghijklmnopqrstuvwxyz";
	public static String letter_space = " abcdefghijklmnopqrstuvwxyz";

	public static void main(String[] args) {
		// ceaser enc - dec
		// System.out.println(ceaser_enc("we need more fuel", 5));
		// System.out.println(ceaser_dec("ajesjjiertwjekzjq", 5));

		// transposition enc test
		// transposition_enc("we need more fuel", "mega buck");

		// ploy alphapitic enc , dec test
		// System.out.println(ploy_enc("ahmed marie", "security"));
		// System.out.println(ploy_dec("tmpzvifzjnh", "security"));

		// affine method enc , dec test
		// System.out.println(affine_enc(7, "war lost", 10, 26));
		// System.out.println(affine_dec(7, "ikzjegn", 10, 26));

		// combination_Substitution enc , dec test
		// System.out.println(combination_Substitution_enc("ahmed mohamed"));
		// System.out.println(combination_Substitution_dec("cegmkkcwatkn"));

		// hillcypher enc , dec test
		// System.out.println(hillcypher_enc("act"));
		// System.out.println(hillcypher_edec("iopr"));

		// One Time Pad enc , dec test
		// System.out.println(one_time_pad_enc("\fV ", "wehavetomeetyou"));

		// diffhelman
		// find key
		// find_key(71, 7, 5, 12);

		// Rsa enc , dec test
		// System.out.println(Rsa_enc("mohamed", 5, 7));
		// System.out.println(Rsa_dec("molamkj", 5, 7));

		// playfaire enc , dec test
		// enc
		// System.out.println("Final Result is : " + play_fair_enc("kill the fox",
		// "monarchy"));

	}

	// ---------------------------- play fair --------------------------
	// enc
	public static StringBuilder play_fair_enc(String pplain, String key) {

		StringBuilder cypher = new StringBuilder();
		// --------------------- key condiotion ---------------
		// no repeating
		if (No_repeating(key)) {

			// ------------------- plain condistions -------------------
			// 1 - delete all white spaces
			String plain = pplain.replaceAll("\\s", "");
			// 2 - delete similar
			StringBuilder ssBuilder = delete_similar(plain);
			// convert to string
			String plainString = ssBuilder.toString();
			// 3 - divid to 2 chars
			// if plain length != even
			if (plainString.length() % 2 != 0) {
				// if last char = x add -> z
				if (plainString.charAt(plainString.length() - 1) == 'x') {

					// add z
					plainString += "z";
				}
				// else add x
				else {
					// add x
					plainString += "x";
				}
			}
			// print plain after preparation
			System.out.println(plainString);

			// ------------------- start enc operations ------------------------
			char[][] matrix = Build_matrix(key);
			print_matrix(matrix);

			// divid to 2 chars
			for (int i = 0; i <= plainString.length(); i++) {
				// condition to divide letter to 2 chars
				if (i % 2 == 0 && i != 0) {
					// divid
					String s = plainString.substring(i - 2, i);
					System.out.print(s + " , ");
					// arrays of 2 chars
					char[] c = s.toCharArray();
					// get indexes from letter
					int[] c_index = new int[2];
					ArrayList<Integer> arrayList = new ArrayList<>();
					for (int j = 0; j < c.length; j++) {
						// array of c
						ArrayList<Integer> l_index = Check_Index(c[j], matrix);
						arrayList.addAll(l_index);
						// System.out.print(l_index);

					}
					int x1 = arrayList.get(0);
					int x2 = arrayList.get(2);
					int y1 = arrayList.get(1);
					int y2 = arrayList.get(3);
					// the same row
					if (x1 == x2) {
						System.out.println("Same row");
						// shift right by 1
						// shift first char
						if (y1 != 4) {
							y1 += 1;

						} else if (y1 == 4) {
							y1 -= 4;

						}
						// shift first char
						if (y2 != 4) {
							y2 += 1;

						} else if (y2 == 4) {
							y2 -= 4;

						}

						char res1 = matrix[x1][y1];
						char res2 = matrix[x2][y2];

						cypher.append(res1);
						cypher.append(res2);
						// System.out.println(cypher + " , ");

					}
					// the same column
					else if (y1 == y2) {
						System.out.println("Same Column");
						// shift right by 1
						// shift down char
						if (x1 != 4) {
							x1 += 1;

						} else if (x1 == 4) {
							x1 -= 4;

						}
						// shift first char
						if (x2 != 4) {
							x2 += 1;

						} else if (x2 == 4) {
							x2 -= 4;

						}

						char res1 = matrix[x1][y1];
						char res2 = matrix[x2][y2];

						cypher.append(res1);
						cypher.append(res2);
						// System.out.println(cypher + " , ");

					} else {
						System.out.println("Not");
						int x;
						x = y1;
						y1 = y2;
						y2 = x;
						char res1 = matrix[x1][y1];
						char res2 = matrix[x2][y2];

						cypher.append(res1);
						cypher.append(res2);
						// System.out.println(cypher + " , ");

					}

				}
			}

		} else {
			System.out.println("Error Key Repeating ");
		}

		return cypher;

	}

	// dec
	public static void play_fair_dec(String cypher, String key) {

		StringBuilder plain = new StringBuilder();
		// --------------------- key condiotion ---------------
		// no repeating
		if (No_repeating(key)) {

		} else {
			System.out.println("Error Key Repeating ");
		}

	}

	// build matrix 5*5
	public static char[][] Build_matrix(String key) {
		// init matrix 5*5
		char[][] matrix = new char[5][5];
		int count = 0;
		int count1 = 0;
		// add another chars
		StringBuilder aanother = get_anotherchars(key);
		// delete j because i =j from stringbuilder
		StringBuilder another = null;
		if (aanother.toString().contains("j") && key.contains("i")) {
			// delete j from another
			another = aanother.deleteCharAt(aanother.indexOf("j"));
		}
		// delete i because j=1 from stringbuilder
		else if (aanother.toString().contains("i") && key.contains("j")) {
			// delete i from another
			another = aanother.deleteCharAt(aanother.indexOf("i"));
		}

		// delete j from another if contain i and j
		else if (aanother.toString().contains("i") && aanother.toString().contains("j")) {
			// delete j from another
			another = aanother.deleteCharAt(aanother.indexOf("j"));
		} else {

			another = aanother;
		}

		// insert chars into matrix
		for (int i = 0; i < matrix.length; i++) {

			for (int j = 0; j < matrix.length; j++) {

				if (count != key.length()) {

					matrix[i][j] = key.charAt(count);
					count++;
				} else {

					char cc = another.charAt(count1);

					matrix[i][j] = cc;

					count1++;

				}

			}

		}

		return matrix;

	}

	// get another chars as a stringbuilder from a -- > z and not contain key chars
	private static StringBuilder get_anotherchars(String key) {
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < letter.length(); i++) {
			char c = letter.charAt(i);
			if (!key.contains(String.valueOf(c))) {
				stringBuilder.append(c);
			}

		}

		return stringBuilder;
	}

	// delete sequence repeating chars
	public static StringBuilder delete_similar(String plain) {

		StringBuilder stringBuilder = new StringBuilder(plain);

		// for each char
		for (int i = 1; i < plain.length(); i++) {
			// last char
			char c = plain.charAt(i);
			// previous char
			char cc = plain.charAt(i - 1);
			// if last = previous or (c=i and cc=j) or (c=j and cc=i)
			if (c == cc || (c == 'i' && cc == 'j') || (c == 'j' && cc == 'i')) {
				// if dublicated chars = x add - > z
				if (c == 'x') {
					// if last char
					if (i == plain.length() - 1) {
						// insert z
						stringBuilder.insert(i + 1, 'z');
					} else {
						// if not last char insert z
						stringBuilder.insert(i, 'z');

					}

				}
				// else add x
				else {
					// if last char
					if (i == plain.length() - 1) {

						stringBuilder.insert(i + 1, 'x');
					} else {
						// if not last char
						stringBuilder.insert(i, 'x');

					}
				}

			}

		}

		return stringBuilder;

	}

	// return true if no repeating else if repeating

	public static boolean No_repeating(String key) {

		// if contains i and j -- > this is repeating alse return false
		if (key.contains("i") && key.contains("j")) {

			return false;

		}

		for (int i = 0; i < key.length(); i++) {

			char c = key.charAt(i);
			if (countOccurrences(key, c) > 1) {
				return false;

			}

		}

		return true;

	}

	// return number of occure char in string
	public static int countOccurrences(String haystack, char needle) {
		int count = 0;
		for (int i = 0; i < haystack.length(); i++) {
			if (haystack.charAt(i) == needle) {
				count++;
			}
		}
		return count;
	}

	// print matrix
	public static void print_matrix(char c[][]) {

		for (int i = 0; i < c.length; i++) {
			for (int j = 0; j < c.length; j++) {

				System.out.print(c[i][j] + " , ");
			}
			System.out.println();
		}

	}

	// print matrix
	public static ArrayList<Integer> Check_Index(char cc, char c[][]) {
		ArrayList<Integer> index = new ArrayList<>();
		for (int i = 0; i < c.length; i++) {
			for (int j = 0; j < c.length; j++) {

				if (c[i][j] == cc) {
					index.add(i);
					index.add(j);

				}

			}

		}
		return index;

	}
	// ------------------------------------------------------------------------------

	public static void hillcypher3_enc(String plain) {
		// delete all white spaces
		String plain_ = plain.replaceAll("\\s", "");
		// number of letters
		int n = letter.length();
		// final cypher
		StringBuilder cypher = new StringBuilder();

		// init matrix 3*3 as akey
		int[][] key = new int[3][3];
		key[0][0] = 1;
		key[0][1] = 3;
		key[0][1] = 1;

		key[1][0] = 1;
		key[1][1] = 1;
		key[1][1] = 2;

		key[2][0] = 2;
		key[2][1] = 3;
		key[2][1] = 4;

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

	}

}
