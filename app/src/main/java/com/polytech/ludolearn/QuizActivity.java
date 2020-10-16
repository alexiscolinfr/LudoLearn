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

    public static int nbQuestionsFrancais, nbQuestionsAnglais, nbQuestionsHistoire, nbQuestionsGeographie,
            nbQuestionsSciences, nbQuestionsMaths;
    public static int nbErreursFrancais, nbErreursAnglais, nbErreursHistoire, nbErreursGeographie,
            nbErreursSciences, nbErreursMaths;
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

        // Générer une liste aléatoire de chiffres sans doublons
        int i = 0;
        while (i < 20) {
            int val = new Random().nextInt(75);
            if (!intList.contains(val)) {
                intList.add(val);
                i++;
            }
        }
        initQuestions();

        // Affichage de la question
        listeQuestion = Question.listAll(Question.class);

        TextView numeroQuestion = (TextView) findViewById(R.id.textViewQuestionNumber);
        numeroQuestion.setText(nQuestion + "/20");
        TextView text = (TextView) findViewById(R.id.textViewQuestion);
        text.setText(listeQuestion.get(intList.get(nQuestion - 1)).getIntitule());
        Button boutonR1 = (Button) findViewById(R.id.button1);
        Button boutonR2 = (Button) findViewById(R.id.button2);
        Button boutonR3 = (Button) findViewById(R.id.button3);
        Button boutonR4 = (Button) findViewById(R.id.button4);

        listeBouton.add(boutonR1);
        listeBouton.add(boutonR2);
        listeBouton.add(boutonR3);
        listeBouton.add(boutonR4);

        Collections.shuffle(listeBouton);
        for (int k = 0 ; k < listeBouton.size(); k++) {
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

        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setProgress(nQuestion);
    }

    public void validate(View view) {
        // Gestion des erreurs
        if (listeQuestion.get(intList.get(nQuestion - 1)).getTag().equals("Français")) {
            if (view.getId() != boutonVrai) {
                nbErreursFrancais++;
            }
            nbQuestionsFrancais++;
        } else if (listeQuestion.get(intList.get(nQuestion - 1)).getTag().equals("Anglais")) {
            if (view.getId() != boutonVrai) {
                nbErreursAnglais++;
            }
            nbQuestionsAnglais++;
        } else if (listeQuestion.get(intList.get(nQuestion - 1)).getTag().equals("Histoire")) {
            if (view.getId() != boutonVrai) {
                nbErreursHistoire++;
            }
            nbQuestionsHistoire++;
        } else if (listeQuestion.get(intList.get(nQuestion - 1)).getTag().equals("Géographie")) {
            if (view.getId() != boutonVrai) {
                nbErreursGeographie++;
            }
            nbQuestionsGeographie++;
        } else if (listeQuestion.get(intList.get(nQuestion - 1)).getTag().equals("Sciences")) {
            if (view.getId() != boutonVrai) {
                nbErreursSciences++;
            }
            nbQuestionsSciences++;
        } else if (listeQuestion.get(intList.get(nQuestion - 1)).getTag().equals("Mathématiques")) {
            if (view.getId() != boutonVrai) {
                nbErreursMaths++;
            }
            nbQuestionsMaths++;
        }
        nQuestion++;

        // Affichage de la bonne réponse
        for (int k = 0 ; k < listeBouton.size() ; k++) {
            if(listeBouton.get(k).getId() == boutonVrai) listeBouton.get(k).getBackground()
                    .setColorFilter(ContextCompat.getColor(this, R.color.correct), PorterDuff.Mode.MULTIPLY);

            else listeBouton.get(k).getBackground()
                    .setColorFilter(ContextCompat.getColor(this, R.color.incorrect), PorterDuff.Mode.MULTIPLY);
            listeBouton.get(k).setClickable(false);
        }
        choixEffectue = true;
    }

    public void initQuestions() {
        Question question1 = new Question (
                "Quelle période historique commence au Vème siècle ?",
                "L'Antiquité",
                "Les Temps Modernes",
                "L'Epoque Contemporaine",
                "Le Moyen-Âge",
                "Histoire"
        );
        question1.save();

        Question question2 = new Question (
                "Quelle événement marque le début du Moyen-Âge ?",
                "La mort de Jules César",
                "La bataille d'Alésia",
                "La naissance de Jésus-Christ",
                "La chute de l’Empire romain",
                "Histoire"
        );
        question2.save();

        Question question3 = new Question (
                "Qui est le roi des Francs ?",
                "Charlemagne",
                "Jule César",
                "Charles Martel",
                "Clovis",
                "Histoire"
        );
        question3.save();

        Question question4 = new Question (
                "Qui sont les descendants de Clovis ?",
                "Les Celtes",
                "Les Romains",
                "Les Carolingiens",
                "Les Mérovingiens",
                "Histoire"
        );
        question4.save();

        Question question5 = new Question (
                "Quelle dynastie commnence au VIIIème siècle ?",
                "Les Capétiens",
                "Les Romains",
                "Les Mérovingiens",
                "Les Carolingiens",
                "Histoire"
        );
        question5.save();

        Question question6 = new Question (
                "Quel roi repousse l'invasion des Sarrasins (Arabes), à Poitiers, en 732 ?",
                "Charlemagne",
                "Jule César",
                "Clovis",
                "Charles Martel",
                "Histoire"
        );
        question6.save();

        Question question7 = new Question (
                "Qui est le premier roi de la dynastie des Carolingiens ?",
                "Charlemagne",
                "Charles Martel",
                "Clovis",
                "Pépin le Bref",
                "Histoire"
        );
        question7.save();

        Question question8 = new Question (
                "Charles Martel, Charlemagne, Pépin le Bref sont respectivement : '",
                "Père, Fils, Grand-Père",
                "Grand-Père, Père, Fils",
                "N'ont aucune relation de parenté",
                "Grand-Père, Fils, Père",
                "Histoire"
        );
        question8.save();

        Question question9 = new Question  (
                "En quelle année Charlemagne devient empereur des Francs ?",
                "732",
                "771",
                "814",
                "800",
                "Histoire"
        );
        question9.save();

        Question question10 = new Question (
                "À la mort de Charlemagne, ses descendants partagent l’empire en trois empires :",
                "la Francie occidentale (à l'est), la Francie orientale (à l'ouest), la Lotharingie (au centre)",
                "la Francie occidentale (au centre), la Francie orientale (à l'est), la Lotharingie (à l'ouest)",
                "la Francie occidentale (à l'ouest), la Francie orientale (au centre), la Lotharingie (à l'est)",
                "la Francie occidentale (à l'ouest), la Francie orientale (à l'est), la Lotharingie (au centre)",
                "Histoire"
        );
        question10.save();

        Question question11 = new Question (
                "Quelle dynastie commennce lors du couronnement de Hugues Capet en roi des Francs ?",
                "Les Carolingiens",
                "Les Romains",
                "Les Mérovingiens",
                "Les Capétiens",
                "Histoire"
        );
        question11.save();

        Question question12 = new Question (
                "Qui a instauré la monarchie absolue au XVIIème siècle ?",
                "Louis XII (le père de Louis XIV)",
                "Napoléon I",
                "Napoléon III",
                "Louis XIV",
                "Histoire"
        );
        question12.save();

        Question question13 = new Question (
                "Quel siècle est considéré comme le siècle des Lumières ?",
                "Le XVIIème siècle",
                "Le XVIème siècle",
                "Le XVème siècle",
                "Le XVIIIème siècle",
                "Histoire"
        );
        question13.save();

        Question question14 = new Question (
                "En quelle année a eu lieu la capitulation de l'Allemagne nazie ?",
                "1914",
                "1918",
                "1939",
                "1945",
                "Histoire"
        );
        question14.save();

        Question question15 = new Question (
                "Suite aux mauvaises relations Américaines et Soviétiques, deux blocs se formés : ",
                "Le bloc de l'Est et le bloc du Nord",
                "Le bloc de l'Ouest et le bloc du Sud",
                "Le bloc du Nord et le bloc du Sud",
                "Le bloc de l'Est et le bloc de l'Ouest",
                "Histoire"
        );
        question15.save();

        Question question16 = new Question (
                "A quel bloc correspond le bloc Sovétique ?",
                "Le bloc du Nord",
                "Le bloc de l'Ouest",
                "Le bloc du Nord",
                "Le bloc de l'Est",
                "Histoire"
        );
        question16.save();

        Question question17 = new Question (
                "A quel bloc correspond le bloc Américain ?",
                "Le bloc du Nord",
                "Le bloc de l'Est",
                "Le bloc du Nord",
                "Le bloc de l'Ouest",
                "Histoire"
        );
        question17.save();

        Question question18 = new Question (
                "Comment s'appelle la période de fortes tensions géopolitiques entre les États-Unis et l'URSS ?",
                "La première guerre mondiale",
                "La deuxième guerre mondiale",
                "La guerre du Golf",
                "La guerre froide",
                "Histoire"
        );
        question18.save();

        Question question19 = new Question (
                "Comment s'appelle la période de fortes tensions géopolitiques entre les États-Unis et l'URSS ?",
                "La première guerre mondiale",
                "La deuxième guerre mondiale",
                "La guerre du Golf",
                "La guerre froide",
                "Histoire"
        );
        question19.save();

        Question question20 = new Question (
                "Quel événement marque le début de la fin de la guerre froide ?",
                "Le premier homme sur la Lune en 1969",
                "La crise de Cuba en 1962",
                "La fin de la guerre du Vietnam en 1975",
                "La chute du mur de Berlin en 1989",
                "Histoire"
        );
        question20.save();

        Question question21 = new Question (
                "Quelle est l'année de la fin de la guerre froide ?",
                "1945",
                "1979",
                "1989",
                "1991",
                "Histoire"
        );
        question21.save();

        Question question22 = new Question (
                "En quelle année a eu lieu la chute de l'URSS ?",
                "1945",
                "1960",
                "1979",
                "1991",
                "Histoire"
        );
        question22.save();

        Question question23 = new Question (
                "En quelle année la Constituion de la Vème République fut-elle adoptée ?",
                "1945",
                "1968",
                "1962",
                "1958",
                "Histoire"
        );
        question23.save();

        Question question24 = new Question (
                "Quel président de la République a fait adopter la Constituion de la Vème République ?",
                "Georges Pompidou",
                "Valéry Giscard d'Estaing",
                "François Mitterrand",
                "Charles de Gaulle",
                "Histoire"
        );
        question24.save();

        Question question25 = new Question (
                "Comment s'appelle la période de forte croissance économique et d'augmentation du niveau de vie qu'a connu la France ?",
                "La première guerre mondiale",
                "La seconde guerre mondiale",
                "L'entre-deux-guerres",
                "Les Trente Glorieuses",
                "Histoire"
        );
        question25.save();

        Question question26 = new Question (
                "Combien la France possède-t-elle de régions ?",
                "13",
                "22",
                "15",
                "18",
                "Géographie"
        );
        question26.save();

        Question question27 = new Question (
                "Comment s'appelle l'espace de libre circulation à l'intérieur de l'Union Européenne ?",
                "L'Europe de l'Est",
                "Les Balkans",
                "Le BéNeLux",
                "L'Espace Schengen",
                "Géographie"
        );
        question27.save();

        Question question28 = new Question (
                "Depuis 2003, comment appelle-t-on les territoires français situés en-dehors du territoire européen ?",
                "Les Antilles",
                "La Corse",
                "Les DOM-TOM",
                "Les DROM-COM",
                "Géographie"
        );
        question28.save();

        Question question29 = new Question (
                "Combien la France possède-t-elle de départements-régions d'outre-mer ?",
                "2",
                "3",
                "4",
                "5",
                "Géographie"
        );
        question29.save();

        Question question30 = new Question (
                "Quelle est la place de l'industrie française dans le monde ?",
                "Première",
                "Deuxième",
                "Troisième",
                "Quatrième",
                "Géographie"
        );
        question30.save();

        Question question31 = new Question (
                "Quelle sont les principales régions industrielles françaises ?",
                "L'Ile-de-France et la Provence-Alpes-Côte d'Azur",
                "L'Occitanie et la Provence-Alpes-Côte d'Azur",
                "Le Grand Est et l'Ile-de-France",
                "L’Ile-de-France et la région Auvergne-Rhône-Alpes",
                "Géographie"
        );
        question31.save();

        Question question32 = new Question (
                "En quelle année la Communauté Economique Européenne (CEE) fut-elle créée ?",
                "200",
                "1945",
                "1993",
                "1957",
                "Géographie"
        );
        question32.save();

        Question question33 = new Question (
                "Quelle communauté a été créée lors du Traité de Rome ?",
                "L'Espace Schengen",
                "L'Europe",
                "L'Union Européenne",
                "La Communauté Economique Européenne (CEE)",
                "Géographie"
        );
        question33.save();

        Question question34 = new Question (
                "Combien de pays la Communauté Economique Européenne avait-elle au départ ?",
                "8",
                "2",
                "4",
                "6",
                "Géographie"
        );
        question34.save();

        Question question35 = new Question (
                "Combien l'Union Européenne compte-t-elle de membres actuellement ?",
                "25",
                "26",
                "27",
                "28",
                "Géographie"
        );
        question35.save();

        Question question36 = new Question (
                "Combien un adulte possède-t-il de dents ?",
                "20",
                "24",
                "28",
                "32",
                "Sciences"
        );
        question36.save();

        Question question37 = new Question (
                "Quelle substance est produite par l'estomac ?",
                "La salive",
                "La bile",
                "Le suc pancréatique",
                "Le suc gastrique",
                "Sciences"
        );
        question37.save();

        Question question38 = new Question (
                "Au niveau de quel organe les aliments broyés passent-ils dans le sang ?",
                "L'estomac",
                "Le rein",
                "Le colon",
                "L'intestin grêle",
                "Sciences"
        );
        question38.save();

        Question question39 = new Question (
                "La respiration se manifeste par des mouvements de : ",
                "La bouche",
                "Des intestins",
                "Des membres inférieurs",
                "La cage thoracique",
                "Sciences"
        );
        question39.save();

        Question question40 = new Question (
                "L'échange gazeux se fait dans le notre corps se fait dans : ",
                "La trachée artère",
                "Les bronches",
                "Les brochioles",
                "Les alvéoles pulmonaires",
                "Sciences"
        );
        question40.save();

        Question question41 = new Question (
                "Lors de l'expiration, le sang se décharge en : ",
                "Azote",
                "Oxygène",
                "Hydrogène",
                "Gaz carbonique",
                "Sciences"
        );
        question41.save();

        Question question42 = new Question (
                "Lors de l'expiration, le sang se décharge en : ",
                "Azote",
                "Oxygène",
                "Hydrogène",
                "Gaz carbonique",
                "Sciences"
        );
        question42.save();

        Question question43 = new Question (
                "Comment appelle-t-on les planètes du Système solaire composées essentiellement de roche et de terre ?",
                "Petites planètes",
                "Planètoïdes",
                "Géantes gazeuses",
                "Planètes telluriques",
                "Sciences"
        );
        question43.save();

        Question question44 = new Question (
                "Comment appelle-t-on les planètes du Système solaire composées essentiellement de gaz ?",
                "Petites planètes",
                "Planètoïdes",
                "Planètes telluriques",
                "Géantes gazeuses",
                "Sciences"
        );
        question44.save();

        Question question45 = new Question (
                "Quelle est la distance moyenne au cours de l'année entre la Terre et le Soleil ?",
                "15 millions de kilomètres",
                "15 milliards de kilomètres",
                "150 milliards de kilomètres",
                "150 millions de kilomètres",
                "Sciences"
        );
        question45.save();

        Question question46 = new Question (
                "Combien de millimètres fait un décimètre ?",
                "1 000",
                "10",
                "10 000",
                "100",
                "Mathématiques"
        );
        question46.save();

        Question question47 = new Question (
                "Combien de centimètres fait un mètre ?",
                "1 000",
                "10",
                "10 000",
                "100",
                "Mathématiques"
        );
        question47.save();

        Question question48 = new Question (
                "Quelle propriété NE possède PAS le carré ?",
                "4 angles droits",
                "4 côtés égaux",
                "Des diagonales de même milieu, de même longueur et perpendiculaires",
                "Des côtés opposés non parallèles",
                "Mathématiques"
        );
        question48.save();

        Question question49 = new Question (
                "Combien il y a-t-il de secondes dans une heure ?",
                "60",
                "360",
                "600",
                "3 600",
                "Mathématiques"
        );
        question49.save();

        Question question50 = new Question (
                "Combien il y a-t-il environ de jours dans une année ?",
                "30",
                "24",
                "600",
                "365",
                "Mathématiques"
        );
        question50.save();

        Question question51 = new Question (
                "Combien il y a-t-il de kilos dans une tonne ?",
                "100",
                "10",
                "10 000",
                "1 000",
                "Mathématiques"
        );
        question51.save();

        Question question52 = new Question (
                "Quelle énoncé est vraie ?",
                "71,4 g = 7 140 dg",
                "71,4 g = 71 400 cg",
                "71,4 g = 714 000 mg",
                "71,4 g = 7,14 dag",
                "Mathématiques"
        );
        question52.save();

        Question question53 = new Question (
                "A quelle famille appartient le losange ?",
                "Les diagonales",
                "Les perpendiculaires",
                "Les parallèles",
                "Les parallélogrammes",
                "Mathématiques"
        );
        question53.save();

        Question question54 = new Question (
                "Un losange est un ?",
                "Triangle",
                "Pentagone",
                "Hexagone",
                "Quadrilatère",
                "Mathématiques"
        );
        question54.save();

        Question question55 = new Question (
                "Quelle propriété NE possède PAS le losange ?",
                "Les diagonales sont perpendiculaires",
                "Les diagonales se coupent en leur milieu",
                "Les diagonales sont les axes de symétrie",
                "Les diagonales sont de même longueur",
                "Mathématiques"
        );
        question55.save();

        Question question56 = new Question (
                "La fonction épithète est exercée par : ",
                "Les noms",
                "Les verbes",
                "Les adverbes",
                "Les adjectifs",
                "Français"
        );
        question56.save();

        Question question57 = new Question (
                "De quoi est composé un groupe nominal minimal ?",
                "D'un nom et d'un verbe",
                "D'un nom et d'un adjectif",
                "D'un nom et d'un adverbe",
                "D'un détérminant et d'un nom",
                "Français"
        );
        question57.save();

        Question question58 = new Question (
                "Quelle est la conjugaison, au présent de l'indicatif, du verbe dire à la deuxième personne du pluriel ?",
                "Vous disez",
                "Vous dire",
                "Vous disais",
                "Vous dites",
                "Français"
        );
        question58.save();

        Question question59 = new Question (
                "En quel genre et en quel nombre est accordé 'quels' dans la phrase 'Quels sont les élèves absents ?' ?",
                "Masculin singulier",
                "Féminin singulier",
                "Féminin pluriel",
                "Masculin pluriel",
                "Français"
        );
        question59.save();

        Question question60 = new Question (
                "Quelle figure de style assimile deux termes pour insister sur les rapports de ressemblance qui les unissent," +
                        " sans l'utilisation d'un mot comparatif ?",
                "La comparaison",
                "L'allégorie",
                "La personnification",
                "La métaphore",
                "Français"
        );
        question60.save();

        Question question61 = new Question (
                "Quelle figure de style désigne un objet, une idée ou un être en utilisant " +
                        "un autre mot qui lui est lié par une relation voisine, dans la langue ou dans la réalité ?",
                "La comparaison",
                "L'allégorie",
                "La personnification",
                "La métonymie",
                "Français"
        );
        question61.save();

        Question question62 = new Question (
                "Quelle figure de style consiste à dire peu pour exprimer davantage ?",
                "L'hyperbole",
                "L'anaphore",
                "L'euphémisme",
                "La litote",
                "Français"
        );
        question62.save();

        Question question63 = new Question (
                "Quelle figure de style consiste en l'utilisation de termes exagérés ?",
                "La litote",
                "L'anaphore",
                "L'euphémisme",
                "L'hyperbole",
                "Français"
        );
        question63.save();

        Question question64 = new Question (
                "Quelle figure de style cherche à atténuer une idée dont l'expression directe " +
                        "aurait quelque chose de déplaisant et de choquant ?",
                "La litote",
                "L'anaphore",
                "L'hyperbole",
                "L'euphémisme",
                "Français"
        );
        question64.save();

        Question question65 = new Question (
                "Quelle est terminaison des verbes à l'imparfait pour la première personne du pluriel ?",
                "–ais",
                "–iez",
                "–aient",
                "–ions",
                "Français"
        );
        question65.save();

        Question question66 = new Question (
                "Comment dit-on bonjour le matin ?",
                "Good afternoon",
                "Good evening",
                "Good night",
                "Good morning",
                "Anglais"
        );
        question66.save();

        Question question67 = new Question (
                "Comment dit-on 'quelqu'un' en Anglais ?",
                "Some",
                "Somewhere",
                "Something",
                "Someone",
                "Anglais"
        );
        question67.save();

        Question question68 = new Question (
                "Comment dit-on 'entendre' en Anglais ?",
                "To work",
                "To listen",
                "To know",
                "To hear",
                "Anglais"
        );
        question68.save();

        Question question69 = new Question (
                "Au présent quelle lettre doit-on ajouter à la fin du verbe, pour la troisième personne du singulier ?",
                "Un t",
                "Un e",
                "Un r",
                "Un s",
                "Anglais"
        );
        question69.save();

        Question question70 = new Question (
                "Comment dit-on 'quinze' en Anglais ?",
                "Fifty",
                "Fifti",
                "Fiftin",
                "Fifteen",
                "Anglais"
        );
        question70.save();

        Question question71 = new Question (
                "Comment dit-on 'peut-être' en Anglais ?",
                "Always",
                "Sometimes",
                "After",
                "Maybe",
                "Anglais"
        );
        question71.save();

        Question question72 = new Question (
                "Comment dit-on 'deux pieds' en Anglais ?",
                "Two foot",
                "Two foots",
                "Two feets",
                "Two feet",
                "Anglais"
        );
        question72.save();

        Question question73 = new Question (
                "Comment dit-on 'Go straight on' en Français ?",
                "Tourner à droite",
                "Tourner à gauche",
                "Revenir en arrière",
                "Aller tout droit",
                "Anglais"
        );
        question73.save();

        Question question74 = new Question (
                "Comment dit-on 'jouer' en Anglais ?",
                "To shine",
                "To sing",
                "To pass",
                "To play",
                "Anglais"
        );
        question74.save();

        Question question75 = new Question (
                "Quel est le pluriel de 'enfant' en Anglais ?",
                "Child",
                "Childs",
                "Childrens",
                "Children",
                "Anglais"
        );
        question75.save();
    }

    public void nextQuestion(View view) {

        // Si aucune réponse n'est donnée
        if (!choixEffectue) {
            Toast.makeText(this, "Tu dois choisir une réponse à cette question !", Toast.LENGTH_SHORT).show();
        } else {
            choixEffectue = false;

            // Remise à zéro des boutons
            for (int k = 0 ; k < listeBouton.size() ; k++) {
                listeBouton.get(k).getBackground().clearColorFilter();
                listeBouton.get(k).setClickable(true);
            }

            // On passe à la question suivante
            if (nQuestion <= 20) {
                listeBouton.clear();
                TextView text = (TextView) findViewById(R.id.textViewQuestion);
                text.setText(listeQuestion.get(intList.get(nQuestion - 1)).getIntitule());
                Button boutonR1 = (Button) findViewById(R.id.button1);
                Button boutonR2 = (Button) findViewById(R.id.button2);
                Button boutonR3 = (Button) findViewById(R.id.button3);
                Button boutonR4 = (Button) findViewById(R.id.button4);
                TextView numeroQuestion = (TextView) findViewById(R.id.textViewQuestionNumber);
                numeroQuestion.setText(nQuestion + "/20");
                ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
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

                if (nQuestion == 20) {
                    Button boutonContinuer = findViewById(R.id.buttonNext);
                    boutonContinuer.setText("Résultats");
                }
            } else {
                Question.deleteAll(Question.class);
                Intent intent = new Intent(this, QuizResultsActivity.class);
                startActivity(intent);
            }
        }
    }
}