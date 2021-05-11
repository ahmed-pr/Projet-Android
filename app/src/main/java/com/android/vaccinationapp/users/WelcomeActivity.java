package com.android.vaccinationapp.users;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.vaccinationapp.R;

public class WelcomeActivity extends AppCompatActivity {

    private final static int SECOND_CALL_ID = 1234;
    private Button btnLogin;
    private Button btnRegister;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);


        btnLogin = findViewById(R.id.button);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        WelcomeActivity.this,
                        LoginActivity.class
                );
                startActivityForResult(intent, SECOND_CALL_ID);

            }
        });

        btnRegister = findViewById(R.id.button2);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        WelcomeActivity.this,
                        RegisterActivity.class
                );
                startActivityForResult(intent, SECOND_CALL_ID);

            }
        });
    }
}