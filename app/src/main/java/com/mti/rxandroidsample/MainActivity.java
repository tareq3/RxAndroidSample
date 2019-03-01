/*
 * Created by Tareq Islam on 3/1/19 4:12 PM
 *
 *  Last modified 3/1/19 4:05 PM
 */

package com.mti.rxandroidsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.mti.rxandroidsample.adapter.ListItemAdapter;
import com.mti.rxandroidsample.model.GitHubRepo;
import com.mti.rxandroidsample.repository.GitHubClient;
import com.mti.rxandroidsample.repository.GitHubService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
     // private Subscription subscription;
    private Disposable disposable;
    private List<GitHubRepo> gitHubRepos = new ArrayList<>();



    RecyclerView recyclerView;
    private ListItemAdapter adapter ;


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView =  findViewById(R.id.list_view_repos);
        recyclerView.setHasFixedSize(true);
        final RecyclerView.LayoutManager mLayoutManagerLinear = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(mLayoutManagerLinear);
        adapter=new ListItemAdapter(this,gitHubRepos,null);

        recyclerView.setAdapter(adapter);

        final EditText editTextUsername = findViewById(R.id.edit_text_username);
        final Button buttonSearch =  findViewById(R.id.button_search);
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = editTextUsername.getText().toString();
                if (!TextUtils.isEmpty(username)) {
                    getStarredRepos(username);
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }

        super.onDestroy();
    }


    private void getStarredRepos(String username) {

        disposable = GitHubClient.getClient().create(GitHubService.class)
                .getStarredRepositories(username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Consumer<List<GitHubRepo>>() {
                            @Override
                            public void accept(List<GitHubRepo> gitHubRepos) throws Exception {
                                Log.i(TAG, "RxJava2: Response from server ...");
                                adapter.updateAdapter(gitHubRepos);
                                adapter.notifyDataSetChanged();

                            }
                        },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable t) throws Exception {
                                Log.i(TAG, "RxJava2, HTTP Error: " + t.getMessage());
                            }
                        }
                );


    }

}
