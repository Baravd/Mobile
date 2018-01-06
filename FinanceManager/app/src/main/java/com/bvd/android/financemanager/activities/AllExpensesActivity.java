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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

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
    private ExpenseAdapter expenseArrayAdapter;

    private AppDatabase database;
    private IExpenseDao iExpenseDao;
    private DatabaseReference expensesReference;

    private FirebaseAuth auth;
    private FirebaseUser currentUser;
    private FirebaseDatabase firebaseDatabase;


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
        Log.v(TAG, "From db=" + iExpenseDao.getAll());

        expenseArrayAdapter.add(expense);
        //expensesReference = firebaseDatabase.getReference("https://financemanager-63f17.firebaseio.com/").child("expenses");
        iExpenseDao.insertAll(expense);
        expensesReference = firebaseDatabase.getReference("users").child(currentUser.getUid()).child("expenses");
        expensesReference.setValue(iExpenseDao.getAll());
        //setDataOnFireBase(iExpenseDao.getAll());

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_expenses);
        ButterKnife.bind(this);
        Log.v(TAG, "On create ALL EXPENSES");

        auth = FirebaseAuth.getInstance();
        currentUser = auth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();
        expenses = new ArrayList<>();


        database = AppDatabase.getAppDatabase(this);

        iExpenseDao = database.iExpenseDao();

        //setDataOnFireBase(expenses);
        //expenses = iExpenseDao.getAll();
        expenses = new ArrayList<>();
        expenseArrayAdapter = new ExpenseAdapter(AllExpensesActivity.this, expenses);
        listView.setAdapter(expenseArrayAdapter);


        expensesReference = firebaseDatabase.getReference("users").child(currentUser.getUid()).child("expenses");
        //expensesReference.setValue(iExpenseDao.getAll());
        expensesReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                //expenses.clear();
                ArrayList<Expense> expenseMap = new ArrayList<>();
                for (DataSnapshot eachExpense : dataSnapshot.getChildren()) {
                    Expense expense = eachExpense.getValue(Expense.class);
                    expenseMap.add(expense);
                }
                Log.v(TAG, "Value is: " + expenseMap);

                /*for(Integer key:expenseMap.keySet()){
                    expenses.add
                }*/

                //expenses.addAll(expenseMap);
                Log.d(TAG, "ListView: " + listView);
                if (expenseMap != null) {
                    expenseArrayAdapter.setExpenses(expenseMap);

                    expenseArrayAdapter.notifyDataSetChanged();

                }


                Log.d(TAG, "User id: " + currentUser.getUid() + "|" + currentUser.getEmail());
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

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

    @Override
    protected void onResume() {
        super.onResume();
        if (expenseArrayAdapter != null) {
            expenseArrayAdapter.notifyDataSetChanged();
        }
    }

    @OnClick(R.id.addExpenseBtn)
    void addNewExpense() {
        Intent addFormView = new Intent(this, AddExpenseActivity.class);

        int result = 0;
        startActivityForResult(addFormView, result);
    }

    private void setDataOnFireBase(final List<Expense> expenses) {
        expensesReference = firebaseDatabase.getReference("users").child(currentUser.getUid()).child("expenses");
        expensesReference.setValue(iExpenseDao.getAll());
        expensesReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                //expenses.clear();
                ArrayList<Expense> expenseMap = (ArrayList<Expense>) dataSnapshot.getValue();
                /*for(Integer key:expenseMap.keySet()){
                    expenses.add
                }*/

                //expenses.addAll(expenseMap);
                expenseArrayAdapter = new ExpenseAdapter(AllExpensesActivity.this, expenseMap);
                expenseArrayAdapter.notifyDataSetChanged();
                listView.setAdapter(expenseArrayAdapter);

                Log.d(TAG, "Value is: " + expenseMap);
                Log.d(TAG, "User id: " + currentUser.getUid() + "|" + currentUser.getEmail());
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }


}
