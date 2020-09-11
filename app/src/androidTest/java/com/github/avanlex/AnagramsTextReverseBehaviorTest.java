package com.github.avanlex;

import android.Manifest;
import android.app.Activity;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.rule.GrantPermissionRule;

import com.squareup.spoon.Spoon;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class AnagramsTextReverseBehaviorTest {

//    public static final String STRING_TO_BE_TYPED = "Espresso";
    public static final String STRING_TO_BE_TYPED = "mood";
    public static final String STRING_TO_BE_EXPECTED = "doom";
    private static final String PACKAGE_NAME = "com.github.avanlex";

    /**
     * Use {@link ActivityScenarioRule} to create and launch the activity under test, and close it
     * after test completes. This is a replacement for {@link androidx.test.rule.ActivityTestRule}.
     */
    /*******************************************************************************************************************************
     * Google recommends using ActivityScenarioRule instead ActivityTestRule, but I don't know How?
     *******************************************************************************************************************************/
    @Rule public ActivityScenarioRule<MainActivity> activityScenarioRule
            = new ActivityScenarioRule<>(MainActivity.class);

    /*******************************************************************************************************************************
     * THAT WAY TO GET ACTIVITY IS DEPRECATED BUT IT'S THE ONLY WAY I FOUND TO DEAL WITH passing to Spoon the Activity
     *******************************************************************************************************************************/
    @Rule public final ActivityTestRule<MainActivity> main
            =new ActivityTestRule(MainActivity.class, true);

    @Rule public GrantPermissionRule mRuntimePermissionRule =
            GrantPermissionRule.grant(
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE);

    @Test
    public void changeText_sameActivity() {
        // Type text and then press the button.
        onView(withId(R.id.tietStringToReverse))
                .perform(typeText(STRING_TO_BE_TYPED), closeSoftKeyboard());
        onView(withId(R.id.btnReverse)).perform(click());
        Activity activity = main.getActivity();  // GET ACTIVITY IS DEPRECATED :(
        Spoon.screenshot(activity, "changeText_sameActivity");
        // Check that the text was reversed.
        onView(withId(R.id.tietReversedString)).check(matches(withText(STRING_TO_BE_EXPECTED)));
    }
}


