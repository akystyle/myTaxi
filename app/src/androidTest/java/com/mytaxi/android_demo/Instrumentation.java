package com.mytaxi.android_demo;

import android.os.Bundle;
import android.support.test.runner.AndroidJUnitRunner;
import cucumber.api.android.CucumberInstrumentationCore;

public class Instrumentation extends AndroidJUnitRunner {

        public static final String TAG = Instrumentation.class.getSimpleName();

        private final CucumberInstrumentationCore instrumentationCore  = new CucumberInstrumentationCore(this);

        @Override
        public void onCreate(final Bundle bundle) {
            super.onCreate(bundle);
            instrumentationCore.create(bundle);
        }

        @Override
        public void onStart() {
            waitForIdleSync();
            instrumentationCore.start();
        }
}
