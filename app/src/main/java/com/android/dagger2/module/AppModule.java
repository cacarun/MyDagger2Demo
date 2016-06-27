package com.android.dagger2.module;

import android.app.Application;

import dagger.Module;
import dagger.Provides;

@Module(includes = GitHubApiModule.class)
public class AppModule {
    private Application application;

    public AppModule(Application application){
        this.application=application;
    }

    @Provides
    public Application provideApplication(){
        return application;
    }
}