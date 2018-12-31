package com.suite;

import com.example.vivek.espressotest.*;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by vivek on 19/12/18.
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ForgotPasswordTests.class,
        LoginAttemptsParameterized.class,
        LoginPageTest.class,
        ChangePasswordParameterized.class,
        ContextualMenuTests.class,
//        MainActivityTests.class,
        MyAccountTests.class,
        NavigationDrawerTests.class,
        RegistrationScreenValidation.class,
        SignUpTests.class
})
public class TestSuite {
}