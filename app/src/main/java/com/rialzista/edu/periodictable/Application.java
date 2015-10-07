package com.rialzista.edu.periodictable;

public class Application extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();
        TypefaceUtil.overrideFont(getApplicationContext(), "SERIF", "fonts/RobotoSlab-Regular.ttf");
    }
}
