package framework.helpers;

import android.support.test.espresso.PerformException;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.view.View;

import org.hamcrest.Matcher;

import static org.hamcrest.Matchers.anything;

/**
 * Created by vivek on 13/12/18.
 */

public class WaitAction implements ViewAction {

    private final Matcher<View> condition;
    private final long timeoutMs;


    public WaitAction(Matcher<View> condition, long timeout) {
        this.condition = condition;
        this.timeoutMs = timeout;
    }

    @Override
    public Matcher<View> getConstraints() {
//        getConstraints();
        return (Matcher) anything();
    }

    @Override
    public String getDescription() {
        return "wait";
    }

    @Override
    public void perform(UiController uiController, View view) {

        uiController.loopMainThreadUntilIdle();
        final long startTime = System.currentTimeMillis();
        final long endTime = startTime + timeoutMs;

        while (System.currentTimeMillis() < endTime) {
            if (condition.matches(view)) {
                return;
            }

            uiController.loopMainThreadForAtLeast(100);
        }

        // Timeout
    }

    public static ViewAction waitFor(Matcher<View> condition, long timeout) {
        return new WaitAction(condition, timeout);
    }


}
