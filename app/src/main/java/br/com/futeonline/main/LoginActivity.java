package br.com.futeonline.main;

import android.Manifest;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import br.com.futeonline.R;
import br.com.futeonline.objects.UserToken;
import br.com.futeonline.utils.Consulta;
import br.com.futeonline.utils.DialogMessage;
import br.com.futeonline.utils.Progress;
import br.com.futeonline.utils.QueryResult;
import br.com.futeonline.utils.Sessions;
import br.com.futeonline.utils.Validator;

public class LoginActivity extends AppCompatActivity {

    private WebView wv;
    private EditText login;
    private EditText password;
    private SharedPreferences pref;  // 0 - for private mode
    // private Progress progress;
    private View mLoginFormView;
    private Sessions sessions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sessions = new Sessions(this);
        setContentView(R.layout.activity_login);
        View v = this.findViewById(android.R.id.content).getRootView();
        wv = (WebView) findViewById(R.id.webView);
        View view = findViewById(R.id.login_form);
        mLoginFormView = findViewById(R.id.login_form);
        pref = getApplicationContext().getSharedPreferences("futeonline", 0);
        try {
            if (!pref.getString("access_token", "").isEmpty()) {
                Intent it = new Intent(LoginActivity.this, MenuActivity.class);
                startActivity(it);
                return;
            }

        } catch (Exception e) {
            e.getMessage();
        }
        // mProgressView = findViewById(R.id.login_progress);
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
        if(login.getText().toString().isEmpty()) {
           login.setText(getMailGoogleAccount(), TextView.BufferType.EDITABLE);
        }
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
    }

    public void attemptLogin() {
        View focusView = null;
        View v = this.findViewById(android.R.id.content).getRootView();


        if (login == null || login.getText().toString().isEmpty()) {
            login.setError(getString(R.string.error_invalid_email));
            focusView = login;
            focusView.requestFocus();
            return;
        }
        if (!Validator.isEmailValid(login.getText().toString())) {
            login.setError(getString(R.string.error_invalid_email));
            focusView = login;
            focusView.requestFocus();
            return;
        }
        if (password == null || password.getText().toString().isEmpty()) {
            password.setError(getString(R.string.error_invalid_password));
            focusView = password;
            focusView.requestFocus();
            return;
        }
        if (!Validator.isPasswordValid(password.getText().toString())) {
            password.setError(getString(R.string.error_invalid_password));
            focusView = password;
            focusView.requestFocus();
            return;
        }
        Map map2 = new HashMap();
        try {
            final ProgressDialog ringProgressDialog = ProgressDialog.show(this, "Please wait ...", "...", true);
            String result = Consulta.result(Consulta.makeRequestNonToken(Defaults.getSite() + "/ws/auth/in/" + login.getText().toString() + "/" + password.getText().toString()));
            ringProgressDialog.dismiss();

            // showProgress(false);
            UserToken userToken = null;
            QueryResult queryResult = null;
            Boolean convert = false;
            try {
                queryResult = new Gson().fromJson(result, QueryResult.class);
                convert = true;
            } catch (Exception e) {

            }
            if (queryResult == null || queryResult.getStatus() == null) {
                try {
                    userToken = new Gson().fromJson(result, UserToken.class);
                    if (userToken == null) {
                        DialogMessage.show(this, "500");
                        return;
                    }
                    try {
                        SharedPreferences.Editor editor = pref.edit();
                        // editor.putString("access_token", userToken.getAccessToken());
                        // editor.commit();
                        sessions.put("access_token", userToken.getAccessToken());
                        String s = pref.getString("access_token", null);
                        Intent it = new Intent(LoginActivity.this, MenuActivity.class);
                        startActivity(it);
                    } catch (Exception e) {
                        DialogMessage.show(this, e.getMessage());
                    }

                } catch (Exception e) {

                }
            } else {
                DialogMessage.show(this, queryResult.getObject().toString());
            }
            // Cookies.get()
        } catch (Exception e) {

        }
    }

    public void register() {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(Defaults.getSite() + "/" + "register.xhtml"));
        startActivity(browserIntent);
    }

    public Boolean isLogged() {
        try {
            if (pref.getString("access_token", null) != null && !pref.getString("access_token", null).isEmpty()) {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    private String getMailGoogleAccount() {
        try {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.GET_ACCOUNTS) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                AccountManager accountManager = AccountManager.get(this);
                Account[] accounts = accountManager.getAccountsByType("com.google");
                if (accounts.length > 0) {
                    Account account = accounts[0];
                    return account.name;
                }
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

}


// sessoes
// http://www.androidhive.info/2012/08/android-session-management-using-shared-preferences/
