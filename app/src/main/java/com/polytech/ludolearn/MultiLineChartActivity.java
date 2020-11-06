package com.polytech.ludolearn;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.orm.query.Condition;
import com.orm.query.Select;
import com.polytech.ludolearn.database.Resultat;

import java.util.ArrayList;
import java.util.List;

public class MultiLineChartActivity extends AppCompatActivity {

    private LineChart chart;
    private String adresseMail,jeu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_line_chart);

        Bundle extras = getIntent().getExtras();
        assert extras != null;
        adresseMail = extras.getString("adresseMail");
        jeu = extras.getString("jeu");

        setTitle("Résultats - " + jeu);

        chart = findViewById(R.id.chart1);

        chart.setDrawGridBackground(true);
        chart.getDescription().setEnabled(false);
        chart.setDrawBorders(false);

        chart.getAxisLeft().setEnabled(false);
        chart.getAxisRight().setDrawAxisLine(false);
        chart.getAxisRight().setDrawGridLines(true);
        chart.getXAxis().setDrawAxisLine(false);
        chart.getXAxis().setDrawGridLines(false);

        chart.setTouchEnabled(true);

        chart.setDragEnabled(true);
        chart.setScaleEnabled(true);

        chart.setPinchZoom(false);

        Legend l = chart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);

        setData();
        chart.animateX(750);
    }

    private void setData() {

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();

        List<Resultat> resultats = Select.from(Resultat.class).where(Condition.prop("adresse_mail").eq(adresseMail),Condition.prop("jeu").eq(jeu)).list();

        ArrayList<Entry> values = new ArrayList<>();

        for (int i = 0; i < resultats.size(); i++) {
            int val = resultats.get(i).getNote();
            values.add(new Entry(i, val));
        }

        LineDataSet d = new LineDataSet(values, "Scores");
        d.setLineWidth(3f);
        d.setCircleRadius(4f);

        int color = ColorTemplate.VORDIPLOM_COLORS[2];
        d.setColor(color);
        d.setCircleColor(color);
        dataSets.add(d);


        LineData data = new LineData(dataSets);
        data.setValueTextSize(8f);
        chart.setData(data);
        chart.invalidate();
    }
}