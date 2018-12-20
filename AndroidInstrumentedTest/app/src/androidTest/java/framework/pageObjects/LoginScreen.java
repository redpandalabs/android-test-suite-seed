package framework.pageObjects;

import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.action.ViewActions;

import com.sdxo.R;

import framework.testBase.TestBase;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by vivek on 13/12/18.
 */

public class LoginScreen extends TestBase {

    private ViewInteraction loginEmailField = onView(withId(R.id.loginUserNameEditText));
    private ViewInteraction loginPasswordField = onView(withId(R.id.loginPasswordEditText));
    private ViewInteraction loginButton = onView(withId(R.id.loginButton));
    private ViewInteraction sodexoLogo = onView(withId(R.id.loginAppImageView));
    private ViewInteraction authenticateUser = onView(withText("My Sodexo Mobile Wallet"));


    public void enterEmail(String email){
        loginEmailField.perform(clearText());
        loginEmailField.perform(typeText(email), closeSoftKeyboard());
    }

    public void enterPassword(String pwd){
        loginPasswordField.perform(clearText());
        loginPasswordField.perform(typeText(pwd), closeSoftKeyboard());
    }

    public void clickLoginButton(){
        loginButton.perform(ViewActions.click());
    }

    public boolean verifyAuthentication(){
        try{
            authenticateUser.check(matches(isDisplayed()));
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public ViewInteraction getAuthentication(){

        return authenticateUser.check(matches(isDisplayed()));

    }

    public void verifySodexoLogo(){
        sodexoLogo.check(matches(isDisplayed()));
    }

}
