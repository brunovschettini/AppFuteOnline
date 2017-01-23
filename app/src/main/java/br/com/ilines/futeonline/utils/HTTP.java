package br.com.ilines.futeonline.utils;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.ilines.futeonline.main.Defaults;

import static android.R.attr.data;

/**
 * Created by rtools2 on 13/01/2017.
 */

public class HTTP {


    public void aaaa () {
        String c = Cookies.get("4cc355t0k3nfut0nl1n3");
        HttpClient client = new DefaultHttpClient();

    HttpClient httpclient = new DefaultHttpClient();
    HttpPost httppost = new HttpPost(Defaults.getSite() + "/notify");

    try {
        //add data
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
        nameValuePairs.add(new BasicNameValuePair("access_token", Cookies.get("4cc355t0k3nfut0nl1n3")));
        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
        //execute http post
        HttpResponse response = httpclient.execute(httppost);
        response.toString();

    } catch (ClientProtocolException e) {

    } catch (Exception e) {
        System.out.print("MESSAGE" + e.getMessage());
    }

    }
}
