package com.bvd.android.financemanager.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.bvd.android.financemanager.converters.DateConverter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by bara on 06.11.2017.
 */

@Entity
public class Expense implements Serializable, Comparable<Expense > {
    @PrimaryKey
    private long id;
    @ColumnInfo(name = "category")
    private String category;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name="price")
    private float price;
    @ColumnInfo(name="purchase_date")
    @TypeConverters({DateConverter.class})
    private Date purchaseDate;




    public Expense(long id, String category, String name, float price, Date purchaseDate) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.price = price;
        this.purchaseDate = purchaseDate;
    }

    public Expense(String category, String name, float price, Date purchaseDate) {
        this.category = category;
        this.name = name;
        this.price = price;
        this.purchaseDate = purchaseDate;
    }

    public Expense() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "id=" + id +
                ", category='" + category + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", purchaseDate=" + purchaseDate +
                '}';
    }

    @Override
    public int compareTo(@NonNull Expense expense) {
        if(expense.getId()==id){
            return 0;
        }
        if(expense.getId()<id)
            return -1;
        return 1;
    }
}
