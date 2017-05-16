package com.rxjava_github.Networker;

import com.rxjava_github.Model.RepoIssue;

import java.util.List;

import retrofit.RestAdapter;
import retrofit.http.Path;
import rx.Observable;

/**
 * Created by ankurkhandelwal on 16/05/17.
 */

public class GithubService {

    public static String URL = "https://api.github.com";

    private GithubApiInterface mGithubAPI;

    public GithubService(GithubApiInterface githubAPI) {
        mGithubAPI = githubAPI;
    }

    public static GithubService getInstance() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(URL)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
        return new GithubService(restAdapter.create(GithubApiInterface.class));
    }


    public Observable<List<RepoIssue>> rxRequestIssueDetails(@Path("username") String username, @Path("reponame") String reponame) {
        return mGithubAPI.rxRequestIssueDetails(username,reponame);
    }
}
