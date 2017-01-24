package br.com.futeonline.main;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.futeonline.R;
import br.com.futeonline.objects.Notify;
import br.com.futeonline.objects.UserToken;
import br.com.futeonline.utils.Consulta;
import br.com.futeonline.utils.Cookies;
import br.com.futeonline.utils.DialogMessage;
import br.com.futeonline.utils.Progress;
import br.com.futeonline.utils.QueryResult;
import br.com.futeonline.utils.Validator;

import static android.Manifest.permission.READ_CONTACTS;


public class LoginActivity extends AppCompatActivity {

    private WebView wv;
    private EditText login;
    private EditText password;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        wv = (WebView) findViewById(R.id.webView);
        pref = getApplicationContext().getSharedPreferences("futeonline-sessions", 0); // 0 - for private mode
        editor = pref.edit();
        try {
            if (pref.getString("access_key", null) != null && !pref.getString("access_key", null).isEmpty()) {
                Intent it = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(it);
                return;
            }

        } catch (Exception e) {
            e.getMessage();
        }
/*
        db = new SQLiteDatabaseHandler(this);
        // create some players
        Player player1 = new Player(1, "Lebron James", "F", 203);
        Player player2 = new Player(2, "Kevin Durant", "F", 208);
        Player player3 = new Player(3, "Rudy Gobert", "C", 214);
        // add them
        db.addPlayer(player1);
        db.addPlayer(player2);
        db.addPlayer(player3);
        // list all players
        List<Player> players = db.allPlayers();

        if (players != null) {
            String[] itemsNames = new String[players.size()];

            for (int i = 0; i < players.size(); i++) {
                itemsNames[i] = players.get(i).toString();
            }

            // display like string instances
            ListView list = (ListView) findViewById(R.id.list);
            list.setAdapter(new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, android.R.id.text1, itemsNames));

        }
        */

        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 16) {
            // wv.getSettings().setMediaPlaybackRequiresUserGesture(false);
        }
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        // Set up the login form.
        login = (EditText) findViewById(R.id.login);
        password = (EditText) findViewById(R.id.password);

        password.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        Button auth = (Button) findViewById(R.id.auth);
        auth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
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

    public void attemptLogin() {
        if (login == null || login.getText().toString().isEmpty()) {
            DialogMessage.show(this, "LOGIN!");
            return;
        }
        if (!Validator.isEmailValid(login.getText().toString())) {
            DialogMessage.show(this, "LOGIN INVÁLIDO!");
            return;
        }
        if (password == null || password.getText().toString().isEmpty()) {
            DialogMessage.show(this, "SENHA!");
            return;
        }
        if (!Validator.isPasswordValid(password.getText().toString())) {
            DialogMessage.show(this, "SENHA INVÁLIDA!");
            return;
        }
        Map map2 = new HashMap();
        try {
            String result = Consulta.result(Consulta.makeRequestNonToken(Defaults.getSite() + "/ws/auth/in/" + login.getText().toString() + "/" + password.getText().toString()));
            UserToken userToken = null;
            QueryResult queryResult = null;
            try {
                queryResult = new Gson().fromJson(result, QueryResult.class);
            } catch (Exception e) {

            }
            if (queryResult == null) {
                try {
                    userToken = new Gson().fromJson(result, UserToken.class);
                    if (userToken == null) {
                        DialogMessage.show(this, "500");
                        return;
                    }
                } catch (Exception e) {

                }
            } else {
                editor.putString("access_key", "1111");
                editor.putInt("user_id", Integer.parseInt("" + userToken.getUsers().getId()));
                editor.commit();
                DialogMessage.show(this, queryResult.getObject().toString());
            }

            Intent it = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(it);
            // Cookies.get()
        } catch (Exception e) {

        }
    }

    public void register() {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(Defaults.getSite() + "/" + "register.xhtml"));
        startActivity(browserIntent);
    }

}


// sessoes
// http://www.androidhive.info/2012/08/android-session-management-using-shared-preferences/
