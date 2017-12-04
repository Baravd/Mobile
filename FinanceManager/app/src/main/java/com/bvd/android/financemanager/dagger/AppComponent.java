package com.bvd.android.financemanager.dagger;

import com.bvd.android.financemanager.activities.AllExpensesActivity;
import com.bvd.android.financemanager.app.FinanceManagerApp;
import com.bvd.android.financemanager.dao.ExpensesDao;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by bara on 02.12.2017.
 */

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    void inject(FinanceManagerApp target);
    void inject(AllExpensesActivity target);
    void inject(ExpensesDao target);
}
