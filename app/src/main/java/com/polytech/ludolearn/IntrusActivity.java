package com.polytech.ludolearn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.polytech.ludolearn.database.Question;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class IntrusActivity extends AppCompatActivity {

    public int nQuestion = 1;
    public List<Question> listeQuestion;
    public ArrayList<Button> listeBouton = new ArrayList<>();
    public ArrayList<Integer> intList = new ArrayList<>();
    public int boutonVrai;
    public static int nbErreurs;
    public boolean choixEffectue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intrus);

        nbErreurs = 0;

        creerQuestions();

        // Génère une liste aléatoire de chiffres sans doublons
        int i = 0;
        while (i < 10) {
            int val = new Random().nextInt(10);
            if (!intList.contains(val)) {
                intList.add(val);
                i++;
            }
        }

        // Affichage de la question
        listeQuestion = Question.listAll(Question.class);

        TextView numeroQuestion = findViewById(R.id.textViewNumeroQuestion);
        numeroQuestion.setText(nQuestion + "/10");
        TextView text = findViewById(R.id.textViewQuestion);
        text.setText(listeQuestion.get(intList.get(nQuestion - 1)).getIntitule());
        Button boutonR1 = findViewById(R.id.bouton1);
        Button boutonR2 = findViewById(R.id.bouton2);
        Button boutonR3 = findViewById(R.id.bouton3);
        Button boutonR4 = findViewById(R.id.bouton4);

        listeBouton.add(boutonR1);
        listeBouton.add(boutonR2);
        listeBouton.add(boutonR3);
        listeBouton.add(boutonR4);

        Collections.shuffle(listeBouton);
        for (int k = 0 ; k < listeBouton.size() ; k++) {
            if (k == 0) {
                listeBouton.get(k).setText(listeQuestion.get(intList.get(nQuestion - 1)).getReponseFausse1());
            } else if (k == 1) {
                listeBouton.get(k).setText(listeQuestion.get(intList.get(nQuestion - 1)).getReponseFausse2());
            } else if (k == 2) {
                listeBouton.get(k).setText(listeQuestion.get(intList.get(nQuestion - 1)).getReponseFausse3());
            } else {
                listeBouton.get(k).setText(listeQuestion.get(intList.get(nQuestion - 1)).getReponseVrai());
                boutonVrai = listeBouton.get(k).getId();
            }
        }

        ProgressBar progressBar = findViewById(R.id.progressBar);
        progressBar.setProgress(nQuestion);
    }

    private void creerQuestions() {
        Question question = new Question (
                "Comiques",
                "Coluche",
                "Fernandel",
                "Dany Boon",
                "Raymond Devos",
                "Intrus"
        );
        question.save();

        Question question2 = new Question (
                "Gastronomie",
                "Cassoulet",
                "Fèves au lard",
                "Chili con carne",
                "Couscous",
                "Intrus"
        );
        question2.save();

        Question question3 = new Question (
                "Prénoms",
                "Camille",
                "Dominique",
                "Claude",
                "Bernard",
                "Intrus"
        );
        question3.save();

        Question question4 = new Question (
                "Informatique",
                "Disque dur",
                "Clé USB",
                "Cd-rom",
                "Carte mère",
                "Intrus"
        );
        question4.save();

        Question question5 = new Question (
                "Sportifs",
                "Vincent Moscato",
                "Éric Cantona",
                "Joël Cantona",
                "Zinédine Zidane",
                "Intrus"
        );
        question5.save();

        Question question6 = new Question (
                "Italie",
                "Florence",
                "Turin",
                "Milan",
                "Capri",
                "Intrus"
        );
        question6.save();

        Question question7 = new Question (
                "Fruits et légumes",
                "Pomme",
                "Kiwi",
                "Tomate",
                "Poivron",
                "Intrus"
        );
        question7.save();

        Question question8 = new Question (
                "Flics de choc",
                "G. I. G. N.",
                "R. A. I. D.",
                "G. I. P. N.",
                "B. R. I. G. A. D.",
                "Intrus"
        );
        question8.save();

        Question question9 = new Question (
                "Jeux télévisés",
                "Slam",
                "Mot de passe",
                "Motus",
                "Les 12 coups de midi",
                "Intrus"
        );
        question9.save();

        Question question10 = new Question (
                "Séries policières françaises",
                "Les Cordier, juge et flic",
                "Navarro",
                "Julie Lescaut",
                "Marc Éliott",
                "Intrus"
        );
        question10.save();
    }

    public void nextIntruder(View view) {
        // Si aucune réponse n'est donnée
        if (!choixEffectue) {
            Toast.makeText(this, "Tu dois choisir une réponse à cette question !", Toast.LENGTH_SHORT).show();
        }

        else {
            choixEffectue = false;
            // Remise à zéro des boutons
            for (int k = 0 ; k < listeBouton.size() ; k++) {
                listeBouton.get(k).getBackground().clearColorFilter();
                listeBouton.get(k).setClickable(true);
            }

            // On passe à la question suivante
            if (nQuestion < 11) {
                listeBouton.clear();
                TextView text = findViewById(R.id.textViewQuestion);
                text.setText(listeQuestion.get(intList.get(nQuestion - 1)).getIntitule());
                Button boutonR1 = findViewById(R.id.bouton1);
                Button boutonR2 = findViewById(R.id.bouton2);
                Button boutonR3 = findViewById(R.id.bouton3);
                Button boutonR4 = findViewById(R.id.bouton4);
                TextView numeroQuestion = findViewById(R.id.textViewNumeroQuestion);
                numeroQuestion.setText(nQuestion + "/10");
                ProgressBar progressBar = findViewById(R.id.progressBar);
                progressBar.setProgress(nQuestion);

                listeBouton.add(boutonR1);
                listeBouton.add(boutonR2);
                listeBouton.add(boutonR3);
                listeBouton.add(boutonR4);

                Collections.shuffle(listeBouton);
                for (int k = 0 ; k < listeBouton.size() ; k++) {
                    if (k == 0) {
                        listeBouton.get(k).setText(listeQuestion.get(intList.get(nQuestion - 1)).getReponseFausse1());
                    } else if (k == 1) {
                        listeBouton.get(k).setText(listeQuestion.get(intList.get(nQuestion - 1)).getReponseFausse2());
                    } else if (k == 2) {
                        listeBouton.get(k).setText(listeQuestion.get(intList.get(nQuestion - 1)).getReponseFausse3());
                    } else {
                        listeBouton.get(k).setText(listeQuestion.get(intList.get(nQuestion - 1)).getReponseVrai());
                        boutonVrai = listeBouton.get(k).getId();
                    }
                }
                System.out.print("ICI");
                if (nQuestion == 10) {
                    Button boutonContinuer = findViewById(R.id.intrus_boutonContinuer);
                    boutonContinuer.setText("Résultats");
                }
            }
            else {
                Question.deleteAll(Question.class);
                Intent intent = new Intent(this, IntrusResultsActivity.class);
                startActivity(intent);
            }
        }
    }
    public void validate(View view) {
        // Gestion des erreurs
        if (view.getId() != boutonVrai) {
            nbErreurs++;
        }
        nQuestion++;

        // Affichage de la bonne réponse
        for (int k = 0 ; k < listeBouton.size() ; k++) {
            if (listeBouton.get(k).getId() == boutonVrai) listeBouton.get(k).getBackground()
                    .setColorFilter(ContextCompat.getColor(this, R.color.correct), PorterDuff.Mode.MULTIPLY);

            else listeBouton.get(k).getBackground()
                    .setColorFilter(ContextCompat.getColor(this, R.color.incorrect), PorterDuff.Mode.MULTIPLY);
            listeBouton.get(k).setClickable(false);
        }
        choixEffectue = true;
    }

    @Override
    public void onBackPressed(){
        Toast.makeText(this, "Tu dois finir cette partie avant de pouvoir revenir au menu principal !", Toast.LENGTH_LONG).show();
    }
}