package com.rxjava_github.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ankurkhandelwal on 16/05/17.
 */

public class RepoIssue {
    public int id;
    public int number;
    public String title;
    public String state;
    public String url;
    public String repository_url;
    public String labels_url;
    public int comments;
}
