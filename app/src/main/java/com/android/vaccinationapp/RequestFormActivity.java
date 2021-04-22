package com.android.vaccinationapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.vaccinationapp.datamodel.Request;
import com.android.vaccinationapp.firestore.RequestsFirestoreManager;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RequestFormActivity extends AppCompatActivity {

    private TextView filledform;
    private Button request;

    private RequestsFirestoreManager requestsFirestoreManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        request = findViewById(R.id.btnRequest);
        request.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {

                //String citizen = fAuth.getCurrentUser().getUid();
                String citizen = "3zTDFGjZzAT7ue4aLqUH";

                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                System.out.println(dtf.format(now));
                String request_date = dtf.format(now);
                //String request_date = "21/04/2021";

                String request_state = "";

                Request req = new Request();

                req.setCitizen(citizen);
                req.setRequest_date(request_date);
                req.setRequest_state(request_state);

                requestsFirestoreManager = RequestsFirestoreManager.newInstance();

                requestsFirestoreManager.createDocumentRequest(req);
                Toast.makeText(RequestFormActivity.this, "Demande déposée", Toast.LENGTH_LONG).show();
            }
        });

        filledform = findViewById(R.id.alreadyFilledForm);
        filledform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RequestFormActivity.this, RequestFollowupActivity.class));
            }
        });
    }
}