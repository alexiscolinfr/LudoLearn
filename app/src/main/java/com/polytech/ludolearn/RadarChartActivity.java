package com.polytech.ludolearn;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet;
import com.orm.query.Condition;
import com.orm.query.Select;
import com.polytech.ludolearn.database.Resultat;

import java.util.ArrayList;
import java.util.List;

public class RadarChartActivity extends AppCompatActivity {

    private RadarChart chart;
    protected Typeface tfLight;
    private String adresseMail,jeu;
    private String[] indexAxisValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radar_chart);

        Bundle extras = getIntent().getExtras();
        assert extras != null;
        adresseMail = extras.getString("adresseMail");
        jeu = extras.getString("jeu");

        setParam();
        setTitle("Résultats - " + jeu);

        tfLight = Typeface.createFromAsset(getAssets(), "OpenSans-Light.ttf");

        chart = findViewById(R.id.chart1);
        chart.setBackgroundColor(Color.rgb(100, 100, 100));
        chart.setRotationEnabled(false);
        chart.getDescription().setEnabled(false);

        chart.setWebLineWidth(1f);
        chart.setWebColor(Color.LTGRAY);
        chart.setWebLineWidthInner(1f);
        chart.setWebColorInner(Color.LTGRAY);
        chart.setWebAlpha(100);

        setData();

        chart.animateXY(1400, 1400, Easing.EaseInOutCirc);

        XAxis xAxis = chart.getXAxis();
        xAxis.setTypeface(tfLight);
        xAxis.setTextSize(9f);
        xAxis.setYOffset(0f);
        xAxis.setXOffset(0f);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(){
            private final String[] mActivities = indexAxisValues;
            @Override
            public String getFormattedValue(float value) {
                return mActivities[(int) value % mActivities.length];
            }
        });
        xAxis.setTextColor(Color.WHITE);

        YAxis yAxis = chart.getYAxis();
        yAxis.setTypeface(tfLight);
        yAxis.setLabelCount(indexAxisValues.length, false);
        yAxis.setTextSize(9f);
        yAxis.setAxisMinimum(0f);
        yAxis.setAxisMaximum(10f);
        yAxis.setDrawLabels(false);

        Legend l = chart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setTypeface(tfLight);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(5f);
        l.setTextColor(Color.WHITE);
    }

    private void setParam() {
        String[] quizIndexAxisValues = {"Français", "Anglais", "Histoire", "Géographie", "Sciences", "Maths"};
        String[] mathsIndexAxisValues = {"Additions", "Soustractions", "Multiplications ", "Divisions"};

        if (jeu.equals("Quiz"))
            indexAxisValues = quizIndexAxisValues;
        else if (jeu.equals("Maths"))
            indexAxisValues = mathsIndexAxisValues;
    }

    private void setData() {

        int cnt = indexAxisValues.length;

        int[] sumQuiz = new int[6];
        int[] nbQuiz = new int[6];
        int[] sumMaths = new int[4];
        int[] nbMaths = new int[4];

        ArrayList<RadarEntry> entries = new ArrayList<>();

        List<Resultat> resultats = Select.from(Resultat.class).where(Condition.prop("adresse_mail").eq(adresseMail),Condition.prop("jeu").eq(jeu)).list();

        for (Resultat resultat : resultats){
            switch (resultat.getCategorie()){
                case "Français": sumQuiz[0] += resultat.getNote(); nbQuiz[0] += 1;break;
                case "Anglais": sumQuiz[1] += resultat.getNote(); nbQuiz[1] += 1;break;
                case "Histoire": sumQuiz[2] += resultat.getNote(); nbQuiz[2] += 1;break;
                case "Géographie": sumQuiz[3] += resultat.getNote(); nbQuiz[3] += 1;break;
                case "Sciences": sumQuiz[4] += resultat.getNote(); nbQuiz[4] += 1;break;
                case "Mathématiques": sumQuiz[5] += resultat.getNote(); nbQuiz[5] += 1;break;
                case "addition": sumMaths[0] += resultat.getNote(); nbMaths[0] += 1;break;
                case "soustraction": sumMaths[1] += resultat.getNote(); nbMaths[1] += 1;break;
                case "multiplication": sumMaths[2] += resultat.getNote(); nbMaths[2] += 1;break;
                case "division": sumMaths[3] += resultat.getNote(); nbMaths[3] += 1;break;
            }
        }

        for (int i = 0; i < cnt; i++) {
            if(jeu.equals("Quiz") && nbQuiz[i] >0){
                float avgQuiz = (float) sumQuiz[i] / nbQuiz[i];
                entries.add(new RadarEntry(avgQuiz));
            }
            else if(jeu.equals("Maths") && nbMaths[i] >0){
                float avgMaths = (float) sumMaths[i] / nbMaths[i];
                entries.add(new RadarEntry(avgMaths));
            }
        }

        RadarDataSet set1 = new RadarDataSet(entries, "Moyenne des résultats");
        set1.setColor(Color.rgb(121, 162, 175));
        set1.setFillColor(Color.rgb(121, 162, 175));
        set1.setDrawFilled(true);
        set1.setFillAlpha(180);
        set1.setLineWidth(2f);
        set1.setDrawHighlightCircleEnabled(true);
        set1.setDrawHighlightIndicators(false);

        ArrayList<IRadarDataSet> sets = new ArrayList<>();
        sets.add(set1);

        RadarData data = new RadarData(sets);
        data.setValueTypeface(tfLight);
        data.setValueTextSize(8f);
        data.setDrawValues(true);
        data.setValueTextColor(Color.WHITE);

        chart.setData(data);
        chart.invalidate();
    }
}