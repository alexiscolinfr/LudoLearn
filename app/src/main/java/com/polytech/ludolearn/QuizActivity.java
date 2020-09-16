package com.polytech.ludolearn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.BlendMode;
import android.graphics.BlendModeColorFilter;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.polytech.ludolearn.database.Question;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class QuizActivity extends AppCompatActivity {

    public static int nbQuestionsFrancais, nbQuestionsAnglais, nbQuestionsSport, nbQuestionsHistoireGeo, nbQuestionsArtsPlastiques, nbQuestionsSciences, nbQuestionsMaths;
    public static int nbErreursFrancais, nbErreursAnglais, nbErreursSport, nbErreursHistoireGeo,  nbErreursArtsPlastiques, nbErreursSciences, nbErreursMaths;
    public int nQuestion=1;
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
        nbErreursSport = 0;
        nbQuestionsSport = 0;
        nbErreursHistoireGeo = 0;
        nbQuestionsHistoireGeo = 0;
        nbErreursArtsPlastiques = 0;
        nbQuestionsArtsPlastiques =0;
        nbErreursSciences = 0;
        nbQuestionsSciences = 0;
        nbErreursMaths = 0;
        nbQuestionsMaths = 0;

        // Générer ma liste aléatoire de chiffres sans doublons
        int i=0;
        while (i<10){
            int val = new Random().nextInt(20);
            if (!intList.contains(val)){
                intList.add(val);
                i++;
            }
        }
        initQuestions();

        //Affichage de ma question
        listeQuestion = Question.listAll(Question.class);

        TextView numeroQuestion = (TextView) findViewById(R.id.textViewQuestionNumber);
        numeroQuestion.setText(nQuestion + "/10");
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
        } else if (listeQuestion.get(intList.get(nQuestion-1)).getTag().equals("Sport")){
            if (view.getId() != boutonVrai){
                nbErreursSport++;
            }
            nbQuestionsSport++;
        } else if (listeQuestion.get(intList.get(nQuestion-1)).getTag().equals("Histoire-Géographie")){
            if (view.getId() != boutonVrai){
                nbErreursHistoireGeo++;
            }
            nbQuestionsHistoireGeo++;
        } else if (listeQuestion.get(intList.get(nQuestion-1)).getTag().equals("Arts-plastiques")){
            if (view.getId() != boutonVrai){
                nbErreursArtsPlastiques++;
            }
            nbQuestionsArtsPlastiques++;
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

        // AFFICHAGE DE LA BONNE REPONSE
        for (int k =0 ; k<listeBouton.size(); k++){
            if(listeBouton.get(k).getId() == boutonVrai) listeBouton.get(k).getBackground().setColorFilter(ContextCompat.getColor(this, R.color.correct), PorterDuff.Mode.MULTIPLY);

            else listeBouton.get(k).getBackground().setColorFilter(ContextCompat.getColor(this, R.color.incorrect), PorterDuff.Mode.MULTIPLY);
            listeBouton.get(k).setClickable(false);
        }
        choixEffectue = true;
    }

    public void initQuestions(){
        Question question = new Question(
                "Les mots «pair» et «paire» sont des...",
                "Des antonymes",
                "Des Anonymes",
                "Des synonymes",
                "Des homonymes",
                "Français");
        question.save();

        Question question2 = new Question(
                "Quel océan entoure l'île de Madagascar ?",
                "Océan Pacifique",
                "Océan Atlantique",
                "Océan Océanique",
                "Océan Indien",
                "Histoire-Géographie");
        question2.save();

        Question question3 = new Question(
                "Combien un cube a-t-il de arêtes ?",
                "4",
                "8",
                "6",
                "12",
                "Mathématiques");
        question3.save();

        Question question4 = new Question(
                "À quelle date correspond l'équinoxe d'automne (à 1 ou 2 jours près) (dans l'hémisphère sud) ?",
                "Le 21 mars",
                "Le 21 juin",
                "Le 21 decembre",
                "Le 21 septembre",
                "Sciences");
        question4.save();

        Question question5 = new Question(
                "Zoé a 20 ans. Sa maman lui dit : 'J'ai exactement le double de ton âge. Mais quel âge aurai-je quand tu auras l'âge que j'ai aujourd'hui ?'",
                "40 ans",
                "80 ans",
                "160 ans",
                "60 ans",
                "Mathématiques");
        question5.save();

        Question question6 = new Question(
                "Lequel de ces pays n'appartient pas à l'Union européenne ?",
                "Grèce",
                " Belgique",
                "Portugal",
                "Suisse",
                "Histoire-Géographie");
        question6.save();

        Question question7 = new Question(
                "Quel composant de l'air l'être humain absorbe-t-il pour respirer ?",
                "Le gaz carbonique",
                "L'azote",
                "Le souffre",
                "L'oxygène",
                "Sciences");
        question7.save();

        Question question8 = new Question(
                "En quelle année a débuté la 2ème Guerre Mondiale ?",
                "1914",
                "2018",
                "1962",
                "1939",
                "Histoire-Géographie");
        question8.save();

        Question question9 = new Question(
                "Quelle est la capitale de l'Angleterre ?",
                "Paris",
                "Berlin",
                "Roybon",
                "Londres",
                "Histoire-Géographie");
        question9.save();

        Question question10 = new Question(
                "Conjugue le verbe chanter au présent à la 3ème personne du singulier.",
                "Il chantes",
                "Ils chantent",
                "Il mange",
                "Il chante",
                "Français");
        question10.save();

        Question question11 = new Question(
                "Par quelle couleur est généralement représenté le sang oxygéné sur les schémas de la circulation sanguine ?",
                "Bleu",
                "Blanc",
                "Pourpre",
                "Rouge",
                "Sciences");
        question11.save();

        Question question12 = new Question(
                "Que signifie 'Goodbye' ?",
                "Bonjour",
                "Bonne nuit",
                "Chat",
                "Au revoir",
                "Anglais");
        question12.save();

        Question question13 = new Question(
                "Quelle est la fonction de 'en Provence' dans la phrase 'je pars en Provence' ?",
                "Sujet",
                "Verbe",
                "Destination",
                "Complément",
                "Français");
        question13.save();

        Question question14 = new Question(
                "Qui a peint la Joconde ?",
                "Gustave Eiffel",
                "Picasso",
                "Charles II",
                "Léonard de Vinci",
                "Arts-plastiques");
        question14.save();

        Question question15 = new Question(
                "Comment appelle-t-on la glace qui peut se former sur la route en hiver ?",
                "La vergle",
                "Le verger",
                "Les glaçons",
                "Le verglas",
                "Sciences");
        question15.save();

        Question question16 = new Question(
                "Dans lequel de ces sports n'avons nous pas besoin d'une raquette ?",
                "Le tennis",
                "Le badminton",
                "Le ping-pong",
                "Le voley-Ball",
                "Sport");
        question16.save();

        Question question17 = new Question(
                "A quel sport joue-t-on avec un ballon rond ?",
                "Tennis",
                "Rugby",
                "Le loup",
                "Handball",
                "Sport");
        question17.save();

        Question question18 = new Question(
                "Quelle est la capitale de l'Italie ?",
                "Tokyo",
                "Venise",
                "Naples",
                "Rome",
                "Histoire-Géographie");
        question18.save();

        Question question19 = new Question(
                "What is your name ?",
                "Mi name ",
                "My names ",
                "Your name ",
                "My name is ",
                "Anglais");
        question19.save();

        Question question20 = new Question(
                "Comment s'écrit gaz au pluriel ?",
                "Gazes",
                "Gazx",
                "Gazs",
                "Gaz",
                "Français");
        question20.save();
    }

    public void nextQuestion(View view) {

        // SI AUCUNE REPONSE N'EST DONNEE
        if(!choixEffectue){
            Toast.makeText(this, "Tu dois choisir une réponse à cette question !", Toast.LENGTH_SHORT).show();
        }

        else{
            choixEffectue = false;
            // REMISE A ZERO DES BOUTONS
            for (int k =0 ; k<listeBouton.size(); k++){
                listeBouton.get(k).getBackground().clearColorFilter();
                listeBouton.get(k).setClickable(true);
            }

            // On passe a la question suivante
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

                if(nQuestion == 10){
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