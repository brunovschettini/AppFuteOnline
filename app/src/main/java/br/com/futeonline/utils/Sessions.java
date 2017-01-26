package br.com.futeonline.utils;


import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

// http://www.androidhive.info/2012/08/android-session-management-using-shared-preferences/

public class Sessions extends AppCompatActivity {

    private SharedPreferences pref;  // 0 - for private mode


    public Sessions() {
        try {
            pref = getApplicationContext().getSharedPreferences("futeonline", 0);
        } catch (Exception e) {

        }
    }

    public Sessions(SharedPreferences pref) {
        try {
            pref = getApplicationContext().getSharedPreferences("futeonline", 0);
        } catch (Exception e) {
            this.pref = pref;
        }
    }


    public void put(String key, String value) {
        try {
            getPref().edit().putString(key, value);
            getPref().edit().commit();
        } catch (Exception e) {

        }
    }

    public void put(String key, Integer value) {
        try {
            getPref().edit().putInt(key, value);
            getPref().edit().commit();
        } catch (Exception e) {

        }
    }


    public void put(String key, Long value) {
        try {
            getPref().edit().putLong(key, value);
            getPref().edit().commit();
        } catch (Exception e) {

        }
    }

    public void put(String key, Float value) {
        try {
            getPref().edit().putFloat(key, value);
            getPref().edit().commit();
        } catch (Exception e) {

        }
    }

    public void put(String key, Boolean value) {
        try {
            getPref().edit().putBoolean(key, value);
            getPref().edit().commit();
        } catch (Exception e) {

        }
    }

    // GET

    public String getString(String session_name) {
        try {
            return getPref().getString(session_name, "");
        } catch (Exception e) {

        }
        return null;
    }

    public Integer getInteger(String session_name) {
        try {
            return getPref().getInt(session_name, new Integer(0));
        } catch (Exception e) {

        }
        return null;
    }

    public Long getLong(String session_name) {
        try {
            return getPref().getLong(session_name, new Long(0));
        } catch (Exception e) {

        }
        return null;
    }

    public Boolean getBoolean(String session_name) {
        try {
            return getPref().getBoolean(session_name, false);
        } catch (Exception e) {

        }
        return null;
    }

    public Float getFloat(String session_name) {
        try {
            return getPref().getFloat(session_name, 0.0f);
        } catch (Exception e) {

        }
        return null;
    }

    // REMOVE

    public void remove(String session_name) {
        try {
            getPref().edit().remove(session_name);
            getPref().edit().commit();
        } catch (Exception e) {

        }
    }

    public void destroy() {
        try {
            getPref().edit().clear();
            getPref().edit().commit();
        } catch (Exception e) {

        }
    }

    public SharedPreferences getPref() {
        return pref;
    }

    public void setPref(SharedPreferences pref) {
        this.pref = pref;
    }
}
