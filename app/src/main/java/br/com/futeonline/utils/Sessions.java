package br.com.futeonline.utils;


import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

// http://www.androidhive.info/2012/08/android-session-management-using-shared-preferences/

public class Sessions {

    private SharedPreferences sharedPreferences;  // 0 - for private mode
    private SharedPreferences.Editor editor;

    public Sessions(Context context) {
        try {
            sharedPreferences = context.getSharedPreferences("futeonline", 0);
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public void put(String key, String value) {
        try {
            editor = sharedPreferences.edit();
            editor.putString(key, value);
            editor.commit();
        } catch (Exception e) {

        }
    }

    public void put(String key, Integer value) {
        try {
            editor = sharedPreferences.edit();
            editor.putInt(key, value);
            editor.commit();
        } catch (Exception e) {

        }
    }

    public void put(String key, Long value) {
        try {
            editor = sharedPreferences.edit();
            editor.putLong(key, value);
            editor.commit();
        } catch (Exception e) {

        }
    }

    public void put(String key, Float value) {
        try {
            editor = sharedPreferences.edit();
            editor.putFloat(key, value);
            editor.commit();
        } catch (Exception e) {

        }
    }

    public void put(String key, Boolean value) {
        try {
            editor = sharedPreferences.edit();
            editor.putBoolean(key, value);
            editor.commit();
        } catch (Exception e) {

        }
    }

    // GET

    public String getString(String session_name) {
        try {
            return sharedPreferences.getString(session_name, "");
        } catch (Exception e) {

        }
        return null;
    }

    public Integer getInteger(String session_name) {
        try {
            return sharedPreferences.getInt(session_name, new Integer(0));
        } catch (Exception e) {

        }
        return null;
    }

    public Long getLong(String session_name) {
        try {
            return sharedPreferences.getLong(session_name, new Long(0));
        } catch (Exception e) {

        }
        return null;
    }

    public Boolean getBoolean(String session_name) {
        try {
            return sharedPreferences.getBoolean(session_name, false);
        } catch (Exception e) {

        }
        return null;
    }

    public Float getFloat(String session_name) {
        try {
            return sharedPreferences.getFloat(session_name, 0.0f);
        } catch (Exception e) {

        }
        return null;
    }

    // REMOVE

    public void remove(String session_name) {
        try {
            editor = sharedPreferences.edit();
            editor.remove(session_name);
            editor.commit();
        } catch (Exception e) {

        }
    }

    public void destroy() {
        try {
            editor = sharedPreferences.edit();
            editor.clear();
            editor.commit();
        } catch (Exception e) {

        }
    }
}
