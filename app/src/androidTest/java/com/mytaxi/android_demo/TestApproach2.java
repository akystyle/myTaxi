package com.mytaxi.android_demo;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.mytaxi.android_demo.activities.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import TestHelper.*;

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


//@RunWith(AndroidJUnit4.class)
//@LargeTest
public class TestApproach2 {

    //@Rule
    public ActivityTestRule<MainActivity> mAct = new ActivityTestRule<>(MainActivity.class,false,false);
    public SharedPrefUtil myPreferenece = new SharedPrefUtil();
    public CustomIdlingResource idlingRes = new CustomIdlingResource("MainActivity");

    //@Test
    //TODO move whole logic to feature file
    public void testMethod() throws Exception{
        myPreferenece.RemovedCachedCredentials();
        mAct.launchActivity(null);

        GenericHelperMethods hm = new GenericHelperMethods();
        //TODO will be removed when using feature files
        String matches = "matches", withText = "withText", withId = "withId", typeText = "typeText", closeSoftKeyboard = "closeSoftKeyboard",
        click = "click";

        //TODO move to a object Repository (Depends on type of framework to be used) and write a helper method to fetch from OR
        String UNBox = "R.id.edt_username",PBox = "R.id.edt_password", LBtn = "R.id.btn_login", SrchBox = "R.id.textSearch";

        //TODO fetch from test data files, depending on organisation preference, test data can be fetched from different sources
        String userName = "crazydog335", pwd = "venture", search = "sa";

        hm.UserCheck("",matches,withText,UNBox,withId);
        hm.UserPerform(typeText,userName,UNBox,withId);
        hm.UserPerform(closeSoftKeyboard,"",UNBox,withId);
        hm.UserCheck("",matches,withText,PBox,withId);
        hm.UserPerform(typeText,pwd,PBox,withId);
        hm.UserPerform(closeSoftKeyboard,"",PBox,withId);
        hm.UserPerform(click,"",LBtn,withId);
        hm.UserWaits("2");
        hm.UserPerform(typeText,search,SrchBox,withId);
        hm.UserPerform(closeSoftKeyboard,"",SrchBox,withId);

        //TODO below steps can also be converted to above mentioned approach
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

        //TODO verify the content sent on fab click
    }
}
