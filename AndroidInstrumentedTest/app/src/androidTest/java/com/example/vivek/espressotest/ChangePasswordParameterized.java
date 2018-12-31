package com.example.vivek.espressotest;

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.rule.ActivityTestRule;
import android.view.View;
import android.widget.TextView;

import com.sdxo.R;
import com.sdxo.ui.activity.login.LoginActivity;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import framework.pageObjects.MainActivityScreen;
import framework.pageObjects.MyAccountScreen;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.Assert.assertTrue;

/**
 * Created by vivek on 26/12/18.
 */

@RunWith(Parameterized.class)
public class ChangePasswordParameterized extends MyAccountScreen {

    @Rule
    public ActivityTestRule<LoginActivity> loginActivityRule = new ActivityTestRule<>(LoginActivity.class);

    private String oldPassword;
    private String newPassword;
    private String retypePassword;
    private String expectedResult;

    @Before
    public void setUp(){
        LoginPageTest test = new LoginPageTest();
        test.verifyUserAuthentication();

        MainActivityScreen main = new MainActivityScreen();
        main.openNavigationDrawer();
        main.openMyAccountScreen();

        MyAccountScreen m = new MyAccountScreen();
        m.redirectToChangePassword();
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data(){

        return Arrays.asList(new Object[][] {
                {"vivek123@", "Vivek1234@", "Vivek1234@", "Old password does not match"},
                {"Vivek123@", "Vivek1234", "Vivek1234", "Password should contain at least 1 special char from !@#$%^&*()_+=-"},
                {"Vivek123@", "vivek1234@", "vivek1234@", "Password should contain at least 1 uppercase and 1 lowercase character"},
                {"Vivek123@", "vivek", "vivek", "Password should contain at least 8 characters"},
                {"Vivek123@", "Vivek123", "Vivek1234", "Retype Password does not Match with new Password."},
                {"", "", "", "Please fill Required Details"},
                {"", "Vivek1234@", "Vivek1234@", "Please fill Required Details"},
                {"Vivek123@", "", "Vivek1234@", "Please fill Required Details"},
                {"Vivek123@", "Vivek1234@", "", "Please fill Required Details"},
                {"Vivek123@", "", "", "Please fill Required Details"},
                {"", "vivek1234@", "", "Please fill Required Details"},
                {"", "", "vivek1234@", "Please fill Required Details"}
        });
    }

    public ChangePasswordParameterized(String oldPassword, String newPassword, String retypePassword, String expectedResult){

        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
        this.retypePassword = retypePassword;
        this.expectedResult = expectedResult;
    }

    @Test
    public void verifyChangePasswordFunctionality(){

        enterOldPassword(oldPassword);
        enterNewPassword(newPassword);
        enterRetypePassword(retypePassword);
        clickSubmitButton();
        boolean result = verifyErrorMessages(expectedResult);
        assertTrue(result);

    }

    private boolean verifyErrorMessages(String expectedResult){

        if(errorToastMessage(expectedResult)){
            return true;
        }
        if(expectedResult.equals(getText(withId(R.id.subTitleTextView)))){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     *
     *
     *
     * @param matcher
     * @return
     *
     * To get text of a TextView then use below function
     */

    private String getText(final Matcher<View> matcher) {
        final String[] stringHolder = { null };
        onView(matcher).perform(new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isAssignableFrom(TextView.class);
            }

            @Override
            public String getDescription() {
                return "getting text from a TextView";
            }

            @Override
            public void perform(UiController uiController, View view) {
                TextView tv = (TextView)view; //Save, because of check in getConstraints()
                stringHolder[0] = tv.getText().toString();
            }
        });
        return stringHolder[0];
    }
}
