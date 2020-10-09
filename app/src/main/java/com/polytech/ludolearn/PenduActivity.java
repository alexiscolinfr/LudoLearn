package com.polytech.ludolearn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Random;

public class PenduActivity extends AppCompatActivity {

    private String motEntier;
    private String motDecoupeClair = "";
    private String motDecoupeCache = "";
    private int nbErreurs = 0;
    private Boolean activityCategoriePendu = true;

    private HashMap<String, String> listeMotHistoire  = new HashMap<>();
    private HashMap<String, String> listeMotGeographie  = new HashMap<>();
    private HashMap<String, String> listeMotFrancais  = new HashMap<>();
    private HashMap<String, String> listeMotMaths  = new HashMap<>();
    private HashMap<String, String> listeMotSport  = new HashMap<>();
    private HashMap<String, String> listeMotArtPlastique  = new HashMap<>();
    private HashMap<String, String> listeMotAnglais  = new HashMap<>();
    private HashMap<String, String> listeMotMusique  = new HashMap<>();
    private HashMap<String, String> listeMotUtilisee  = new HashMap<>();

    TextView motPendu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categorie_pendu);
        initListeMots();
    }

    // CHOISI UN MOT AU HASARD DANS LA CATEGORIE CHOISIE
    public void randMot(int buttonId){
        motPendu = findViewById(R.id.motADeviner);
        switch(buttonId){
            case R.id.categorieHistoire :
                listeMotUtilisee = listeMotHistoire;
                break;
            case R.id.categorieGeographie :
                listeMotUtilisee = listeMotGeographie;
                break;
            case R.id.categorieFrancais :
                listeMotUtilisee = listeMotFrancais;
                break;
            case R.id.categorieMaths :
                listeMotUtilisee = listeMotMaths;
                break;
            case R.id.categorieSport :
                listeMotUtilisee = listeMotSport;
                break;
            case R.id.categorieArtPlastique :
                listeMotUtilisee = listeMotArtPlastique;
                break;
            case R.id.categorieAnglais :
                listeMotUtilisee = listeMotAnglais;
                break;
            case R.id.categorieMusique :
                listeMotUtilisee = listeMotMusique;
                break;
        }

        // RANDOMISATION DU MOT DU PENDU
        Object[] motsPossibles = listeMotUtilisee.keySet().toArray();
        Object cle = motsPossibles[new Random().nextInt(motsPossibles.length)];
        motEntier = cle.toString();

        // CREATION DES VARIABLES A UTILISER POUR LE PENDU
        for(int i = 0; i < motEntier.length(); i ++){
            motDecoupeClair += motEntier.charAt(i) + "-";
        }
        motDecoupeClair = motDecoupeClair.substring(0, motDecoupeClair.length() - 1); // ON ENLEVE LE DERNIER CARACTERE "-"

        String separateur1 = "-";
        for(int i = 0; i < motDecoupeClair.length(); i ++){
            if(motDecoupeClair.charAt(i) == separateur1.charAt(0)) motDecoupeCache += "-";
            else motDecoupeCache += "_";
        }

        motPendu.setText(suppSeparateur(motDecoupeCache));
    }

    // VERIFIE SI LA LETTRE CLIQUEE FAIT PARTIE DU MOT A DEVINER
    public void letterVerification(View view) {
        Button b = (Button) view;
        if(nbErreurs < 11) {
            String lettre = b.getText().toString();

            if (motDecoupeClair.contains(lettre)) {
                String strMotDecoupeCacheTemp = "";
                for (int i = 0; i < motDecoupeClair.length(); i++) {
                    if (motDecoupeClair.charAt(i) == lettre.charAt(0)) strMotDecoupeCacheTemp += motDecoupeClair.charAt(i);
                    else strMotDecoupeCacheTemp += motDecoupeCache.charAt(i);
                }
                motDecoupeCache = strMotDecoupeCacheTemp;

                b.setTextColor(Color.parseColor("#069335"));
                b.setClickable(false);
                b.getBackground().setColorFilter(ContextCompat.getColor(this, R.color.correct), PorterDuff.Mode.MULTIPLY);
                motPendu.setText(suppSeparateur(motDecoupeCache));

                // SI LE MOT EST TROUVE ENTIEREMENT
                if(motDecoupeCache.equals(motDecoupeClair)){

                    TextView definition = (TextView) findViewById(R.id.textDefinition);
                    definition.setText(listeMotUtilisee.get(motEntier));

                    finPartie();
                }
            }

            else {
                b.getBackground().setColorFilter(ContextCompat.getColor(this, R.color.incorrect), PorterDuff.Mode.MULTIPLY);
                b.setClickable(false);
                TextView erreurs = findViewById(R.id.textCoupsRestants);
                nbErreurs += 1;
                erreurs.setText("Erreur(s): " + nbErreurs);
                ImageView imagePendu = findViewById(R.id.imagePendu);
                switch (nbErreurs) {
                    case 1:
                        imagePendu.setImageResource(R.drawable.pendu1);
                        break;
                    case 2:
                        imagePendu.setImageResource(R.drawable.pendu2);
                        break;
                    case 3:
                        imagePendu.setImageResource(R.drawable.pendu3);
                        break;
                    case 4:
                        imagePendu.setImageResource(R.drawable.pendu4);
                        break;
                    case 5:
                        imagePendu.setImageResource(R.drawable.pendu5);
                        break;
                    case 6:
                        imagePendu.setImageResource(R.drawable.pendu6);
                        break;
                    case 7:
                        imagePendu.setImageResource(R.drawable.pendu7);
                        break;
                    case 8:
                        imagePendu.setImageResource(R.drawable.pendu8);
                        break;
                    case 9:
                        imagePendu.setImageResource(R.drawable.pendu9);
                        break;
                    case 10:
                        imagePendu.setImageResource(R.drawable.pendu10);
                        break;
                    case 11:
                        imagePendu.setImageResource(R.drawable.game_over);
                        motPendu.setText(motEntier);

                        TextView definition = findViewById(R.id.textDefinition);
                        definition.setText(listeMotUtilisee.get(motEntier));

                        finPartie();
                        break;
                }
            }
        }
    }

    // AJOUTE LES MOTS ET DEFINITIONS AUX CATEGORIES
    public void initListeMots(){
        listeMotHistoire.put("NAPOLEON", "Napoléon est le premier empereur des Français (");
        listeMotHistoire.put("REVOLUTION", "Une révolution est un renversement brusque d’un régime politique par la force.");
        listeMotHistoire.put("PYRAMIDE", "Les pyramides d'Égypte sont les restes les plus impressionnants et les plus emblématiques de cette civilisation.");
        listeMotGeographie.put("CONTINENT", "Ce terme désigne une vaste étendue continue du sol à la surface du globe terrestre. Il y a 7 continents sur Terre.");
        listeMotGeographie.put("EUROPE", "C'est un des continents, la France en fait partie, ainsi que l'Allemagne, la Belgique et bien d'autres... ");
        listeMotGeographie.put("OCEANIE", "C'est le plus petit continent");
        listeMotFrancais.put("GRAMMAIRE", "C'est l'ensemble des règles à suivre pour parler et écrire correctement une langue.");
        listeMotFrancais.put("CONJUGAISON", "C'est l'ensemble des formes verbales suivant les voix, les modes, les temps, les personnes, les nombres.");
        listeMotFrancais.put("DICTIONNAIRE", "Recueil contenant des mots, des expressions d'une langue, présentés dans un ordre convenu, et qui donne des définitions, des informations sur eux.");
        listeMotMaths.put("RACINECARREE", "En mathématiques élémentaires, la racine carrée d'un nombre réel positif x est l'unique réel positif qui, lorsqu'il est multiplié par lui-même, donne x, c'est-à-dire le nombre positif dont le carré vaut x.");
        listeMotMaths.put("DIVISION", "Symbolisé par le symbole '/'");
        listeMotMaths.put("MULTIPLICATION", "Symbolisé par le symbole 'x'");
        listeMotSport.put("ESCALADE", "Sport qui consiste à grimper sur des obstacles");
        listeMotSport.put("SKI", "Sport d'hiver / Longue lame relevée à l'avant, placée sous le pied pour glisser sur la neige.");
        listeMotSport.put("FOOTBALL", "Sport opposant deux équipes de onze joueurs, où il faut faire pénétrer un ballon rond dans les buts adverses sans utiliser les mains.");
        listeMotArtPlastique.put("PEINTURE", "La peinture représentation, suggestion du monde visible ou imaginaire sur une surface plane au moyen de couleurs.");
        listeMotArtPlastique.put("PICASSO", "Pablo Ruiz Picasso est un peintre, dessinateur, sculpteur et graveur espagnol.");
        listeMotArtPlastique.put("CROQUIS", "Dessin, esquisse rapide.");
        listeMotAnglais.put("WORK", "An activity, such as a job");
        listeMotAnglais.put("HOUSE", "A building for human habitation.");
        listeMotAnglais.put("MARKET", "An actual or nominal place where forces of demand and supply operate, and where buyers and sellers interact.");
        listeMotMusique.put("FLUTE", "C'est un instrument de musique à vent.");
        listeMotMusique.put("VIOLON", "C'est un instrument de musique composé de 4 cordes.");
        listeMotMusique.put("MOZART", "Wolfgang Amadeus Mozart est un compositeur de musique classique");
    }

    // APPELLE AU CLIC D'UN BOUTON DE CATEGORIE
    // DEFINI LA CATEGORIE UTILISEE POUR LE PENDU
    public void choixCategorie(View view) {
        Button b = (Button) view;
        setContentView(R.layout.activity_pendu);
        randMot(view.getId());
        TextView textCategorie = findViewById(R.id.textCategorie);
        textCategorie.setText("Catégorie: " + b.getText().toString());
        activityCategoriePendu = false;
    }

    @Override
    public void onBackPressed()
    {
        motEntier = "";
        nbErreurs = 0;
        motDecoupeClair = "";
        motDecoupeCache = "";

        if(!activityCategoriePendu){
            setContentView(R.layout.categorie_pendu);
            activityCategoriePendu = true;
        }
        else{
            Intent intent = new Intent(this, ChoixExerciceActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    }

    public void finPartie(){
        LinearLayout layout = findViewById(R.id.layout_A_F);
        for(int i = 0; i < layout.getChildCount(); i++){
            layout.getChildAt(i).setClickable(false);
        }
        layout.setVisibility(View.GONE);

        layout = findViewById(R.id.layout_G_L);
        for(int i = 0; i < layout.getChildCount(); i++){
            layout.getChildAt(i).setClickable(false);
        }
        layout.setVisibility(View.GONE);

        layout = findViewById(R.id.layout_M_R);
        for(int i = 0; i < layout.getChildCount(); i++){
            layout.getChildAt(i).setClickable(false);
        }
        layout.setVisibility(View.GONE);

        layout = findViewById(R.id.layout_S_X);
        for(int i = 0; i < layout.getChildCount(); i++){
            layout.getChildAt(i).setClickable(false);
        }
        layout.setVisibility(View.GONE);

        layout = findViewById(R.id.layout_Y_Z);
        for(int i = 0; i < layout.getChildCount(); i++){
            layout.getChildAt(i).setClickable(false);
        }
        layout.setVisibility(View.GONE);

        Button boutonRecommencer = findViewById(R.id.boutonRecommencer);
        boutonRecommencer.setVisibility(View.VISIBLE);
    }

    public String suppSeparateur(String arg){
        String res = "";
        String separateur = "-";
        for (int i = 0; i < arg.length(); i++) {
            if (arg.charAt(i) == separateur.charAt(0)) res += " ";
            else res += arg.charAt(i);
        }
        return res;
    }

    public void restartPendu(View view) {
        motEntier = "";
        nbErreurs = 0;
        motDecoupeClair = "";
        motDecoupeCache = "";

        setContentView(R.layout.categorie_pendu);
        activityCategoriePendu = true;
    }
}