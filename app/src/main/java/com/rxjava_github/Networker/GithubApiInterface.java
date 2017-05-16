package com.rxjava_github.Networker;

import com.rxjava_github.Model.RepoIssue;

import java.util.List;

import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by ankurkhandelwal on 16/05/17.
 */

public interface GithubApiInterface {
    @GET("/repos/{username}/{reponame}/issues")
    Observable<List<RepoIssue>> rxRequestIssueDetails(@Path("username") String username, @Path("reponame") String reponame);

}
