package com.bvd.android.financemanager.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bvd.android.financemanager.R;
import com.bvd.android.financemanager.dao.AppDatabase;
import com.bvd.android.financemanager.dao.ExpensesDao;
import com.bvd.android.financemanager.dao.IExpenseDao;
import com.bvd.android.financemanager.model.Categories;
import com.bvd.android.financemanager.model.Expense;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.LargeValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StatisticsActivity extends AppCompatActivity {

    @BindView(R.id.bargraph)
    public BarChart barChart;
    private ArrayList<BarEntry> barEntries;
    private AppDatabase appDatabase;
    private IExpenseDao iExpenseDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        ButterKnife.bind(this);
        appDatabase = AppDatabase.getAppDatabase(this);
        iExpenseDao = appDatabase.iExpenseDao();

        int count = 0;
        ArrayList<String> labels = new ArrayList<>();
        for (Categories c : Categories.values()) {
            labels.add(String.valueOf(c));
            //barEntries.add(new BarEntry(44f,count));
            //count++;
        }


        barEntries = generateBarEntries();
        BarDataSet barDataSet = new BarDataSet(barEntries, "Percentage");
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);


        BarData data = new BarData(barDataSet);

        barChart.setData(data);
        barChart.setScaleEnabled(true);
        barChart.setFitBars(true);
        barChart.getXAxis().setDrawGridLines(false);
        barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(labels));


    }

    ArrayList<BarEntry> generateBarEntries() {
        ArrayList<BarEntry> barEntries = new ArrayList<>();
        int count = 0;
        for (Categories c : Categories.values()) {
            int size = iExpenseDao.getByCategory(String.valueOf(c)).size();
            barEntries.add(new BarEntry(count, size));
            count++;
        }


        return barEntries;
    }
}
