package com.rialzista.edu.periodictable;

import com.google.gson.Gson;

public class Application extends android.app.Application {

    private Gson mGson = null;

    @Override
    public void onCreate() {
        super.onCreate();
        TypefaceUtil.overrideFont(getApplicationContext(), "SERIF", "fonts/RobotoSlab-Regular.ttf");
    }

    public Gson getGson() {
        return mGson == null ? mGson = new Gson() : mGson;
    }
}
