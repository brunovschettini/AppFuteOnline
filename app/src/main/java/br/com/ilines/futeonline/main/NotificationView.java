package br.com.ilines.futeonline.main;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
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

import br.com.ilines.futeonline.futeonline.R;
import br.com.ilines.futeonline.objects.Notify;
import br.com.ilines.futeonline.utils.Consulta;
import br.com.ilines.futeonline.utils.Cookies;

public class NotificationView extends ActionBarActivity {

    List<String> opcoes;
    List<Notify> notifyList;
    Button btn_back;
    ListView list_notifies;
    ArrayAdapter<String> adaptador;
    ArrayAdapter<Notify> adaptador2;
    WebView wv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification);
        initComponents();
    }


    public void initComponents() {
        btn_back = (Button) findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent it = new Intent(NotificationView.this, MainActivity.class);
                startActivity(it);
            }
        });
        opcoes = new ArrayList<String>();
        notifyList = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("access_token", "903e346610a64c66d12af4f35bb98166");
            String result = "";
            Gson gson = new Gson();
            result = Consulta.result(Consulta.makeRequest(Defaults.getSite() + "/ws/notify/show/", jsonObject));
            if (result != null && !result.isEmpty()) {
                List<Notify> list = gson.fromJson(result, new TypeToken<List<Notify>>() {
                }.getType());
                if (!list.isEmpty()) {
                    notifyList = list;
                }
                for (int i = 0; i < list.size(); i++) {
                    opcoes.add(list.get(i).getViewDate() + " - " + list.get(i).getDescription() + " - Enviada por " + list.get(i).getUsersSent().getPeople().getName());
                }

            }
        } catch (Exception e) {
            opcoes.add("NENHUMA NOTIFICAÇÃO");
            notifyList.add(new Notify(null, null, null, null, null, null, null, null, null, null));
        }
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


    private void exibirSobre() {
        // Intent it = new Intent(NotificationView.this, actSobre.class);
        // startActivity(it);
    }

    private void fazerLigacao() {
        //   Uri uri = Uri.parse("tel:0123456789012");
        //   Intent itNavegar = new Intent(Intent.ACTION_DIAL,uri);
        //   startActivity(itNavegar);
    }

    private void navegarInternet() {
        //   Uri uri = Uri.parse("http://www.devmedia.com.br");
        //   Intent itNavegar = new Intent(Intent.ACTION_VIEW,uri);
        //   startActivity(itNavegar);
    }
}