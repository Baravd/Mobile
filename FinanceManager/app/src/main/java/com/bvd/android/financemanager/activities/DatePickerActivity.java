package com.bvd.android.financemanager.activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.DatePicker;

import com.bvd.android.financemanager.R;
import com.bvd.android.financemanager.Utils;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DatePickerActivity extends AppCompatActivity {
    private static final String TAG = DatePickerActivity.class.getName();
    private Date selectedDate;

    @BindView(R.id.datePicker)
    public DatePicker datePicker;

    @BindView(R.id.returnDateBtn)
    public Button returnDateBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_picker);
        ButterKnife.bind(this);


    }

    @OnClick(R.id.returnDateBtn)
    public void returnDate() {
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year = datePicker.getYear();
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        selectedDate = calendar.getTime();

        Intent resultIntent = new Intent();
        Utils utils = new Utils();
        String date = utils.getFormattedDate(selectedDate);
        resultIntent.putExtra("selectedDate", date);
        setResult(Activity.RESULT_OK, resultIntent);

        Log.v(TAG, "Selected date=" + selectedDate);

        finish();


    }

    @Override
    public void onBackPressed(){
        Intent resultIntent = new Intent();
        Utils utils = new Utils();
        String date = utils.getFormattedDate(new Date());
        resultIntent.putExtra("selectedDate", date);
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }
}
