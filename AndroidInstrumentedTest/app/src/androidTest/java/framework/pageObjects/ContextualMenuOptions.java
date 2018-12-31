package framework.pageObjects;

import android.support.test.espresso.ViewInteraction;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by vivek on 26/12/18.
 */

public class ContextualMenuOptions {

    private ViewInteraction my_account = onView(withText("My Account"));
    private ViewInteraction logout = onView(withText("Logout"));


    protected boolean openMyAccountScreen(){

        try{

            MainActivityScreen main = new MainActivityScreen();
            my_account.perform(click());
            main.my_account_title.check(matches(isDisplayed()));
            return true;
        }catch (Exception err){
            return false;
        }

    }


    protected boolean openLoginScreen(){

        try{
            LoginScreen ls = new LoginScreen();
            logout.perform(click());
            ls.sodexoLogo.check(matches(isDisplayed()));
            return true;
        }catch (Exception err){
            return false;
        }
    }
}
