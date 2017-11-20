package security;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

// مش كامله اخر خطوه 
// باقى ال dec
public class Transposition {
	public static String letter = "abcdefghijklmnopqrstuvwxyz";
	public static String letter_space = " abcdefghijklmnopqrstuvwxyz";

	// ----------------------------- transposition ---------------
	// enc
	public static void transposition_enc(String plain, String key) {
		int n = letter.length();
		// delete all white spaces
		String plain_ = plain.replaceAll("\\s", "");
		String key_ = key.replaceAll("\\s", "");
		// ---------------------------------- need remove duplication
		// plain len
		int plain_len = plain_.length();
		// key len
		int key_len = key_.length();
		int mod_res = Math.floorMod(plain_len, key_len);
		// all indexes of content
		ArrayList<Integer> indexes = new ArrayList<>();

		// check if plain length > key
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
		System.out.println(list_key);
		// all content of string but dividing
		Map<Integer, String> content = new HashMap<>();
		int count = 0;

		for (int j = 0; j < key_len; j++) {
			char c = list_key.get(j);
			// System.out.print("s");
			indexes.add(Get_Index(c, arr_key));
			// Get_Index(c, arr_key);
		}
		StringBuilder stringBuilder1 = new StringBuilder();
		StringBuilder stringBuilder2 = new StringBuilder();
		StringBuilder stringBuilder3 = new StringBuilder();

		for (int j = 0; j < indexes.size(); j++) {
			int index = indexes.get(j);
			stringBuilder1.append(arr_key[index]);

		}
		for (int i = 0; i < plain_.length(); i += key_len) {
			String ss = plain_.substring(i, key_len + i);
			for (int j = 0; j < indexes.size(); j++) {
				int index = indexes.get(j);
				// stringBuilder1.append(arr_key[index]);
				stringBuilder2.append(ss.charAt(index));
				// stringBuilder3.append(ss.charAt(index));
			}
			// stringBuilder2.append(ss.charAt(i));

		}

		System.out.println(stringBuilder1);
		System.out.println(stringBuilder2);
		System.out.println(indexes);

	}

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
}
