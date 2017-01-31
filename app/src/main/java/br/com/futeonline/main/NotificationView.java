package br.com.futeonline.main;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.com.futeonline.objects.Notify;
import br.com.futeonline.utils.Consulta;
import br.com.futeonline.R;
import br.com.futeonline.utils.Sessions;

public class NotificationView  extends AppCompatActivity {

    List<String> opcoes;
    List<Notify> notifyList;
    Button btn_back;
    ListView list_notifies;
    ArrayAdapter<String> adaptador;
    ArrayAdapter<Notify> adaptador2;
    WebView wv;
    Sessions sessions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        initComponents();
    }

    // https://developer.android.com/reference/android/app/Fragment.html
    public void initComponents() {
        Boolean empty = true;
        opcoes = new ArrayList<String>();
        notifyList = new ArrayList<>();
        sessions = new Sessions(this);
        try {
            JSONObject jsonObject = new JSONObject();
            try {
                if (sessions.getString("access_token").isEmpty()) {
                    Intent it = new Intent(NotificationView.this, MainActivity.class);
                    startActivity(it);
                    return;
                }

            } catch (Exception e) {
                e.getMessage();
            }
            jsonObject.put("access_token", sessions.getString("access_token"));
            String result = "";
            Gson gson = new Gson();
            result = Consulta.result(Consulta.makeRequest(sessions.getString("access_token"), Defaults.getSite() + "/ws/notify/show", null));
            if (result != null && !result.isEmpty()) {
                List<Notify> list = gson.fromJson(result, new TypeToken<List<Notify>>() {
                }.getType());
                if (!list.isEmpty()) {
                    notifyList = list;
                }
                for (int i = 0; i < list.size(); i++) {
                    opcoes.add(list.get(i).getViewDate() + " - " + list.get(i).getDescription() + " - Enviada por " + list.get(i).getUsersSent().getPeople().getName());
                }
                if(opcoes.isEmpty()) {
                    empty = true;
                }

            }
        } catch (Exception e) {
            empty = true;
            opcoes.add("NENHUMA NOTIFICAÇÃO");
            notifyList.add(new Notify(null, null, null, null, null, null, null, null, null, null));
        }
        if(!empty) {
            try {
                // adaptador = new ArrayAdapter<Notify>(NotificationView.this, android.R.layout.simple_list_item_1, notifyList);
                adaptador2 = new ArrayAdapter<Notify>(NotificationView.this, android.R.layout.simple_list_item_1, notifyList);
                list_notifies = (ListView) findViewById(R.id.list_notifies);
                list_notifies.setAdapter(adaptador2);
                list_notifies.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        for(int i = 0; i < notifyList.size(); i++) {
                            if (notifyList.get(i).getLink() != null && !notifyList.get(i).getLink().isEmpty()) {
                                wv = (WebView) findViewById(R.id.webView);
                                wv.setWebViewClient(new WebViewClient());
                                try {
                                    wv.loadUrl(Defaults.getSite() + "/" + notifyList.get(i).getLink());
                                } catch (Exception e) {
                                    e.getMessage();
                                }
                                return;
                            } else {
                                return;
                            }
                        }
                    }
                });
            } catch (Exception e) {
                e.getMessage();
            }
        }

    }

}