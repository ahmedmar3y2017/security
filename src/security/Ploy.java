package security;

import java.util.ArrayList;

public class Ploy {

	public static String letter = "abcdefghijklmnopqrstuvwxyz";
	public static String letter_space = " abcdefghijklmnopqrstuvwxyz";

	// ------------------------- ploy method ------------------------

	public static void main(String[] args) {
		// System.out.println(ploy_enc("fuel", "itys"));
		// System.out.println(ploy_dec("oncd", "security"));
	}

	// ploy alphapitic encryption
	public static StringBuilder ploy_enc(String plain, String kkey) {

		// key
		StringBuilder key = new StringBuilder(kkey);
		// result
		StringBuilder result = new StringBuilder();
		int inc = 0;
		// check if plain text > key
		while (plain.length() > key.length()) {// append again key
			key.append(key.charAt(inc));

			inc++;
		}
		// check if plain text < key
		if (plain.length() < key.length()) {
			key.replace(0, key.length(), key.substring(0, plain.length()));

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
		// check if plain text > key
		while (cypher.length() > key.length()) {// append again key
			key.append(key.charAt(inc));

			inc++;
		}
		// check if plain text < key
		if (cypher.length() < key.length()) {
			key.replace(0, key.length(), key.substring(0, cypher.length()));

		}

		for (int i = 0; i < cypher.length(); i++) {
			int sub = letter_space.indexOf(cypher.charAt(i)) - letter_space.indexOf(key.charAt(i));

			while (sub < 0) {
				sub += letter_space.length();

			}
			result.append(letter_space.charAt(Math.floorMod(sub, letter_space.length())));
		}
		return result;

	}

}
