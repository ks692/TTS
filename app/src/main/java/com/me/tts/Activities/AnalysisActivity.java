package com.me.tts.Activities;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.me.tts.R;

import java.util.ArrayList;

public class AnalysisActivity extends AppCompatActivity {

    LineChart mChart;
    PieChart pieChart;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis);

        mChart = (LineChart) findViewById(R.id.linechart);
        pieChart = (PieChart) findViewById(R.id.piechart);
        spinner = (Spinner) findViewById(R.id.spinner);
        final String[] paths = {"line Chart", "pie Chart"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(AnalysisActivity.this,
                android.R.layout.simple_spinner_item, paths);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        pieChart.setVisibility(View.GONE);
                        mChart.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        pieChart.setVisibility(View.VISIBLE);
                        mChart.setVisibility(View.GONE);
                        break;


                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner.setAdapter(adapter);
        pieChart.setVisibility(View.GONE);

    }

    void createLineChart(ArrayList<String> x, ArrayList<Float> y) {
        final ArrayList<String> k = x;
        final ArrayList<Float> k1 = y;
        ArrayList<Entry> entries = new ArrayList<>();
        for (int i = 0; i < y.size(); i++) {
            entries.add(new Entry(i, y.get(i)));
        }


        LineDataSet dataSet = new LineDataSet(entries, "Dates");

        dataSet.setColor(ContextCompat.getColor(this, R.color.colorPrimary));

        dataSet.setValueTextColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        XAxis xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);

        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {

                int temp = (int) value;
                return k.get(temp);
            }

        });
        YAxis yAxisRight = mChart.getAxisRight();
        yAxisRight.setEnabled(false);
        YAxis yAxisLeft = mChart.getAxisLeft();
        yAxisLeft.setGranularity(1f);
        LineData data = new LineData(dataSet);
        mChart.setData(data);
        Description description = new Description();
        description.setTextColor(Color.BLACK);
        description.setText("Chart Data");
        mChart.setDescription(description);
        mChart.animateX(2500);
        mChart.invalidate();
    }

    void createPieChart( ArrayList<Float> yvals) {
        ArrayList<String> xVals = new ArrayList<String>();

        xVals.add("Positive");
        xVals.add("Negative");
        xVals.add("Neutral");


        ArrayList<PieEntry> yvalues = new ArrayList<>();
        float check=0;
        for (int i = 0; i < yvals.size(); i++) {
            check=check+yvals.get(i);
            yvalues.add(new PieEntry(yvals.get(i), xVals.get(i)));
        }
        if(check>100){
            return;
        }
        if(check<100){
            yvalues.add(new PieEntry(100-check,"others"));
        }
        PieDataSet dataSet = new PieDataSet(yvalues, "Overall Sentiment");
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS
        );

        Description description = new Description();
        description.setTextColor(Color.BLACK);
        description.setText("Chart Data");
        pieChart.setDescription(description);
        PieData data = new PieData(dataSet);
        data.setValueTextSize(13f);
        data.setValueTextColor(Color.DKGRAY);
        data.setValueFormatter(new PercentFormatter());
        pieChart.setData(data);
    }



}