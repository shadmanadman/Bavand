package org.bavand;

import android.app.Application;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class BavandApplication extends Application {
    private static BavandApplication INSTANCE;

    public static BavandApplication getInstance() {
        return INSTANCE;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/IRANSANS(FANUM)_LIGHT.TTF")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }
}
