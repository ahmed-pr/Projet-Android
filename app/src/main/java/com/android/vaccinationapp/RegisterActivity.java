package com.android.vaccinationapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.vaccinationapp.beans.User;
import com.android.vaccinationapp.dao.DAOFactory;
import com.android.vaccinationapp.dao.implementation.UserDaoImp;
import com.android.vaccinationapp.dao.interfaces.UserDao;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private DAOFactory daoFactory;
    private static final String TAG = "RegisterActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        daoFactory = DAOFactory.getInstance();
        mAuth = FirebaseAuth.getInstance();

        TextView login = findViewById(R.id.alreadyHaveAnAccount);
        login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){
                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));                }
            });

        Button btnreg = findViewById(R.id.btnRegister);
        btnreg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String full_name = ((EditText)findViewById(R.id.inputPassword)).getText().toString();
                String email = ((EditText)findViewById(R.id.inputEmail)).getText().toString();
                String password = ((EditText)findViewById(R.id.inputPasswd)).getText().toString();
                String confirm_pass = ((EditText)findViewById(R.id.inputConformPassword)).getText().toString();
                User newUser = new User(null,full_name,email,null,null,null,0);
                if(!(email.isEmpty() || password.isEmpty() || full_name.isEmpty() || confirm_pass.isEmpty())) {
                    if(password == confirm_pass) {
                        mAuth.createUserWithEmailAndPassword(email, password)
                                .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            FirebaseUser user = mAuth.getCurrentUser();
                                            newUser.setId(user.getUid());
                                            new UserDaoImp(daoFactory).addUser(newUser);
                                            new LoginActivity().updateUI(user);
                                        } else {
                                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                            Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                                    Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    }
                    else{
                        Toast.makeText(RegisterActivity.this,"Veuillez verifier correctement le mot de passe",Toast.LENGTH_LONG)
                                .show();
                    }
                }
                else{
                    Toast.makeText(RegisterActivity.this,"Veuillez remplir toutes les champs",Toast.LENGTH_LONG)
                            .show();
                }

            }
        });
    }
}