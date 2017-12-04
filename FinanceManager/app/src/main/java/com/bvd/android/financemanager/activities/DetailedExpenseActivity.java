package com.bvd.android.financemanager.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bvd.android.financemanager.R;
import com.bvd.android.financemanager.Utils;
import com.bvd.android.financemanager.dao.AppDatabase;
import com.bvd.android.financemanager.dao.IExpenseDao;
import com.bvd.android.financemanager.model.Expense;

import java.text.ParseException;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by bara on 07.11.2017.
 */

public class DetailedExpenseActivity extends AppCompatActivity {

    @BindView(R.id.saveBtn)
    public Button saveBtn;

    @BindView(R.id.categoryEditText)
    EditText category;

    @BindView(R.id.nameEditText)
    EditText name;

    @BindView(R.id.purchaseDateEditText)
    EditText purchaseDate;

    @BindView(R.id.priceEditText)
    EditText price;

    @BindView(R.id.idTextView)
    TextView id;

    private Utils utils;
    private AppDatabase appDatabase;
    private IExpenseDao iExpenseDao;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_expense);

        ButterKnife.bind(this);

        appDatabase = AppDatabase.getAppDatabase(this);
        iExpenseDao = appDatabase.iExpenseDao();

        Intent intent = getIntent();
        Expense extras = (Expense) intent.getExtras().getSerializable("expense");

        utils = new Utils();

        category.setText(extras.getCategory());
        name.setText(extras.getName());
        price.setText(String.valueOf(extras.getPrice()));
        purchaseDate.setText(utils.getFormattedDate(extras.getPurchaseDate()));
        id.setText(String.valueOf(extras.getId()));

    }

    @OnClick(R.id.saveBtn)
    public void updateEntity() {
        int idValue = Integer.parseInt(id.getText().toString());
        float price = Float.valueOf(this.price.getText().toString());
        String name = this.name.getText().toString();
        String category = this.category.getText().toString();
        Utils utils = new Utils();
        Date date = null;
        try {
            date = utils.getDateFromString(this.purchaseDate.getText().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Expense expense = new Expense(idValue,category,name,price,date);

        iExpenseDao.updateExpenses(expense);
        finish();

    }
    public void deleteExpense() {
        int idValue = Integer.parseInt(id.getText().toString());
        float price = Float.valueOf(this.price.getText().toString());
        String name = this.name.getText().toString();
        String category = this.category.getText().toString();
        Utils utils = new Utils();
        Date date = null;
        try {
            date = utils.getDateFromString(this.purchaseDate.getText().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Expense expense = new Expense(idValue,category,name,price,date);

        iExpenseDao.delete(expense);
    }
    @OnClick(R.id.deleteExpBtn)
    public void addDialogAtDeleteAction() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Confirm");
        builder.setMessage("Are you sure?");

        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                deleteExpense();
                dialog.dismiss();
                finish();

            }
        });

        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }
}
