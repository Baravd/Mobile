package com.bvd.android.financemanager.dagger;

import android.content.Context;
import android.util.Log;

import com.bvd.android.financemanager.app.FinanceManagerApp;
import com.bvd.android.financemanager.dao.ExpensesDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by bara on 02.12.2017.
 */

@Module
public class AppModule {
    private static final String TAG = AppModule.class.getSimpleName();
    private FinanceManagerApp app;

    public AppModule(FinanceManagerApp app) {
        this.app = app;
        Log.v(TAG, "appModule");

    }
    @Provides
    @Singleton
    public Context provideContext() {
        return  app;
    }

    @Provides
    @Singleton
    public ExpensesDao provideExpenseDao(){
        return new ExpensesDao(app);
    }


}
