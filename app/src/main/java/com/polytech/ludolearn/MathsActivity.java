package com.polytech.ludolearn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MathsActivity extends AppCompatActivity {

    public int reponse1,reponse2,reponse3,reponse4,reponse5;
    Boolean verificationReponse = false;    // Vérifie que la correction a été affichée avant de pouvoir voir son résultat
    public String choixCalculs = ""; // Catégorie de calculs choisie
    public int nbErreurs = 0;

    public TextView operation1, operation2, operation3, operation4, operation5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maths);

        choixCalculs = getIntent().getStringExtra("choixCalculs");
        operation1 = findViewById(R.id.operation1);
        operation2 = findViewById(R.id.operation2);
        operation3 = findViewById(R.id.operation3);
        operation4 = findViewById(R.id.operation4);
        operation5 = findViewById(R.id.operation5);

        switch (choixCalculs) {
            case "addition":
                initAdditions();
                break;
            case "soustraction":
                initSoustractions();
                break;
            case "division":
                initDivision();
                break;
            default:
                initMultiplication();
                break;
        }
    }

    private void initSoustractions() {
        Random random = new Random();
        int randNum = random.nextInt(100) + 1;
        int randNum2 = random.nextInt(100) + 1;
        while (randNum2 > randNum) {
            randNum2 = random.nextInt(100) + 1;
        }
        reponse1 = randNum - randNum2;
        operation1.setText(randNum + " - " + randNum2 + " = ");

        randNum = random.nextInt(100) + 1;
        randNum2 = random.nextInt(100) + 1;
        while (randNum2 > randNum) {
            randNum2 = random.nextInt(100) + 1;
        }
        reponse2 = randNum - randNum2;
        operation2.setText(randNum + " - " + randNum2 + " = ");

        randNum = random.nextInt(100) + 1;
        randNum2 = random.nextInt(100) + 1;
        while (randNum2 > randNum)  {
            randNum2 = random.nextInt(100) + 1;
        }
        reponse3 = randNum - randNum2;
        operation3.setText(randNum + " - " + randNum2 + " = ");

        randNum = random.nextInt(100) + 1;
        randNum2 = random.nextInt(100) + 1;
        while (randNum2 > randNum){
            randNum2 = random.nextInt(100) + 1;
        }
        reponse4 = randNum - randNum2;
        operation4.setText(randNum + " - " + randNum2 + " = ");

        randNum = random.nextInt(100) + 1;
        randNum2 = random.nextInt(100) + 1;
        while (randNum2 > randNum) {
            randNum2 = random.nextInt(100) + 1;
        }
        reponse5 = randNum - randNum2;
        operation5.setText(randNum + " - " + randNum2 + " = ");
    }

    private void initAdditions() {
        Random random = new Random();
        int randNum = random.nextInt(100) + 1;
        int randNum2 = random.nextInt(100) + 1;
        reponse1 = randNum + randNum2;
        operation1.setText(randNum + " + " + randNum2 + " = ");

        randNum = random.nextInt(100) + 1;
        randNum2 = random.nextInt(100) + 1;
        reponse2 = randNum + randNum2;
        operation2.setText(randNum + " + " + randNum2 + " = ");

        randNum = random.nextInt(100) + 1;
        randNum2 = random.nextInt(100) + 1;
        reponse3 = randNum + randNum2;
        operation3.setText(randNum + " + " + randNum2 + " = ");

        randNum = random.nextInt(100) + 1;
        randNum2 = random.nextInt(100) + 1;
        reponse4 = randNum + randNum2;
        operation4.setText(randNum + " + " + randNum2 + " = ");

        randNum = random.nextInt(100) + 1;
        randNum2 = random.nextInt(100) + 1;
        reponse5 = randNum + randNum2;
        operation5.setText(randNum + " + " + randNum2 + " = ");
    }

    private void initDivision() {
        Random random = new Random();
        int randNum = 0;
        int randNum2 = 1;
        while (randNum % randNum2 != 0 || randNum2 > randNum) {
            randNum = random.nextInt(10) + 1;
            randNum2 = random.nextInt(10) + 1;
        }
        reponse1 = randNum / randNum2;
        operation1.setText(randNum + " / " + randNum2 + " = ");


        randNum = 0;
        randNum2 = 1;
        while (randNum % randNum2 != 0 || randNum2 > randNum) {
            randNum = random.nextInt(10) + 1;
            randNum2 = random.nextInt(10) + 1;
        }
        reponse2 = randNum / randNum2;
        operation2.setText(randNum + " / " + randNum2 + " = ");

        randNum = 0;
        randNum2 = 1;
        while (randNum % randNum2 != 0 || randNum2 > randNum) {
            randNum = random.nextInt(10) + 1;
            randNum2 = random.nextInt(10) + 1;
        }
        reponse3 = randNum / randNum2;
        operation3.setText(randNum + " / " + randNum2 + " = ");

        randNum = 0;
        randNum2 = 1;
        while (randNum%randNum2 != 0 || randNum2 > randNum) {
            randNum = random.nextInt(10) + 1;
            randNum2 = random.nextInt(10) + 1;
        }
        reponse4 = randNum / randNum2;
        operation4.setText(randNum + " / " + randNum2 + " = ");

        randNum = 0;
        randNum2 = 1;
        while (randNum % randNum2 != 0 || randNum2 > randNum) {
            randNum = random.nextInt(10) + 1;
            randNum2 = random.nextInt(10) + 1;
        }
        reponse5 = randNum / randNum2;
        operation5.setText(randNum + " / " + randNum2 + " = ");
    }

    private void initMultiplication() {
        int table = getIntent().getIntExtra("table", 1);
        Random random = new Random();
        int randNum = random.nextInt(10) + 1;
        reponse1 = randNum * table;
        operation1.setText(randNum + " x " + table + " = ");

        randNum = random.nextInt(10) + 1;
        reponse2 = randNum * table;
        operation2.setText(randNum + " x " + table + " = ");

        randNum = random.nextInt(10) + 1;
        reponse3 = randNum * table;
        operation3.setText(randNum + " x " + table + " = ");

        randNum = random.nextInt(10) + 1;
        reponse4 = randNum * table;
        operation4.setText(randNum + " x " + table + " = ");

        randNum = random.nextInt(10) + 1;
        reponse5 = randNum * table;
        operation5.setText(randNum + " x " + table + " = ");
    }

    public void validate(View v) {
        if (verifChamps()) {
            if(!verificationReponse) {
                verifierReponses();
                verificationReponse = true;
                Button b = findViewById(R.id.boutonValiderCalculs);
                b.setText("Afficher ton score");
            }
            else {
                Intent intent = new Intent(this, MathsResultsActivity.class);
                intent.putExtra("nbErreurs", nbErreurs);
                intent.putExtra("choixCalculs",choixCalculs);
                startActivity(intent);
            }
        }
        else Toast.makeText(this, "Vous devez renseigner tous les champs !", Toast.LENGTH_SHORT).show();
    }

    // Vérifie que toutes les réponses sont entrées
    public Boolean verifChamps() {
        EditText rep1 = findViewById(R.id.rep1);
        EditText rep2 = findViewById(R.id.rep2);
        EditText rep3 = findViewById(R.id.rep3);
        EditText rep4 = findViewById(R.id.rep4);
        EditText rep5 = findViewById(R.id.rep5);

        return !rep1.getText().toString().equals("")
                && !rep2.getText().toString().equals("")
                && !rep3.getText().toString().equals("")
                && !rep4.getText().toString().equals("")
                && !rep5.getText().toString().equals("");
    }

    // Vérifie si les réponses sont correctes
    public void verifierReponses() {
        EditText rep1 = findViewById(R.id.rep1);
        EditText rep2 = findViewById(R.id.rep2);
        EditText rep3 = findViewById(R.id.rep3);
        EditText rep4 = findViewById(R.id.rep4);
        EditText rep5 = findViewById(R.id.rep5);

        if (Integer.parseInt(rep1.getText().toString()) == reponse1) {
            rep1.setBackgroundColor(ContextCompat.getColor(this, R.color.correct));
        }
        else {
            nbErreurs++;
            rep1.setText(String.valueOf((reponse1)));
            rep1.setBackgroundColor(ContextCompat.getColor(this, R.color.incorrect));
        }

        if (Integer.parseInt(rep2.getText().toString()) == reponse2) {
            rep2.setBackgroundColor(ContextCompat.getColor(this, R.color.correct));
        }
        else {
            nbErreurs++;
            rep2.setText(String.valueOf((reponse2)));
            rep2.setBackgroundColor(ContextCompat.getColor(this, R.color.incorrect));
        }

        if (Integer.parseInt(rep3.getText().toString()) == reponse3) {
            rep3.setBackgroundColor(ContextCompat.getColor(this, R.color.correct));
        }
        else {
            nbErreurs++;
            rep3.setText(String.valueOf((reponse3)));
            rep3.setBackgroundColor(ContextCompat.getColor(this, R.color.incorrect));
        }

        if (Integer.parseInt(rep4.getText().toString()) == reponse4) {
            rep4.setBackgroundColor(ContextCompat.getColor(this, R.color.correct));
        }
        else {
            nbErreurs++;
            rep4.setText(String.valueOf((reponse4)));
            rep4.setBackgroundColor(ContextCompat.getColor(this, R.color.incorrect));
        }

        if (Integer.parseInt(rep5.getText().toString()) == reponse5) {
            rep5.setBackgroundColor(ContextCompat.getColor(this, R.color.correct));
        }
        else {
            nbErreurs++;
            rep5.setText(String.valueOf((reponse5)));
            rep5.setBackgroundColor(ContextCompat.getColor(this, R.color.incorrect));
        }
        rep1.setInputType(InputType.TYPE_NULL);
        rep2.setInputType(InputType.TYPE_NULL);
        rep3.setInputType(InputType.TYPE_NULL);
        rep4.setInputType(InputType.TYPE_NULL);
        rep5.setInputType(InputType.TYPE_NULL);
    }
}