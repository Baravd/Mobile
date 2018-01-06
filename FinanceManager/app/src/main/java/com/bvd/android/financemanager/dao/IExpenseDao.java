package com.bvd.android.financemanager.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.bvd.android.financemanager.model.Expense;

import java.util.List;

/**
 * Created by bara on 03.12.2017.
 */

@Dao
public interface IExpenseDao {
    @Query("SELECT * FROM expense ")
    List<Expense> getAll();

    @Insert
    void insertAll(Expense... expenses);

    @Update
    void updateExpenses(Expense... expenses);

    @Delete
    void delete(Expense expense);

    @Query("SELECT * FROM Expense WHERE category=:category ")
    List<Expense> getByCategory(String category);

    @Query("DELETE  FROM Expense")
    void  deleteAll();


}
