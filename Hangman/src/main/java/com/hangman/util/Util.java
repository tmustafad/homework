package com.hangman.util;

/**
 * Created by Turkmen on 29/11/2017
 */
public class Util {

	// here I do the masking . The word got out of the wording pool goes through this method and masked before persisting to db.
	//
	public static String generateMask(String word, String guess, String realWord, boolean isFirst) {
		// this util method is masking the guess for not showing the whole word to api
		// caller.
		if (!isFirst) {
			String display = realWord;
//			String mask = display.replaceAll("\\S", "*");
			char[] cLetters = display.toCharArray();
			char[] resultArr = word.toCharArray();
//			char[] cMask = mask.toCharArray();

			for (int i = 0; i < cLetters.length; i++) {
				if (cLetters[i] == guess.charAt(0)) {
					resultArr[i] = cLetters[i];
				}
			}

			String retString = new String(resultArr);

			return retString;
		} else {

			return realWord.replaceAll("\\S", "*");
			

		}

	}

	public static void main(String[] args) {
		System.out.println(generateMask("BL********", "D", "BLACKBOARD", false));
	}

}
