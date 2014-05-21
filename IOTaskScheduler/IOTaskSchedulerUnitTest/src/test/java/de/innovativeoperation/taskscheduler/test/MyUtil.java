package de.innovativeoperation.taskscheduler.test;

import java.util.Random;

/**
 * MyUtil enth�lt Methoden, die sich zum Testen als n�tzlich erwiesen haben.
 * 
 * @author Joscha Zander
 * 
 */
public class MyUtil {

	/**
	 * generates a String with desired length and content of desired letters
	 * "content"
	 * 
	 * @param length
	 *            - desired length of generatedString, it is the total length if
	 *            content is one char only
	 * @param content
	 *            - desired content as String, try to avoid more than one char
	 *            or you have to do math with the length (append)
	 * @return
	 */
	public static String generateRandomStringWithLength(int length){
		StringBuffer outputBuffer = new StringBuffer(length);
		
			 Random r = new Random();

			    String alphabet = "1234567890 abcdefghijklmnopqrstuvwxyz_ABCDEFGHIJKLMNOPQRSTUVWXYZ������\",.-:;#'+*~?=)(/&%$�!^�<>|�@";
			    for (int i = 0; i < length; i++) {			        
			        outputBuffer.append(alphabet.charAt(r.nextInt(alphabet.length())));
			     // prints "length" random characters from alphabet
		}
		return outputBuffer.toString();
	}
	public static String generateSingleCharStringOfLength(int length, String content){
		StringBuffer outputBuffer = new StringBuffer(length);
		
			for (int i = 0; i < length; i++) {
				outputBuffer.append(content);
		
		}
		return outputBuffer.toString();
	}
}
