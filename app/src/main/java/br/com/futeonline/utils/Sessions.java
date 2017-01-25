package br.com.futeonline.utils;


import android.app.Activity;
import android.content.SharedPreferences;
import android.support.annotation.BoolRes;

// http://www.androidhive.info/2012/08/android-session-management-using-shared-preferences/

public class Sessions extends Activity {

    private SharedPreferences pref = getApplicationContext().getSharedPreferences("futeonline", 0);  // 0 - for private mode


    // PUT

    public void put(String key, String value) {
        try {
            pref.edit().putString(key, value);
            pref.edit().commit();
        } catch (Exception e) {

        }
    }

    public void put(String key, Integer value) {
        try {
            pref.edit().putInt(key, value);
            pref.edit().commit();
        } catch (Exception e) {

        }
    }


    public void put(String key, Long value) {
        try {
            pref.edit().putLong(key, value);
            pref.edit().commit();
        } catch (Exception e) {

        }
    }

    public void put(String key, Float value) {
        try {
            pref.edit().putFloat(key, value);
            pref.edit().commit();
        } catch (Exception e) {

        }
    }

    public void put(String key, Boolean value) {
        try {
            pref.edit().putBoolean(key, value);
            pref.edit().commit();
        } catch (Exception e) {

        }
    }

    // GET

    public String getString(String session_name) {
        try {
            return pref.getString(session_name, "");
        } catch (Exception e) {

        }
        return null;
    }

    public Integer getInteger(String session_name) {
        try {
            return pref.getInt(session_name, new Integer(0));
        } catch (Exception e) {

        }
        return null;
    }

    public Long getLong(String session_name) {
        try {
            return pref.getLong(session_name, new Long(0));
        } catch (Exception e) {

        }
        return null;
    }

    public Boolean getBoolean(String session_name) {
        try {
            return pref.getBoolean(session_name, false);
        } catch (Exception e) {

        }
        return null;
    }

    public Float getFloat(String session_name) {
        try {
            return pref.getFloat(session_name, 0.0f);
        } catch (Exception e) {

        }
        return null;
    }

    // REMOVE

    public void remove(String session_name) {
        try {
            pref.edit().remove(session_name);
            pref.edit().commit();
        } catch (Exception e) {

        }
    }

    public void destroy() {
        try {
            pref.edit().clear();
            pref.edit().commit();
        } catch (Exception e) {

        }
    }

}
