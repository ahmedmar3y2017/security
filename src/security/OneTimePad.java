package security;

public class OneTimePad {

	// enc
	// download aski code tables
	public static StringBuilder one_time_pad_enc(String plain, String kkey) {

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
		// check if plain text < key cut key to plain length
		if (plain.length() < key.length()) {
			key.replace(0, key.length(), key.substring(0, plain.length()));

		}

		// get index for each element and add them then get new char
		for (int i = 0; i < plain.length(); i++) {
			char c1 = plain.charAt(i);
			char k1 = key.charAt(i);
			// get aski
			int aski1 = (int) c1;
			int aski2 = (int) k1;
			// convert to binary
			String binary1 = Integer.toBinaryString(aski1);
			String binary2 = Integer.toBinaryString(aski2);

			StringBuilder rest_xor = calc_xor(binary1, binary2);
			// System.out.println(rest_xor);
			int res_aski = Integer.parseInt(rest_xor.toString(), 2);
			char res_char = (char) res_aski;
			// System.out.println(res_char);
			result.append(res_char);
		}

		return result;

	}

	public static StringBuilder calc_xor(String s1, String s2) {
		// string 1
		StringBuilder one = new StringBuilder(s1);
		// string 2
		StringBuilder two = new StringBuilder(s2);
		int len1 = one.length();
		int len2 = two.length();
		// if one > two add chars to two
		while (one.length() > two.length()) {
			two.insert(0, '0');
		}
		// if one < two add chars to one
		while (one.length() < two.length()) {
			one.insert(0, '0');
		}
		// System.out.println(one);
		// System.out.println(two);
		StringBuilder sb = new StringBuilder();
		// append result to sb after xor
		for (int i = 0; i < one.length(); i++)
			sb.append((one.charAt(i) ^ two.charAt(i)));

		return sb;

	}
}
