package br.com.futeonline.main;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.futeonline.R;
import br.com.futeonline.objects.Notify;
import br.com.futeonline.utils.Consulta;
import br.com.futeonline.utils.QueryResult;

public class WebSiteActivity extends AppCompatActivity {

    private SharedPreferences pref;  // 0 - for private mode
    Button btn_notify;
    WebView wv;
    Thread threadPush;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        pref = getApplicationContext().getSharedPreferences("futeonline", 0);
        try {
            if (pref.getString("access_token", "").isEmpty()) {
                Intent it = new Intent(WebSiteActivity.this, MainActivity.class);
                startActivity(it);
                return;
            }

        } catch (Exception e) {
            e.getMessage();
        }

        wv = (WebView) findViewById(R.id.webView);
        WebSettings ws = wv.getSettings();
        ws.setJavaScriptEnabled(true);
        ws.setSupportZoom(false);
        //ws.setJavaScriptCanOpenWindowsAutomatically(true);
        // ws.setPluginState(WebSettings.PluginState.ON);
        wv.setWebViewClient(new WebViewClient());
        // wv.setWebChromeClient(new WebChromeClient() {});
        // wv.getSettings().setAllowFileAccess(true);
        wv.setSoundEffectsEnabled(true);
        initComponents();

        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 16) {
            // wv.getSettings().setMediaPlaybackRequiresUserGesture(false);
        }
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        btn_notify = (Button) findViewById(R.id.btn_notify);

        btn_notify.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent it = new Intent(WebSiteActivity.this, NotificationView.class);
                startActivity(it);
            }
        });

        /**
         String path = getFilesDir().toString();
         File file = new File(path + "/defaults.json");
         String url = "";
         String mac = "";
         String client = "";
         String tipo = "";
         String catraca = "";
         Boolean test = false;
         String urlString = "";

         if(file.exists()) {
         Defaults defaults = new Defaults();
         defaults.loadJson(getFilesDir().toString());
         String json = JsonUtils.read(getFilesDir().toString() + "/defaults.json");
         try {
         if(!defaults.getUrl().isEmpty()) {
         urlString = "http://" + defaults.getUrl() + "/monitorCatraca/index.xhtml?";
         }
         if(!defaults.getClient().isEmpty()) {
         urlString += "cliente=" + defaults.getClient();
         }
         if(!defaults.getMac().isEmpty()) {
         urlString += "&mac=" + defaults.getMac();
         }
         if(!defaults.getType().isEmpty()) {
         urlString += "&tipo=" + defaults.getType();
         }
         if(defaults.getTest()) {
         urlString += "&test=" + defaults.getTest();
         }
         if(!defaults.getCatraca().isEmpty()) {
         urlString += "&catraca=" + defaults.getCatraca();
         }
         } catch (Exception e) {

         }
         }
         */
        // "http://sinecol.ddns.net:7070/Sindical/ws/senha.jsf?client=ComercioLimeira&mac=94-0C-6D-86-94-34&tipo=SALA&test=true"
        // http://sindical.rtools.com.br:8080/Sindical/ws/senha.jsf?client=Sindical&mac=00-11-D8-3D-45-D0&tipo=SALA


        try {
            wv.loadUrl(Defaults.getSite());
        } catch (Exception e) {

        }


        threadPush = new Thread(n);
        threadPush.start();


        //cookieManager.setAcceptCookie(true);


        // Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://sindical.rtools.com.br:8080/Sindical/ws/senha.jsf?cliente=Sindical&mac=00-11-D8-3D-45-D0&tipo=SALA"));
        // startActivity(browserIntent);
    }

    public void initComponents() {
        /*btnConfig = (Button) findViewById(R.id.btn_config);

        btnConfig.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this, Config.class);
                startActivity(it);
            }
        });

        Object syncToken = new Object();
        threadPush = new Thread(push);
        threadPush.start();*/
    }

    Runnable push = new Runnable() {
        @Override
        public void run() {

        }
    };

    @Override
    public void onBackPressed() {

    }


    Runnable n = new Runnable() {
        @Override
        public void run() {
            notifies();
        }
    };

    public void notifies() {
        Boolean c = true;
        while (true == c) {
            try {

                Map map2 = new HashMap();
                try {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("access_token", pref.getString("access_token", ""));
                    String result = Consulta.result(Consulta.makeRequest(Defaults.getKeyToken(), Defaults.getSite() + "/ws/notify/my", jsonObject));
                    Gson gson = new Gson();
                    QueryResult notifyResponse = (QueryResult) gson.fromJson(result, QueryResult.class);
                    if (notifyResponse.getStatus() == 1) {
                        result = "" + notifyResponse.getResult();
                        if (notifyResponse.getResult() > 0) {
                            result = Consulta.result(Consulta.makeRequest(Defaults.getKeyToken(), Defaults.getSite() + "/ws/notify/show/", jsonObject));
                            c = true;
                            List<Notify> listNotify = gson.fromJson(result, new TypeToken<List<Notify>>() {
                            }.getType());
                            notifies(this.wv, listNotify);
                        }
                    }
                } catch (Exception e) {

                }
                Thread.sleep(
                        5 *   // minutes to sleep
                                60 *   // seconds to a minute
                                1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }


    }

    public void notifies(View view, Object list) {
        notifies(view, list);
    }

    public void notifies(View view, List<Notify> list) {

        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        PendingIntent p = PendingIntent.getActivity(this, 0, new Intent(this, NotificationView.class), 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setTicker("Ticker Texto");
        builder.setContentTitle("Título");
        //builder.setContentText("Descrição");
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
        builder.setContentIntent(p);

        NotificationCompat.InboxStyle style = new NotificationCompat.InboxStyle();
        for (int i = 0; i < list.size(); i++) {
            style.addLine(list.get(i).getDescription());
            if (i == 5) {
                break;
            }
        }
        builder.setStyle(style);

        Notification n = builder.build();
        n.vibrate = new long[]{150, 300, 150, 600};
        n.flags = Notification.FLAG_AUTO_CANCEL;
        nm.notify(R.mipmap.ic_launcher, n);
        try {
            Uri som = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone toque = RingtoneManager.getRingtone(this, som);
            toque.play();
        } catch (Exception e) {
        }
    }


}
