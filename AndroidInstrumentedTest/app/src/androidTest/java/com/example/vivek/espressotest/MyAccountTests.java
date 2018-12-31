package com.example.vivek.espressotest;

import android.support.test.rule.ActivityTestRule;

import com.sdxo.ui.activity.login.LoginActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import framework.dataGenerator.DataGenerator;
import framework.pageObjects.MainActivityScreen;
import framework.pageObjects.MyAccountScreen;

import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.Assert.assertTrue;

/**
 * Created by vivek on 24/12/18.
 */

public class MyAccountTests extends MyAccountScreen {

    private DataGenerator testdata = new DataGenerator();

    @Rule
    public ActivityTestRule<LoginActivity> loginActivityRule = new ActivityTestRule<>(LoginActivity.class);

    @Before
    public void setUp(){
        LoginPageTest test = new LoginPageTest();
        test.verifyUserAuthentication();

        MainActivityScreen main = new MainActivityScreen();
        main.openNavigationDrawer();
        main.openMyAccountScreen();

        /**
         *
         * For directly navigating and click to navigation item use
         *
         *         onView(withText(R.string.drawer_open)).perform(NavigationViewActions.navigateTo(R.string.nav_item_home));
         *
         *  Note: NavigationViewActions is a helper class which has navigateTo method defined
         */
    }

    @Test
    public void verifyEditProfile(){

        editProfile(testdata.getName(), "6232125456399");
//        editBirthdate(testdata.getYear(), testdata.getMonthOfYear(), testdata.getDayOfMonth());
        editGender(testdata.getGender());
        updateProfile("Updated the profile");
        pressBack();

        MainActivityScreen main = new MainActivityScreen();
        main.openNavigationDrawer();

        main.authenticate_user.check(matches(withText(testdata.getName())));
        main.openMyAccountScreen();

    }

    @Test
    public void verifyThatUserRedirectToHome(){

        boolean result = redirectToHome();
        assertTrue(result);

    }

    @Test
    public void doesChangePasswordDialogAppear(){
         boolean result = redirectToChangePassword();
         assertTrue(result);
    }

    @Test
    public void verifyClearFields(){
        clearFieldsAndUpdate();
    }

    @Test
    public void verifyTheErrorMsgOnBlankBirthdateField(){
        clearBirthdateAndUpdate();
    }

    @Test
    public void verifyTheErrorMsgOnBlankCityField(){
        clearCityAndUpdate();
    }

}
