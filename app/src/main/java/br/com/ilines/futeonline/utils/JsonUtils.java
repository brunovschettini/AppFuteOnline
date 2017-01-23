package br.com.ilines.futeonline.utils;

import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Created by rtools2 on 31/08/2016.
 */
public class JsonUtils {

    public static String read(String localFile) {
        String json = null;
        try {
            InputStream is = new FileInputStream(localFile);

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");


        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;

    }

}