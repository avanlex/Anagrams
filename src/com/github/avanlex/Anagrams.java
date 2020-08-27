/**
 * 
 */
package com.github.avanlex;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.HashSet;

/**
 * @author Aleksey Andreev https://github.com/avanlex
 */

public class Anagrams {

	static HashSet<Character> dictionary = new HashSet<Character>();
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	private static boolean isExcluded(char c){
		return getDictionary().contains(c);
	}

	public static String inputString() throws IOException {

		System.out.println("Enter a String:");
		String line = br.readLine(); //"Jav!!a program7ming is fun an8d easy";
		//System.out.println(line);
		return line;
	}

	public static void inputDict() throws IOException {
		System.out.print("Enter a Dictionary (string of char)\nIf You want to skip it, just hit ENTER");
		Charset utf8Charset = Charset.forName("UTF-8");
		PrintStream out = new PrintStream(System.out, true, utf8Charset.name());
		out.println("\u21b5");
		String line = br.readLine().replaceAll("\\s",""); // reading line and remove whitespaces
		setDictionary(line);
	}
	
	private static String reverseWords( String in )
	{
		char[] chars = in.toCharArray();
		for( int i = 0, len = chars.length; i < len; i++ )
		{
			if( chars[i] != ' ')
			{
				int end = i;
				while( ++end < len && chars[end] != ' ' );
				int j = end - 1;
				while( j > i )
				{
					// Ignore excluded characters from the left
					if (isExcluded(chars[i]))
						i++;
					// Ignore excluded characters from the right
					else if(isExcluded(chars[j]))
						j--;
					else {
						char temp = chars[i];
						chars[i++] = chars[j];
						chars[j--] = temp;
					}
				}
				i = end;
			}
		}
		return String.valueOf( chars );
	}

	/**
	 * getter for dict
	 */
	private static HashSet<Character> getDictionary(){
		return dictionary;
	}

	/**
	 * @param s - string of chars which will be added to set
	 */
	private static void setDictionary(String s){
		for (Character c : s.toCharArray()) {
			dictionary.add(c);
		}
	}	

	/**
	 * main routine
	 */
	public static void main(String[] args) throws IOException {
		String string = inputString();
		inputDict();
		System.out.println(reverseWords(string));
	}
}
