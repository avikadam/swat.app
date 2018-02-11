package org.sits.probs.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Pattern;

public class StringOperations {
	/*
	 * Using LinkedHashMap to find first non repeated character of String
	 * Algorithm :
	 * 
	 * Step 1: get character array and loop through it to build a * hash table
	 * with char and their count.
	 * 
	 * Step 2: loop through LinkedHashMap to find an entry with value 1, that's
	 * your first non-repeated character, as LinkedHashMap maintains insertion
	 * order.
	 */
	public static char getFirstNonRepeatedChar(String str) {
		Map<Character, Integer> counts = new LinkedHashMap<>(str.length());
		for (char c : str.toCharArray()) {
			counts.put(c, counts.containsKey(c) ? counts.get(c) + 1 : 1);
		}
		for (Entry<Character, Integer> entry : counts.entrySet()) {
			if (entry.getValue() == 1) {
				return entry.getKey();
			}
		}
		throw new RuntimeException("didn't find any non repeated Character");
	}

	/*
	 * Finds first non repeated character in a String in just one pass.
	 * 
	 * It uses two storage to cut down one iteration, standard space vs time
	 * trade-off.Since we store repeated and non-repeated character separately,
	 * at the end of iteration, first element from List is our first non
	 * repeated character from String.
	 */
	public static char firstNonRepeatingChar(String word) {
		Set<Character> repeating = new HashSet<>();
		List<Character> nonRepeating = new ArrayList<>();
		for (int i = 0; i < word.length(); i++) {
			char letter = word.charAt(i);
			if (repeating.contains(letter)) {
				continue;
			}
			if (nonRepeating.contains(letter)) {
				nonRepeating.remove((Character) letter);
				repeating.add(letter);
			} else {
				nonRepeating.add(letter);
			}
		}
		return nonRepeating.get(0);
	}

	/*
	 * Using HashMap to find first non-repeated character from String in Java.
	 * Algorithm :
	 * 
	 * Step 1 : Scan String and store count of each character in HashMap
	 * 
	 * Step 2 : traverse String and get count for each character from Map. Since
	 * we are going through String from first to last character, when count for
	 * any character is 1, we break, it's the first non repeated character. Here
	 * order is achieved by going through String again.
	 */
	public static char firstNonRepeatedCharacter(String word) {
		HashMap<Character, Integer> scoreboard = new HashMap<>();
		// build table [char -> count]
		for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			if (scoreboard.containsKey(c)) {
				scoreboard.put(c, scoreboard.get(c) + 1);
			} else {
				scoreboard.put(c, 1);
			}
		}
		// since HashMap doesn't maintain order, going through string again
		for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			if (scoreboard.get(c) == 1) {
				return c;
			}
		}
		throw new RuntimeException("Undefined behaviour");
	}

	/**
	 * Loop over to reverse string
	 * 
	 * @param str
	 * @return
	 */
	public static String reverse(String str) {
		StringBuilder strBuilder = new StringBuilder();
		char[] strChars = str.toCharArray();

		for (int i = strChars.length - 1; i >= 0; i--) {
			strBuilder.append(strChars[i]);
		}
		return strBuilder.toString();
	}

	/**
	 * Reverse string recursively
	 * 
	 * @param str
	 * @return
	 */
	public static String reverseRecursively(String str) {
		// base case to handle one char string and empty string
		if (null == str || str.isEmpty() || str.length() < 2) {
			return str;
		}
		return reverseRecursively(str.substring(1)) + str.charAt(0);
	}

	public static boolean isNumber(String input) {
		Pattern pattern = Pattern.compile(".*[^0-9].*");
		// Pattern pattern = Pattern.compile(".*\\D.*");
		return !pattern.matcher(input).matches();

	}

	/**
	 * First take out the first char from String and permute the remaining chars
	 * 
	 * If String = "123"
	 * 
	 * First char = 1 and remaining chars permutations are 23 and 32.
	 * 
	 * Now we can insert first char in the available positions in the
	 * permutations.
	 * 
	 * 23 -> 123, 213, 231
	 * 
	 * 22 -> 132, 312, 321
	 * 
	 * @param str
	 * @return
	 */
	public static Set<String> permutation(String str) {
		Set<String> result = new HashSet<String>();
		if (str == null) {
			return null;
		} else if (str.length() == 0) {
			result.add("");
			return result;
		}

		char firstChar = str.charAt(0);
		String remaining = str.substring(1);
		Set<String> words = permutation(remaining);
		for (String newString : words) {
			for (int i = 0; i <= newString.length(); i++) {
				result.add(charAdd(newString, firstChar, i));
			}
		}
		return result;
	}

	private static String charAdd(String str, char c, int i) {
		String first = str.substring(0, i);
		String last = str.substring(i);
		return first + c + last;
	}

	/*
	 * Recursive method which actually prints all permutations of given String,
	 * but since we are passing an empty String as current permutation to start
	 * with, I have made this method private and didn't exposed it to client.
	 */
	private static void permutation(String perm, String word) {
		if (word.isEmpty()) {
			System.out.println(perm + word);
		} else {
			for (int i = 0; i < word.length(); i++) {
				permutation(perm + word.charAt(i), word.substring(0, i) + word.substring(i + 1, word.length()));
			}
		}
	}

	public static String reverseString(String line) {
		if (line.trim().isEmpty()) {
			return line;
		}
		StringBuilder reverse = new StringBuilder();
		String[] sa = line.trim().split("\\s");
		for (int i = sa.length - 1; i >= 0; i--) {
			reverse.append(sa[i]);
			reverse.append(' ');
		}
		return reverse.toString().trim();

	}

	/**
	 * Java method to check if given String is Palindrome
	 * 
	 * @param text
	 * @return true if text is palindrome, otherwise false
	 */
	public static boolean isPalindromString(String text) {
		String reverse = reverse(text);
		if (text.equals(reverse)) {
			return true;
		}
		return false;
	}

	// Returns true if str1[] is a subsequence of str2[]
	// m is length of str1 and n is length of str2
	static boolean isSubSequence(String str1, String str2, int m, int n) {
		// Base Cases
		if (m == 0)
			return true;
		if (n == 0)
			return false;

		// If last characters of two strings are matching
		if (str1.charAt(m - 1) == str2.charAt(n - 1))
			return isSubSequence(str1, str2, m - 1, n - 1);

		// If last characters are not matching
		return isSubSequence(str1, str2, m, n - 1);
	}

	/**
	 * Find given string is substring of parent string without using indexOf()
	 * 
	 * @param given
	 * @param parent
	 * @return
	 */
	public static boolean isSubstring(String given, String parent) {
		int counter = findFirstMatchPosition(given.charAt(0), parent);
		boolean isSubstring = true;
		if (counter >= 0) {
			for (int i = 1; i < given.length(); i++) {
				if (given.charAt(i) != parent.charAt(counter + i)) {
					isSubstring = false;
				}
			}
		} else {
			isSubstring = false;
		}
		return isSubstring;
	}

	// This part determines if the characters following the 1st
	// matching character are also matches
	private static int findFirstMatchPosition(char c, String parent) {
		for (int j = 0; j < parent.length(); j++) {
			if (parent.charAt(j) == c) {
				return j;
			}
		}
		return -1;
	}

	public static String removeRecursive(String word, char ch) {
		int index = word.indexOf(ch);
		if (index == -1) {
			return word;
		}
		return removeRecursive(word.substring(0, index) + word.substring(index + 1, word.length()), ch);
	}

	public static void main(String[] args) {
		System.out.println(reverseRecursively("abcd"));
		Set<String> result = permutation("AB");
		result.stream().forEach(System.out::println);
		permutation("", "YAHOO");
		System.out.println(isSubstring("AC", "ABCDEF"));
	}
}
