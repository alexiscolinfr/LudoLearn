package com.polytech.ludolearn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ChoixMathsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choix_maths);
    }

    public void addition(View view){
        Intent intent = new Intent(this, MathsActivity.class);
        intent.putExtra("choixCalculs", "addition");
        startActivity(intent);
    }

    public void soustraction(View view){
        Intent intent = new Intent(this, MathsActivity.class);
        intent.putExtra("choixCalculs", "soustraction");
        startActivity(intent);
    }

    public void multiplication(View view){
        Intent intent = new Intent(this, ChoixTableMultActivity.class);
        startActivity(intent);
    }

    public void division(View view) {
        Intent intent = new Intent(this, MathsActivity.class);
        intent.putExtra("choixCalculs", "division");
        startActivity(intent);
    }
}