package com.example.sofysmo.visualtranslator.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

/**
 * Created by sofysmo on 24.06.16.
 */
public class TinyDB {
    private Context mContext;
    private SharedPreferences preferences;
    public TinyDB(Context appContext) {
        mContext = appContext;
        preferences = PreferenceManager.getDefaultSharedPreferences(mContext);
    }
    public int getInt(String key) {
        return preferences.getInt(key, 0);
    }

    public long getLong(String key) {
        return preferences.getLong(key, 0l);
    }

    public String getString(String key) {
        return preferences.getString(key, "");
    }

    public double getDouble(String key) {
        String number = getString(key);

        try {
            return Double.parseDouble(number);

        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public void putInt(String key, int value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public void putLong(String key, long value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putLong(key, value);
        editor.apply();
    }

    public void putDouble(String key, double value) {
        putString(key, String.valueOf(value));
    }

    public void putString(String key, String value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public void putList(String key, ArrayList<String> marray) {
        SharedPreferences.Editor editor = preferences.edit();
        String[] mystringlist = marray.toArray(new String[marray.size()]);
        // the comma like character used below is not a comma it is the SINGLE
        // LOW-9 QUOTATION MARK unicode 201A and unicode 2017 they are used for
        // seprating the items in the list
        editor.putString(key, TextUtils.join("‚‗‚", mystringlist));
        editor.apply();
    }

    public ArrayList<String> getList(String key) {
        // the comma like character used below is not a comma it is the SINGLE
        // LOW-9 QUOTATION MARK unicode 201A and unicode 2017 they are used for
        // seprating the items in the list
        String[] mylist = TextUtils.split(preferences.getString(key, ""), "‚‗‚");
        return new ArrayList<String>(Arrays.asList(mylist));
    }
    public ArrayList<Integer> getListInt(String key) {
        // the comma like character used below is not a comma it is the SINGLE
        // LOW-9 QUOTATION MARK unicode 201A and unicode 2017 they are used for
        // seprating the items in the list
        String[] mylist = TextUtils.split(preferences.getString(key, ""), "‚‗‚");
        ArrayList<String> gottenlist = new ArrayList<String>(Arrays.asList(mylist));
        ArrayList<Integer> gottenlist2 = new ArrayList<Integer>();

        for (int i = 0; i < gottenlist.size(); i++) {
            gottenlist2.add(Integer.parseInt(gottenlist.get(i)));
        }

        return gottenlist2;
    }

    public void putListDouble(String key, ArrayList<Double> marray) {
        SharedPreferences.Editor editor = preferences.edit();
        Double[] mystringlist = marray.toArray(new Double[marray.size()]);
        // the comma like character used below is not a comma it is the SINGLE
        // LOW-9 QUOTATION MARK unicode 201A and unicode 2017 they are used for
        // seprating the items in the list
        editor.putString(key, TextUtils.join("â€šâ€—â€š", mystringlist));
        editor.apply();
    }

    public ArrayList<Double> getListDouble(String key) {
        // the comma like character used below is not a comma it is the SINGLE
        // LOW-9 QUOTATION MARK unicode 201A and unicode 2017 they are used for
        // seprating the items in the list
        String[] mylist = TextUtils.split(preferences.getString(key, ""), "â€šâ€—â€š");
        ArrayList<String> gottenlist = new ArrayList<String>(Arrays.asList(mylist));
        ArrayList<Double> gottenlist2 = new ArrayList<Double>();

        for (int i = 0; i < gottenlist.size(); i++) {
            gottenlist2.add(Double.parseDouble(gottenlist.get(i)));
        }

        return gottenlist2;
    }

    public void putListBoolean(String key, ArrayList<Boolean> marray) {
        ArrayList<String> origList = new ArrayList<String>();

        for (Boolean b : marray) {
            if (b) {
                origList.add("true");
            } else {
                origList.add("false");
            }
        }

        putList(key, origList);
    }

    public ArrayList<Boolean> getListBoolean(String key) {
        ArrayList<String> origList = getList(key);
        ArrayList<Boolean> mBools = new ArrayList<Boolean>();

        for (String b : origList) {
            if (b.equals("true")) {
                mBools.add(true);
            } else {
                mBools.add(false);
            }
        }

        return mBools;
    }

    public void putBoolean(String key, boolean value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public boolean getBoolean(String key) {
        return preferences.getBoolean(key, false);
    }

    public void putFloat(String key, float value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putFloat(key, value);
        editor.apply();
    }

    public float getFloat(String key) {
        return preferences.getFloat(key, 0f);
    }

    public void remove(String key) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(key);
        editor.apply();
    }
    public void clear() {
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
    }

    public Map<String, ?> getAll() {
        return preferences.getAll();
    }

    public void registerOnSharedPreferenceChangeListener(
            SharedPreferences.OnSharedPreferenceChangeListener listener) {

        preferences.registerOnSharedPreferenceChangeListener(listener);
    }

    public void unregisterOnSharedPreferenceChangeListener(
            SharedPreferences.OnSharedPreferenceChangeListener listener) {

        preferences.unregisterOnSharedPreferenceChangeListener(listener);
    }

    public boolean contains(String key) {
        return preferences.contains(key);
    }
}
