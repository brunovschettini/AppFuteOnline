package br.com.futeonline.main;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import br.com.futeonline.R;
import br.com.futeonline.utils.Sessions;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences pref;  // 0 - for private mode

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pref = getApplicationContext().getSharedPreferences("futeonline", 0);
        try {
            if (!pref.getString("access_token", "").isEmpty()) {
                Intent it = new Intent(MainActivity.this, MenuActivity.class);
                startActivity(it);
                return;
            }

        } catch (Exception e) {
            e.getMessage();
        }

        Button loggin = (Button) findViewById(R.id.loggin);
        loggin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(it);
            }
        });

        Button register = (Button) findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });


    }

    public void register() {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(Defaults.getSite() + "/" + "register.xhtml"));
        startActivity(browserIntent);
    }

}
