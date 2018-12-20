package com.example.vivek.androidinstrumentedtest;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.LargeTest;

import com.sdxo.R;
import com.sdxo.ui.activity.login.LoginActivity;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import framework.pageObjects.LoginScreen;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


/**
 * Created by vivek on 10/12/18.
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(AndroidJUnit4.class)
@LargeTest
public class LoginPageTests extends ActivityInstrumentationTestCase2<LoginActivity> {

    private String registered_email_address= "testmail@gmail.com";
    private String registered_password = "Abc123@";

    @Rule
    public ActivityTestRule<LoginActivity> loginActivityRule = new ActivityTestRule<LoginActivity>(LoginActivity.class);

    public LoginPageTests() {

        super(LoginActivity.class);

    }

    @Test
    public void verifyLogo(){

        LoginScreen ls = new LoginScreen();
        ls.verifySodexoLogo();
    }


    @Test
    public void verifyUserAuthentication(){
        LoginScreen ls = new LoginScreen();
        ls.enterEmail(registered_email_address);
        ls.enterPassword(registered_password);
        ls.clickLoginButton();
        assertTrue(ls.verifyAuthentication());
    }

//    @Test
//    public void verifyString() throws Exception{
//
//        onView(withId(R.id.loginUserNameEditText)).perform(typeText("vvekkurhe555.com"), closeSoftKeyboard());
//        onView(withId(R.id.loginPasswordEditText)).perform(typeText("Vivek123"), closeSoftKeyboard());
//        onView(withId(R.id.loginButton)).perform(click());
//        String error_msg = InstrumentationRegistry.getTargetContext().getString(R.string.email_validation_message);
//        assertEquals("Please enter valid email", error_msg);
//
//    }


    @Test
    public void has_forgot_password_link() throws Exception{
        onView(withId(R.id.loginForgotPasswordTextView)).check(matches(isDisplayed()));
    }

    @Test
    public void click_forgot_password(){
        onView(withId(R.id.loginForgotPasswordTextView)).perform(click());
        onView(withText(R.string.forgot_password_title)).check(matches(isDisplayed()));

    }

}
