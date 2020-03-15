package com.example.hp.filez;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class ree extends AppCompatActivity {
TextView t1;
TextView t2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ree);
        t1=findViewById(R.id.tv);
        t2=findViewById(R.id.tv1);
        PieChart pieChart = findViewById(R.id.piechart);
        ArrayList NoOfEmp = new ArrayList();
        SharedPreferences sp =
                getSharedPreferences
                        ("mycredentials",
                                Context.MODE_PRIVATE);
        int name = Integer.parseInt(sp.getString
                ("students","NA"));
        int name1=Integer.parseInt(sp.getString("faculty","NA"));
        t1.setText("Number of students"+name);
        t2.setText("Number of Faculties"+name1);
        NoOfEmp.add(new Entry(name, 1));
        NoOfEmp.add(new Entry(name1, 3));

        PieDataSet dataSet = new PieDataSet(NoOfEmp, "Number Of Students");

        ArrayList year = new ArrayList();

        year.add("faculty");
        year.add("Students");

        PieData data = new PieData(year, dataSet);
        pieChart.setData(data);
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieChart.animateXY(5000, 5000);
    }
}