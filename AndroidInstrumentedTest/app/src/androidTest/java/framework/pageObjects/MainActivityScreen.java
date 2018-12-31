package framework.pageObjects;

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.ViewInteraction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.View;

import com.sdxo.R;

import org.hamcrest.Matcher;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;

/**
 * Created by vivek on 21/12/18.
 */

public class MainActivityScreen {

    private ViewInteraction home = onView(allOf(withText(R.string.nav_item_home), withParent(withId(R.id.challenges))));
    private ViewInteraction my_vouchers = onView(allOf(withText(R.string.nav_item_vouchers), withParent(withId(R.id.challenges))));
    private ViewInteraction group_and_pay = onView(allOf(withText(R.string.nav_item_group_pay), withParent(withId(R.id.challenges))));
    private ViewInteraction activation_code = onView(allOf(withText(R.string.nav_item_activate), withParent(withId(R.id.challenges))));
    private ViewInteraction transfer_vouchers = onView(allOf(withText(R.string.nav_item_transfer), withParent(withId(R.id.challenges))));
    private ViewInteraction my_account = onView(allOf(withText(R.string.nav_item_my_account), withParent(withId(R.id.challenges))));
    private ViewInteraction my_transaction_history = onView(allOf(withText(R.string.nav_transaction_history), withParent(withId(R.id.challenges))));
    private ViewInteraction support = onView(allOf(withText(R.string.nav_item_support), withParent(withId(R.id.challenges))));
    private ViewInteraction logout = onView(allOf(withText(R.string.nav_item_logout), withParent(withId(R.id.challenges))));
    private ViewInteraction home_screen_title = onView(withText(R.string.title_home));
    public ViewInteraction authenticate_user = onView(withId(R.id.name));


    private ViewInteraction group_and_pay_title = onView(allOf(withText(R.string.title_group_n_pay), withParent(withId(R.id.toolbar))));
    private ViewInteraction my_vouchers_title = onView(allOf(withText(R.string.title_my_vouchers), withParent(withId(R.id.toolbar))));
    private ViewInteraction activation_code_title = onView(allOf(withText(R.string.title_activation_code), withParent(withId(R.id.toolbar))));
    private ViewInteraction transfer_vouchers_title = onView(allOf(withText(R.string.title_transfer), withParent(withId(R.id.toolbar))));
    protected ViewInteraction my_account_title = onView(allOf(withText(R.string.title_profile), withParent(withId(R.id.toolbar))));
    private ViewInteraction my_transaction_history_title = onView(allOf(withText(R.string.title_my_transactions), withParent(withId(R.id.toolbar))));
    private ViewInteraction support_title = onView(allOf(withText(R.string.support), withParent(withId(R.id.toolbar))));

    private ViewInteraction navigationDrawer = onView(withId(R.id.drawer_layout));


    protected boolean openHomeScreen(){

        try{
            home.perform(click());
            home_screen_title.check(matches(isDisplayed()));
            return true;
        }catch (Exception err){
            return false;
        }

    }


    protected boolean openMyVouchersScreen(){

        try{

            my_vouchers.perform(click());
            my_vouchers_title.check(matches(isDisplayed()));
            return true;
        }catch (Exception err){

            return false;
        }

    }

    protected boolean openGroupAndPayScreenTest(){

        try{
            group_and_pay.perform(click());
            group_and_pay_title.check(matches(isDisplayed()));
            return true;
        }catch(Exception err){

            return false;
        }

        /**
         *
         * Below locator also works
         * onView(allOf(withText("Group & Pay"),
         *                 isDescendantOfA(withId(R.id.toolbar))))
         *                 .check(matches(isDisplayed()));
         */

    }


    protected boolean openActivationCodeScreen(){

        try{
            activation_code.perform(click());
            activation_code_title.check(matches(isDisplayed()));
            return true;

        }catch (Exception err){
            return false;
        }

    }

    protected boolean openTransferVoucherScreen(){

        try{
            transfer_vouchers.perform(click());
            transfer_vouchers_title.check(matches(isDisplayed()));
            return true;
        }catch (Exception err){
            return false;
        }
    }

    public boolean openMyAccountScreen(){

        try{
            my_account.perform(click());
            my_account_title.check(matches(isDisplayed()));
            return true;
        }catch (Exception err){
            return false;
        }
    }

    protected boolean openMyTransactionHistoryScreen(){

        try{
            my_transaction_history.perform(click());
            my_transaction_history_title.check(matches(isDisplayed()));
            return true;

        }catch (Exception err){
            return false;
        }
    }

    protected boolean openSupportScreen(){

        try{
            support.perform(click());
            support_title.check(matches(isDisplayed()));
            return true;
        }catch (Exception err){
            return false;
        }
    }

    protected boolean openLoginScreen(){

        LoginScreen ls = new LoginScreen();

        try{
            logout.perform(click());
            ls.sodexoLogo.check(matches(isDisplayed()));
            return true;
        }catch (Exception err){
            return false;
        }
    }

    public void openNavigationDrawer(){
        navigationDrawer.perform(actionOpenDrawer());
    }


    private static ViewAction actionOpenDrawer() {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isAssignableFrom(DrawerLayout.class);
            }
            @Override
            public String getDescription() {
                return "open drawer";
            }
            @Override
            public void perform(UiController uiController, View view) {
                ((DrawerLayout) view).openDrawer(GravityCompat.START);
            }
        };
    }

}
