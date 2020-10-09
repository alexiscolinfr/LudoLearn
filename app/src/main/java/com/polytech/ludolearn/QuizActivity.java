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

public class QuizActivity extends AppCompatActivity {

    public static int nbQuestionsFrancais, nbQuestionsAnglais, nbQuestionsHistoire, nbQuestionsGeographie, nbQuestionsSciences, nbQuestionsMaths;
    public static int nbErreursFrancais, nbErreursAnglais, nbErreursHistoire, nbErreursGeographie, nbErreursSciences, nbErreursMaths;
    public int nQuestion = 1;
    public List<Question> listeQuestion;
    public ArrayList<Button> listeBouton = new ArrayList<Button>();
    public ArrayList<Integer> intList = new ArrayList<Integer>();
    public int boutonVrai;
    public boolean choixEffectue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        nbErreursFrancais = 0;
        nbQuestionsFrancais = 0;
        nbErreursAnglais = 0;
        nbQuestionsAnglais = 0;
        nbErreursHistoire = 0;
        nbQuestionsHistoire = 0;
        nbErreursGeographie = 0;
        nbQuestionsGeographie = 0;
        nbErreursSciences = 0;
        nbQuestionsSciences = 0;
        nbErreursMaths = 0;
        nbQuestionsMaths = 0;

        // Générer une liste aléatoire de chiffres sans doublon
        int i = 0;
        while (i < 20){
            int val = new Random().nextInt(50);
            if (!intList.contains(val)){
                intList.add(val);
                i++;
            }
        }
        initQuestions();

        //Affichage de la question
        listeQuestion = Question.listAll(Question.class);

        TextView numeroQuestion = (TextView) findViewById(R.id.textViewQuestionNumber);
        numeroQuestion.setText(nQuestion + "/20");
        TextView text = (TextView) findViewById(R.id.textViewQuestion);
        text.setText(listeQuestion.get(intList.get(nQuestion-1)).getIntitule());
        Button boutonR1 = (Button) findViewById(R.id.button1);
        Button boutonR2 = (Button) findViewById(R.id.button2);
        Button boutonR3 = (Button) findViewById(R.id.button3);
        Button boutonR4 = (Button) findViewById(R.id.button4);

        listeBouton.add(boutonR1);
        listeBouton.add(boutonR2);
        listeBouton.add(boutonR3);
        listeBouton.add(boutonR4);

        Collections.shuffle(listeBouton);
        for (int k = 0 ; k < listeBouton.size(); k++){
            if (k == 0){
                listeBouton.get(k).setText(listeQuestion.get(intList.get(nQuestion-1)).getReponseFausse1());
            } else if (k == 1){
                listeBouton.get(k).setText(listeQuestion.get(intList.get(nQuestion-1)).getReponseFausse2());
            } else if (k == 2){
                listeBouton.get(k).setText(listeQuestion.get(intList.get(nQuestion-1)).getReponseFausse3());
            } else {
                listeBouton.get(k).setText(listeQuestion.get(intList.get(nQuestion-1)).getReponseVrai());
                boutonVrai=listeBouton.get(k).getId();
            }
        }

        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setProgress(nQuestion);
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void validate(View view) {

        //Gestion des erreurs
        if (listeQuestion.get(intList.get(nQuestion-1)).getTag().equals("Français")){
            if (view.getId() != boutonVrai){
                nbErreursFrancais++;
            }
            nbQuestionsFrancais++;
        } else if (listeQuestion.get(intList.get(nQuestion-1)).getTag().equals("Anglais")){
            if (view.getId() != boutonVrai){
                nbErreursAnglais++;
            }
            nbQuestionsAnglais++;
        } else if (listeQuestion.get(intList.get(nQuestion-1)).getTag().equals("Histoire")){
            if (view.getId() != boutonVrai){
                nbErreursHistoire++;
            }
            nbQuestionsHistoire++;
        } else if (listeQuestion.get(intList.get(nQuestion-1)).getTag().equals("Géographie")){
            if (view.getId() != boutonVrai){
                nbErreursGeographie++;
            }
            nbQuestionsGeographie++;
        } else if (listeQuestion.get(intList.get(nQuestion-1)).getTag().equals("Sciences")){
            if (view.getId() != boutonVrai){
                nbErreursSciences++;
            }
            nbQuestionsSciences++;
        } else if (listeQuestion.get(intList.get(nQuestion-1)).getTag().equals("Mathématiques")){
            if (view.getId() != boutonVrai){
                nbErreursMaths++;
            }
            nbQuestionsMaths++;
        }
        nQuestion++;

        // Affichage de la bonne réponse
        for (int k =0 ; k<listeBouton.size(); k++){
            if(listeBouton.get(k).getId() == boutonVrai) listeBouton.get(k).getBackground().setColorFilter(ContextCompat.getColor(this, R.color.correct), PorterDuff.Mode.MULTIPLY);

            else listeBouton.get(k).getBackground().setColorFilter(ContextCompat.getColor(this, R.color.incorrect), PorterDuff.Mode.MULTIPLY);
            listeBouton.get(k).setClickable(false);
        }
        choixEffectue = true;
    }

    public void initQuestions(){
        Question question1 = new Question(
                "Quelle période historique commence au Vème siècle ?",
                "L'Antiquité",
                "Les Temps Modernes",
                "L'Epoque Contemporaine",
                "Le Moyen-Âge",
                "Histoire");
        question1.save();

        Question question2 = new Question(
                "Quelle événement marque le début du Moyen-Âge ?",
                "La mort de Jules César",
                "La bataille d'Alésia",
                "La naissance de Jésus-Christ",
                "La chute de l’Empire romain",
                "Histoire");
        question2.save();

        Question question3 = new Question(
                "Qui est le roi des Francs ?",
                "Charlemagne",
                "Jule César",
                "Charles Martel",
                "Clovis",
                "Histoire");
        question3.save();

        Question question4 = new Question(
                "Qui sont les descendants de Clovis ?",
                "Les Celtes",
                "Les Romains",
                "Les Carolingiens",
                "Les Mérovingiens",
                "Histoire");
        question4.save();

        Question question5 = new Question(
                "Quelle dynastie commnence au VIIIème siècle ?",
                "Les Capétiens",
                "Les Romains",
                "Les Mérovingiens",
                "Les Carolingiens",
                "Histoire");
        question5.save();

        Question question6 = new Question(
                "Quel roi repousse l'invasion des Sarrasins (Arabes), à Poitiers, en 732 ?",
                "Charlemagne",
                "Jule César",
                "Clovis",
                "Charles Martel",
                "Histoire");
        question6.save();

        Question question7 = new Question(
                "Qui est le premier roi de la dynastie des Carolingiens ?",
                "Charlemagne",
                "Charles Martel",
                "Clovis",
                "Pépin le Bref",
                "Histoire");
        question7.save();

        Question question8 = new Question(
                "Charles Martel, Charlemagne, Pépin le Bref sont respectivement : '",
                "Père, Fils, Grand-Père",
                "Grand-Père, Père, Fils",
                "N'ont aucune relation de parenté",
                "Grand-Père, Fils, Père",
                "Histoire");
        question8.save();

        Question question9 = new Question(
                "En quelle année Charlemagne devient empereur des Francs ?",
                "732",
                "771",
                "814",
                "800",
                "Histoire");
        question9.save();

        Question question10 = new Question(
                "À la mort de Charlemagne, ses descendants partagent l’empire en trois empires :",
                "la Francie occidentale (à l'est), la Francie orientale (à l'ouest), la Lotharingie (au centre)",
                "la Francie occidentale (au centre), la Francie orientale (à l'est), la Lotharingie (à l'ouest)",
                "la Francie occidentale (à l'ouest), la Francie orientale (au centre), la Lotharingie (à l'est)",
                "la Francie occidentale (à l'ouest), la Francie orientale (à l'est), la Lotharingie (au centre)",
                "Histoire");
        question10.save();

        Question question11 = new Question(
                "Quelle dynastie commennce lors du couronnement de Hugues Capet en roi des Francs ?",
                "Les Carolingiens",
                "Les Romains",
                "Les Mérovingiens",
                "Les Capétiens",
                "Histoire");
        question11.save();

        Question question12 = new Question(
                "Qui a instauré la monarchie absolue au XVIIème siècle ?",
                "Louis XII (le père de Louis XIV)",
                "Napoléon I",
                "Napoléon III",
                "Louis XIV",
                "Histoire");
        question12.save();

        Question question13 = new Question(
                "Quel siècle est considéré comme le siècle des Lumières ?",
                "Le XVIIème siècle",
                "Le XVIème siècle",
                "Le XVème siècle",
                "Le XVIIIème siècle",
                "Histoire");
        question13.save();

        Question question14 = new Question(
                "En quelle année a eu lieu la capitulation de l'Allemagne nazie ?",
                "1914",
                "1918",
                "1939",
                "1945",
                "Histoire");
        question14.save();

        Question question15 = new Question(
                "Suite aux mauvaises relations Américaines et Soviétiques, deux blocs se formés : ",
                "Le bloc de l'Est et le bloc du Nord",
                "Le bloc de l'Ouest et le bloc du Sud",
                "Le bloc du Nord et le bloc du Sud",
                "Le bloc de l'Est et le bloc de l'Ouest",
                "Histoire");
        question15.save();

        Question question16 = new Question(
                "A quel bloc correspond le bloc Sovétique ?",
                "Le bloc du Nord",
                "Le bloc de l'Ouest",
                "Le bloc du Nord",
                "Le bloc de l'Est",
                "Histoire");
        question16.save();

        Question question17 = new Question(
                "A quel bloc correspond le bloc Américain ?",
                "Le bloc du Nord",
                "Le bloc de l'Est",
                "Le bloc du Nord",
                "Le bloc de l'Ouest",
                "Histoire");
        question17.save();

        Question question18 = new Question(
                "Comment s'appelle la période de fortes tensions géopolitiques entre les États-Unis et l'URSS ?",
                "La première guerre mondiale",
                "La deuxième guerre mondiale",
                "La guerre du Golf",
                "La guerre froide",
                "Histoire");
        question18.save();

        Question question19 = new Question(
                "Comment s'appelle la période de fortes tensions géopolitiques entre les États-Unis et l'URSS ?",
                "La première guerre mondiale",
                "La deuxième guerre mondiale",
                "La guerre du Golf",
                "La guerre froide",
                "Histoire");
        question19.save();

        Question question20 = new Question(
                "Quel événement marque le début de la fin de la guerre froide ?",
                "Le premier homme sur la Lune en 1969",
                "La crise de Cuba en 1962",
                "La fin de la guerre du Vietnam en 1975",
                "La chute du mur de Berlin en 1989",
                "Histoire");
        question20.save();

        Question question21 = new Question(
                "Quelle est l'année de la fin de la guerre froide ?",
                "1945",
                "1979",
                "1989",
                "1991",
                "Histoire");
        question21.save();

        Question question22 = new Question(
                "En quelle année a eu lieu la chute de l'URSS ?",
                "1945",
                "1960",
                "1979",
                "1991",
                "Histoire");
        question21.save();

    }

    public void nextQuestion(View view) {

        // Si aucune réponse n'est donnée
        if(!choixEffectue){
            Toast.makeText(this, "Tu dois choisir une réponse à cette question !", Toast.LENGTH_SHORT).show();
        }

        else{
            choixEffectue = false;
            // Remise à zéro des boutons
            for (int k = 0 ; k < listeBouton.size(); k++){
                listeBouton.get(k).getBackground().clearColorFilter();
                listeBouton.get(k).setClickable(true);
            }

            // On passe à la question suivante
            if (nQuestion <11){
                listeBouton.clear();
                TextView text = (TextView) findViewById(R.id.textViewQuestion);
                text.setText(listeQuestion.get(intList.get(nQuestion-1)).getIntitule());
                Button boutonR1 = (Button) findViewById(R.id.button1);
                Button boutonR2 = (Button) findViewById(R.id.button2);
                Button boutonR3 = (Button) findViewById(R.id.button3);
                Button boutonR4 = (Button) findViewById(R.id.button4);
                TextView numeroQuestion = (TextView) findViewById(R.id.textViewQuestionNumber);
                numeroQuestion.setText(nQuestion + "/10");
                ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
                progressBar.setProgress(nQuestion);

                listeBouton.add(boutonR1);
                listeBouton.add(boutonR2);
                listeBouton.add(boutonR3);
                listeBouton.add(boutonR4);

                Collections.shuffle(listeBouton);
                for (int k =0 ; k<listeBouton.size(); k++){
                    if (k==0){
                        listeBouton.get(k).setText(listeQuestion.get(intList.get(nQuestion-1)).getReponseFausse1());
                    } else if (k==1){
                        listeBouton.get(k).setText(listeQuestion.get(intList.get(nQuestion-1)).getReponseFausse2());
                    } else if (k==2){
                        listeBouton.get(k).setText(listeQuestion.get(intList.get(nQuestion-1)).getReponseFausse3());
                    } else {
                        listeBouton.get(k).setText(listeQuestion.get(intList.get(nQuestion-1)).getReponseVrai());
                        boutonVrai=listeBouton.get(k).getId();
                    }
                }

                if(nQuestion == 20){
                    Button boutonContinuer = findViewById(R.id.buttonNext);
                    boutonContinuer.setText("Résultats");
                }
            }
            else {
                Question.deleteAll(Question.class);
                Intent intent = new Intent(this, QuizResultsActivity.class);
                startActivity(intent);
            }
        }
    }
}