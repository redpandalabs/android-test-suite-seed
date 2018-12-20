package framework.pageObjects;

import android.content.res.Resources;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.contrib.PickerActions;
import android.support.test.espresso.matcher.RootMatchers;
import android.widget.DatePicker;

import com.sdxo.R;

import org.hamcrest.Matchers;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
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
    private ViewInteraction termsAndConditions = onView(withId(R.id.sodexoTermsAndConditions));
    private ViewInteraction closeButton = onView(withText("CLOSE"));
    private ViewInteraction checkTitle = onView(withText("T&C (Terms & Conditions)"));

    private ViewInteraction registrationFailureMessage = onView(withId(R.id.subTitleTextView));

    protected void enterName(String username){

        name.perform(typeText(username), closeSoftKeyboard());

    }

    protected void enterEmail(String email){
        registerEmail.perform(typeText(email), closeSoftKeyboard());
    }

    protected void enterPassword(String pwd){
        registerPassword.perform(typeText(pwd), closeSoftKeyboard());
    }

    protected void enterConfirmPassword(String pwd){
        registerConfirmPassword.perform(typeText(pwd), closeSoftKeyboard());
    }

    protected void clickRegisterButton(){
        registerButton.perform(click());
    }

    protected void verifyTermsAndConditions(){
        termsAndConditions.perform(click());
        checkTitle.check(matches(isDisplayed()));
        closeButton.perform(click());
    }

    protected void selectGender(){

    }

    protected void selectCity(String city){

        citySelector.perform(click());
        onData(equalTo(city)).inRoot(RootMatchers.isPlatformPopup()).perform(click());

    }

    protected void selectBirthdate(int year, int monthOfYear, int dayOfMonth){

        birthDate.perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(year, monthOfYear, dayOfMonth));
        onView(withText("OK")).perform(click());
    }

    protected void verifySignUpScreen(){

        signUpLink.perform(click());
        onView(withText("Please fill in the form")).check(matches(isDisplayed()));

    }

    protected String getCity(String city){

//        To get the array of strings, use following approach
        Resources resource = InstrumentationRegistry.getContext().getResources();
        String[] citiesList = resource.getStringArray(R.array.cities);
        int size = citiesList.length;

        for(int i=0; i<size; i++){

            if(citiesList[i].equals(city)){
                return city;
            }
        }

        return null;
    }

    protected boolean getRegistrationFailureMessage(){

        try{
            registrationFailureMessage.check(matches(isDisplayed()));
            return true;

        }catch (Exception err){
            return false;
        }
    }

}
