package com.bvd.android.financemanager.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bvd.android.financemanager.R;
import com.bvd.android.financemanager.Utils;
import com.bvd.android.financemanager.model.Expense;

/**
 * Created by bara on 07.11.2017.
 */

public class DetailedExpenseActivity extends AppCompatActivity {
    private  Utils utils;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_expense);
        Intent intent = getIntent();
        Expense extras = (Expense) intent.getExtras().getSerializable("expense");
        EditText category = findViewById(R.id.categoryEditText);
        EditText name = findViewById(R.id.nameEditText);
        EditText price = findViewById(R.id.priceEditText);
        EditText purchaseDate = findViewById(R.id.purchaseDateEditText);
        TextView id = findViewById(R.id.idTextView);

        utils = new Utils();

        category.setText(extras.getCategory());
        name.setText(extras.getName());
        price.setText(extras.getPrice().toString());
        purchaseDate.setText(utils.getFormattedDate(extras.getPurchaseDate()));
        id.setText(String.valueOf(extras.getId()));

    }
}
