package com.mytaxi.android_demo;

import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.mytaxi.android_demo.activities.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import TestHelper.CustomIdlingResource;
import TestHelper.SharedPrefUtil;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ExampleInstrumentedTest {


    @Rule
    public ActivityTestRule<MainActivity> mAct = new ActivityTestRule<>(MainActivity.class,false,false);
    public SharedPrefUtil myPreferenece = new SharedPrefUtil();
    public CustomIdlingResource idlingRes = new CustomIdlingResource("MainActivity");

    @Test
    public void approach1() throws Exception {

        myPreferenece.RemovedCachedCredentials();
        mAct.launchActivity(null);
        onView(withId(R.id.edt_username)).perform(clearText());
        onView(withId(R.id.edt_username)).perform(ViewActions.typeText("crazydog335"), closeSoftKeyboard());
        onView(withId(R.id.edt_password)).perform(clearText());
        onView(withId(R.id.edt_password)).perform(ViewActions.typeText("venture"), closeSoftKeyboard());
        onView(withId(R.id.btn_login)).perform(click());
        Thread.sleep(2000);
        onView(withId(R.id.textSearch)).perform(clearText());
        onView(withId(R.id.textSearch)).perform(ViewActions.typeText("sa"), closeSoftKeyboard());

        onView(withText("Sara Christensen"))
                .inRoot(withDecorView(not(is(mAct.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
        onView(withText("Sarah Scott"))
                .inRoot(withDecorView(not(is(mAct.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));

        onView(withText("Sarah Scott"))
                .inRoot(withDecorView(not(is(mAct.getActivity().getWindow().getDecorView()))))
                .perform(click());

        onView(withId(R.id.fab)).perform(click());
    }

    @Test
    public void approach2() throws Exception{
        myPreferenece.RemovedCachedCredentials();
        mAct.launchActivity(null);
        onView(withId(R.id.edt_username)).check(matches(ViewMatchers.withText("")));
        onView(withId(R.id.edt_username)).perform(ViewActions.typeText("crazydog335"), closeSoftKeyboard());
        onView(withId(R.id.edt_password)).check(matches(ViewMatchers.withText("")));
        onView(withId(R.id.edt_password)).perform(ViewActions.typeText("venture"), closeSoftKeyboard());
        onView(withId(R.id.btn_login)).perform(click());
        //TODO implement idling resource mechanism instead of Thread Sleep
        Thread.sleep(2000);
        onView(withId(R.id.textSearch)).perform(clearText());
        onView(withId(R.id.textSearch)).perform(ViewActions.typeText("sa"), closeSoftKeyboard());

        onView(withText("Sara Christensen"))
                .inRoot(withDecorView(not(is(mAct.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
        onView(withText("Sarah Scott"))
                .inRoot(withDecorView(not(is(mAct.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));

        onView(withText("Sarah Scott"))
                .inRoot(withDecorView(not(is(mAct.getActivity().getWindow().getDecorView()))))
                .perform(click());

        onView(withId(R.id.fab)).perform(click());
    }
}
