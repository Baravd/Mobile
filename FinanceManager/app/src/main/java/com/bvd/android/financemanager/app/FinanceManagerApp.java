package com.bvd.android.financemanager.app;

import android.app.Application;

import com.bvd.android.financemanager.dagger.AppComponent;
import com.bvd.android.financemanager.dagger.AppModule;
import com.bvd.android.financemanager.dagger.DaggerAppComponent;
import com.bvd.android.financemanager.dao.ExpensesDao;


import javax.inject.Inject;

/**
 * Created by bara on 02.12.2017.
 */

public class FinanceManagerApp extends Application {
    private static final String TAG = FinanceManagerApp.class.getName();

    public AppComponent injector;



    @Override
    public void onCreate() {
        super.onCreate();
        injector = initDagger(this);
    }

    public AppComponent getInjector() {
        return injector;
    }

    protected AppComponent initDagger(FinanceManagerApp  application) {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(application))
                .build();
    }
}
