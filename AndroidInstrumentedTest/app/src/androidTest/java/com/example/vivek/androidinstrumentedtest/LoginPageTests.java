package com.example.vivek.androidinstrumentedtest;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.LargeTest;

import com.sdxo.ui.activity.login.LoginActivity;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import framework.pageObjects.ForgotPasswordScreen;
import framework.pageObjects.LoginScreen;


/**
 * Created by vivek on 10/12/18.
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(AndroidJUnit4.class)
@LargeTest
public class LoginPageTests extends ActivityInstrumentationTestCase2<LoginActivity> {

    private String registered_email_address= "testmail@example.com";
    private String registered_password = "test123@";

    private LoginScreen loginActivity = new LoginScreen();
    private ForgotPasswordScreen showDialog = new ForgotPasswordScreen();

    @Rule
    public ActivityTestRule<LoginActivity> loginActivityRule = new ActivityTestRule<LoginActivity>(LoginActivity.class);

    public LoginPageTest() {

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


    @Test
    public void isForgotPasswordLinkPresent() throws Exception{
        loginActivity.verifyForgotPasswordLink();
    }


    @Test
    public void verifyForgotPasswordLink(){
        loginActivity.clickForgotPasswordLink();
        showDialog.doesResetPasswordDialogAppear();
    }

}
