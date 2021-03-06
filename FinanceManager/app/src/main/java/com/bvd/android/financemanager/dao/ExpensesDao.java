package com.bvd.android.financemanager.dao;

import com.bvd.android.financemanager.model.Expense;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by bara on 07.11.2017.
 */

public class ExpensesDao {

    public List<Expense> getAll() {
        List<Expense> expenses = new ArrayList<>();
        expenses.add(new Expense(1, "Food", "Cheese", new BigDecimal("3.98"),new Date()));
        expenses.add(new Expense(2, "Food", "Cola", new BigDecimal("1.98"), new Date()));
        expenses.add(new Expense(3, "House Keeping", "Electricity", new BigDecimal("42.50"), new Date()));
        return expenses;
    }
}
