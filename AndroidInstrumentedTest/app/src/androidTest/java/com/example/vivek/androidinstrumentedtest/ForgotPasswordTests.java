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

import static junit.framework.Assert.assertTrue;


/**
 * Created by vivek on 13/12/18.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ForgotPasswordTests extends ForgotPasswordScreen {

    String expectedToastMessage = "Invalid Email";
    String expectedUserNotFoundMessage = "User not found";
    String expectedEmailSentMessage = "Email Sent";

    String wrongEmail = "test123.com";
    String correctEmail = "testmail@gmail.com";

    @Rule
    public ActivityTestRule<LoginActivity> forgotPassword = new ActivityTestRule<>(LoginActivity.class);


    public void clickForgotPasswordLink(){
        LoginPageTests lpt = new LoginPageTests();
        lpt.click_forgot_password();
    }

    @Before
    public void setUp(){

        //ToDo - Implement Idling Resource in Espresso
        //ToDo - Call increment() and decrement() in source code where Response code is implemented
//        IdlingRegistry.getInstance().register(EspressoIdlingResource.getIdlingResource());

    }

    //    @Test
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
        assertTrue(verifyTitle(expectedUserNotFoundMessage));
        clickSubmitButton();

    }

    //    @Test
    public void reset_password_with_valid_mail(){

        clickForgotPasswordLink();
        enterEmail(correctEmail);
        clickSubmitButton();
        waitForResponse();
        verifyEmailSentTitle(expectedEmailSentMessage);
        clickSubmitButton();
    }

}
