package com.bvd.android.financemanager.adapters;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bvd.android.financemanager.R;
import com.bvd.android.financemanager.Utils;
import com.bvd.android.financemanager.dao.ExpensesDao;
import com.bvd.android.financemanager.model.Expense;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by bara on 07.11.2017.
 */

public class ExpenseAdapter extends ArrayAdapter<Expense> {
    private List<Expense> expenses;
    private Context context;
    private Utils utils;

    public ExpenseAdapter(Context context, List<Expense> expenses) {
        super(context, -1, expenses);
        this.expenses = expenses;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.row_layout, parent, false);

        TextView textView = rowView.findViewById(R.id.firstLine);
        Expense expense = getItem(position);
        textView.setText(expense.getName());

        TextView textView2 = rowView.findViewById(R.id.secondLine);
        utils= new Utils();
        textView2.setText(utils.getFormattedDate(expense.getPurchaseDate()));

        ImageView imageView = rowView.findViewById(R.id.icon);
        imageView.setImageResource(R.drawable.ic_launcher_background);

        return rowView;
    }
}
