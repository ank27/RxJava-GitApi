package com.rxjava_github;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.rxjava_github.Model.RepoIssue;
import com.rxjava_github.Networker.Network;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private List<RepoIssue> repoIssues;
    private ProgressBar progressBar;
    LinearLayout issueLayout;
    RecyclerView issue_container;
    RelativeLayout no_issue_layout,no_server_respone;
    IssuesAdapter adapter;
    Activity activity;
    Toolbar toolbarIssues;
    Network mNetwork;
    String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activity = this;
        progressBar= (ProgressBar) findViewById(R.id.progressBar);
        issueLayout= (LinearLayout) findViewById(R.id.issuelayout);
        no_issue_layout= (RelativeLayout) findViewById(R.id.no_issue_layout);
        toolbarIssues= (Toolbar) findViewById(R.id.toolbarMain);
        issue_container = (RecyclerView) findViewById(R.id.issue_container);

        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        issue_container.setLayoutManager(layoutManager);
        issue_container.setHasFixedSize(true);

        adapter = new IssuesAdapter(activity);
        issue_container.setAdapter(adapter);
        mNetwork= new Network();
        showAlertDialog();
    }

    void showAlertDialog(){
        LayoutInflater li = LayoutInflater.from(activity);
        final View promptsView = li.inflate(R.layout.dialog_repo, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(activity);
        alertDialogBuilder.setView(promptsView);
        final EditText userInput = (EditText) promptsView.findViewById(R.id.username);
        final EditText repoInput = (EditText) promptsView.findViewById(R.id.reponame);
        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                String username = userInput.getText().toString();
                                String reponame = repoInput.getText().toString();
                                if (username.length() == 0 || reponame.length() == 0)
                                    Toast.makeText(activity.getApplicationContext(), "Please fill both field", Toast.LENGTH_SHORT).show();
                                else {
//                                    Log.d(TAG, "fetching issues");
//                                    mNetwork.rxFetchRepoIsuue(username, reponame).subscribeOn(Schedulers.io())
//                                            .observeOn(AndroidSchedulers.mainThread()).subscribe(repoIssues -> {
//                                                progressBar.setVisibility(View.GONE);
//                                                if (repoIssues.size()>0) {
//                                                    issueLayout.setVisibility(View.VISIBLE);
//                                                    no_issue_layout.setVisibility(View.GONE);
//                                                    adapter.updateData(repoIssues);
//                                                }else {
//                                                    Toast.makeText(activity.getApplicationContext(),"No issues in repo",Toast.LENGTH_SHORT).show();
//                                                    no_issue_layout.setVisibility(View.VISIBLE);
//                                                    issueLayout.setVisibility(View.GONE);
//                                                }
//                                            },
//
//                                            e ->
//                                                    progressBar.setVisibility(View.GONE),
//                                            ()->
//                                                    progressBar.setVisibility(View.GONE));

                                    Log.d(TAG, "fetching issues");
                                    mNetwork.rxFetchRepoIsuue(username, reponame).subscribeOn(Schedulers.io())
                                            .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<List<RepoIssue>>() {
                                        @Override
                                        public void onCompleted() {
                                            progressBar.setVisibility(View.GONE);
                                        }

                                        @Override
                                        public void onError(Throwable e) {
                                            progressBar.setVisibility(View.GONE);
                                        }

                                        @Override
                                        public void onNext(List<RepoIssue> repoIssues) {
                                            if (repoIssues.size() > 0) {
                                                issueLayout.setVisibility(View.VISIBLE);
                                                no_issue_layout.setVisibility(View.GONE);
                                                adapter.updateData(repoIssues);
                                            } else {
                                                Toast.makeText(activity.getApplicationContext(), "No issues in repo", Toast.LENGTH_SHORT).show();
                                                no_issue_layout.setVisibility(View.VISIBLE);
                                                issueLayout.setVisibility(View.GONE);
                                            }
                                        }
                                    });
                                }
                            }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_search) {
            showAlertDialog();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
