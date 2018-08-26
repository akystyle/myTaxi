package TestHelper;

import android.support.test.espresso.IdlingResource;

public class CustomIdlingResource implements IdlingResource {

    private volatile  ResourceCallback callback;
    private final String mResourceName;

    public CustomIdlingResource(String ReourceName){
        mResourceName = ReourceName;
    }

    @Override
    public String getName() {
        return mResourceName;
    }

    @Override
    public boolean isIdleNow() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback callback) {
        this.callback = callback;
    }
}
