package br.com.futeonline.main;

import android.support.v7.app.AppCompatActivity;

import org.json.JSONObject;

import br.com.futeonline.utils.Cookies;
import br.com.futeonline.utils.JsonUtils;

public class Defaults extends AppCompatActivity {

    private String url;
    private String client;
    private String mac;
    private Boolean test;
    private String type;
    private String catraca;

    public Defaults() {
        this.url = "";
        this.client = "";
        this.mac = "";
        this.type = "";
        this.test = false;
        this.catraca = "0";
    }

    public Defaults(String url, String client, String mac, String type, Boolean test, String catraca) {
        this.url = url;
        this.client = client;
        this.mac = mac;
        this.type = type;
        this.test = test;
        this.catraca = catraca;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCatraca() {
        return catraca;
    }

    public void setCatraca(String catraca) {
        this.catraca = catraca;
    }

    public Boolean getTest() {
        return test;
    }

    public void setTest(Boolean test) {
        this.test = test;
    }

    public void loadJson() {
        loadJson(getFilesDir().toString());
    }

    public void loadJson(String path) {
        try {
            String json = JsonUtils.read(path + "/defaults.json");
            JSONObject obj = new JSONObject(json);
            try {
                url = obj.getString("url");
            } catch (Exception e) {

            }
            try {
                client = obj.getString("client");
            } catch (Exception e) {

            }
            try {
                mac = obj.getString("mac");
            } catch (Exception e) {

            }
            try {
                type = obj.getString("type");
            } catch (Exception e) {

            }
            try {
                test = obj.getBoolean("test");
            } catch (Exception e) {

            }
            try {
                catraca = obj.getString("catraca");
            } catch (Exception e) {

            }
        } catch (Exception e) {
        }
    }

    public static String getSite() {
        // return "http://futeonline.ddns.net";
        return "http://192.168.1.160:8080/FuteOnline";
    }

    public static String getKeyToken() {
        String key_token = Cookies.get("4cc355t0k3nfut0nl1n3");
        if (key_token == null) {
            return "";
        }
        return key_token;
    }
}
