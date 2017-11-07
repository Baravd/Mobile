package com.bvd.android.financemanager.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by bara on 06.11.2017.
 */

public class Expense implements Serializable {
    private long id;
    private String category;
    private String name;
    private BigDecimal price;
    private Date purchaseDate;

    public Expense(long id, String category, String name, BigDecimal price, Date purchaseDate) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.price = price;
        this.purchaseDate = purchaseDate;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
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
}
