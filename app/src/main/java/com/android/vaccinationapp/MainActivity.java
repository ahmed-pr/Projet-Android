package com.example.vaccination;

import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        TableLayout table = (TableLayout) this.findViewById(R.id.table);

        for(int i=0; i<10; i++) {

            CitizenRequest c = new CitizenRequest();

            c.id = 2;
            c.full_name = "Ahmed Fakhir" + String.valueOf(i);
            c.email = "blabla@email";
            c.password = "pass";
            c.phone_number = "0612345678";
            c.cin = "CD5555";
            c.age = 19;
            c.address = "Mon adresse, Rue Abdelhdai Skalli, Quartier Adarissa, Fes";

            c.id_request = 2;
            c.request_date = "20/2/2021";
            c.request_state = "l";

            TableRow r = (TableRow)(LayoutInflater.from(this).inflate(R.layout.temp, null, false));

            ((TextView) r.getChildAt(0)).setText(c.full_name);
            ((Button) r.getChildAt(1)).setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    Intent intent = new Intent(MainActivity.this, Demande.class);
                    intent.putExtra("message", c);
                    startActivity(intent);
                }
            } ) ;;

            table.addView(r);
        }




    }
}