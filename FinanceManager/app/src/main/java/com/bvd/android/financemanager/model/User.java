package com.bvd.android.financemanager.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by bara on 1/5/2018.
 */

public class User implements Serializable {
    List<Expense> expenses;
    boolean isPremium;
    String uid;
    String email;

    public User(List<Expense> expenses, boolean isPremium, String uid, String email) {
        this.expenses = expenses;
        this.isPremium = isPremium;
        this.uid = uid;
        this.email = email;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
    }

    public boolean isPremium() {
        return isPremium;
    }

    public void setPremium(boolean premium) {
        isPremium = premium;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "expenses=" + expenses +
                ", isPremium=" + isPremium +
                ", uid='" + uid + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
