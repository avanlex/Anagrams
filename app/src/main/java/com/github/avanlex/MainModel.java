package com.github.avanlex;

import android.util.Log;

import java.util.HashSet;

public class MainModel implements MainContract.Model, Reversable {

    private static final String TAG = "MainModel";

    private HashSet<Character> makeDictionary(String s) {
        HashSet<Character> charSet = new HashSet<>();
        for (Character c : s.toCharArray()) {
            charSet.add(c);
        }
        Log.d(TAG, "makeDictionary()");
        return charSet;
    }

    @Override
    public String getReversed(String string, String dictionaryString) {
        HashSet<Character> exclusionDictionary = makeDictionary(dictionaryString);
        char[] chars = string.toCharArray();

        for (int headIndex = 0, len = chars.length; headIndex < len; headIndex++) {
            if (chars[headIndex] != ' ') {
                int wordEndIndex = headIndex; // zero length word

                //noinspection StatementWithEmptyBody
                while (++wordEndIndex < len && chars[wordEndIndex] != ' ') {} // scan word until [space] symbol
                int tailIndex = wordEndIndex - 1; // set index of char before space symbol

                while (tailIndex > headIndex) {
                    // Ignore excluded characters from the left
                    if (exclusionDictionary.contains(chars[headIndex]))
                        headIndex++;
                        // Ignore excluded characters from the right
                    else if (exclusionDictionary.contains(chars[tailIndex]))
                        tailIndex--;
                    else {
                        char temp = chars[headIndex];
                        chars[headIndex++] = chars[tailIndex];
                        chars[tailIndex--] = temp;
                    }
                }

                headIndex = wordEndIndex;
            }
        }
        Log.d(TAG, "getReversed()");
        return String.valueOf(chars);
    }
}
