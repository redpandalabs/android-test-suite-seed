package com.example.vivek.androidinstrumentedtest;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.sdxo.ui.activity.login.LoginActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import framework.pageObjects.SignUpScreen;

import static junit.framework.Assert.assertTrue;


/**
 * Created by vivek on 14/12/18.
 */


@RunWith(AndroidJUnit4.class)
@LargeTest
public class SignUpTests extends SignUpScreen {

    private String registeredEmailAddress = "testmail@gmail.com";
    String password = "sffefewe";
    String confirmPassword = "erewfer123##";
    String city = "Bantul";

    @Rule
    public ActivityTestRule<LoginActivity> forgotPassword = new ActivityTestRule<LoginActivity>(LoginActivity.class);

    @Before
    public void setUp(){

        verifySignUpScreen();

    }

    public void verifyRegistrationWithFullFlow(){

    }

    @Test
    public void registrationWithAlreadyRegisteredEmailAddress(){

        enterName("Testname");
        selectBirthdate(1993, 10, 22);
        selectGender();
        selectCity(city);
        enterEmail(registeredEmailAddress);
        enterPassword(password);
        enterConfirmPassword(confirmPassword);
        clickRegisterButton();
        assertTrue(getRegistrationFailureMessage());
    }



    @Test
    public void testTermsAndConditions(){
        verifyTermsAndConditions();
    }
}
