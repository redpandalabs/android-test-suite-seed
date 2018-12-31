package framework.helpers;

import android.support.annotation.IdRes;
import android.support.test.espresso.ViewInteraction;

import org.junit.Assert;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by vivek on 13/12/18.
 */

public class WaitForUIUpdate {

    public static void waifForWithId(@IdRes int stringId) {

        ViewInteraction element;
        do {
            waitFor(500);

            //simple example using withText Matcher.
            element = onView(withText(stringId));

        } while (!framework.helpers.MatcherExtension.exists(element));
//        (!com.example.vivek.espressotest.MatcherExtension.exists(element))
    }

    static void waitFor(int ms) {
        final CountDownLatch signal = new CountDownLatch(1);

        try {
            signal.await(ms, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            Assert.fail(e.getMessage());
        }
    }

}
