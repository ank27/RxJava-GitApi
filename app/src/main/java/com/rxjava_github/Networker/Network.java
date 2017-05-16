package com.rxjava_github.Networker;

import com.rxjava_github.Model.RepoIssue;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ankurkhandelwal on 16/05/17.
 */

public class Network {
    private GithubService mService;

    public Network() {
        mService = GithubService.getInstance();
    }

    public Observable<List<RepoIssue>> rxFetchRepoIsuue(String username, String reponame) {
        return mService.rxRequestIssueDetails(username, reponame).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread());
    }

}
