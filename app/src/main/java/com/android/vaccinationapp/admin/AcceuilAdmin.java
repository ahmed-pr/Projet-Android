package com.android.vaccinationapp.admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.android.vaccinationapp.R;
import com.android.vaccinationapp.modele.DAO;
import com.android.vaccinationapp.users.WelcomeActivity;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class AcceuilAdmin extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceuil_admin);

        new DAO().connect();

        menu();

        CardView c = (CardView) this.findViewById(R.id.form);
        c.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(AcceuilAdmin.this, ListeDemande.class);
                startActivity(intent);
            }
        } ) ;

        c = (CardView) this.findViewById(R.id.followup);
        c.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(AcceuilAdmin.this, ListeVaccin.class);
                startActivity(intent);
            }
        } ) ;


        c = (CardView) this.findViewById(R.id.info);
        c.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                /*Intent intent = new Intent(AcceuilAdmin.this, ListeVaccin.class);
                startActivity(intent);*/
            }
        } ) ;

    }









    private DrawerLayout drawerLayout;

    public void menu() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_main_toolbar);
        this.setSupportActionBar(toolbar);

        this.drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        this.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        ( (NavigationView) findViewById(R.id.nav_view) ).setNavigationItemSelectedListener(this);





    }

    @Override
    public void onBackPressed() {
        // 5 - Handle back click to close menu
        if (this.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        // 4 - Handle Navigation Item Click
        int id = item.getItemId();

        switch (id){
            case R.id.acceuil :
                startActivity(new Intent(this, AcceuilAdmin.class));
                break;
            case R.id.demande:
                startActivity(new Intent(this, ListeDemande.class));
                break;
            case R.id.citoyen:
                startActivity(new Intent(this, ListeVaccin.class));
                break;
            case R.id.info:
                startActivity(new Intent(this, ListeVaccin.class));
                break;
            case R.id.deconnecter:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(this, WelcomeActivity.class));
        }

        this.drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }



}