package com.android.dagger2.component;

import android.support.annotation.NonNull;

import com.android.dagger2.ReposListActivity;
import com.android.dagger2.module.AppModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = { AppModule.class})
public interface AppComponent {

    // 这样这里会不会很多，或者所有需要创建的对象都放到 Base 类里面好像也不好
    void inject(ReposListActivity activity);

    class Instance {
        private static AppComponent sComponent;

        public static void init(@NonNull AppComponent component) {
            sComponent = component;
        }

        public static AppComponent get() {
            return sComponent;
        }
    }
}