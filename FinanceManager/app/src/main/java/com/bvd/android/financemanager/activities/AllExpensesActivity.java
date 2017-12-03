package com.bvd.android.financemanager.activities;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.bvd.android.financemanager.R;
import com.bvd.android.financemanager.R2;
import com.bvd.android.financemanager.adapters.ExpenseAdapter;
import com.bvd.android.financemanager.dao.ExpensesDao;
import com.bvd.android.financemanager.model.Expense;

import javax.inject.Inject;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AllExpensesActivity extends AppCompatActivity {
    @BindView(R.id.allExpensesListView)
    public ListView listView;

    @BindView(R.id.addExpenseBtn)
    public FloatingActionButton addBtn;

    private List<Expense> expenses;
    private ExpensesDao expensesDao;
    private ExpenseAdapter expenseArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_expenses);
        ButterKnife.bind(this);

        expensesDao = new ExpensesDao();
        expenses = expensesDao.getAll();
        // listView = findViewById();

        expenseArrayAdapter = new ExpenseAdapter(this, expenses);
        listView.setAdapter(expenseArrayAdapter);

        //todo adapterul sa fie injectat dupa ce e configurat daggerul si adaugat @Onclik
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
    //id-urile sunt la nivel de activiry sau global?
    @OnClick(R.id.addExpenseBtn) void addNewExpense(){
        Intent addFormView = new Intent(this,AddExpenseActivity.class);
        addFormView.putExtra("bd","nimic");
        startActivity(addFormView);
    }


}
