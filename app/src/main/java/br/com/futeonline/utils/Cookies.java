package br.com.futeonline.utils;

import android.webkit.CookieManager;

import br.com.futeonline.main.Defaults;

public class Cookies {

    public static String get(String cookieName){
        String cookieValue = null;
        try {
        CookieManager cookieManager = CookieManager.getInstance();
        String cookies = cookieManager.getCookie(Defaults.getSite());
        String[] temp=cookies.split(";");
        for (String ar1 : temp ){
            if(ar1.contains(cookieName)){
                String[] temp1=ar1.split("=");
                cookieValue = temp1[1];
            }
        }

        } catch (Exception e) {
            cookieValue = "";
        }
        return cookieValue;
    }

    public static String put(String cookieName, String cookieValue){
        try {
            CookieManager cookieManager = CookieManager.getInstance();
            String cookies = cookieManager.getCookie(Defaults.getSite());
            String[] temp=cookies.split(";");
            for (String ar1 : temp ){
                if(ar1.contains(cookieName)){
                    String[] temp1=ar1.split("=");
                    cookieValue = temp1[1];
                }
            }

        } catch (Exception e) {
            cookieValue = "";
        }
        return cookieValue;
    }
}
