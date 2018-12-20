package com.Suite;
import com.example.vivek.androidinstrumentedtest.ForgotPasswordTests;
import com.example.vivek.androidinstrumentedtest.LoginPageTests;
import com.example.vivek.androidinstrumentedtest.SignUpTests;
import com.example.vivek.androidinstrumentedtest.TestData;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by vivek on 19/12/18.
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({

        SignUpTests.class,
        LoginPageTests.class,
        TestData.class,
        ForgotPasswordTests.class,

})
public class TestSuite {
}