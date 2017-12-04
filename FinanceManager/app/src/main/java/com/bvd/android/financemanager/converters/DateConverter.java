package com.bvd.android.financemanager.converters;

import android.arch.persistence.room.TypeConverter;


import com.bvd.android.financemanager.Utils;

import java.lang.invoke.ConstantCallSite;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by bara on 03.12.2017.
 */

public class DateConverter {
    @TypeConverter
    public Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public Long dateToTimestamp(Date date) {
        if (date == null) {
            return null;
        } else {
            return date.getTime();
        }
    }
}
