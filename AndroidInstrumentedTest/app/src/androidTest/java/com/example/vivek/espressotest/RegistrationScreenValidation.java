package com.example.vivek.espressotest;

import android.support.test.rule.ActivityTestRule;

import com.sdxo.ui.activity.login.LoginActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by vivek on 19/12/18.
 */

@RunWith(Parameterized.class)
public class RegistrationScreenValidation {

    @Rule
    public ActivityTestRule<LoginActivity> loginActivityRule = new ActivityTestRule<>(LoginActivity.class);

    private String name;
    private String email;
    private String city;
    private String password;
    private String confirmPassword;

    @Parameterized.Parameters
    public static Collection<Object[]> data(){

        return Arrays.asList(new Object[][] {
                {},
                {},
                {},
                {},
                {},
                {},
                {}
        });
    }

    public RegistrationScreenValidation(String name, String email, String city, String password, String confirmPassword){

        this.name = name;
        this.email = email;
        this.city = city;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    @Test
    public void verifyRegistration(){

    }

    public boolean verifyErrorMessages(String expectedResult){

        return true;
    }
}
