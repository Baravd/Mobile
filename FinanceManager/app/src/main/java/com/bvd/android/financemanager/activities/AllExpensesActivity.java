package com.bvd.android.financemanager.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.bvd.android.financemanager.R;
import com.bvd.android.financemanager.adapters.ExpenseAdapter;
import com.bvd.android.financemanager.dao.ExpensesDao;
import com.bvd.android.financemanager.model.Expense;
import javax.inject.Inject;


import java.util.List;

public class AllExpensesActivity extends AppCompatActivity {
    private ListView listView;
    private List<Expense> expenses;
    @Inject
    public ExpensesDao expensesDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_expenses);
       // expensesDao = new ExpensesDao();
        expenses = expensesDao.getAll();
        listView = findViewById(R.id.allExpensesListView);

        final ExpenseAdapter expenseArrayAdapter = new ExpenseAdapter(this, expenses);
        listView.setAdapter(expenseArrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Expense expense = expenseArrayAdapter.getItem(i);
                Intent intent = new Intent(view.getContext(), DetailedExpenseActivity.class);
                intent.putExtra("expense", expense);
                startActivity(intent);
            }
        });
    }
}
