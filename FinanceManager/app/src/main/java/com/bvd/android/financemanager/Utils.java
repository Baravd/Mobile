package com.bvd.android.financemanager;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by bara on 07.11.2017.
 */

public class Utils {
    public String getFormattedDate(Date date) {
        Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sDate = formatter.format(date);
        return sDate;

    }
}
