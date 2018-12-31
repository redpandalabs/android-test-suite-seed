package framework.pageObjects;

import android.content.res.Resources;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.contrib.PickerActions;
import android.support.test.espresso.matcher.RootMatchers;
import android.widget.DatePicker;

import com.sdxo.R;

import org.hamcrest.Matchers;

import java.util.Random;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by vivek on 14/12/18.
 */

public class SignUpScreen {

    private ViewInteraction signUpLink = onView(withId(R.id.loginRegisterTextView));
    private ViewInteraction name = onView(withId(R.id.registerNameEditText));
    private ViewInteraction birthDate = onView(withId(R.id.registerBirthdateEditText));
    private ViewInteraction citySelector = onView(withId(R.id.autocompleteCityText));
    private ViewInteraction registerEmail = onView(withId(R.id.registerEmailEditText));
    private ViewInteraction registerPassword = onView(withId(R.id.registerPasswordEditText));
    private ViewInteraction registerConfirmPassword = onView(withId(R.id.registerConfirmPasswordEditText));
    private ViewInteraction registerButton = onView(withId(R.id.registerButton));
    private ViewInteraction termsAndConditions = onView(allOf(withId(R.id.sodexoTermsAndConditions), isClickable()));
    private ViewInteraction okButton = onView(withText("OK"));
    private ViewInteraction datePicker = onView(withClassName(Matchers.equalTo(DatePicker.class.getName())));
    private ViewInteraction signUpScreenTitle = onView(withText("Please fill in the form"));
    private ViewInteraction registrationFailureMessage = onView(withId(R.id.subTitleTextView));

    protected void enterName(String username){

        name.perform(clearText()).perform(typeText(username)).perform(closeSoftKeyboard());

    }

    protected void enterEmail(String email){

        registerEmail.perform(clearText()).perform(typeText(email)).perform(closeSoftKeyboard());
    }

    protected void enterPassword(String pwd){

        registerPassword.perform(clearText()).perform(typeText(pwd)).perform(closeSoftKeyboard());
    }

    protected void enterConfirmPassword(String confirmPassword){
        registerConfirmPassword.perform(clearText()).perform(typeText(confirmPassword)).perform(closeSoftKeyboard());
    }

    protected void clickRegisterButton(){
        registerButton.perform(click());
    }

    protected void openTermsAndConditionsPage(){
        termsAndConditions.perform(click());
    }

    protected void selectGender(String gender){

        onView(withText(gender)).perform(click());
    }

    protected void selectCity(String city){

        citySelector.perform(click());
        onData(equalTo(city)).inRoot(RootMatchers.isPlatformPopup()).perform(click());

    }

    protected void selectBirthdate(int year, int monthOfYear, int dayOfMonth){

        birthDate.perform(click());
        datePicker.perform(PickerActions.setDate(year, monthOfYear, dayOfMonth));
        okButton.perform(click());
    }

    protected void verifySignUpScreen(){

        signUpLink.perform(click());
        signUpScreenTitle.check(matches(isDisplayed()));

    }

    protected String getCity(){

        /**
         *
         * To get the array of strings from strings.xml file, use following approach
         *
         *<string-array name="cities">
         *     <item>string1</item>
         *     <item>string2</item>
         *     :
         *</string-array>
         *
         *
         */

        Random rand = new Random();
        Resources resource = InstrumentationRegistry.getContext().getResources();
        String[] citiesList = resource.getStringArray(R.array.cities);
        int size = citiesList.length;
        int x = rand.nextInt(size);
        return citiesList[x];

    }

    protected boolean getRegistrationFailureMessage(){

        try{
            registrationFailureMessage.check(matches(isDisplayed()));
            okButton.perform(click());
            return true;

        }catch (Exception err){
            return false;
        }
    }

}
