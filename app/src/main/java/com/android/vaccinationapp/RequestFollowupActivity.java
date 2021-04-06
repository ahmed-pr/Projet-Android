package com.android.vaccinationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class RequestFollowupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_followup);

        TextView certif = findViewById(R.id.textView11);
        certif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RequestFollowupActivity.this, DownloadCertificateActivity.class));
            }
        });

        TextView req = findViewById(R.id.textView13);
        req.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RequestFollowupActivity.this, RequestFormActivity.class));
            }
        });
    }
}