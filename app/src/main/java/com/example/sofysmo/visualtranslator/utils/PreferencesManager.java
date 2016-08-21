package com.example.sofysmo.visualtranslator.utils;

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
    public String getStringSetting(String key)
    {
        return tinyDB.getString(key);
    }
    public int getIntSetting(String key)
    {
        return tinyDB.getInt(key);
    }
    public void saveSetting(String key, String content)
    {
        tinyDB.putString(key,content);
    }
    public void saveSetting(String key, int content)
    {
        tinyDB.putInt(key,content);
    }
}
