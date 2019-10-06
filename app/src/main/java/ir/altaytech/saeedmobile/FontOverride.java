package ir.altaytech.saeedmobile;

import android.app.Application;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class FontOverride extends Application {
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()

                .setDefaultFontPath("fonts/far_traffic.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }
}