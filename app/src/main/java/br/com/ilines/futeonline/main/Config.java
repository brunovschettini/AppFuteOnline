package br.com.ilines.futeonline.main;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import br.com.ilines.futeonline.futeonline.R;
import br.com.ilines.futeonline.utils.Consulta;
import br.com.ilines.futeonline.utils.Cookies;
import br.com.ilines.futeonline.utils.DialogMessage;
import br.com.ilines.futeonline.utils.HTTP;

public class Config extends AppCompatActivity {

    private String url = "http://futeonline.ddns.net";
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);
        initComponents();
    }

    public void initComponents() {
        btnBack = (Button) findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent it = new Intent(Config.this, MainActivity.class);
                startActivity(it);
            }
        });
        String ck = Cookies.get("4cc355t0k3nfut0nl1n3");
        String resultado = Consulta.get("/ws/notify");
        List<NameValuePair> parans = new ArrayList();
        parans.add(new BasicNameValuePair("access_token", ck));
        resultado = Consulta.post("/ws/notify/my/" + ck, parans);
        if (resultado.isEmpty()) {
            return;
        }
    }

}
