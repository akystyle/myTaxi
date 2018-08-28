package com.mytaxi.android_demo;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;

@CucumberOptions(
        glue = {"TestHelper","com.myTaxi.android_demo"},
        features = {"src/androidTest/java/cucumberTests"}
        )
public class AppSmokeTestSuite {



}
