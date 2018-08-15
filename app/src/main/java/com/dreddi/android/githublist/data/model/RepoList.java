package com.dreddi.android.githublist.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RepoList {

    @SerializedName("total_count")
    private long totalCount;

    @SerializedName("incomplete_results")
    private boolean incompleteResults;

    @SerializedName("items")
    private List<Repo> repoList;

    public long getTotalCount() {
        return totalCount;
    }

    public boolean isIncompleteResults() {
        return incompleteResults;
    }

    public List<Repo> getRepoList() {
        return repoList;
    }
}
