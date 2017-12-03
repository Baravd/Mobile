package com.bvd.android.financemanager.dao;

import com.bvd.android.financemanager.model.Expense;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.inject.Singleton;

/**
 * Created by bara on 07.11.2017.
 */

@Singleton
public class ExpensesDao {

    private List<Expense> expenses;

    public ExpensesDao() {
        expenses = new ArrayList<>();
    }

    public List<Expense> getAll() {

        expenses.add(new Expense(1, "Food", "Cheese", new BigDecimal("3.98"), new Date()));
        expenses.add(new Expense(2, "Food", "Cola", new BigDecimal("1.98"), new Date()));
        expenses.add(new Expense(3, "House", "Electricity", new BigDecimal("42.50"), new Date()));
        return expenses;
    }

    public void save(Expense expense) {
        long id = generateId();
        expense.setId(id);
        expenses.add(expense);
    }

    private long generateId() {
        long id = -1;
        for (Expense e : expenses) {
            if (e.getId() > id) {
                id = e.getId();
            }
        }
        return id;
    }
}
