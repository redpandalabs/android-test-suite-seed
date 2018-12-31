package framework.waits;

/**
 * Created by vivek on 13/12/18.
 */

import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.FailureHandler;
import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.PerformException;
import android.support.test.espresso.Root;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.ViewInteraction;
import android.view.View;

import org.hamcrest.Matcher;

import java.util.concurrent.TimeoutException;

/**
 * Contains view interactions, view actions and view assertions which allow to set a timeout
 * for finding a view and performing an action/view assertion on it.
 * To be used instead of {@link Espresso} methods.
 *
 * @author Piotr Zawadzki
 */
public class TimeoutEspresso {

    private static final int SLEEP_IN_A_LOOP_TIME = 50;

    private static final long DEFAULT_TIMEOUT_IN_MILLIS = 10 * 1000L;

    /**
     * Use instead of {@link Espresso#onView(Matcher)}
     * @param timeoutInMillis timeout after which an error is thrown
     * @param viewMatcher view matcher to check for view
     * @return view interaction
     */
    public static TimedViewInteraction onViewWithTimeout(long timeoutInMillis, @NonNull final Matcher<View> viewMatcher) {

        final long startTime = System.currentTimeMillis();
        final long endTime = startTime + timeoutInMillis;

        do {
            try {
                return new TimedViewInteraction(Espresso.onView(viewMatcher));
            } catch (NoMatchingViewException ex) {
                //ignore
            }

            SystemClock.sleep(SLEEP_IN_A_LOOP_TIME);
        }
        while (System.currentTimeMillis() < endTime);

        // timeout happens
        throw new PerformException.Builder()
                .withCause(new TimeoutException("Timeout occurred when trying to find: " + viewMatcher.toString()))
                .build();
    }

    /**
     * Use instead of {@link Espresso#onView(Matcher)}.
     * Same as {@link #onViewWithTimeout(long, Matcher)} but with the default timeout {@link #DEFAULT_TIMEOUT_IN_MILLIS}.
     * @param viewMatcher view matcher to check for view
     * @return view interaction
     */
    public static TimedViewInteraction onViewWithTimeout(@NonNull final Matcher<View> viewMatcher) {
        return onViewWithTimeout(DEFAULT_TIMEOUT_IN_MILLIS, viewMatcher);
    }

    /**
     * A wrapper around {@link ViewInteraction} which allows to set timeouts for view actions and assertions.
     */
    public static class TimedViewInteraction {

        private ViewInteraction wrappedViewInteraction;

        public TimedViewInteraction(ViewInteraction wrappedViewInteraction) {
            this.wrappedViewInteraction = wrappedViewInteraction;
        }

        /**
         * @see ViewInteraction#perform(ViewAction...)
         */
        public TimedViewInteraction perform(final ViewAction... viewActions) {
            wrappedViewInteraction.perform(viewActions);
            return this;
        }

        /**
         * {@link ViewInteraction#perform(ViewAction...)} with a timeout of {@link #DEFAULT_TIMEOUT_IN_MILLIS}.
         * @see ViewInteraction#perform(ViewAction...)
         */
        public TimedViewInteraction performWithTimeout(final ViewAction... viewActions) {
            return performWithTimeout(DEFAULT_TIMEOUT_IN_MILLIS, viewActions);
        }

        /**
         * {@link ViewInteraction#perform(ViewAction...)} with a timeout.
         * @see ViewInteraction#perform(ViewAction...)
         */
        public TimedViewInteraction performWithTimeout(long timeoutInMillis, final ViewAction... viewActions) {
            final long startTime = System.currentTimeMillis();
            final long endTime = startTime + timeoutInMillis;

            do {
                try {
                    return perform(viewActions);
                } catch (RuntimeException ex) {
                    //ignore
                }

                SystemClock.sleep(SLEEP_IN_A_LOOP_TIME);
            }
            while (System.currentTimeMillis() < endTime);

            // timeout happens
            throw new PerformException.Builder()
                    .withCause(new TimeoutException("Timeout occurred when trying to perform view actions: " + viewActions))
                    .build();
        }

        /**
         * @see ViewInteraction#withFailureHandler(FailureHandler)
         */
        public TimedViewInteraction withFailureHandler(FailureHandler failureHandler) {
            wrappedViewInteraction.withFailureHandler(failureHandler);
            return this;
        }

        /**
         * @see ViewInteraction#inRoot(Matcher)
         */
        public TimedViewInteraction inRoot(Matcher<Root> rootMatcher) {
            wrappedViewInteraction.inRoot(rootMatcher);
            return this;
        }

        /**
         * @see ViewInteraction#check(ViewAssertion)
         */
        public TimedViewInteraction check(final ViewAssertion viewAssert) {
            wrappedViewInteraction.check(viewAssert);
            return this;
        }

        /**
         * {@link ViewInteraction#check(ViewAssertion)} with a timeout of {@link #DEFAULT_TIMEOUT_IN_MILLIS}.
         * @see ViewInteraction#check(ViewAssertion)
         */
        public TimedViewInteraction checkWithTimeout(final ViewAssertion viewAssert) {
            return checkWithTimeout(DEFAULT_TIMEOUT_IN_MILLIS, viewAssert);
        }

        /**
         * {@link ViewInteraction#check(ViewAssertion)} with a timeout.
         * @see ViewInteraction#check(ViewAssertion)
         */
        public TimedViewInteraction checkWithTimeout(long timeoutInMillis, final ViewAssertion viewAssert) {
            final long startTime = System.currentTimeMillis();
            final long endTime = startTime + timeoutInMillis;

            do {
                try {
                    return check(viewAssert);
                } catch (RuntimeException ex) {
                    //ignore
                }

                SystemClock.sleep(SLEEP_IN_A_LOOP_TIME);
            }
            while (System.currentTimeMillis() < endTime);

            // timeout happens
            throw new PerformException.Builder()
                    .withCause(new TimeoutException("Timeout occurred when trying to check: " + viewAssert.toString()))
                    .build();
        }
    }
}