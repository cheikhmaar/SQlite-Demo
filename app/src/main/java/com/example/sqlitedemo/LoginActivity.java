package com.example.sqlitedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText usernameTxt, pwdTxt;
    Button loginBtn;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameTxt = findViewById(R.id.username1_txt);
        pwdTxt = findViewById(R.id.pwd1_txt);
        loginBtn = findViewById(R.id.login_btn);
        DB = new DBHelper(this);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = usernameTxt.getText().toString();
                String pass = pwdTxt.getText().toString();

                if (TextUtils.isEmpty(user) || TextUtils.isEmpty(pass)){
                    Toast.makeText(LoginActivity.this, "Tous les champs sont obligatoires", Toast.LENGTH_LONG).show();
                }else {
                    Boolean checkuserpass =DB.checkusernamepwd(user, pass);
                    if (checkuserpass==true){
                        Toast.makeText(LoginActivity.this, "Connexion reussie", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(), AccueilActivity.class);
                        startActivity(intent);

                    }else {
                        Toast.makeText(LoginActivity.this, "Echec de la connexion", Toast.LENGTH_LONG).show();

                    }
                }

            }
        });
    }
}