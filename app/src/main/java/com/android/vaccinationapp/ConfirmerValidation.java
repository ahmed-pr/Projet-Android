package com.example.vaccination;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConfirmerValidation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmer_validation);

        Intent i = getIntent();
        CitizenRequest c = i.getParcelableExtra("message");

        DatePicker dp = (DatePicker) this.findViewById(R.id.date);

        dp.setMinDate(new Date().getTime());

        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

        try {
            dp.setMaxDate(format.parse("31-12-2022").getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Button confirm = (Button) this.findViewById(R.id.confirm);
        confirm.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                 Log.i("Message", "\nLe day : "+String.valueOf(dp.getDayOfMonth()) );

                 Toast.makeText(ConfirmerValidation.this, "La demande de vaccination a été bien validée.", Toast.LENGTH_LONG).show();
                 Intent intent = new Intent(ConfirmerValidation.this, MainActivity.class);
                 startActivity(intent);


            }
        } ) ;;




        Button annuler = (Button) this.findViewById(R.id.annuler);
        annuler.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        } ) ;;
    }
}