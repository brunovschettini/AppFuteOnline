package br.com.futeonline.main;


import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import br.com.futeonline.R;
import br.com.futeonline.utils.Sessions;

public class WebSiteActivity extends AppCompatActivity {

    WebView wv;
    Sessions sessions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_site);
        sessions = new Sessions(this);
        try {
            if (sessions.getString("access_token").isEmpty()) {
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
        wv.setWebViewClient(new WebViewClient());
        wv.setSoundEffectsEnabled(true);

        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 16) {
            // wv.getSettings().setMediaPlaybackRequiresUserGesture(false);
        }
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        try {
            wv.loadUrl(Defaults.getSite() + "/login.xhtml?access_token=" + sessions.getString("access_token"));
        } catch (Exception e) {

        }
    }


    @Override
    public void onBackPressed() {

    }
}
