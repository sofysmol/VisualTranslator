package com.example.sofysmo.visualtranslator.Activity.Utils;

import android.content.Context;

/**
 * Created by sofysmo on 24.06.16.
 */
public class PreferencesManager {
    private static PreferencesManager preferencesManager;
    private TinyDB tinyDB;
    private Context context;

    public static PreferencesManager getInstance() {
        if (preferencesManager == null) {
            preferencesManager = new PreferencesManager();
        }
        return preferencesManager;
    }
    public void init(Context appContext) {
        tinyDB = new TinyDB(appContext);
        context=appContext;
    }

}
