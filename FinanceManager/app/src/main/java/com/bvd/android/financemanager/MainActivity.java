package com.bvd.android.financemanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.bvd.android.financemanager.activities.AllExpensesActivity;
import com.bvd.android.financemanager.activities.MailActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button showExpensesBtn = findViewById(R.id.showAllExpensesBtn);
        final Button mailBtn = findViewById(R.id.emailActivityBtn);

        showExpensesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAllExpenses(view);
            }
        });
        mailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMailActivity();
            }
        });

    }

    public void showAllExpenses(View view) {
        Intent intent = new Intent(this, AllExpensesActivity.class);
        startActivity(intent);
    }

    public void showMailActivity() {
        Intent intent = new Intent(this, MailActivity.class);
        startActivity(intent);
    }

}
