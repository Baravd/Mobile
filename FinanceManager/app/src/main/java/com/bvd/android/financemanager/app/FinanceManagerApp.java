package com.bvd.android.financemanager.app;

import android.app.Application;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.FirebaseDatabase;


/**
 * Created by bara on 12/27/2017.
 */

public class FinanceManagerApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
       /* if(!FirebaseApp.getApps(this).isEmpty()) {
        }*/
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

    }
}
