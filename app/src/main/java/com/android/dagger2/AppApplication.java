package com.android.dagger2;

import android.app.Application;

import com.android.dagger2.component.AppComponent;
import com.android.dagger2.component.DaggerAppComponent;
import com.android.dagger2.module.AppModule;

public class AppApplication extends Application {

    private static AppApplication sInstance;

    public static AppApplication getsInstance() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        this.sInstance = this;
        initDagger();
    }

    private void initDagger() {

        // 可以一个个创建
        //appComponent = DaggerAppComponent.builder().githubApiModule(new GithubApiModule()).appModule(new AppModule(this)).build();

        // 也可以统一放在 AppModule里面而在这里加载一次
        AppComponent.Instance.init(DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .build());
    }

}
