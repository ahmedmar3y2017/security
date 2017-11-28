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
		// dec
		// System.out.println(play_fair_dec("eksuplcfphzu", "monarchy"));

	}

	// ---------------------------- Hill cypher 3
	// --------------------------------------------------

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
