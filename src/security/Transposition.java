package security;

import java.util.ArrayList;
import java.util.Collections;

// باقى ال dec
public class Transposition {
	public static String letter = "abcdefghijklmnopqrstuvwxyz";
	public static String letter_space = " abcdefghijklmnopqrstuvwxyz";

	// public static void main(String[] args) {
	// // enc
	// // transposition_enc("we need more fuel", "mega buck");
	// System.out.println(decrypt("aeubeecmaeeegnfkobmwrudl", "megabuck"));
	//
	// }

	// ----------------------------- transposition ---------------
	// enc
	public static StringBuilder transposition_enc(String plain, String key) {
		StringBuilder FinalCypher = new StringBuilder();

		// delete all white spaces
		String plain_ = plain.replaceAll("\\s", "");
		String key_ = key.replaceAll("\\s", "");
		// need remove duplication
		if (No_repeating(key_)) {
			// if true Continue
			// plain len
			int plain_len = plain_.length();
			// key len
			int key_len = key_.length();
			int mod_res = Math.floorMod(plain_len, key_len);

			// check if plain length > key add character to plain
			if (mod_res != 0) {
				// mode key len / res
				int f_res = key_len - mod_res;
				// add chars from a ---> z
				for (int i = 0; i < f_res; i++) {
					plain_ += letter.charAt(i);

				}

			}
			// array of chars (key )
			char[] arr_key = key_.toCharArray();
			char[] arr_plain = plain_.toCharArray();
			// list to sort chars key
			ArrayList<Character> list_key = new ArrayList<>();
			// add array plain to list
			for (int i = 0; i < arr_key.length; i++) {
				list_key.add(arr_key[i]);
			}

			// sort key chars
			Collections.sort(list_key);
			int numString = plain_.length() / key_.length();

			System.out.println("Finel Plain : " + plain_);

			for (int j = 0; j < key_len; j++) {
				char c = list_key.get(j);
				FinalCypher.append(c);
				// System.out.print("s");
				int index1 = Get_Index(c, arr_key);
				System.out.print("Index : " + index1 + "\t" + c);

				for (int i = 1; i <= numString; i++) {
					char c1 = arr_plain[index1 + ((i - 1) * key_len)];
					System.out.print("\t" + c1);
					FinalCypher.append(c1);

				}
				System.out.println();

			}
			System.out.println("Final Cypher : " + FinalCypher);

		} else {
			System.out.println("Key has repeating character");

		}

		return FinalCypher;

	}

	// return index from old key
	public static int Get_Index(char c, char[] cc) {
		int x = 0;
		for (int i = 0; i < cc.length; i++) {
			if (c == cc[i]) {

				x = i;
				break;
			}

		}
		return x;

	}
	// return true if no repeating else if repeating

	public static boolean No_repeating(String key) {

		for (int i = 0; i < key.length(); i++) {

			char c = key.charAt(i);
			if (countOccurrences(key, c) > 1) {
				return false;

			}

		}

		return true;

	}

	// dec
	public static String decrypt(String cypher, String kKey) {
		String plain = "";
		String Key = kKey.replaceAll("\\s", "");
		// new Key
		ArrayList<Character> keyList = new ArrayList<>();
		int keyLength = Key.length();
		int cypherlength = cypher.length();

		// put the element of the key in the key list
		for (int i = 0; i < keyLength; i++) {
			keyList.add(Key.charAt(i));
		}
		// sort the key list
		Collections.sort(keyList);

		// createthe matrix
		if (cypherlength % keyLength != 0) {
			System.out.println("the number of the cypher length must be divisable of the length of key");
			return "1";
		} else {
			int numOfRows = cypherlength / keyLength + 1;
			// create the matrix
			char[][] cypherMatrix = new char[numOfRows][keyLength];
			// enter the element of sorted key in the first row of the matrix
			for (int i = 0; i < keyLength; i++) {
				cypherMatrix[0][i] = keyList.get(i);
			}

			// complete the cypher matrix
			int cypherIndex = 0;
			for (int j = 0; j < keyLength; j++) {
				for (int i = 1; i < numOfRows; i++) {
					cypherMatrix[i][j] = cypher.charAt(cypherIndex);
					cypherIndex++;

				}
			}
			// compare old key without sort with new key
			int orderindex[] = new int[keyLength];
			for (int i = 0; i < keyLength; i++) {
				for (int j = 0; j < keyLength; j++) {
					if (Key.charAt(i) == cypherMatrix[0][j]) {

						// plain=plain.concat(String.valueOf(cypherMatrix[1][j]));
						orderindex[i] = j;
						break;
					}
				}
			}

			for (int i = 1; i < numOfRows; i++) {
				for (int j = 0; j < keyLength; j++) {

					System.out.print(String.valueOf(cypherMatrix[i][orderindex[j]]) + "\t ");
					plain = plain.concat(String.valueOf(cypherMatrix[i][orderindex[j]]));
				}
				System.out.println();
			}
			return plain;
		}
	}

	// return number of occure char in string
	public static int countOccurrences(String text, char charin) {
		int count = 0;
		for (int i = 0; i < text.length(); i++) {
			if (text.charAt(i) == charin) {
				count++;
			}
		}
		return count;
	}

}
