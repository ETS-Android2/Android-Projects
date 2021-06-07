package com.example.assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.ArrayList;

public class DisplayScore extends AppCompatActivity {

    BarChart barChart;
    int score;
    Bundle username;
    StudentDBHelper studentDBHelper;
    TextView scoreText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_score);
        getSupportActionBar().hide();
        username = getIntent().getExtras();
        score = username.getInt("score");

        barChart = (BarChart) findViewById(R.id.fragment_verticalbarchart_chart);
        //The x-axis labels
        ArrayList<String> students = new ArrayList<>();
        students.add(username.getString("user"));

        ArrayList<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(0,username.getInt("score") ));

        BarDataSet barDataSet = new BarDataSet(barEntries, "Points");

        BarData theData = new BarData();
        theData.addDataSet(barDataSet);
        barChart.setData(theData);

        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(students));

        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawAxisLine(false);
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1f);
        xAxis.setLabelCount(students.size());
        barChart.animateX(2000);//delay to plot
    }
}