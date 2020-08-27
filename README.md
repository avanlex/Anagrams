# Task 1. Anagrams


## Application
Create an application that rotates a string according to a user-defined algorithm.

## Required functionality
+ Write an Android application that accepts one string as an input.

+ String should be typed by the user.

+ Program should reverse all words of input text and show result on the screen.

+ Make set of characters that will be ignored in rotation, allow users to extend that set.


## Technology stack
+ Language - Java

## Description
For example:

1. **Ignored characters are “0123456789” (numbers only);**
    1. **Input:** “Foxminded cool 24/7” -> **Output:** “dednimxoF looc 24/7”
    1. **Input:** “abcd efgh”   -> **Output:** “dcba hgfe”
    1. **Input:** “a1bcd efg!h” -> **Output:** “d1cba hgf!e”


1. **Ignored characters are “xl”;**
    1. **Input:** “Foxminded cool 24/7” -> **Output:** “dexdnimoF oocl 24/7”
    1. **Input:** “abcd efgh” -> **Output:** “dcba hgfe”
    1. **Input:** “a1bcd efglh” -> **Output:** “dcb1a hgfle”

