package com.rxjava_github;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.rxjava_github.Model.RepoIssue;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ankurkhandelwal on 16/05/17.
 */

public class IssuesAdapter extends RecyclerView.Adapter<IssuesAdapter.ViewHolder>  {
    List<RepoIssue> issueList;
    Activity activity;

    public void updateData(List<RepoIssue> repos) {
        issueList = repos;
        notifyDataSetChanged();
    }

    public IssuesAdapter(Activity context) {
        this.activity= context;
        issueList = new ArrayList<>(0);
    }

    @Override
    public IssuesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(activity.getApplicationContext()).inflate(R.layout.single_issue_view, parent, false);
        return new ViewHolder(itemLayoutView);
    }

    @Override
    public void onBindViewHolder(final IssuesAdapter.ViewHolder holder, final int position) {
        RepoIssue issue = issueList.get(position);
        holder.title.setText(issue.title);
        holder.number.setText(issue.number);
        holder.comment_number.setText(issue.comments);
    }

    @Override
    public int getItemCount() {
        return issueList.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView title;
        public TextView comment_number;
        public TextView number;
        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            title= (TextView) itemLayoutView.findViewById(R.id.title);
            comment_number = (TextView) itemLayoutView.findViewById(R.id.comment_number);
            number=(TextView) itemLayoutView.findViewById(R.id.number);
        }
    }
}
