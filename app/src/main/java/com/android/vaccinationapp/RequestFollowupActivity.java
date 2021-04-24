package com.android.vaccinationapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.vaccinationapp.beans.Request;
import com.android.vaccinationapp.beans.User;
import com.android.vaccinationapp.beans.Vaccination;
import com.android.vaccinationapp.dao.DAOFactory;
import com.android.vaccinationapp.dao.implementation.RequestDaoImp;
import com.android.vaccinationapp.dao.implementation.UserDaoImp;
import com.android.vaccinationapp.dao.interfaces.RequestDao;
import com.android.vaccinationapp.dao.interfaces.UserDao;
import com.android.vaccinationapp.dao.interfaces.VaccinationDao;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;
import java.util.Objects;

public class RequestFollowupActivity extends AppCompatActivity {
    private static final String TAG = "RequestFollowupActivity";
    private FirebaseUser fb_user ;
    private DAOFactory daoFactory;
    private UserDao userDao;
    private RequestDao requestDao;
    private VaccinationDao vaccinationDao;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_followup);

        TextView request_state = (TextView)findViewById(R.id.textView6);
        TextView date_dose1 = (TextView)findViewById(R.id.textView7);
        TextView date_dose2 = (TextView)findViewById(R.id.textView8);
        TextView location = (TextView)findViewById(R.id.textView9);
        fb_user = getIntent().getExtras().getParcelable("currUser");
        daoFactory = DAOFactory.getInstance();
        FirebaseFirestore db = daoFactory.getDb();
        userDao = new UserDaoImp(daoFactory);
        requestDao = new RequestDaoImp(daoFactory);

        User user = new User();
        user.setId(fb_user.getUid());
        List<Request> requests = requestDao.findRequests(user);
        //Vaccination vaccination = vaccinationDao.findVaccination()
        request_state.setText(requests.get(0).getRequest_state());


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