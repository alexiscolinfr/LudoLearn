package com.polytech.ludolearn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ChoixConnectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choix_connection);
    }

    public void logIn(View view){
        Intent intent = new Intent(this, ConnexionActivity.class);
        startActivity(intent);
    }

    public void signIn(View view){
        Intent intent = new Intent(this, InscriptionActivity.class);
        startActivity(intent);
    }

}