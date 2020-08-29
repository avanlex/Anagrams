package com.github.avanlex;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.HashSet;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private Button buttonReverse;
    private TextInputLayout mEditTextFixedChars;
    private TextInputLayout mEditTextStringToReverse;
    private TextInputLayout mEditTextReversedString;

    static HashSet<Character> dictionary = new HashSet<Character>();

    public String inputStringToReverse() {
        return Objects.requireNonNull(mEditTextStringToReverse.getEditText()).getText().toString();
    }
    public void outputReversedString(String line) {
         Objects.requireNonNull(mEditTextReversedString.getEditText()).setText(line);
    }

    /**
     * @param s - string of chars which will be added to set
     */
    private static void setDictionary(String s) {
        dictionary.clear();
        for (Character c : s.toCharArray()) {
            dictionary.add(c);
        }
    }

    public void setupDictionary() {
        String line = Objects.requireNonNull(mEditTextFixedChars.getEditText()).getText().toString();
        // reading line and remove whitespaces
        line = line.replaceAll("\\s", "");
        setDictionary(line);
    }

    /**
     * getter for dict
     */
    private static HashSet<Character> getDictionary() {
        return dictionary;
    }

    private boolean isExcluded(char c) {
        return getDictionary().contains(c);
    }

    private String reverseWords(String in) {
        char[] chars = in.toCharArray();
        for (int i = 0, len = chars.length; i < len; i++) {
            if (chars[i] != ' ') {
                int end = i;
                //noinspection StatementWithEmptyBody
                while (++end < len && chars[end] != ' ') {}
                int j = end - 1;
                while (j > i) {
                    // Ignore excluded characters from the left
                    if (isExcluded(chars[i]))
                        i++;
                        // Ignore excluded characters from the right
                    else if (isExcluded(chars[j]))
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
        return String.valueOf(chars);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonReverse            = (Button)          findViewById(R.id.buttonReverse);
        mEditTextFixedChars      = (TextInputLayout) findViewById(R.id.editTextFixedChars);
        mEditTextReversedString  = (TextInputLayout) findViewById(R.id.editTextReversedString);
        mEditTextStringToReverse = (TextInputLayout) findViewById(R.id.editTextStringToReverse);

        buttonReverse.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String line = inputStringToReverse();
                        setupDictionary();
                        String result = reverseWords(line);
                        outputReversedString(result);
                    }
                });
    }


}