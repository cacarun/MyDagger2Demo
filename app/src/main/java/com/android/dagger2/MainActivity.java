package com.android.dagger2;

import android.content.Intent;
import android.os.Bundle;

import com.android.dagger2.component.AppComponent;

import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @OnClick(R.id.id_main_button)
    public void onShowRepositoriesClick() {
        startActivity(new Intent(this, ReposListActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public int getLayoutId(){
        return R.layout.activity_main;
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent){

    }
}
