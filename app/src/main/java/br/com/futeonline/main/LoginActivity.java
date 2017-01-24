package br.com.futeonline.main;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
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

import java.util.List;

import br.com.futeonline.R;
import br.com.futeonline.utils.DialogMessage;
import br.com.futeonline.utils.Validator;

import static android.Manifest.permission.READ_CONTACTS;


public class LoginActivity extends AppCompatActivity {


    private EditText login;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        login = (EditText) findViewById(R.id.login);
        // populateAutoComplete();

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
        auth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });
    }

    private void attemptLogin() {
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
        if (Validator.isPasswordValid(password.getText().toString())) {
            DialogMessage.show(this, "SENHA INVÁLIDA!");
            return;
        }
    }

    public void register() {
        Intent it = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(it);
    }

}

