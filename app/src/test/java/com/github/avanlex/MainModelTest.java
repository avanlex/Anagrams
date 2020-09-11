package com.github.avanlex;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MainModelTest {
    String output;
    MainModel mainModel = new MainModel();
    String [] dictionaries = {"0123456789!", "xl"};
    String [] input = {"Foxminded cool 24/7", "abcd efgh", "a1bcd efg!h", "Foxminded cool 24/7", "abcd efgh", "a1bcd efglh"};
    String [] xpect = {"dednimxoF looc 24/7", "dcba hgfe", "d1cba hgf!e", "dexdnimoF oocl 7/42", "dcba hgfe", "dcb1a hgfle"};

    @Test
    public void getReversedNums() {
        for (int d = 0; d < dictionaries.length; d++){
            for (int i = 0; i < 3; i++) {
                int idx = (d+1) * (i+1) - 1;
                output = mainModel.getReversed(input[idx], dictionaries[d]);
                assertEquals(xpect[idx], output);
            }
        }
    }
}