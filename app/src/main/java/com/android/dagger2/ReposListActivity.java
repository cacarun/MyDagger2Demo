package com.android.dagger2;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.android.dagger2.component.AppComponent;
import com.android.dagger2.model.Repo;
import com.android.dagger2.service.GitHubApiService;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.Bind;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ReposListActivity extends BaseActivity {

    @Bind(R.id.repos_rv_list)
    RecyclerView mRvList;

    @Bind(R.id.pbLoading)
    ProgressBar pbLoading;


    @Inject
    GitHubApiService gitHubApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_repo_list;
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        // 注入
        appComponent.inject(this);
    }


    private void initView() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRvList.setLayoutManager(manager);

        ListAdapter adapter = new ListAdapter();
        mRvList.setAdapter(adapter);
        loadData(adapter);
    }

    private void loadData(final ListAdapter adapter) {
        showLoading(true);
        gitHubApiService.getRepoData(getUser())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SimpleObserver<ArrayList<Repo>>() {
            @Override
            public void onNext(ArrayList<Repo> repos) {
                showLoading(false);
                adapter.setRepos(repos);
            }

            @Override
            public void onError(Throwable e) {
                showLoading(false);
            }
        });
    }

    private String getUser() {
        return "bird1015";
    }

    public void showLoading(boolean loading) {
        Log.i("info", loading + " Repos");
        pbLoading.setVisibility(loading ? View.VISIBLE : View.GONE);
    }
}