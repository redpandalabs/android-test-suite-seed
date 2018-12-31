package framework.pageObjects;

import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.contrib.PickerActions;
import android.widget.DatePicker;

import com.sdxo.R;

import org.hamcrest.Matchers;

import framework.helpers.ToastMatcher;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.matcher.ViewMatchers.*;

import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static junit.framework.Assert.assertEquals;
import static org.hamcrest.core.AllOf.allOf;

/**
 * Created by vivek on 24/12/18.
 */

public class MyAccountScreen extends MainActivityScreen {

    protected ViewInteraction nameEditText = onView(withId(R.id.myaccountNameEditText));
    protected ViewInteraction birthdateEditText = onView(withId(R.id.myaccountBirthdateEditText));
    private ViewInteraction datePicker = onView(withClassName(Matchers.equalTo(DatePicker.class.getName())));
    private ViewInteraction okButton = onView(withText("OK"));
    private ViewInteraction cancelButton = onView(withText("CANCEL"));
    private ViewInteraction maleRadioButton = onView(withId(R.id.male));
    private ViewInteraction femaleRadioButton = onView(withId(R.id.female));

    private ViewInteraction emailEditText = onView(withId(R.id.myaccountEmailEditText));
    private ViewInteraction mobileEditText = onView(withId(R.id.myaccountMobileEditText));
    private ViewInteraction cityAutoCompleteText = onView(withId(R.id.autocompleteCityText));
    private ViewInteraction countryAutoCompleteText = onView(withId(R.id.myaccountCountryEditText));

    private ViewInteraction home = onView(allOf(withText(R.string.home), withParent(withId(R.id.bottommenu1))));
    private ViewInteraction home_screen_title = onView(withText("My Sodexo Mobile Wallet"));
    protected ViewInteraction update = onView(allOf(withText(R.string.update), withParent(withId(R.id.bottommenu3))));
    private ViewInteraction change_password_button = onView(allOf(withText(R.string.change_password), withParent(withId(R.id.bottommenu2))));

    private ViewInteraction change_password_dialog_title = onView(withId(R.id.titleTextView));
    private ViewInteraction submitButton = onView(withId(R.id.actionButton));

//    Change password text boxes
    private ViewInteraction oldPwdEditText = onView(withId(R.id.oldPasswordEditText));
    private ViewInteraction newPwdEditText = onView(withId(R.id.newPasswordEditText));
    private ViewInteraction retypePwdEditText = onView(withId(R.id.retypePasswordEditText));


    /**
     *
     * Change Password Dialog Functions
     *
     * @param oldPassword
     */
    protected void enterOldPassword(String oldPassword){
        oldPwdEditText.perform(clearText()).perform(typeText(oldPassword)).perform(closeSoftKeyboard());
    }

    protected void enterNewPassword(String newPassword){
        newPwdEditText.perform(clearText()).perform(typeText(newPassword)).perform(closeSoftKeyboard());
    }

    protected void enterRetypePassword(String retypePassword){
        retypePwdEditText.perform(clearText()).perform(typeText(retypePassword)).perform(closeSoftKeyboard());
    }

    protected void clickSubmitButton(){
        submitButton.perform(click());
    }


    /**
     *
     *
     * My Account screen functions
     *
     */

    protected void editName(String name){
        nameEditText.perform(clearText()).perform(typeText(name)).perform(closeSoftKeyboard());
    }

    protected void editBirthdate(int year, int monthOfYear, int dayOfMonth){
        birthdateEditText.perform(click());
        datePicker.perform(PickerActions.setDate(year, monthOfYear, dayOfMonth));
        okButton.perform(click());
    }

    protected void editMobileNo(String mobileno){
        mobileEditText.perform(clearText()).perform(typeText(mobileno)).perform(closeSoftKeyboard());
    }

    protected void editCity(String city){

        cityAutoCompleteText.perform(clearText()).perform(typeText(city)).perform(closeSoftKeyboard());
    }


    protected void editProfile(String name, String mobileno){
        nameEditText.perform(clearText()).perform(typeText(name)).perform(closeSoftKeyboard());
        mobileEditText.perform(clearText()).perform(typeText(mobileno)).perform(closeSoftKeyboard());

    }

    protected void editGender(String gender){
        onView(withText(gender)).perform(click());
    }

    protected boolean updateProfile(String message){

        try{

            update.perform(click());
            onView(withText(message)).inRoot(new ToastMatcher()).check(matches(isDisplayed()));
            return true;
        }catch (Exception err){
            return false;
        }
    }


    protected boolean redirectToHome(){

        try{
            home.perform(click());
            home_screen_title.check(matches(isDisplayed()));
            return true;
        }catch (Exception err){
            return false;
        }

    }


    public boolean redirectToChangePassword(){

        try {

            change_password_button.perform(click());
//            for dialog display
            change_password_dialog_title.check(matches(allOf(withText(R.string.change_password), isDisplayed())));
            return true;
        }catch (Exception err){

            return false;
        }
    }


    protected boolean errorToastMessage(String message){

        try{
            onView(withText(message)).inRoot(new ToastMatcher()).check(matches(isDisplayed()));
            return true;
        }catch (Exception err){
            return false;
        }
    }


    protected void clearFieldsAndUpdate(){

        nameEditText.perform(clearText());
        birthdateEditText.perform(clearText());
        emailEditText.perform(clearText());
        mobileEditText.perform(clearText());
        cityAutoCompleteText.perform(clearText());
        countryAutoCompleteText.perform(clearText());
        update.perform(click());
        assertEquals(InstrumentationRegistry.getTargetContext().getString(R.string.enter_name_validation_message),
                "Please enter name");
    }

    protected void clearBirthdateAndUpdate(){

        birthdateEditText.perform(clearText());
        update.perform(click());
        assertEquals(InstrumentationRegistry.getTargetContext().getString(R.string.select_birthdate_validation_message),
                "Please select birthdate");
    }

    protected void clearCityAndUpdate(){
        cityAutoCompleteText.perform(clearText());
        update.perform(click());
        assertEquals(InstrumentationRegistry.getTargetContext().getString(R.string.select_city_from_list),
                "Please select a city from the list.");
    }

}
