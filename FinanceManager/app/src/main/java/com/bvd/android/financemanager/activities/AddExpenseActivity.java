package com.bvd.android.financemanager.activities;

import android.app.Activity;
import android.arch.persistence.room.Dao;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.bvd.android.financemanager.R;
import com.bvd.android.financemanager.Utils;
import com.bvd.android.financemanager.adapters.CategoriesSpinnerAdapter;
import com.bvd.android.financemanager.adapters.ExpenseAdapter;
import com.bvd.android.financemanager.dao.ExpensesDao;
import com.bvd.android.financemanager.dao.IExpenseDao;
import com.bvd.android.financemanager.model.Categories;
import com.bvd.android.financemanager.model.Expense;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddExpenseActivity extends AppCompatActivity {
    public static final String TAG = AddExpenseActivity.class.getName();
    public static final int CATEGORY_SPINNER = R.id.category_spinner;
    @BindView(R.id.category_spinner)
    public Spinner categorySpinner;
    @BindView(R.id.expNameTxt)
    public EditText expenseName;
    @BindView(R.id.priceExpTxt)
    public EditText expensePrice;
    @BindView(R.id.selectDateBtn)
    public Button selectDateBtn;
    @BindView(R.id.saveNewExpBtn)
    public Button saveNewExpBtn;
    @BindView(R.id.deleteExpBtn)
    public Button deleteExpBtn;


    private ExpensesDao expensesDao;

    private int status;

    IExpenseDao iExpenseDao;

    private Date selectedDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v(TAG, "Spinner=" + categorySpinner);
        setContentView(R.layout.activity_add_expense);
        ButterKnife.bind(this);

        expensesDao = new ExpensesDao();

        //categorySpinner = findViewById(CATEGORY_SPINNER);
        List<Categories> categories = Arrays.asList(Categories.values());
        ArrayAdapter<Categories> categoriesSpinnerAdapter = new ArrayAdapter<Categories>(this, R.layout.support_simple_spinner_dropdown_item, categories);
        categoriesSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1);

        categorySpinner.setAdapter(categoriesSpinnerAdapter);
        Log.v(TAG, "Spinner=" + categorySpinner);


    }

    @OnClick(R.id.selectDateBtn)
    public void selectDate() {
        Intent intent = new Intent(this, DatePickerActivity.class);
        startActivityForResult(intent, status);
    }

    @OnClick(R.id.saveNewExpBtn)
    public void submit() {
        String priceString = "0";
        if (!expensePrice.getText().equals(null) && expensePrice.getText().length() > 0) {
            priceString = String.valueOf(expensePrice.getText());
        }
        float price = Float.valueOf(priceString);
        String name = String.valueOf(expenseName.getText());
        String category = categorySpinner.getSelectedItem().toString();
        Expense expense = new Expense(category, name, price, selectedDate);
        expensesDao.save(expense);

        Intent resultIntent = new Intent();
        resultIntent.putExtra("expense", expense);
        setResult(Activity.RESULT_OK, resultIntent);
        Log.v(TAG, "Saving expense from add view=" + expense);

        finish();

    }


    //daca aveam de exemplu 2 pickere? cum se poate face mai clean asta..care e ordinea methodelor @Onclick vs @Override
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK) {
            Log.v(TAG, "Something went wronng");
        } else {
            String resultedDate = data.getStringExtra("selectedDate");

            Utils utils = new Utils();
            try {
                this.selectedDate = utils.getDateFromString(resultedDate);
                selectDateBtn.setText(utils.getFormattedDate(selectedDate));
            } catch (ParseException e) {
                Log.v(TAG, "Date format is incorrect");
            }
            Log.v(TAG, "Selected date=" + resultedDate);
        }
    }
}
