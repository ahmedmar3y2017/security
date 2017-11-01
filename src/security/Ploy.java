package security;

import java.util.ArrayList;

public class Ploy {

	public static String letter = "abcdefghijklmnopqrstuvwxyz";
	public static String letter_space = " abcdefghijklmnopqrstuvwxyz";

	// ------------------------- ploy method ------------------------
	// ploy alphapitic encryption
	public static StringBuilder ploy_enc(String plain, String kkey) {

		// key
		StringBuilder key = new StringBuilder(kkey);
		// result
		StringBuilder result = new StringBuilder();
		int inc = 0;
		// check if plain text > key
		for (;;) {
			if (plain.length() > key.length()) {

				// append again key
				key.append(key.charAt(inc));

				inc++;
			} else {
				break;
			}
		}

		// get index for each element and add them then get new char
		for (int i = 0; i < plain.length(); i++) {
			int sum = letter_space.indexOf(plain.charAt(i)) + letter_space.indexOf(key.charAt(i));
			result.append(letter_space.charAt(Math.floorMod(sum, letter_space.length())));
		}

		return result;

	}

	// poly alphapitic decryption

	public static StringBuilder ploy_dec(String cypher, String kkey) {

		StringBuilder key = new StringBuilder(kkey);
		StringBuilder result = new StringBuilder();

		ArrayList<Integer> results = new ArrayList<>();
		int inc = 0;
		for (;;) {
			if (cypher.length() > key.length()) {

				key.append(key.charAt(inc));
				inc++;
			} else {
				break;
			}
		}

		for (int i = 0; i < cypher.length(); i++) {
			int sub = letter_space.indexOf(cypher.charAt(i)) - letter_space.indexOf(key.charAt(i));

			if (sub < 0) {

				sub += 27;
			}
			result.append(letter_space.charAt(Math.floorMod(sub, letter_space.length())));
		}
		return result;

	}

}
