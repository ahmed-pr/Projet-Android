package com.android.vaccinationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.vaccinationapp.firestore.UsersFirestoreManager;

public class MainActivity extends AppCompatActivity {

    private final static int SECOND_CALL_ID = 1234;
    private Button btnLogin;

    private UsersFirestoreManager usersFirestoreManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usersFirestoreManager = UsersFirestoreManager.newInstance();

        btnLogin = findViewById(R.id.button);
        /*btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usersFirestoreManager.sendUsersBulk();
                Toast.makeText(MainActivity.this, "Bulk users sent", Toast.LENGTH_LONG).show();
            }
        });*/

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        MainActivity.this,
                        LoginActivity.class
                );
                startActivityForResult(intent, SECOND_CALL_ID);

            }
        });
    }
}