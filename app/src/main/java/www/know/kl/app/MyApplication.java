package www.retrofit.com.retrofitrxjavademo.app;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by kui.liu on 2016/10/13.
 */

public class MyApplication extends Application {

    private static MyApplication myApplication;

    public static Application getContext() {
        return myApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        myApplication = this;
    }

}
