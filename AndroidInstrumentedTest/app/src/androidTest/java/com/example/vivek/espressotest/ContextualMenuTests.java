package com.example.vivek.espressotest;

import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.sdxo.ui.activity.login.LoginActivity;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import framework.pageObjects.ContextualMenuOptions;
import framework.pageObjects.LoginScreen;

import static junit.framework.Assert.assertTrue;

/**
 * Created by vivek on 26/12/18.
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(AndroidJUnit4.class)
@LargeTest
public class ContextualMenuTests extends ContextualMenuOptions {

    @Rule
    public ActivityTestRule<LoginActivity> loginActivityRule = new ActivityTestRule<>(LoginActivity.class);

    @Before
    public void setUp() throws Exception{
        LoginScreen ls = new LoginScreen();
        ls.enterEmail("Vvekkurhe555@gmail.com");
        ls.enterPassword("Vivek123@");
        ls.clickLoginButton();
        Espresso.openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());

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
    public void verifyMyAccount(){
        boolean result = openMyAccountScreen();
        assertTrue(result);
    }

    @Test
    public void verifyLogout(){

        boolean result = openLoginScreen();
        assertTrue(result);
    }

}
