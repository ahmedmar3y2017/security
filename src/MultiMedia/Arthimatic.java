package MultiMedia;

import java.awt.HeadlessException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Arthimatic {

	public static void main(String[] args) {

		String Message = "baca";

		Arthimatic_compression(Message);

		// System.out.println(calc_probalility('a', "aame"));
	}

	public static void Arthimatic_compression(String Message) {
		double low = 0;
		double high = 1;
		double range = 1;
		double[] lows = new double[Message.length()];
		double[] highs = new double[Message.length()];

		HashMap<Character, Double> probabilities = new HashMap<>();
		// remove dublication characters
		String new_Message = remove_dublicated(Message);
		System.out.println(new_Message);
		// calculate probabilities and ranges
		for (int i = 0; i < new_Message.length(); i++) {
			char c = new_Message.charAt(i);

			// calc_low(low, range, low_range);
			// calc_high(low, range, high_range);
			// calc_range(low, high)

			probabilities.put(c, calc_probalility(c, Message));

		}

		// sort Hsahmap By Probabilities
		HashMap<Character, Double> Sorted_probabilities = sortHashMapByValues(probabilities);
		System.out.println(Sorted_probabilities);
		// calculate hight , low ranges
		int count = 0;
		for (Character c : Sorted_probabilities.keySet()) {
			if (count == 0) {
				lows[count] = 0;
				highs[count] = Sorted_probabilities.get(c);

			} else {
				lows[count] = highs[count - 1];
				highs[count] = lows[count] + Sorted_probabilities.get(c);
			}
			count++;

		}

		System.out.println(
				lows[0] + " " + highs[0] + " \n " + lows[1] + "  " + highs[1] + "  \n  " + lows[2] + "   " + highs[2]);
		double Range_lows[] = new double[Message.length()];
		double Range_highs[] = new double[Message.length()];
		double ranges[] = new double[Message.length()];
		// begin arthimatic operations for each character
		for (int i = 0; i < Message.length(); i++) {
			char c = Message.charAt(i);
			// get index from map
			int index = getIndex(c, Sorted_probabilities);
			// System.out.println(lows[index] + " " + highs[index]);
			double low1;
			double high1;
			double rr;
			if (i == 0) {

				low1 = calc_low(i, range, lows[index]);
				high1 = calc_high(i, range, highs[index]);
				rr = high1 - low1;

			} else {
				low1 = calc_low(Range_lows[i - 1], ranges[i - 1], lows[index]);
				high1 = calc_high(Range_lows[i - 1], ranges[i - 1], highs[index]);
				rr = high1 - low1;
				// int hight;
			}
			Range_lows[i] = low1;
			Range_highs[i] = high1;
			ranges[i] = rr;
			// ---------------------------  print in android label --------------------------------------
			System.out.println(c + "\tLow : " + low1 + " \t " + "High : " + high1 + " \t " + "Range : " + rr);

		}

	}

	private static int getIndex(char c, HashMap<Character, Double> sorted_probabilities) {
		int count = 0;
		for (Character cc : sorted_probabilities.keySet()) {
			if (cc == c) {

				break;
			}

			count++;
		}

		return count;
	}

	public static String remove_dublicated(String string) {

		char[] chars = string.toCharArray();
		Set<Character> charSet = new LinkedHashSet();
		for (char c : chars) {
			charSet.add(c);
		}

		StringBuilder sb = new StringBuilder();
		for (Character character : charSet) {
			sb.append(character);
		}
		return sb.toString();

	}

	// Method to sort a string alphabetically
	public static String sortString(String inputString) {
		// convert input string to char array
		char tempArray[] = inputString.toCharArray();

		// sort tempArray
		Arrays.sort(tempArray);

		// return new sorted string
		return new String(tempArray);
	}

	private static double calc_probalility(char c, String message) {
		int count = 0;
		double totalProbPos = message.length();
		for (int i = 0; i < totalProbPos; i++) {

			if (message.charAt(i) == c) {
				count++;

			}

		}

		totalProbPos = (count / totalProbPos);

		return totalProbPos;
	}

	public static double calc_range(double high, double low) {

		return high - low;

	}

	public static double calc_high(double low, double range, double high_range) {

		return low + (range * high_range);
	}

	public static double calc_low(double low, double range, double low_range) {

		return low + (range * low_range);

	}

	public static LinkedHashMap<Character, Double> sortHashMapByValues(HashMap<Character, Double> passedMap) {
		List<Character> mapKeys = new ArrayList<>(passedMap.keySet());
		List<Double> mapValues = new ArrayList<>(passedMap.values());
		Collections.sort(mapValues, Collections.reverseOrder());
		Collections.sort(mapKeys);

		LinkedHashMap<Character, Double> sortedMap = new LinkedHashMap<>();

		Iterator<Double> valueIt = mapValues.iterator();
		while (valueIt.hasNext()) {
			Double val = valueIt.next();
			Iterator<Character> keyIt = mapKeys.iterator();

			while (keyIt.hasNext()) {
				Character key = keyIt.next();
				Double comp1 = passedMap.get(key);
				Double comp2 = val;

				if (comp1.equals(comp2)) {
					keyIt.remove();
					sortedMap.put(key, val);
					break;
				}
			}
		}
		return sortedMap;
	}

}
