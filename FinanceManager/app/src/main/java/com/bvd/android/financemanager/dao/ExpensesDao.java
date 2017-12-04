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
        expenses.add(new Expense(1, "Food", "Cheese", 3.98f, new Date()));
        expenses.add(new Expense(2, "Food", "Cola",1.98f, new Date()));
        expenses.add(new Expense(3, "House", "Electricity", 42.50f, new Date()));
        expenses.add(new Expense(4, "House", "Electricity",  42.50f, new Date()));
        expenses.add(new Expense(5, "House", "Electricity", 42.50f, new Date()));
        expenses.add(new Expense(6, "Auto", "Electricity", 42.50f, new Date()));
        expenses.add(new Expense(7, "Bills", "Electricity", 42.50f, new Date()));
    }

    public List<Expense> getAll() {
        return expenses;
    }

    public void save(Expense expense) {
        long id = generateId();
        expense.setId(id);
        expenses.add(expense);
    }
    public void remove(Expense expense) {
        expenses.remove(expense);
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
    public List<Expense> getForCategory(String category) {
        List<Expense>  expensesForCategory = new ArrayList<>();
        for(Expense expense: expenses){
            if(expense.getCategory().equals(category)){
                expensesForCategory.add(expense);
            }
        }

        return expensesForCategory;
    }
}
