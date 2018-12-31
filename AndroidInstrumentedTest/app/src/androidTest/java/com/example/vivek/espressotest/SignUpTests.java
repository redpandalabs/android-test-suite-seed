package com.example.vivek.espressotest;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.sdxo.ui.activity.login.LoginActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import framework.dataGenerator.DataGenerator;
import framework.pageObjects.SignUpScreen;

import static junit.framework.Assert.assertTrue;


/**
 * Created by vivek on 14/12/18.
 */


@RunWith(AndroidJUnit4.class)
@LargeTest
public class SignUpTests extends SignUpScreen {

    private String registeredEmailAddress = "vvekkurhe555@gmail.com";
    String city = "Bantul";

    private DataGenerator testData = new DataGenerator();

    @Rule
    public ActivityTestRule<LoginActivity> forgotPassword = new ActivityTestRule<>(LoginActivity.class);

    @Before
    public void setUp(){

        verifySignUpScreen();

    }

//    public void verifyRegistrationWithFullFlow(){
//
//        enterName(testData.getName());
//        selectBirthdate(4,4,4);
//        selectGender(gender);
//        selectCity(testData.getCity());
//        enterEmail(testData.getEmail());
//        enterPassword(testData.getPassword());
//        enterConfirmPassword(testData.getPassword());
//        clickRegisterButton();
//    }

    @Test
    public void registrationWithAlreadyRegisteredEmailAddress(){

        enterName(testData.getName());
        selectBirthdate(testData.getYear(), testData.getMonthOfYear(), testData.getDayOfMonth());
        selectGender(testData.getGender());
        selectCity(city);
        enterEmail(registeredEmailAddress);
        enterPassword(testData.getPassword());
        enterConfirmPassword(testData.getPassword());
        clickRegisterButton();
        assertTrue(getRegistrationFailureMessage());
    }



//    @Test
    public void verifyTermsAndConditions(){
        openTermsAndConditionsPage();
    }
}
