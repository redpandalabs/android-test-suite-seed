package com.example.vivek.androidinstrumentedtest;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.sdxo.ui.activity.login.LoginActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import framework.pageObjects.ForgotPasswordScreen;
import framework.pageObjects.LoginScreen;

import static junit.framework.Assert.assertTrue;


/**
 * Created by vivek on 13/12/18.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ForgotPasswordTests extends ForgotPasswordScreen {

    private String expectedToastMessage = "Invalid Email";
    private String expectedToastErrorMessage = "User not found";
    private String expectedEmailSentMessage = "Email Sent";

    private String wrongEmail = "testemail@.com";
    private String correctEmail = "testmail@example.com";

    @Rule
    public ActivityTestRule<LoginActivity> forgotPassword = new ActivityTestRule<LoginActivity>(LoginActivity.class);


    public void clickForgotPasswordLink(){
        LoginScreen ls = new LoginScreen();
        ls.clickForgotPasswordLink();
    }

    @Before
    public void setUp(){

        //ToDo - Implement Idling Resource in Espresso
        //ToDo - Call increment() and decrement() in source code where Response code is implemented
//        IdlingRegistry.getInstance().register(EspressoIdlingResource.getIdlingResource());

    }

    @Test
    public void reset_password_without_email() {

        clickForgotPasswordLink();
        clickSubmitButton();
        verifyToastMessage(expectedToastMessage);
    }

    @Test
    public void reset_password_with_invalid_email(){

        clickForgotPasswordLink();
        enterEmail(wrongEmail);
        clickSubmitButton();
        assertTrue(verifyTitle(expectedToastErrorMessage));

    }

    @Test
    public void reset_password_with_valid_mail(){

        clickForgotPasswordLink();
        enterEmail(correctEmail);
        clickSubmitButton();
        verifySuccessEmailSentMessage(expectedEmailSentMessage);
        clickSubmitButton();
    }

}
