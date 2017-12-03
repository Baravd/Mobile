package com.bvd.android.financemanager.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

import com.bvd.android.financemanager.activities.AddExpenseActivity;
import com.bvd.android.financemanager.model.Categories;

import java.util.List;

/**
 * Created by bara on 03.12.2017.
 */

public class CategoriesSpinnerAdapter extends ArrayAdapter<Categories> {
    List<Categories> categories;

    public CategoriesSpinnerAdapter( Context context,int i, List<Categories> categories) {
        super(context, i, categories);
        this.categories = categories;
    }


}
