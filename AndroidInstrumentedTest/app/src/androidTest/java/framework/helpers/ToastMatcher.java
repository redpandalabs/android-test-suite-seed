package framework.helpers;

import android.os.IBinder;
import android.support.test.espresso.Root;
import android.view.WindowManager;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;


/**
 * Created by vivek on 13/12/18.
 */

public class ToastMatcher extends TypeSafeMatcher<Root> {

    @Override
    public boolean matchesSafely(Root item) {

        int type = item.getWindowLayoutParams().get().type;

        if((type == WindowManager.LayoutParams.TYPE_TOAST)){

            IBinder windowToken = item.getDecorView().getWindowToken();
            IBinder appToken = item.getDecorView().getApplicationWindowToken();

            if(windowToken == appToken){
                return true;
            }
        }
        return false;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("is toast");
    }

}
