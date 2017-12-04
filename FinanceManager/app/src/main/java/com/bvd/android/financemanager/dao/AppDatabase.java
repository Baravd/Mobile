package com.bvd.android.financemanager.dao;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.bvd.android.financemanager.model.Expense;

/**
 * Created by bara on 03.12.2017.
 */


@Database(entities = {Expense.class},version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract IExpenseDao iExpenseDao();
}
