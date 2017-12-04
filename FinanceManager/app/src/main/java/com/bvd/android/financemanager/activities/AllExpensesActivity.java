package com.bvd.android.financemanager.activities;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.bvd.android.financemanager.R;
import com.bvd.android.financemanager.adapters.ExpenseAdapter;
import com.bvd.android.financemanager.dao.AppDatabase;
import com.bvd.android.financemanager.dao.ExpensesDao;
import com.bvd.android.financemanager.dao.IExpenseDao;
import com.bvd.android.financemanager.model.Expense;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AllExpensesActivity extends AppCompatActivity {
    private static final String TAG = AllExpensesActivity.class.getName();
    @BindView(R.id.allExpensesListView)
    public ListView listView;

    @BindView(R.id.addExpenseBtn)
    public FloatingActionButton addBtn;

    private List<Expense> expenses;
    private ExpensesDao expensesDao;
    private ExpenseAdapter expenseArrayAdapter;

    private AppDatabase database;
    private IExpenseDao iExpenseDao;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_CANCELED) {
            Log.v(TAG, "Back was pressed");
            return;
        }
        if (resultCode != Activity.RESULT_OK) {
            Log.v(TAG, "New expense is not ok!");
            return;

        }
        Expense expense = (Expense) data.getSerializableExtra("expense");
        Log.v(TAG, "We received the expense=" + expense);
        //expensesDao.save(expense);

        iExpenseDao.insertAll(expense);
        //Log.v(TAG, "On activity result" + expensesDao.getAll() + "Count from adapter=" + expenseArrayAdapter.getCount());
        Log.v(TAG, "From db=" + iExpenseDao.getAll());

        expenseArrayAdapter.setExpenses(iExpenseDao.getAll());
        expenseArrayAdapter.notifyDataSetChanged();


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_expenses);
        ButterKnife.bind(this);
        Log.v(TAG, "On create ALL EXPENSES");

        /*expensesDao = new ExpensesDao();
        expenses = expensesDao.getAll();*/
        database = AppDatabase.getAppDatabase(this);

        iExpenseDao = database.iExpenseDao();

        expenses = iExpenseDao.getAll();

        expenseArrayAdapter = new ExpenseAdapter(this, expenses);
        expenseArrayAdapter.notifyDataSetChanged();
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

    @OnClick(R.id.addExpenseBtn)
    void addNewExpense() {
        Intent addFormView = new Intent(this, AddExpenseActivity.class);

        int result = 0;
        startActivityForResult(addFormView, result);
    }


}
