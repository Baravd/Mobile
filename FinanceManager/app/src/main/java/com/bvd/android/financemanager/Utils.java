package com.bvd.android.financemanager;

import android.util.Log;

import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by bara on 07.11.2017.
 */

public class Utils {
    private String TAG = Utils.class.getName();

    public String getFormattedDate(Date date) {
        Log.v(TAG, "Data Utils=" + date);
        if(date==null){
            return " ";
        }
        Format formatter = new SimpleDateFormat("yyyy-MM-dd");
        String sDate = formatter.format(date);
        return sDate;

    }

    public Date getDateFromString(String date) throws ParseException {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        Date date1 = formatter.parse(date);
        return date1;
    }
}
