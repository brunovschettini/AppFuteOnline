package br.com.futeonline.utils;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Future;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.params.ConnRouteParams;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.TargetApi;
import android.os.AsyncTask;
import android.os.Build;

import br.com.futeonline.main.Defaults;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

public class Consulta {


    public static String get(String path) {
        return new Consulta().invisibledGet(path);
    }

    public static String post(String path, List<NameValuePair> parans) {
        return new Consulta().invisibledPost(path, parans);
    }


    protected String invisibledGet(String path) {

        String URL = Defaults.getSite() + path;
        String linha = "";
        String result = "";

        if (!path.isEmpty())
            // faço qualquer coisa com os parâmetros

            try {

                HttpClient client = new DefaultHttpClient();
                HttpGet requisicao = new HttpGet();
                requisicao.setHeader("Content-Type",
                        "text/plain; charset=utf-8");
                requisicao.setURI(new URI(URL));
                HttpResponse resposta = client.execute(requisicao);
                BufferedReader br = new BufferedReader(new InputStreamReader(
                        resposta.getEntity().getContent()));
                StringBuffer sb = new StringBuffer("");

                while ((linha = br.readLine()) != null) {
                    sb.append(linha);
                }

                br.close();

                result = sb.toString();

            } catch (Exception e) {
                result = "";
            }

        return result;
    }

    public static HttpResponse makeRequestNonToken(String path) throws Exception {
        return makeRequest(null, path, null);
    }


    public static HttpResponse makeRequest(String path) throws Exception {
        String key_token = Cookies.get("4cc355t0k3nfut0nl1n3");
        return makeRequest(key_token, path, null);
    }


    public static HttpResponse makeRequest(String path, JSONObject jsonObject) throws Exception {
        String key_token = Cookies.get("4cc355t0k3nfut0nl1n3");
        return makeRequest(key_token, path, jsonObject);
    }


    public static HttpResponse makeRequest(String key_token, String path, JSONObject jsonObject) throws Exception {
        try {
            final HttpParams httpParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpParams, 30000);
            //instantiates httpclient to make request
            HttpClient httpclient = new DefaultHttpClient(httpParams);

            //url with the post data
            HttpPost httpost = new HttpPost(path);

            //convert parameters into JSON object
            // JSONObject holder = getJsonObjectFromMap(params);

            //passes the results to a string builder/entity

            if (jsonObject != null) {
                StringEntity se = new StringEntity(jsonObject.toString());
                //sets the post request as the resulting string
                httpost.setEntity(se);

            }
            HttpParams params = httpclient.getParams();
            HttpConnectionParams.setConnectionTimeout(params, 10000);
            HttpConnectionParams.setSoTimeout(params, 10000);
            //sets a request header so the page receving the request
            //will know what to do with it
            httpost.setHeader("Accept", "application/json");
            httpost.setHeader("Content-type", "application/json");
            if (key_token != null) {
                httpost.setHeader("X-FuteOnline-Key-Token", key_token);
            }

            return httpclient.execute(httpost);


        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }


    @TargetApi(Build.VERSION_CODES.KITKAT)
    protected String invisibledPost(String path, List<NameValuePair> parans) {
        String parans_string = "";
        for (int i = 0; i < parans.size(); i++) {
            if (i == 0) {
                parans_string += "?" + parans.get(i).getValue();
            } else {
                parans_string += "?" + parans.get(i).getValue();
            }
        }
        String URL = Defaults.getSite() + path + parans_string;
        /*try {
            URL url = new URL(URL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2");
            con.setUseCaches(true);
            con.setRequestMethod("POST");
            try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                String str = in.readLine();
                return str;
            } catch (Exception e) {

            }

        } catch (Exception e) {

        }
        */
        //List<NameValuePair> nameValuePairs = new ArrayList(7);

        //nameValuePairs.add(new BasicNameValuePair("client", GenericaSessao.getString("sessaoCliente")));
        //nameValuePairs.add(new BasicNameValuePair("document", cnpj));
        // Encapsulando


        String linha = "";
        String result = "";
        String htmlx = "";
        if (!path.isEmpty())
            // faço qualquer coisa com os parâmetros


            try {
                HttpClient client = new DefaultHttpClient();
                HttpPost requisicao = new HttpPost();
                UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(parans, "UTF8");
                // A adição dos parâmetros
                requisicao.setEntity(urlEncodedFormEntity);

                requisicao.setHeader("Content-Type",
                        "text/plain; charset=utf-8");
                HttpResponse resposta = client.execute(requisicao);
                BufferedReader br = new BufferedReader(new InputStreamReader(
                        resposta.getEntity().getContent()));
                StringBuffer sb = new StringBuffer("");

                while ((linha = br.readLine()) != null) {
                    sb.append(linha);
                }

                br.close();

                result = sb.toString();

            } catch (Exception e) {
                result = "";
            }

        return result;
    }


    private static JSONObject getJsonObjectFromMap(Map params) throws JSONException {

        //all the passed parameters from the post request
        //iterator used to loop through all the parameters
        //passed in the post request
        Iterator iter = params.entrySet().iterator();

        //Stores JSON
        JSONObject holder = new JSONObject();

        //using the earlier example your first entry would get email
        //and the inner while would get the value which would be 'foo@bar.com'
        //{ fan: { email : 'foo@bar.com' } }

        //While there is another entry
        while (iter.hasNext()) {
            //gets an entry in the params
            Map.Entry pairs = (Map.Entry) iter.next();

            //creates a key for Map
            String key = (String) pairs.getKey();

            //Create a new map
            Map m = (Map) pairs.getValue();

            //object for storing Json
            JSONObject data = new JSONObject();

            //gets the value
            Iterator iter2 = m.entrySet().iterator();
            while (iter2.hasNext()) {
                Map.Entry pairs2 = (Map.Entry) iter2.next();
                data.put((String) pairs2.getKey(), (String) pairs2.getValue());
            }

            //puts email and 'foo@bar.com'  together in map
            holder.put(key, data);
        }
        return holder;
    }

    public static String result(HttpResponse httpResponse) {
        try {
            HttpResponse resposta = httpResponse;
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    resposta.getEntity().getContent()));
            StringBuffer sb = new StringBuffer("");
            String linha = "";
            while ((linha = br.readLine()) != null) {
                sb.append(linha);
            }

            br.close();

            return sb.toString();

        } catch (Exception e) {
            return null;
        }
    }
}