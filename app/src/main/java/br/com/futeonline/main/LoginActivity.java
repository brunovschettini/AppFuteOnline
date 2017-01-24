package br.com.futeonline.main;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
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
import br.com.futeonline.utils.DialogMessage;
import br.com.futeonline.utils.Progress;
import br.com.futeonline.utils.QueryResult;
import br.com.futeonline.utils.Validator;

import static android.Manifest.permission.READ_CONTACTS;


public class LoginActivity extends AppCompatActivity {

    private EditText login;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
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
                return;
            }
        });
        Button register = (Button) findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
                return;
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
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("access_token", "903e346610a64c66d12af4f35bb98166");
            String result = Consulta.result(Consulta.makeRequestNonToken(Defaults.getSite() + "/ws/auth"));
            UserToken userToken = null;
            try {
                userToken = new Gson().fromJson(result, UserToken.class);
            } catch (Exception e) {

            }
            if(userToken == null) {
                return;
            }
            DialogMessage.show(this, "LOGIN RE INVÁLIDA!");
        } catch (Exception e) {

        }
    }

    public void register() {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(Defaults.getSite() + "/" + "register"));
        startActivity(browserIntent);
    }

}

