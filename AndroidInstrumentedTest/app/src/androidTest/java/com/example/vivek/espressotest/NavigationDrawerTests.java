package com.example.vivek.espressotest;

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

import framework.pageObjects.MainActivityScreen;

import static junit.framework.Assert.assertTrue;

/**
 * Created by vivek on 21/12/18.
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(AndroidJUnit4.class)
@LargeTest
public class NavigationDrawerTests extends MainActivityScreen {

    @Rule
    public ActivityTestRule<LoginActivity> loginActivityRule = new ActivityTestRule<>(LoginActivity.class);

    @Before
    public void setUp(){
        LoginPageTest test = new LoginPageTest();
        test.verifyUserAuthentication();
        openNavigationDrawer();

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
    public void test1_verifyHomeScreen(){

        boolean result = openHomeScreen();
        assertTrue(result);

    }

    @Test
    public void test2_verifyGroupAndPayScreen(){

        boolean result = openGroupAndPayScreenTest();
        assertTrue(result);


    }

    @Test
    public void test3_verifyMyVouchersScreen(){

        boolean result = openMyVouchersScreen();
        assertTrue(result);

    }

    @Test
    public void test4_verifyActivationCodeScreen(){

        boolean result = openActivationCodeScreen();
        assertTrue(result);

    }

    @Test
    public void test5_verifyTransferVoucherScreen(){

        boolean result = openTransferVoucherScreen();
        assertTrue(result);

    }

    @Test
    public void test6_verifyMyAccountScreen(){

        boolean result = openMyAccountScreen();
        assertTrue(result);

    }

    @Test
    public void test7_verifyMyTransactionHistoryScreen(){

        boolean result = openMyTransactionHistoryScreen();
        assertTrue(result);

    }

    @Test
    public void test8_verifySupportScreen(){

        boolean result = openSupportScreen();
        assertTrue(result);

    }

    @Test
    public void test9_verifyLogout(){

        boolean result = openLoginScreen();
        assertTrue(result);

        /**
         *
         *   This opens Face icon having 'My Account' and 'Logout' options
         *    Method 1
         *    Espresso.openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
         *    onView(withText("Logout")).perform(click());
         *
         *   Method 2
         *   Espresso.openContextualActionModeOverflowMenu();
         *
         *
         */

    }

}
