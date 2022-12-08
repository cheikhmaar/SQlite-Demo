package com.example.sqlitedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText usernameTxt, pwdTxt, repwdTxt;
    Button signupBtn, signinBtn;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        usernameTxt = findViewById(R.id.username_txt);
        pwdTxt = findViewById(R.id.pwd_txt);
        repwdTxt = findViewById(R.id.repwd_txt);
        signupBtn = findViewById(R.id.signup_btn);
        signinBtn = findViewById(R.id.signin_btn);
        DB = new DBHelper(this);


        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = usernameTxt.getText().toString();
                String pass = pwdTxt.getText().toString();
                String repass = repwdTxt.getText().toString();

                if (TextUtils.isEmpty(user) || TextUtils.isEmpty(pass) || TextUtils.isEmpty(repass)){
                    Toast.makeText(RegisterActivity.this, "Tous les champs sont obligatoires", Toast.LENGTH_LONG).show();
                }else {
                    if (pass.equals(repass)){
                        Boolean checkuser = DB.checkusername(user);
                        if (checkuser==false){
                            Boolean insert = DB.insertData(user,pass);
                            if (insert==true){
                                Toast.makeText(RegisterActivity.this, "Enregistré avec succes", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(getApplicationContext(), AccueilActivity.class);
                                startActivity(intent);

                            }else{
                                Toast.makeText(RegisterActivity.this, "Enregistré echouer", Toast.LENGTH_LONG).show();

                            }
                        }else {
                            Toast.makeText(RegisterActivity.this, "Nom utilisateur existe deja", Toast.LENGTH_LONG).show();

                        }

                    }else {
                        Toast.makeText(RegisterActivity.this, "Le mot de passe ne correspond pas", Toast.LENGTH_LONG).show();

                    }

                }

            }
        });

        signinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);

            }
        });


    }
}